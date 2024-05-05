package main.java.com.myproject.service;
import main.java.com.myproject.model.ClothingItem;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private List<ClothingItem> clothingItems;
    private static final String FILE_PATH = "clothing_data.txt";

    public AdminService() {
        this.clothingItems = new ArrayList<>();
        loadClothingItemsFromFile(); // Load clothing items from file when AdminService is instantiated
    }

    // Method to add a new clothing item
    public void addClothingItem(ClothingItem item) {
        clothingItems.add(item);
        writeClothingItemsToFile();
    }

    // Method to remove a clothing item
    public boolean removeClothingItem(String itemName) {
        for (ClothingItem item : clothingItems) {
            if (item.getName().equals(itemName)) {
                clothingItems.remove(item);
                writeClothingItemsToFile();
                return true; // Item found and removed successfully
            }
        }
        return false; // Item not found
    }

    // Method to retrieve all clothing items
    public List<ClothingItem> getAllClothingItems() {
        return new ArrayList<>(clothingItems);
    }

    private void loadClothingItemsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String itemName = parts[0];
                String itemType = parts[1];
                ClothingItem item = new ClothingItem(itemName, itemType);
                clothingItems.add(item);
            }
        } catch (IOException e) {
            // Handle file IO exception
            e.printStackTrace();
        }
    }

    private void writeClothingItemsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ClothingItem item : clothingItems) {
                writer.write(item.getName() + "," + item.getType());
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle file IO exception
            e.printStackTrace();
        }
    }
}