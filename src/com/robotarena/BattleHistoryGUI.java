package com.robotarena;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class BattleHistoryGUI extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public BattleHistoryGUI() {

        setTitle("🏆 ROBOT ARENA - BATTLE HISTORY");
        setSize(1100, 650);
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
                "🏆  BATTLE HISTORY",
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
                "YOUR BATTLES • YOUR VICTORIES • YOUR LEGACY",
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


        // ================= TABLE =================

        model = new DefaultTableModel(
                new String[]{
                        "BATTLE ID",
                        "ROBOT 1",
                        "ROBOT 2",
                        "BATTLE DATE",
                        "WINNER"
                },
                0
        ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };


        table = new JTable(model);

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        table.setRowHeight(45);

        table.setBackground(
                new Color(15, 24, 40)
        );

        table.setForeground(
                new Color(225, 235, 245)
        );

        table.setGridColor(
                new Color(50, 70, 95)
        );

        table.setSelectionBackground(
                new Color(80, 55, 20)
        );

        table.setSelectionForeground(
                Color.WHITE
        );


        // ================= TABLE HEADER =================

        JTableHeader header =
                table.getTableHeader();

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        header.setBackground(
                new Color(30, 40, 60)
        );

        header.setForeground(
                new Color(255, 200, 70)
        );

        header.setPreferredSize(
                new Dimension(
                        header.getWidth(),
                        45
                )
        );


        // ================= CENTER ALIGN =================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0;
             i < table.getColumnCount();
             i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }


        // ================= COLUMN WIDTH =================

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(180);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(180);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(250);

        table.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(180);


        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(255, 190, 50),
                        2
                )
        );

        scrollPane.getViewport()
                .setBackground(
                        new Color(15, 24, 40)
                );


        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setOpaque(false);

        tablePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 40, 15, 40
                )
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );


        // ================= REFRESH BUTTON =================

        JButton refreshButton =
                new JButton(
                        "⟳  REFRESH HISTORY"
                );

        refreshButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        refreshButton.setForeground(
                new Color(5, 12, 20)
        );

        refreshButton.setBackground(
                new Color(255, 195, 55)
        );

        refreshButton.setFocusPainted(false);

        refreshButton.setBorder(
                BorderFactory.createEmptyBorder(
                        13, 30, 13, 30
                )
        );


        refreshButton.addActionListener(
                e -> loadHistory()
        );


        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setOpaque(false);

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 25, 10
                )
        );

        bottomPanel.add(
                refreshButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // ================= LOAD DATA =================

        loadHistory();


        setContentPane(mainPanel);
    }


    // =====================================================
    // LOAD BATTLE HISTORY
    // =====================================================

    private void loadHistory() {

        model.setRowCount(0);


        String sql =
                "SELECT b.battle_id, "
                + "r1.robot_name AS robot1, "
                + "r2.robot_name AS robot2, "
                + "b.battle_date, "
                + "w.robot_name AS winner "
                + "FROM battles b "
                + "JOIN robots r1 "
                + "ON b.robot1_id = r1.robot_id "
                + "JOIN robots r2 "
                + "ON b.robot2_id = r2.robot_id "
                + "JOIN robots w "
                + "ON b.winner_id = w.robot_id "
                + "ORDER BY b.battle_id DESC";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();


            while (rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt("battle_id"),

                                rs.getString("robot1"),

                                rs.getString("robot2"),

                                rs.getTimestamp(
                                        "battle_date"
                                ),

                                "🏆 "
                                + rs.getString("winner")
                        }
                );
            }


            rs.close();
            ps.close();
            con.close();


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load battle history.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BattleHistoryGUI gui =
                    new BattleHistoryGUI();

            gui.setVisible(true);
        });
    }
}