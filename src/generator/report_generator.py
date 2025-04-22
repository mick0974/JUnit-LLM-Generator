import json
import os
import xml.etree.ElementTree as ET
from collections import defaultdict


def _get_coverage_percentages(xml_path):
    tree = ET.parse(xml_path)
    root = tree.getroot()

    percentages = {}
    for counter in root.findall(".//counter"):
        counter_type = counter.attrib["type"]
        missed = int(counter.attrib["missed"])
        covered = int(counter.attrib["covered"])
        total = missed + covered
        percentage = (100.0 * covered / total) if total > 0 else 0.0

        percentages[counter_type] = {
            "percentage": round(percentage, 2),
            "covered": covered,
            "missed": missed,
            "total": total
        }

    return percentages


class ReportGenerator:
    def __init__(self, model_name, prompt_in_use, generation_scope):
        self.iteration = None
        self.java_class = None
        self.coverage = None

        self.model_name = model_name
        self.prompt_in_use = prompt_in_use
        self.generation_scope = generation_scope

        # reports[java_class][iteration] = list of entries
        self.reports = defaultdict(lambda: defaultdict(list))

    def add_java_class(self, java_class):
        self.java_class = java_class

    def add_iteration(self, iteration):
        self.iteration = iteration

    def add_entry(self, generation_target, exec_after_heuristics, exec_after_cleanup, removed_methods):
        self.reports[self.java_class][self.iteration].append({
            "generation_target": generation_target,
            "executable_after_heuristics": exec_after_heuristics,
            "executable_after_cleanup": exec_after_cleanup,
            "removed_methods_during_cleanup": removed_methods,
        })

    def add_entry_error(self, generation_target):
        self.reports[self.java_class][self.iteration].append({
            "generation_target": generation_target,
            "Error": "Heuristics couldn't fix the model response"
        })

    def add_coverage(self, xml_path):
        if not os.path.exists(xml_path):
            print(f"File di coverage non trovato: {xml_path}")
            self.coverage = {"error": "Coverage file not found"}
        else:
            try:
                self.coverage = _get_coverage_percentages(xml_path)
            except Exception as e:
                print(f"Errore nel parsing del file di coverage: {e}")
                self.coverage = {"error": str(e)}

    def write_report(self):
        output_path = f"./Results/GeneratedTests/{self.model_name}/{self.prompt_in_use}/{self.java_class}/{self.iteration}/report.json"
        os.makedirs(os.path.dirname(output_path), exist_ok=True)

        final_report = {
            "model": self.model_name,
            "prompt": self.prompt_in_use,
            "scope": self.generation_scope,
            "reports": self.reports,
            "coverage_summary": (
                self.coverage if self.coverage and "error" not in self.coverage
                else "No coverage data available"
            )
        }

        with open(output_path, 'w') as f:
            json.dump(final_report, f, indent=4)

    def clear_data(self):
        self.reports.clear()
