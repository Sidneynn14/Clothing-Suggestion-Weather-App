package main.java.com.myproject.model;

public class WeatherData {
    private double temperature;
    private double precipitationProbability;
    private double windSpeed;

    public WeatherData(double temperature, double precipitationProbability, double windSpeed) {
        this.temperature = temperature;
        this.precipitationProbability = precipitationProbability;
        this.windSpeed = windSpeed;
    }

    // Getters and setters for weather data attributes
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getChanceOfRain() {
        return precipitationProbability;
    }

    public void setChanceOfRain(double precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}