package com.robotarena;

import java.awt.*;
import javax.swing.*;

public class WeaponGUI extends JFrame {

    private JTextField nameField;
    private JComboBox<String> typeBox;
    private JTextField damageField;
    private JTextField levelField;
    private JTextField priceField;

    public WeaponGUI() {

        setTitle("⚔ ROBOT ARENA - WEAPONS");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // ================= MAIN BACKGROUND =================

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(7, 13, 25));
        mainPanel.setLayout(new BorderLayout());

        // ================= TITLE =================

        JLabel title = new JLabel(
                "⚔  WEAPONS",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 42)
        );

        title.setForeground(
                new Color(255, 200, 70)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 10, 10, 10
                )
        );

        mainPanel.add(title, BorderLayout.NORTH);


        // ================= SUBTITLE =================

        JLabel subtitle = new JLabel(
                "BUILD YOUR ULTIMATE ARSENAL",
                SwingConstants.CENTER
        );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        subtitle.setForeground(
                new Color(150, 190, 220)
        );

        JPanel topPanel = new JPanel(
                new BorderLayout()
        );

        topPanel.setOpaque(false);

        topPanel.add(
                title,
                BorderLayout.NORTH
        );

        topPanel.add(
                subtitle,
                BorderLayout.CENTER
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );


        // ================= FORM CARD =================

        JPanel card = new JPanel();

        card.setBackground(
                new Color(15, 24, 40)
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(255, 190, 50),
                                2
                        ),

                        BorderFactory.createEmptyBorder(
                                30, 50, 30, 50
                        )
                )
        );

        card.setLayout(
                new GridLayout(
                        5,
                        2,
                        20,
                        22
                )
        );


        // ================= FIELDS =================

        nameField =
                createTextField("Enter weapon name");

        typeBox =
                new JComboBox<>(
                        new String[]{
                                "Energy",
                                "Laser",
                                "Missile",
                                "Plasma"
                        }
                );

        damageField =
                createTextField("40");

        levelField =
                createTextField("1");

        priceField =
                createTextField("500");


        // ================= LABELS =================

        card.add(createLabel("WEAPON NAME"));
        card.add(nameField);

        card.add(createLabel("WEAPON TYPE"));
        card.add(typeBox);

        card.add(createLabel("DAMAGE"));
        card.add(damageField);

        card.add(createLabel("LEVEL"));
        card.add(levelField);

        card.add(createLabel("PRICE"));
        card.add(priceField);


        JPanel centerPanel = new JPanel(
                new GridBagLayout()
        );

        centerPanel.setOpaque(false);

        centerPanel.add(card);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // ================= ADD WEAPON BUTTON =================

        JButton addButton =
                new JButton("⚡  ADD WEAPON");

        addButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        addButton.setForeground(
                new Color(5, 12, 20)
        );

        addButton.setBackground(
                new Color(255, 195, 55)
        );

        addButton.setFocusPainted(false);

        addButton.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 35, 15, 35
                )
        );


        // ================= BUTTON ACTION =================

        addButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText();

                String type =
                        typeBox.getSelectedItem()
                                .toString();

                int damage =
                        Integer.parseInt(
                                damageField.getText()
                        );

                int level =
                        Integer.parseInt(
                                levelField.getText()
                        );

                int price =
                        Integer.parseInt(
                                priceField.getText()
                        );


                if (name.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter weapon name.",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }


                Weapon weapon =
                        new Weapon(
                                0,
                                name,
                                type,
                                damage,
                                level,
                                price
                        );


                WeaponDAO dao =
                        new WeaponDAO();

                dao.addWeapon(weapon);


                JOptionPane.showMessageDialog(
                        this,
                        "⚔ Weapon added successfully!",
                        "Weapon Created",
                        JOptionPane.INFORMATION_MESSAGE
                );


                nameField.setText("");


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Damage, Level and Price must be numbers.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // ================= BOTTOM =================

        JPanel bottomPanel = new JPanel();

        bottomPanel.setOpaque(false);

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 10, 25, 10
                )
        );

        bottomPanel.add(addButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // ================= FRAME =================

        setContentPane(mainPanel);
    }


    // =====================================================
    // LABEL DESIGN
    // =====================================================

    private JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        label.setForeground(
                new Color(210, 220, 235)
        );

        return label;
    }


    // =====================================================
    // TEXT FIELD DESIGN
    // =====================================================

    private JTextField createTextField(
            String text) {

        JTextField field =
                new JTextField(text);

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        field.setForeground(
                Color.WHITE
        );

        field.setBackground(
                new Color(25, 36, 55)
        );

        field.setCaretColor(
                new Color(255, 200, 70)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(70, 100, 130),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                8, 12, 8, 12
                        )
                )
        );

        return field;
    }
}