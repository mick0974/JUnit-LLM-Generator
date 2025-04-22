import re

from langchain.prompts import PromptTemplate


def _extract_imports_from_java_class(java_code):
    class_match = re.search(r'\b(class|interface|enum)\b\s+\w+', java_code)
    if not class_match:
        return ""

    before_class = java_code[:class_match.start()]
    import_pattern = re.compile(r'^\s*import\s+[^\n;]+;', re.MULTILINE)
    imports = import_pattern.findall(before_class)

    return '\n'.join(imp.strip() for imp in imports)


def _load_java_class(file_path):
    with open(file_path, 'r') as file:
        return file.read()


class PromptGenerator:
    def __init__(self,
                 prompt_template: tuple[str, str],
                 java_class_path: str,
                 java_class_name: str,
                 number_tests: int,
                 method_signature: str,
                 test_class_name: str):

        if prompt_template is None:
            return

        if len(prompt_template) != 2:
            return

        java_class_template = PromptTemplate(
            template=prompt_template[0],
            input_variables=["java_class_code", "java_class_name"],
        )

        junit_class_template = PromptTemplate(
            template=prompt_template[1],
            input_variables=["java_class_imports", "method_signature", "java_class_name", "test_class_name",
                             "number_tests"],
        )

        java_class_code = _load_java_class(java_class_path)
        java_class_imports = _extract_imports_from_java_class(java_class_code)

        self.java_class_prompt = java_class_template.format_prompt(
            java_class_code=java_class_code,
            java_class_name=java_class_name
        ).text

        self.junit_test_prompt = junit_class_template.format_prompt(
            java_class_imports=java_class_imports,
            method_signature=method_signature,
            java_class_name=java_class_name,
            test_class_name=test_class_name,
            number_tests=number_tests
        ).text

        self.prompt = self.java_class_prompt + self.junit_test_prompt
