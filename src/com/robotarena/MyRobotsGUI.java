package com.robotarena;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyRobotsGUI extends JFrame {

    private String username;

    public MyRobotsGUI(String username) {

        this.username = username;

        setTitle("Robot Battle Arena - My Robots");
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        // Background panel
        BackgroundPanel background =
                new BackgroundPanel();

        background.setLayout(null);

        setContentPane(background);

        // =========================================
        // PLAY BUTTON
        // =========================================

        JButton playButton = new JButton();

        // Make button invisible
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);

        // Mouse cursor
        playButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        /*
         * Position of PLAY button.
         *
         * The image is stretched to 1536 x 864,
         * so this area is placed over the PLAY button.
         */
        playButton.setBounds(
                520,   // X
                585,   // Y
                470,   // Width
                155    // Height
        );

        background.add(playButton);

        // =========================================
        // PLAY CLICK
        // =========================================

        playButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        // Open Battle Arena
                        new BattleArenaGUI().setVisible(true);

                        // Close My Robots screen
                        dispose();
                    }
                }
        );

        setVisible(true);
    }


    // =========================================
    // BACKGROUND PANEL
    // =========================================

    class BackgroundPanel extends JPanel {

        private Image backgroundImage;

        public BackgroundPanel() {

            backgroundImage =
                    new ImageIcon(
                            getClass().getResource(
                                    "/resources/my_robot_bg.png"
                            )
                    ).getImage();
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();

            // Draw image to full screen
            g2.drawImage(
                    backgroundImage,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
            );

            g2.dispose();
        }
    }


    // =========================================
    // MAIN METHOD
    // =========================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    new MyRobotsGUI("Mano");
                }
        );
    }
}