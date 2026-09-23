package com.robotarena;

import javax.swing.*;
import java.awt.*;

public class SettingsGUI extends JFrame {

    public SettingsGUI() {

        setTitle("Robot Battle Arena - Settings");
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);

        JLabel title = new JLabel("⚙ SETTINGS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBounds(80, 60, 500, 50);

        panel.add(title);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setBounds(100, 170, 200, 30);

        panel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(100, 210, 400, 45);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));

        panel.add(usernameField);

        JButton saveButton = new JButton("SAVE SETTINGS");
        saveButton.setBounds(100, 290, 200, 50);
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(saveButton);

        saveButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Settings saved successfully!"
            );

        });

        setContentPane(panel);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new SettingsGUI().setVisible(true);
        });

    }
}