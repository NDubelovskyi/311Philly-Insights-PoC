package org.example;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class Main {
    public static void main(String[] args) {
        // Set up Google AI Studio API Key before using this program
        // Replace Keys.GOOGLE_AI_STUDIO_API below with "YOUR_API" String
        Client client = Client.builder().apiKey(Keys.GOOGLE_AI_STUDIO_API).build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3-flash-preview",
                        "In a 2 sentences, why there was a surge under 'Snow Removal' category in Philadelphia's 311 data report in January 2016",
                        null);

        System.out.println(response.text());
    }
}