package main.java.com.myproject.model;

public class User {
    private String username;
    private String password;
    private String location;
    private boolean isAdmin;

    public User(String username, String password, String location, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.location = location;
        this.isAdmin = isAdmin;
    }

    // Getters and setters for user attributes
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}