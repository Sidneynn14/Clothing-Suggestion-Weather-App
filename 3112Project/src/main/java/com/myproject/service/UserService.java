package main.java.com.myproject.service;
import main.java.com.myproject.model.User; 
import java.io.*;
import java.util.*;

public class UserService {
    private Map<String, User> users;
    private static final String USER_DATA_FILE = "user_data.txt";

    public UserService() {
        users = new HashMap<>();
        loadUserDataFromFile();
    }

    // Load user data from file
    private void loadUserDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                String location = parts[2];
                boolean isAdmin = Boolean.parseBoolean(parts[3]);
                User user = new User(username, password, location, isAdmin);
                users.put(username, user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save user data to file
    public void saveUserDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (User user : users.values()) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getLocation() + "," + user.isAdmin());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a new user
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Remove a user
    public void removeUser(String username) {
        users.remove(username);
    }

    // Authenticate user based on username and password
    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Get user by username
    public User getUser(String username) {
        return users.get(username);
    }

    // Update user information
    public void updateUser(User user) {
        users.put(user.getUsername(), user);
    }
}