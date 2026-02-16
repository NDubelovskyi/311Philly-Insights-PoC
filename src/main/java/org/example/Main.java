package org.example;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.io.*;
import java.net.*;
import java.net.http.*;
import org.json.*;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // OpenDataPhilly Test
        // OpenDataPhilly Database Basic Call:
        // https://phl.carto.com/api/v2/sql?filename=public_cases_fc&format=csv&skipfields=cartodb_id,the_geom,the_geom_webmercator&q=SELECT%20*%20FROM%20public_cases_fc%20WHERE%20requested_datetime%20%3E=%20%272025-01-01%27%20AND%20requested_datetime%20%3C%20%272026-01-01%27

        // Construct the SQL query and URL
        String sql = "SELECT * FROM public_cases_fc WHERE requested_datetime >= '2026-01-01' AND requested_datetime < '2026-01-02'";
        String url = "https://phl.carto.com/api/v2/sql?q=" + java.net.URLEncoder.encode(sql, java.nio.charset.StandardCharsets.UTF_8)
                + "&format=JSON";

        // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Send request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON
        JSONObject json = new JSONObject(response.body());
        JSONArray rows = json.getJSONArray("rows");

        // Print the first 3 rows
        System.out.println("OpenPhillyData Sample Data Request:");
        System.out.println("-----------");
        for (int i = 0; i < 3; i++) {
            JSONObject row = rows.getJSONObject(i);
            System.out.println("Date: " + row.optString("requested_datetime", "N/A"));
            System.out.println("Category: " + row.optString("subject", "N/A"));
            System.out.println("Address: " + row.optString("address", "N/A"));
            System.out.println("-----------");
        }



        // Google AI Studio Test
        // Set up Google AI Studio API Key before using this program
        // Replace Keys.GOOGLE_AI_STUDIO_API below with "YOUR_API" String
        Client googleClient = Client.builder().apiKey(Keys.GOOGLE_AI_STUDIO_API).build();

        GenerateContentResponse googleResponse =
                googleClient.models.generateContent(
                        "gemini-3-flash-preview",
                        "In a single sentence, why there was a surge under 'Snow Removal' category in Philadelphia's 311 data report in January 2016",
                        null);

        System.out.println("\nGoogle Studio AI Sample Response:");
        System.out.println(googleResponse.text());
    }
}