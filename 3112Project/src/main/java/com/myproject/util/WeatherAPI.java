package main.java.com.myproject.util;
import main.java.com.myproject.model.WeatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.lang.Double;


public class WeatherAPI {
    // Method to fetch weather data for a given location
    public WeatherData getWeather() {
        try {
            // Construct the API URL with the given location
            String apiUrl = "https://api.tomorrow.io/v4/weather/forecast?location=35.2271,-80.8431&apikey=rFbXP8jnmJgZqOmdsGAF9wJjZLFhWFbB";
            //Need to use uri due to depreciated url command
            URI uri = new URI(apiUrl);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Get response code
            int responseCode = connection.getResponseCode();
            // Check if request was successful
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // Parse JSON response manually
                double temperature = extractDoubleFromJSON(response.toString(), "temperature");
                double windspeed = extractDoubleFromJSON(response.toString(), "windSpeed");
                double precipitation = extractDoubleFromJSON((response.toString()), "precipitationProbability");
                // Create and return WeatherData object
                return new WeatherData(temperature, windspeed, precipitation);
            } else {
                // Handle unsuccessful request
                System.out.println("Failed to fetch weather data. Response code: " + responseCode);
                return null;
            }
        } catch (IOException | URISyntaxException e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }

    // Method to extract double value from JSON response
    private double extractDoubleFromJSON(String jsonResponse, String key) {
        try{
            int index = jsonResponse.indexOf("\"" + key + "\":");

            if (index == -1) {
                return Double.NaN; // Key not found
            }
            index = index + key.length() + 3; // Move index to the start of the value
            int endIndex = jsonResponse.indexOf(',', index);
            if (endIndex == -1) {
                endIndex = jsonResponse.indexOf('}', index);
            }
            String value = jsonResponse.substring(index, endIndex);
            String updatedValue = ((String) value).replaceAll("[^\\d.]", "");
            return Double.parseDouble(updatedValue);
        } catch (Exception e) {
            System.err.println("Error extracting double value for key '" + key + "' from JSON object: " + e.getMessage());
            return Double.NaN;
        }
    }
}
