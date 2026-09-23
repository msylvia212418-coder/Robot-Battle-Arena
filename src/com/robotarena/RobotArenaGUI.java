package com.robotarena;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class RobotArenaGUI extends JFrame {

    private Image backgroundImage;

    public RobotArenaGUI() {

        setTitle("ROBOT ARENA");

        setSize(1536, 1024);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // ================= LOAD BACKGROUND =================

        ImageIcon icon = new ImageIcon(
                getClass().getResource(
                        "/resources/robot_arena_bgg.png"
                )
        );

        backgroundImage = icon.getImage();


        // ================= BACKGROUND PANEL =================

        JPanel backgroundPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                g.drawImage(
                        backgroundImage,
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        this
                );
            }
        };


        // ================= MOUSE CLICK =================

        backgroundPanel.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        int x = e.getX();
                        int y = e.getY();


                        // CREATE ROBOT
                        if (x >= 175 && x <= 460
                                && y >= 330 && y <= 800) {

                            RobotCreationGUI gui =
                                    new RobotCreationGUI();

                            gui.setVisible(true);
                        }


                        // BATTLE ARENA
                        else if (x >= 480 && x <= 765
                                && y >= 330 && y <= 800) {

                            BattleArenaGUI gui =
                                    new BattleArenaGUI();

                            gui.setVisible(true);
                        }


                        // WEAPONS
                        else if (x >= 785 && x <= 1070
                                && y >= 330 && y <= 800) {

                            WeaponGUI gui =
                                    new WeaponGUI();

                            gui.setVisible(true);
                        }


                        // BATTLE HISTORY
                        else if (x >= 1090 && x <= 1375
                                && y >= 330 && y <= 800) {

                            BattleHistoryGUI gui =
                                    new BattleHistoryGUI();

                            gui.setVisible(true);
                        }
                    }
                }
        );


        // ================= SET BACKGROUND =================

        setContentPane(backgroundPanel);
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            RobotArenaGUI gui =
                    new RobotArenaGUI();

            gui.setVisible(true);
        });
    }
}