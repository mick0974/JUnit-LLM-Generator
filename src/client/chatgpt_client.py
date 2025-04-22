from openai import OpenAI


class ChatGptClient:
    def __init__(self, api_key: str = None, model: str = "gpt-4o"):
        if api_key:
            self.client = OpenAI(api_key=api_key)
        else:
            self.client = OpenAI()

        self.model = model

    def send_prompt(self, prompt: str) -> str:
        system_message = (
            "You are a senior Java developer specialized in writing JUnit 4 test classes. "
            "Respond with clear, professional, and efficient Java code."
        )

        response = self.client.chat.completions.create(
            model=self.model,
            messages=[
                {"role": "system", "content": system_message},
                {"role": "user", "content": prompt}
            ]
        )

        return response.choices[0].message.content.strip()
