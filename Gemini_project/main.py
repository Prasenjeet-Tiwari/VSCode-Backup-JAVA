import os
import streamlit as st
from streamlit_option_menu import option_menu
from gemini_utility import load_gemini_pro_model

# To get the current working directory, doing so that no need to change it every time
working_directory = os.path.dirname(os.path.abspath(__file__))
print(working_directory)

# Page configuration data
st.set_page_config(
    page_title="Gemini Model By Prasenjeet",
    page_icon="🖥️",
    layout="centered"
)

# Made use of "Bootstrap Icon" website 

with st.sidebar:
    selected = option_menu(
        menu_title="Gemini Model",
        options=["Chatbot", "Caption Image", "Text Embedding", "Ask me anything"],
        menu_icon='robot',
        icons=['chat-dots-fill', 'image-fill', 'textarea-t', 'patch-question-fill'],
        default_index=0
    )

def translate_role_for_streamlit(user_role):
    if user_role == "model":
        return "assistant"
    else:
        return user_role

# Chatbot page
if selected == 'ChatBot':
    model = load_gemini_pro_model()

    # Initialize chat session in Streamlit if not already present
    if "chat_session" not in st.session_state:  # Renamed for clarity
        st.session_state.chat_session = model.start_chat(history=[])

    # Display the chatbot's title on the page
    st.title("🤖 ChatBot")

    # Display the chat history
    for message in st.session_state.chat_session.history:
        with st.chat_message(translate_role_for_streamlit(message.role)):
            st.markdown(message.parts[0].text)

    # Input field for user's message
    user_prompt = st.chat_input("Ask Gemini-Pro...")  # Renamed for clarity
    if user_prompt:
        # Add user's message to chat and display it
        st.chat_message("user").markdown(user_prompt)

        # Send user's message to Gemini-Pro and get the response
        gemini_response = st.session_state.chat_session.send_message(user_prompt)  # Renamed for clarity

        # Display Gemini-Pro's response
        with st.chat_message("assistant"):
            st.markdown(gemini_response.text)
