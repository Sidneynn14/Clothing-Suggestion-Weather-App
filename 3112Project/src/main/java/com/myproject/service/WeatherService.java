package main.java.com.myproject.service;
import main.java.com.myproject.model.WeatherData;
import main.java.com.myproject.util.WeatherAPI;;

public class WeatherService {
    private WeatherAPI weatherAPI;

    //Method to create weather api instance in main class 
    public void setWeatherAPI() {
        weatherAPI = new WeatherAPI();
    }

    // Method to fetch weather data based on location
    public WeatherData getWeather() {
        // Simulated weather data retrieval from an external API
        double temperature = getTemperature();
        double chanceOfRain = getChanceOfRain();
        double windSpeed = getWindSpeed();

        return new WeatherData(temperature, chanceOfRain, windSpeed);
    }

    // Method to simulate fetching temperature from an external API
    public double getTemperature() {
        WeatherData weatherData = weatherAPI.getWeather();
        // Return temperature from WeatherData object
        return (weatherData != null) ? weatherData.getTemperature() : Double.NaN;
    }

    // Method to simulate fetching chance of rain from an external API
    public double getChanceOfRain() {
        // Call the getWeather method of WeatherAPI
        WeatherData weatherData = weatherAPI.getWeather();
        // Return chance of rain from WeatherData object
        return (weatherData != null) ? weatherData.getChanceOfRain() : Double.NaN;
    }

    // Method to simulate fetching wind speed from an external API
    public double getWindSpeed() {
        WeatherData weatherData = weatherAPI.getWeather();
        // Return wind speed from WeatherData object
         return (weatherData != null) ? weatherData.getWindSpeed() : Double.NaN;
    }
}