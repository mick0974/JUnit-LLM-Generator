import re


def parse_maven_log(maven_log):
    compilation_error_pattern = re.compile(r'^\[ERROR\].*COMPILATION ERROR.*')
    test_failure_pattern = re.compile(r'^\[ERROR\].*Failures:.*')
    test_error_pattern = re.compile(r'^\[ERROR\].*Errors:.*')
    plugin_error_pattern = re.compile(r'^\[ERROR\].*Failed to execute goal.*')
    dependency_error_pattern = re.compile(r'^\[ERROR\].*Could not resolve dependencies.*')
    pom_error_pattern = re.compile(r'^\[ERROR\].*Non-resolvable parent POM.*')
    packaging_error_pattern = re.compile(r'^\[ERROR\].*Error assembling JAR.*')

    errors = {
        "compilation_errors": [],
        "test_failures": [],
        "test_errors": [],
        "plugin_errors": [],
        "dependency_errors": [],
        "pom_errors": [],
        "packaging_errors": []
    }

    lines = maven_log.splitlines()

    for line in lines:
        if compilation_error_pattern.match(line):
            errors["compilation_errors"].append(line)
        elif test_failure_pattern.match(line):
            errors["test_failures"].append(line)
        elif test_error_pattern.match(line):
            errors["test_errors"].append(line)
        elif plugin_error_pattern.match(line):
            errors["plugin_errors"].append(line)
        elif dependency_error_pattern.match(line):
            errors["dependency_errors"].append(line)
        elif pom_error_pattern.match(line):
            errors["pom_errors"].append(line)
        elif packaging_error_pattern.match(line):
            errors["packaging_errors"].append(line)

    return errors