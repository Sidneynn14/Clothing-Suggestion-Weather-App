package main.java.com.myproject.gui;
import main.java.com.myproject.model.ClothingItem;
import main.java.com.myproject.service.AdminService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminWindow extends JFrame {
    private AdminService adminService;
    private JTextArea clothingItemsTextArea;
    private JButton addButton;
    private JButton removeButton;
    private JButton logoutButton;


    public AdminWindow(AdminService adminService) {
        this.adminService = adminService;
        initializeComponents();
        setupLayout();
        loadClothingItems();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Window");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center window on the screen
    }

    private void initializeComponents() {
        addButton = new JButton("Add Clothing Item");
        removeButton = new JButton("Remove Selected Item");
        logoutButton = new JButton("Logout");
        clothingItemsTextArea = new JTextArea();
        clothingItemsTextArea.setEditable(false); // Make the text area read-only        

    }

    private void setupLayout() {
        add(new JScrollPane(clothingItemsTextArea), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void loadClothingItems() {
        // Load clothing items from the service and display them
        List<ClothingItem> clothingItems = adminService.getAllClothingItems();
        StringBuilder sb = new StringBuilder();
        for (ClothingItem item : clothingItems) {
            sb.append(item.getName()).append(" - ").append(item.getType()).append("\n");
        }
        clothingItemsTextArea.setText(sb.toString());
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    public void addLogoutButtonListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addClothingItem(String name, String type) {
        ClothingItem newItem = new ClothingItem(name, type);
        adminService.addClothingItem(newItem);
    }

    // Method to display error message
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void loadClothingItems(List<ClothingItem> clothingItems) {
        clothingItemsTextArea.setText(""); // Clear previous content
        for (ClothingItem item : clothingItems) {
            clothingItemsTextArea.append(item.getName() + " - " + item.getType() + "\n");
        }
    }
}
