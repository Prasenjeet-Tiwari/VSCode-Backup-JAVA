import os
import json
import google.generativeai as genai

# Working directory
working_directory = os.path.dirname(os.path.abspath(__file__))

# Path of configuration data
config_file_path = f"{working_directory}/config.json"
config_data = json.load(open(config_file_path))  # Use config_file_path instead of "config.json"

# Loading the API
GOOGLE_API_KEY = config_data["GOOGLE_API_KEY"]  # Use square brackets for dictionary access

# Configure google.generativeai
genai.configure(api_key=GOOGLE_API_KEY)

# Loading gemini pro model
def load_gemini_pro_model():
    gemini_pro_model = genai.GenerativeModel("gemini-pro")
    return gemini_pro_model
