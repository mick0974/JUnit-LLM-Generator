import os
import shutil
import subprocess


class MavenProject:
    def __init__(self, project_path, java_class_path, pom_path):
        self.error = None

        if os.path.exists(project_path):
            shutil.rmtree(project_path)
        os.makedirs(project_path)

        self.project_dir = project_path
        self.src_main_dir = os.path.join(self.project_dir, "src/main/java")
        self.src_test_dir = os.path.join(self.project_dir, "src/test/java")

        os.makedirs(self.src_main_dir)
        os.makedirs(self.src_test_dir)

        if os.path.exists(pom_path):
            shutil.copy(pom_path, os.path.join(self.project_dir, "pom.xml"))
        else:
            print(f"Errore: {pom_path} non trovato")
            self.error = True

        if os.path.exists(java_class_path):
            shutil.copy(java_class_path, os.path.join(self.src_main_dir, os.path.basename(java_class_path)))
        else:
            print(f"Errore: {java_class_path} non trovato.")
            self.error = True

    def add_generated_test(self, test_class_code, test_class_name):
        test_class_path = os.path.join(self.src_test_dir, test_class_name + ".java")
        print("Current test class path: ", test_class_path)
        try:
            with open(test_class_path, 'w', encoding='utf-8') as file:
                file.write(test_class_code)
        except (IOError, OSError) as e:
            raise RuntimeError(f"Errore durante il salvataggio della classe di test in {test_class_path}: {e}")

        return test_class_path

    def run_maven_tests(self):
        print("Eseguendo mvn clean test...")

        # "--batch-mode" removes color from the prints
        process = subprocess.Popen(
            ["mvn", "--batch-mode", "clean", "compile", "test"],
            cwd=self.project_dir,
            stdout=subprocess.PIPE,
            stderr=subprocess.STDOUT,
            text=True
        )

        maven_log = ""
        for line in process.stdout:
            print(line, end='')
            maven_log += str(line)

        process.wait()

        if process.returncode != 0:
            return False, maven_log
        else:
            print("Test eseguiti con successo!")
            return True, None

    def verify_generated_test(self, test_class_code, test_class_name):
        current_test_class_path = self.add_generated_test(test_class_code, test_class_name)
        result = self.run_maven_tests()
        if result[0] is False:
            os.remove(current_test_class_path)
            return result

        return result
