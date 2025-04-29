import os
import torch
import yaml

from src.client.client_factory import ClientFactory
from src.generator.report_generator import ReportGenerator
from src.utils.java_class_utils import *
from src.mvn_project.maven_project import MavenProject
from src.generator.prompt_generator import PromptGenerator
from src.utils.model_response_utils import *
from src.utils.mvn_error_utils import parse_maven_log

CONFIG_FILE = "config.yml"
MODELS_CONFIG_FILE = "./src/available_models.yml"
PROMPT_TEMPLATE_FILE = "./src/prompts.yml"
POM = "./src/mvn_project/pom.xml"


def load_yaml(file_path):
    with open(file_path, 'r') as f:
        return yaml.safe_load(f)


def save_file(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w') as f:
        f.write(content)


def generate_test(client, mvn_project, prompt_generator, test_class_name,
                  generation_target_name, max_retries, report_generator, base_dir,
                  model_in_use, prompt_in_use):

    # Prompt LLM
    original_code = client.send_prompt(prompt_generator.prompt)
    save_file(f"{base_dir}/Model_Response/{test_class_name}.java", original_code)

    # Apply heuristics
    print("Applicazione euristiche...")
    fixed_code = apply_heuristics(model_in_use, prompt_in_use, original_code, prompt_generator.java_class_prompt, prompt_generator.junit_test_prompt)
    if fixed_code is None:
        print("Le euristiche hanno restituito un errore")
        report_generator.add_entry_error(generation_target_name)
        return

    save_file(f"{base_dir}/After_Heuristics/{test_class_name}.java", fixed_code)

    # Compile and test
    removed_methods = 0
    result = mvn_project.verify_generated_test(fixed_code, test_class_name)
    executable_after_heuristics = result[0]

    executable_after_cleanup = False
    for i in range(max_retries):
        if result[0]:
            executable_after_cleanup = True
            break

        error_types = parse_maven_log(result[1])
        to_remove = []

        if error_types["test_failures"] or error_types["test_errors"]:
            to_remove += extract_failed_method_names(result[1], test_class_name)

        if error_types["compilation_errors"]:
            lines = extract_failed_line_numbers(result[1], f"{test_class_name}.java")
            to_remove += find_method_names_by_line_number(fixed_code, lines)

        print(f"Iteratione {i + 1}/{max_retries} - Metodi da rimuovere: {to_remove}")

        if not to_remove:
            break

        for method in to_remove:
            fixed_code = remove_method_from_class(fixed_code, method)
            removed_methods += 1

        result = mvn_project.verify_generated_test(fixed_code, test_class_name)

    save_file(f"{base_dir}/After_Maven_Cleanup/{test_class_name}.java", fixed_code)
    if executable_after_cleanup:
        save_file(f"{base_dir}/Executable/{test_class_name}.java", fixed_code)

    report_generator.add_entry(generation_target_name, executable_after_heuristics,
                               executable_after_cleanup if not executable_after_heuristics else None, removed_methods)


def main():
    torch.cuda.empty_cache()

    config = load_yaml(CONFIG_FILE)
    models = load_yaml(MODELS_CONFIG_FILE)
    all_prompts = load_yaml(PROMPT_TEMPLATE_FILE)

    client = ClientFactory.get_client(config["model_in_use"], models[config["model_in_use"]])
    prompt_type = config["prompt_in_use"]
    prompt_templates = (all_prompts[prompt_type]["class_template"], all_prompts[prompt_type]["test_template"])
    prompt_scope = all_prompts[prompt_type]["generation_scope"]

    report = ReportGenerator(config["model_in_use"], prompt_type, prompt_scope)

    continue_previous_generation = config["continue_previous_generation"]
    for target in config["java_classes"]:
        java_class_path = target["java_class_path"]
        java_class_name = target["java_class_name"]
        report.add_java_class(java_class_name)

        print(f"Elaborazione classe: {java_class_name}")
        class_code = load_java_class(java_class_path)
        public_methods = extract_public_method_signatures(class_code)

        #for suffix in range(config["number_iteration_for_class"]):
        for suffix in range(0, 1):
            report.add_iteration(suffix)
            print(f"Iteration {suffix+1}/{config['number_iteration_for_class']}")

            mvn_project = MavenProject(f"./Results/Projects/{config['model_in_use']}/{prompt_type}/{java_class_name}Test/{suffix}",
                                       java_class_path, POM, continue_previous_generation)
            base_dir = f"./Results/GeneratedTests/{config['model_in_use']}/{prompt_type}/{java_class_name}/{suffix}"

            print("Progetto maven creato con successo.")

            if prompt_scope == "method":
                for method_signature in public_methods:
                    method_name = extract_method_name(method_signature)
                    test_class_name = f"{java_class_name}_{method_name}Test"

                    previous_generated_tests = []
                    if continue_previous_generation and os.path.isdir(base_dir) and len(os.listdir(base_dir)) > 0:
                        print("continue_previous_generation: ", continue_previous_generation)
                        previous_generated_tests = os.listdir(f"{base_dir}/Model_Response")
                        print(previous_generated_tests)
                        print(f"'{test_class_name}.java' in previous_generated_tests: ", f"{test_class_name}.java" in previous_generated_tests)

                    if f"{test_class_name}.java" in previous_generated_tests:
                        continue

                    prompt_gen = PromptGenerator(prompt_templates, java_class_path, java_class_name,
                                                 config["number_tests_for_method"], method_signature,
                                                 test_class_name)

                    print(f"Interrogazione modello per metodo: {method_signature}")
                    generate_test(client, mvn_project, prompt_gen, test_class_name,
                                  method_name, config["mvn_max_retries"], report, base_dir,
                                  config["model_in_use"], config["prompt_in_use"])

                    report.write_iteration_report()
                    report.clear_data()

            elif prompt_scope == "class":
                test_class_name = f"{java_class_name}Test"
                prompt_gen = PromptGenerator(prompt_templates, java_class_path, java_class_name,
                                             config["number_tests_for_method"], "",
                                             test_class_name)

                print(f"Interrogazione modello per intera classe")
                generate_test(client, mvn_project, prompt_gen, test_class_name,
                              "", config["mvn_max_retries"], report, base_dir,
                              config["model_in_use"], config["prompt_in_use"])

                report.write_iteration_report()
                report.clear_data()

            else:
                raise ValueError("Scope del prompt sconosciuto")

            report.add_coverage(f"{mvn_project.project_dir}/target/site/jacoco/jacoco.xml")
            report.write_coverage_summary()
            report.clear_data()


if __name__ == "__main__":
    main()
