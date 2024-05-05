package main.java.com.myproject;

import main.java.com.myproject.gui.LoginWindow;
import main.java.com.myproject.gui.UserWindow;

import javax.swing.JOptionPane;

import main.java.com.myproject.gui.AdminWindow;
//import main.java.com.myproject.model.ClothingItem;
import main.java.com.myproject.model.User;
import main.java.com.myproject.service.AdminService;
import main.java.com.myproject.service.UserService;
import main.java.com.myproject.service.WeatherService;
import main.java.com.myproject.model.WeatherData;

public class Main {
    public static void main(String[] args) {
    // Initialize services
    UserService userService = new UserService();
    WeatherService weatherService = new WeatherService();
    weatherService.setWeatherAPI();
    AdminService adminService = new AdminService();

    // Initialize GUI components
    LoginWindow loginWindow = new LoginWindow(userService);
    UserWindow userWindow = new UserWindow();
    AdminWindow adminWindow = new AdminWindow(adminService);

    // Display login window
    loginWindow.setVisible(true);

    // Event handling for login
    loginWindow.addLoginListener((username, password) -> {
        User user = userService.authenticateUser(username, password);
        if (user != null) {
            loginWindow.setVisible(false);
            if (user.isAdmin()) {
                adminWindow.setVisible(true);
                //setupAdminWindowListeners(adminWindow, adminService); // Call this to set up admin window listeners
            } else {
                userWindow.setUser(user);
                // Fetch weather data
                double temperature = weatherService.getTemperature();
                double chanceOfRain = weatherService.getChanceOfRain();
                double windSpeed = weatherService.getWindSpeed();
                // Display weather information
                userWindow.displayWeatherInfo(temperature, chanceOfRain, windSpeed);
                userWindow.setVisible(true);
                JOptionPane.showMessageDialog(loginWindow, "Welcome, " + username + "!");
            }
        } else {
            // Display error message for invalid credentials
            loginWindow.showErrorMessage("Invalid username or password");
        }
    });

    // Event handling for logout
    userWindow.addLogoutListener(() -> {
        userWindow.setVisible(false);
        loginWindow.clearFields();
        loginWindow.setVisible(true);
    });

    // Event handling for admin logout
    adminWindow.addLogoutButtonListener(e -> {
        adminWindow.setVisible(false);
        loginWindow.clearFields();
        loginWindow.setVisible(true);
    });

    // Event handling for getting weather and suggesting outfit for user
    userWindow.addWeatherUpdateListener(() -> {
        WeatherData weatherData = weatherService.getWeather();
        String outfitSuggestion = suggestOutfit(weatherData);
        userWindow.displayOutfitSuggestion(outfitSuggestion);
    });
}

    /*  Removed Admin funtions due to its lack of necessity to overall project function

    private static void setupAdminWindowListeners(AdminWindow adminWindow, AdminService adminService) {
        adminWindow.addAddButtonListener(e -> {
            String itemName = JOptionPane.showInputDialog(adminWindow, "Enter item name:");
            String itemType = JOptionPane.showInputDialog(adminWindow, "Enter item type:");
            ClothingItem newItem = new ClothingItem(itemName, itemType);
            adminService.addClothingItem(newItem);
            adminWindow.loadClothingItems(adminService.getAllClothingItems()); // Refresh clothing items in the window
        });

        adminWindow.addRemoveButtonListener(e -> {
            String selectedItemName = JOptionPane.showInputDialog(adminWindow, "Enter item name to remove:");
            boolean removed = adminService.removeClothingItem(selectedItemName);
            if (removed) {
                // Removal was successful
                adminWindow.loadClothingItems(adminService.getAllClothingItems()); // Refresh clothing items in the window
            } else {
                // Removal failed
                JOptionPane.showMessageDialog(adminWindow, "Item not found or could not be removed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        adminWindow.addLogoutButtonListener(e -> {
            adminWindow.setVisible(false);
        });    
    }
    */

    // Method to suggest outfit based on weather conditions
    private static String suggestOutfit(WeatherData weatherData) {
        double temperature = weatherData.getTemperature();
        double precipitation = weatherData.getChanceOfRain();
        String suggestedClothing = "";

        //Top choices
        if (temperature < 10) { // Cold 
            suggestedClothing += "Heavy Jacket, Sweater, T-shirt, ";
        } else if (temperature < 20) { // Mild 
            suggestedClothing += "Sweater, T-shirt, ";
        } else { // Hot 
            suggestedClothing += "T-shirt, ";
        }

        //Bottom Choices
        if (temperature < 10) { // Cold 
            suggestedClothing += "Thick pants, ";
        } else if (temperature < 20) { // Mild 
            suggestedClothing += "Pants, ";
        } else { // Hot 
            suggestedClothing += "Shorts, ";
        }

        //Shoe Choices
        if (temperature < 10) { // Cold 
            suggestedClothing += "Boots, ";
        } else if (temperature < 20) { // Mild 
            suggestedClothing += "Sneakers, ";
        } else { // Hot 
            suggestedClothing += "Sandals, ";
        }

        //If Rain
        if (precipitation > 1) {
            suggestedClothing += "and bring an umbrella. ";
        }
        
        return "Suggested outfit: " + suggestedClothing;
    }
}