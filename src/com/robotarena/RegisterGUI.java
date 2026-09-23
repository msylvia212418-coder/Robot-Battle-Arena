package com.robotarena;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;

public class RegisterGUI extends JFrame {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegisterGUI() {

        setTitle("Robot Battle Arena - Register");
        setSize(1536, 864);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // ================= BACKGROUND =================

        BackgroundPanel background =
                new BackgroundPanel();

        background.setLayout(
                new GridBagLayout()
        );


        // ================= REGISTER CARD =================

        JPanel registerCard = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Transparent dark card

                g2.setColor(
                        new Color(5, 10, 18, 230)
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


        registerCard.setOpaque(false);

        registerCard.setPreferredSize(
                new Dimension(450, 650)
        );

        registerCard.setLayout(
                new BoxLayout(
                        registerCard,
                        BoxLayout.Y_AXIS
                )
        );

        registerCard.setBorder(
                BorderFactory.createEmptyBorder(
                        28,
                        45,
                        25,
                        45
                )
        );


        // ================= ROBOT ICON =================

        JLabel logo =
                new JLabel("🤖");

        logo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        48
                )
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // ================= TITLE =================

        JLabel title =
                new JLabel(
                        "CREATE YOUR PILOT ACCOUNT"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        title.setForeground(
                new Color(255, 200, 65)
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel subtitle =
                new JLabel(
                        "JOIN THE ROBOT BATTLE ARENA"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        subtitle.setForeground(
                new Color(185, 205, 225)
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        registerCard.add(logo);

        registerCard.add(
                Box.createVerticalStrut(3)
        );

        registerCard.add(title);

        registerCard.add(
                Box.createVerticalStrut(4)
        );

        registerCard.add(subtitle);

        registerCard.add(
                Box.createVerticalStrut(25)
        );


        // ================= USERNAME =================

        registerCard.add(
                createLabel("USERNAME")
        );

        registerCard.add(
                Box.createVerticalStrut(6)
        );

        usernameField =
                createTextField();

        registerCard.add(usernameField);

        registerCard.add(
                Box.createVerticalStrut(14)
        );


        // ================= EMAIL =================

        registerCard.add(
                createLabel("EMAIL")
        );

        registerCard.add(
                Box.createVerticalStrut(6)
        );

        emailField =
                createTextField();

        registerCard.add(emailField);

        registerCard.add(
                Box.createVerticalStrut(14)
        );


        // ================= PASSWORD =================

        registerCard.add(
                createLabel("PASSWORD")
        );

        registerCard.add(
                Box.createVerticalStrut(6)
        );

        passwordField =
                createPasswordField();

        registerCard.add(passwordField);

        registerCard.add(
                Box.createVerticalStrut(14)
        );


        // ================= CONFIRM PASSWORD =================

        registerCard.add(
                createLabel("CONFIRM PASSWORD")
        );

        registerCard.add(
                Box.createVerticalStrut(6)
        );

        confirmPasswordField =
                createPasswordField();

        registerCard.add(confirmPasswordField);

        registerCard.add(
                Box.createVerticalStrut(25)
        );


        // ================= REGISTER BUTTON =================

        JButton registerButton =
                new JButton(
                        "⚡  CREATE ACCOUNT"
                );

        styleMainButton(registerButton);

        registerButton.addActionListener(
                e -> registerUser()
        );

        registerCard.add(registerButton);

        registerCard.add(
                Box.createVerticalStrut(15)
        );


        // ================= LOGIN TEXT =================

        JLabel alreadyText =
                new JLabel(
                        "ALREADY HAVE AN ACCOUNT?"
                );

        alreadyText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        alreadyText.setForeground(
                new Color(180, 195, 210)
        );

        alreadyText.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        registerCard.add(alreadyText);


        JButton loginButton =
                new JButton("BACK TO LOGIN");

        loginButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        loginButton.setForeground(
                new Color(255, 200, 65)
        );

        loginButton.setBackground(
                new Color(5, 10, 18)
        );

        loginButton.setFocusPainted(false);

        loginButton.setBorderPainted(false);

        loginButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        loginButton.addActionListener(
                e -> {

                    new LoginGUI().setVisible(true);

                    dispose();
                }
        );

        registerCard.add(loginButton);


        // ================= ADD CARD =================

        background.add(registerCard);

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
                        12
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
                        44
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
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

    private JPasswordField createPasswordField() {

        JPasswordField field =
                new JPasswordField();

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        44
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
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
    // BUTTON STYLE
    // ==================================================

    private void styleMainButton(
            JButton button) {

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
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
                        12,
                        55,
                        12,
                        55
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
    // REGISTER USER
    // ==================================================

    private void registerUser() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        String confirmPassword =
                new String(
                        confirmPasswordField
                                .getPassword()
                );


        // ================= VALIDATION =================

        if (username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        if (!password.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Registration",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // ================= DATABASE =================

        String sql =
                "INSERT INTO users "
                + "(username, email, password) "
                + "VALUES (?, ?, ?)";


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
                    email
            );

            ps.setString(
                    3,
                    password
            );

            ps.executeUpdate();


            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully! 🤖\n"
                    + "Welcome to Robot Battle Arena!",
                    "Registration Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );


            ps.close();
            con.close();


            // Go back to Login

            new LoginGUI().setVisible(true);

            dispose();


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration failed.\n"
                    + "Username or email may already exist.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }


    // ==================================================
    // BACKGROUND
    // ==================================================

    class BackgroundPanel extends JPanel {

        private Image backgroundImage;


        public BackgroundPanel() {

            backgroundImage =
                    new ImageIcon(
                            getClass()
                                    .getResource(
                                            "/resources/robot_info_bg.jpg"
                                    )
                    ).getImage();
        }


        @Override
        protected void paintComponent(
                Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();


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
                    (panelWidth - newWidth)
                            / 2;

            int y =
                    (panelHeight - newHeight)
                            / 2;


            g2.drawImage(
                    backgroundImage,
                    x,
                    y,
                    newWidth,
                    newHeight,
                    this
            );


            // Cinematic dark overlay

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

                    new RegisterGUI()
                            .setVisible(true);

                }
        );
    }
}