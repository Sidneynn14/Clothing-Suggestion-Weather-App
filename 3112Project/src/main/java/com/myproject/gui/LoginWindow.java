package main.java.com.myproject.gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.com.myproject.service.UserService;
import main.java.com.myproject.model.User;;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;
    private JLabel errorMessageLabel;

    private LoginListener loginListener;
    private UserService userService;

    public LoginWindow(UserService userService) {
        this.userService = userService;
        initializeComponents();
        setupLayout();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(300, 150);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void initializeComponents() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account");
        errorMessageLabel = new JLabel();
    }

    private void setupLayout() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(createAccountButton);
        panel.add(errorMessageLabel);
        add(panel);
    }

    private void setupListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (loginListener != null) {
                    loginListener.onLogin(username, password);
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a dialog to create a new account
                String username = JOptionPane.showInputDialog(LoginWindow.this, "Enter username:");
                String password = JOptionPane.showInputDialog(LoginWindow.this, "Enter password:");
                String location = JOptionPane.showInputDialog(LoginWindow.this, "Enter location:");
                boolean isAdmin = false; // Assume users are not admins by default
                User newUser = new User(username, password, location, isAdmin);
                userService.addUser(newUser);
                userService.saveUserDataToFile(); // Save user data to file
                JOptionPane.showMessageDialog(LoginWindow.this, "Account created successfully!");
            }
        });
    }

    public void addLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    public void showErrorMessage(String message) {
        errorMessageLabel.setText(message);
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        errorMessageLabel.setText("");
    }

    public interface LoginListener {
        void onLogin(String username, String password);
    }
}