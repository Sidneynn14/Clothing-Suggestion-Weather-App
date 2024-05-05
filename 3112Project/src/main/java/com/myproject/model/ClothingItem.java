package main.java.com.myproject.model;

public class ClothingItem {
    private String name;
    private String type;
    // Add other properties as needed

    public ClothingItem(String name, String type) {
        this.name = name;
        this.type = type;
        // Initialize other properties as needed
    }

    // Getters and setters for name and type
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Add getters and setters for other properties as needed
}