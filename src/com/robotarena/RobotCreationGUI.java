package com.robotarena;

import java.awt.*;
import javax.swing.*;

public class RobotCreationGUI extends JFrame {

    private JTextField nameField;
    private JComboBox<String> typeBox;
    private JTextField healthField;
    private JTextField attackField;
    private JTextField defenseField;
    private JTextField speedField;

    public RobotCreationGUI() {

        setTitle("🤖 ROBOT ARENA - CREATE ROBOT");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // ================= MAIN BACKGROUND =================

        JPanel mainPanel = new JPanel(
                new BorderLayout()
        );

        mainPanel.setBackground(
                new Color(7, 13, 25)
        );


        // ================= TITLE =================

        JLabel title = new JLabel(
                "🤖  CREATE YOUR ROBOT",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        40
                )
        );

        title.setForeground(
                new Color(255, 200, 70)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 5, 10
                )
        );


        JLabel subtitle = new JLabel(
                "BUILD • CUSTOMIZE • DOMINATE",
                SwingConstants.CENTER
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(
                new Color(150, 190, 220)
        );


        JPanel topPanel =
                new JPanel(
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
                                30, 55, 30, 55
                        )
                )
        );

        card.setLayout(
                new GridLayout(
                        6,
                        2,
                        25,
                        20
                )
        );


        // ================= FIELDS =================

        nameField =
                createTextField(
                        "Enter robot name"
                );


        typeBox =
                new JComboBox<>(
                        new String[]{
                                "Attack",
                                "Defense",
                                "Speed"
                        }
                );

        typeBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        typeBox.setForeground(
                Color.WHITE
        );

        typeBox.setBackground(
                new Color(25, 36, 55)
        );


        healthField =
                createTextField("100");

        attackField =
                createTextField("50");

        defenseField =
                createTextField("50");

        speedField =
                createTextField("50");


        // ================= ADD FIELDS =================

        card.add(
                createLabel("ROBOT NAME")
        );

        card.add(
                nameField
        );


        card.add(
                createLabel("ROBOT TYPE")
        );

        card.add(
                typeBox
        );


        card.add(
                createLabel("HEALTH")
        );

        card.add(
                healthField
        );


        card.add(
                createLabel("ATTACK POWER")
        );

        card.add(
                attackField
        );


        card.add(
                createLabel("DEFENSE POWER")
        );

        card.add(
                defenseField
        );


        card.add(
                createLabel("SPEED")
        );

        card.add(
                speedField
        );


        // ================= CENTER =================

        JPanel centerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        centerPanel.setOpaque(false);

        centerPanel.add(card);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // ================= CREATE BUTTON =================

        JButton createButton =
                new JButton(
                        "⚡  CREATE ROBOT"
                );

        createButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        createButton.setForeground(
                new Color(5, 12, 20)
        );

        createButton.setBackground(
                new Color(255, 195, 55)
        );

        createButton.setFocusPainted(false);

        createButton.setBorder(
                BorderFactory.createEmptyBorder(
                        14, 40, 14, 40
                )
        );


        // ================= CREATE ACTION =================

        createButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText().trim();

                String type =
                        typeBox.getSelectedItem()
                                .toString();


                if (name.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a robot name.",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }


                int health =
                        Integer.parseInt(
                                healthField.getText()
                        );

                int attack =
                        Integer.parseInt(
                                attackField.getText()
                        );

                int defense =
                        Integer.parseInt(
                                defenseField.getText()
                        );

                int speed =
                        Integer.parseInt(
                                speedField.getText()
                        );


                // ================= CREATE ROBOT =================

                Robot robot =
                        new Robot(
                                0,
                                1,
                                name,
                                type,
                                health,
                                attack,
                                defense,
                                speed,
                                1,
                                0,
                                0
                        );


                // ================= SAVE =================

                RobotDAO dao =
                        new RobotDAO();

                dao.addRobot(robot);


                // ================= SUCCESS =================

                JOptionPane.showMessageDialog(
                        this,
                        "🤖 Robot created successfully!\n\n"
                        + "Name: " + name
                        + "\nType: " + type
                        + "\nHealth: " + health
                        + "\nAttack: " + attack
                        + "\nDefense: " + defense
                        + "\nSpeed: " + speed,
                        "ROBOT CREATED",
                        JOptionPane.INFORMATION_MESSAGE
                );


                nameField.setText("");


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Health, Attack, Defense and Speed "
                        + "must be numbers.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // ================= BOTTOM =================

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setOpaque(false);

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 25, 10
                )
        );

        bottomPanel.add(
                createButton
        );

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

    private JLabel createLabel(
            String text) {

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