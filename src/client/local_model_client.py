import os, torch
from transformers import AutoModelForCausalLM, AutoTokenizer, pipeline, BitsAndBytesConfig
from langchain_huggingface import HuggingFacePipeline


class LocalModelClient:
    def __init__(self, model_path: str, quantize: bool = True, max_new_tokens: int = 2048):
        self.model_name = os.path.basename(model_path)

        bnb_config = BitsAndBytesConfig(
            load_in_4bit=True,
            bnb_4bit_quant_type="nf4",
            bnb_4bit_use_double_quant=True,
            bnb_4bit_compute_dtype=torch.float16
        )

        tokenizer = AutoTokenizer.from_pretrained(model_path, trust_remote_code=True)

        model = AutoModelForCausalLM.from_pretrained(
            model_path,
            device_map="auto",
            quantization_config=bnb_config if quantize else None,
            trust_remote_code=True
        )

        text_gen_pipeline = pipeline(
            "text-generation",
            model=model,
            tokenizer=tokenizer,
            max_new_tokens=max_new_tokens
        )

        self.client = HuggingFacePipeline(pipeline=text_gen_pipeline)

    def send_prompt(self, prompt: str) -> str:
        return self.client.invoke(prompt)