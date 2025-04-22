from google.generativeai import GenerativeModel, configure

class GeminiClient:
    def __init__(self, api_key: str, model: str = "gemini-1.5-pro"):
        configure(api_key=api_key)
        self.model = GenerativeModel(model_name=model)

    def send_prompt(self, prompt: str) -> str:
        system_message = (
            "You are a senior Java developer specialized in writing JUnit 4 test classes. "
            "Respond with clear, professional, and efficient Java code."
        )

        full_prompt = f"{system_message}\n\n{prompt}"

        response = self.model.generate_content(full_prompt)

        # Extract code form json response
        if hasattr(response, 'text'):
            content = response.text.strip()
        elif hasattr(response, 'candidates') and response.candidates:
            content = response.candidates[0].content.strip()
        else:
            raise ValueError(f"Unexpected response format: {response}")

        print(f"Gemini response:\n{content}")

        return response.text.strip()
