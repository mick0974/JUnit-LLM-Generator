import os

from src.client.chatgpt_client import ChatGptClient
from src.client.gemini_client import GeminiClient
from src.client.local_model_client import LocalModelClient


class ClientFactory:
    @staticmethod
    def get_client(model_name, config):
        client_type = config["type"].lower()

        if client_type == "local":
            return LocalModelClient(
                model_path=config["path"],
                quantize=config["quantize"],
                max_new_tokens=config["max_new_tokens"]
            )

        elif client_type == "api":
            if model_name == "chatgpt":
                return ChatGptClient(
                    api_key=config["api_key"],
                    model=config["model"]
                )
            elif model_name == "gemini":
                return GeminiClient(
                    api_key=config["api_key"] if config["api_key"] else os.environ["GEMINI_API_KEY"],
                    model=config["model"]
                )
            else:
                raise ValueError(f"Unknown model name: {model_name}")
        else:
            raise ValueError(f"Unknown client type: {client_type}")