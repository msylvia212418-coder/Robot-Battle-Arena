package com.robotarena;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RankingGUI extends JFrame {

    public RankingGUI() {

        setTitle("Robot Battle Arena - Ranking");
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(10, 10, 15));
        panel.setLayout(null);

        // ================= TITLE =================

        JLabel title = new JLabel("🏆 ROBOT RANKING");

        title.setForeground(new Color(255, 200, 60));
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBounds(80, 45, 600, 55);

        panel.add(title);

        JLabel subtitle = new JLabel(
                "THE STRONGEST ROBOTS IN THE ARENA"
        );

        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setBounds(85, 100, 500, 30);

        panel.add(subtitle);

        // ================= TABLE =================

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "RANK",
                                "ROBOT",
                                "PLAYER",
                                "WINS",
                                "LOSSES",
                                "POINTS"
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

        // Load real data from MySQL
        loadRanking(model);

        JTable table = new JTable(model);

        table.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );

        table.setRowHeight(55);

        table.setBackground(
                new Color(20, 20, 28)
        );

        table.setForeground(Color.WHITE);

        table.setGridColor(
                new Color(60, 60, 70)
        );

        table.setSelectionBackground(
                new Color(100, 70, 20)
        );

        table.setSelectionForeground(Color.WHITE);

        // ================= HEADER =================

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        table.getTableHeader().setBackground(
                new Color(35, 35, 45)
        );

        table.getTableHeader().setForeground(
                new Color(255, 200, 60)
        );

        table.getTableHeader().setPreferredSize(
                new Dimension(0, 50)
        );

        // ================= CENTER TABLE DATA =================

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

        // ================= SCROLL PANE =================

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(
                80,
                170,
                1370,
                350
        );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(255, 200, 60),
                        1
                )
        );

        panel.add(scrollPane);

        // ================= INFO =================

        JLabel info = new JLabel(
                "⚡ Rankings are based on wins and battle points"
        );

        info.setForeground(Color.GRAY);

        info.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        info.setBounds(
                85,
                550,
                600,
                30
        );

        panel.add(info);

        // ================= BACK BUTTON =================

        JButton backButton =
                new JButton(
                        "← BACK TO DASHBOARD"
                );

        backButton.setBounds(
                80,
                620,
                250,
                50
        );

        backButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        backButton.setForeground(Color.WHITE);

        backButton.setBackground(
                new Color(35, 35, 45)
        );

        backButton.setFocusPainted(false);

        panel.add(backButton);

        backButton.addActionListener(e -> {

            new DashboardGUI("Mano")
                    .setVisible(true);

            dispose();

        });

        setContentPane(panel);
    }

    // =====================================================
    // LOAD RANKING FROM MYSQL
    // =====================================================

    private void loadRanking(
            DefaultTableModel model) {

    	String sql =
    	        "SELECT r.robot_name, u.username, r.wins, r.losses "
    	        + "FROM robots r "
    	        + "JOIN users u ON r.user_id = u.user_id "
    	        + "ORDER BY r.wins DESC, r.losses ASC";
        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            int rank = 1;

            while (rs.next()) {

                String robotName =
                        rs.getString("robot_name");

                int wins =
                        rs.getInt("wins");

                int losses =
                        rs.getInt("losses");

                // Calculate points
                int points =
                        (wins * 100)
                        - (losses * 25);

                model.addRow(
                        new Object[]{
                                "#" + rank,
                                robotName,
                                rs.getString("username"),
                                wins,
                                losses,
                                points
                        }
                );

                rank++;
            }

            rs.close();

            ps.close();

            con.close();

            } catch (Exception e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to load ranking from database!"
                );

            }
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            new RankingGUI()
                    .setVisible(true);

        });
    }
}