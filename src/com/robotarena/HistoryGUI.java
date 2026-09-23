package com.robotarena;

import javax.swing.*;
import java.awt.*;

public class HistoryGUI extends JFrame {

    public HistoryGUI() {

        setTitle("Robot Battle Arena - Battle History");
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);

        JLabel title = new JLabel("📜 BATTLE HISTORY");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBounds(80, 60, 500, 50);

        panel.add(title);

        setContentPane(panel);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new HistoryGUI().setVisible(true);
        });

    }
}