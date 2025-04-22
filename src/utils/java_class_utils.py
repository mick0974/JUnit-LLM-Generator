import re

def load_java_class(file_path):
    with open(file_path, 'r') as file:
        return file.read()

def extract_imports_from_java_class(java_code):
    class_match = re.search(r'\b(class|interface|enum)\b\s+\w+', java_code)
    if not class_match:
        return ""

    before_class = java_code[:class_match.start()]
    import_pattern = re.compile(r'^\s*import\s+[^\n;]+;', re.MULTILINE)
    imports = import_pattern.findall(before_class)

    return '\n'.join(imp.strip() for imp in imports)
def extract_public_method_signatures(java_class_code):
    # Regex per estrarre le firme dei metodi pubblici e pubblici statici
    method_pattern = re.compile(
        r'public\s+(static\s+)?[\w<>\[\]]+\s+(\w+)\s*\(([^)]*)\)\s*(throws\s+[\w<>,\s]+)?\s*{',
        re.MULTILINE
    )

    matches = method_pattern.findall(java_class_code)

    method_signatures = []
    for match in matches:
        static_keyword = "static " if match[0] else ""
        method_name = match[1]
        parameters = match[2]
        throws_clause = match[3]

        signature = f"public {static_keyword}{method_name}({parameters})"
        if throws_clause:
            signature += f" {throws_clause}"

        method_signatures.append(signature)

    return method_signatures

def extract_method_name(method_signature):
    match = re.search(r'\b(\w+)\s*\(', method_signature)
    if match:
        return match.group(1)
    return None
