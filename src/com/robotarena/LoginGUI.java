package com.robotarena;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {

        setTitle("Robot Battle Arena");
        setSize(1536, 864);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ================= BACKGROUND =================

        BackgroundPanel background =
                new BackgroundPanel();

        background.setLayout(
                new GridBagLayout()
        );


        // ================= LOGIN CARD =================

        JPanel loginCard = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Transparent dark background
                g2.setColor(
                        new Color(5, 10, 18, 225)
                );

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        25,
                        25
                );

                // Gold border
                g2.setColor(
                        new Color(255, 190, 55)
                );

                g2.setStroke(
                        new BasicStroke(2)
                );

                g2.drawRoundRect(
                        1,
                        1,
                        getWidth() - 3,
                        getHeight() - 3,
                        25,
                        25
                );

                g2.dispose();
            }
        };


        loginCard.setOpaque(false);

        loginCard.setPreferredSize(
                new Dimension(430, 540)
        );

        loginCard.setLayout(
                new BoxLayout(
                        loginCard,
                        BoxLayout.Y_AXIS
                )
        );

        loginCard.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        45,
                        30,
                        45
                )
        );


        // ================= GAME LOGO =================

        JLabel logo =
                new JLabel("🤖");

        logo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        55
                )
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // ================= TITLE =================

        JLabel title =
                new JLabel(
                        "ROBOT BATTLE ARENA"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(255, 200, 65)
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // ================= SUBTITLE =================

        JLabel subtitle =
                new JLabel(
                        "ENTER THE ARENA"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        subtitle.setForeground(
                new Color(190, 205, 220)
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        loginCard.add(logo);

        loginCard.add(
                Box.createVerticalStrut(5)
        );

        loginCard.add(title);

        loginCard.add(
                Box.createVerticalStrut(5)
        );

        loginCard.add(subtitle);

        loginCard.add(
                Box.createVerticalStrut(35)
        );


        // ================= USERNAME =================

        JLabel usernameLabel =
                createLabel("USERNAME");

        usernameField =
                createTextField();

        loginCard.add(usernameLabel);

        loginCard.add(
                Box.createVerticalStrut(8)
        );

        loginCard.add(usernameField);

        loginCard.add(
                Box.createVerticalStrut(20)
        );


        // ================= PASSWORD =================

        JLabel passwordLabel =
                createLabel("PASSWORD");

        passwordField =
                new JPasswordField();

        stylePasswordField(passwordField);

        loginCard.add(passwordLabel);

        loginCard.add(
                Box.createVerticalStrut(8)
        );

        loginCard.add(passwordField);

        loginCard.add(
                Box.createVerticalStrut(30)
        );


        // ================= LOGIN BUTTON =================

        JButton loginButton =
                new JButton("⚔  LOGIN");

        styleMainButton(loginButton);

        loginButton.addActionListener(
                e -> loginUser()
        );

        loginCard.add(loginButton);

        loginCard.add(
                Box.createVerticalStrut(25)
        );


        // ================= REGISTER TEXT =================

        JLabel registerText =
                new JLabel(
                        "DON'T HAVE AN ACCOUNT?"
                );

        registerText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        registerText.setForeground(
                new Color(180, 195, 210)
        );

        registerText.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginCard.add(registerText);

        loginCard.add(
                Box.createVerticalStrut(5)
        );


        // ================= REGISTER BUTTON =================

        JButton registerButton =
                new JButton("CREATE ACCOUNT");

        registerButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        registerButton.setForeground(
                new Color(255, 200, 65)
        );

        registerButton.setBackground(
                new Color(5, 10, 18)
        );

        registerButton.setFocusPainted(false);

        registerButton.setBorderPainted(false);

        registerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        registerButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        registerButton.addActionListener(
                e -> showRegisterMessage()
        );

        loginCard.add(registerButton);


        // ================= ADD CARD =================

        background.add(loginCard);

        setContentPane(background);
    }


    // ==================================================
    // LABEL
    // ==================================================

    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                new Color(225, 230, 240)
        );

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return label;
    }


    // ==================================================
    // TEXT FIELD
    // ==================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        field.setForeground(Color.WHITE);

        field.setBackground(
                new Color(20, 30, 45)
        );

        field.setCaretColor(
                new Color(255, 200, 65)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(90, 120, 150),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                5,
                                12,
                                5,
                                12
                        )
                )
        );

        return field;
    }


    // ==================================================
    // PASSWORD FIELD
    // ==================================================

    private void stylePasswordField(
            JPasswordField field) {

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        field.setForeground(Color.WHITE);

        field.setBackground(
                new Color(20, 30, 45)
        );

        field.setCaretColor(
                new Color(255, 200, 65)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(90, 120, 150),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                5,
                                12,
                                5,
                                12
                        )
                ));
    }


    // ==================================================
    // MAIN BUTTON
    // ==================================================

    private void styleMainButton(
            JButton button) {

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        button.setForeground(
                new Color(10, 15, 20)
        );

        button.setBackground(
                new Color(255, 195, 55)
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        13,
                        70,
                        13,
                        70
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }


    // ==================================================
    // LOGIN
    // ==================================================

    private void loginUser() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );


        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        String sql =
                "SELECT * FROM users "
                + "WHERE username = ? "
                + "AND password = ?";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    username
            );

            ps.setString(
                    2,
                    password
            );

            ResultSet rs =
                    ps.executeQuery();


            if (rs.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Welcome " + username + "! 🤖",
                        "LOGIN SUCCESS",
                        JOptionPane.INFORMATION_MESSAGE
                );

                
                new DashboardGUI(username).setVisible(true);

                // Close Login window
                dispose();

            } else {

          

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "LOGIN FAILED",
                        JOptionPane.ERROR_MESSAGE
                );
            }


            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database connection failed.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }


    // ==================================================
    // REGISTER
    // ==================================================

    private void showRegisterMessage() {

        JOptionPane.showMessageDialog(
                this,
                "Registration screen will be created next.",
                "Create Account",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // ==================================================
    // BACKGROUND PANEL
    // ==================================================

    class BackgroundPanel extends JPanel {

        private Image backgroundImage;


        public BackgroundPanel() {

            backgroundImage =
                    new ImageIcon(
                            getClass()
                                    .getResource(
                                            "/resources/robot_login_bg.jpg"
                                    )
                    ).getImage();
        }


        @Override
        protected void paintComponent(
                Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();


            // Smooth image scaling

            g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR
            );


            int panelWidth =
                    getWidth();

            int panelHeight =
                    getHeight();


            int imageWidth =
                    backgroundImage.getWidth(this);

            int imageHeight =
                    backgroundImage.getHeight(this);


            // Cover entire window

            double scale =
                    Math.max(
                            (double) panelWidth
                                    / imageWidth,

                            (double) panelHeight
                                    / imageHeight
                    );


            int newWidth =
                    (int)
                    (imageWidth * scale);

            int newHeight =
                    (int)
                    (imageHeight * scale);


            int x =
                    (panelWidth - newWidth) / 2;

            int y =
                    (panelHeight - newHeight) / 2;


            g2.drawImage(
                    backgroundImage,
                    x,
                    y,
                    newWidth,
                    newHeight,
                    this
            );


            // Dark cinematic overlay

            g2.setColor(
                    new Color(
                            0,
                            0,
                            0,
                            65
                    )
            );

            g2.fillRect(
                    0,
                    0,
                    panelWidth,
                    panelHeight
            );


            g2.dispose();
        }
    }


    // ==================================================
    // MAIN
    // ==================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    new LoginGUI()
                            .setVisible(true);

                }
        );
    }
}