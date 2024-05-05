package main.java.com.myproject.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import main.java.com.myproject.model.User;

public class UserWindow extends JFrame {
    private JLabel welcomeLabel;
    private JButton weatherUpdateButton;
    private JButton logoutButton;
    private JLabel outfitSuggestionLabel;

    private User user;
    private WeatherUpdateListener weatherUpdateListener;
    private LogoutListener logoutListener;

    public UserWindow() {
        initializeComponents();
        setupLayout();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("User Window");
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void initializeComponents() {
        welcomeLabel = new JLabel();
        weatherUpdateButton = new JButton("What should you wear?");
        logoutButton = new JButton("Logout");
        outfitSuggestionLabel = new JLabel();
    }

    private void setupLayout() {
        JPanel panel = new JPanel();
        panel.add(welcomeLabel);
        panel.add(weatherUpdateButton);
        panel.add(logoutButton);
        panel.add(outfitSuggestionLabel);
        add(panel);
    }

    private void setupListeners() {
        weatherUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (weatherUpdateListener != null) {
                    weatherUpdateListener.onWeatherUpdate();
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (logoutListener != null) {
                    logoutListener.onLogout();
                }
            }
        });
    }

    public void setUser(User user) {
        this.user = user;
        welcomeLabel.setText("Welcome, " + user.getUsername() + "!");
    }

    public User getUser() {
        return user;
    }

    public void setOutfitSuggestion(String suggestion) {
        outfitSuggestionLabel.setText(suggestion);
    }

    public void displayOutfitSuggestion(String suggestion) {
        outfitSuggestionLabel.setText(suggestion);
    }

    public void displayWeatherInfo(double temperature, double chanceOfRain, double windSpeed) {
        // Message for weather information
        String weatherInfo = String.format("Temperature: %.2f°C\nChance of Rain: %.2f%%\nWind Speed: %.2f m/s", temperature, chanceOfRain, windSpeed);
        // Show weather information
        JOptionPane.showMessageDialog(this, weatherInfo, "Weather Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addWeatherUpdateListener(WeatherUpdateListener listener) {
        this.weatherUpdateListener = listener;
    }

    public void addLogoutListener(LogoutListener listener) {
        this.logoutListener = listener;
    }

    public interface WeatherUpdateListener {
        void onWeatherUpdate();
    }

    public interface LogoutListener {
        void onLogout();
    }
}