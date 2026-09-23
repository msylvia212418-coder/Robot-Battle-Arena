package com.robotarena;

import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardGUI extends JFrame {

    private String username;
    
    private int totalBattles = 0;
    private int totalWins = 0;
    private int totalPoints = 0;

    // ================= COLORS =================

    private final Color backgroundColor =
            new Color(8, 12, 22);

    private final Color sidebarColor =
            new Color(10, 16, 28, 235);

    private final Color cardColor =
            new Color(15, 24, 40, 235);

    private final Color gold =
            new Color(255, 195, 55);

    private final Color textColor =
            new Color(235, 240, 250);

    private final Color secondaryText =
            new Color(155, 170, 190);
    
    
    private void loadDashboardStats() {

        String sql =
                "SELECT "
                + "COUNT(DISTINCT b.battle_id) AS battles, "
                + "COALESCE(SUM(r.wins), 0) AS wins, "
                + "COALESCE(SUM(r.wins * 100 - r.losses * 25), 0) AS points "
                + "FROM robots r "
                + "LEFT JOIN battles b "
                + "ON r.robot_id = b.robot1_id "
                + "OR r.robot_id = b.robot2_id "
                + "WHERE r.user_id = ?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            // Find the logged-in user
            PreparedStatement userPs =
                    con.prepareStatement(
                            "SELECT user_id FROM users WHERE username = ?"
                    );

            userPs.setString(1, username);

            ResultSet userRs =
                    userPs.executeQuery();

            if (userRs.next()) {

                int userId =
                        userRs.getInt("user_id");

                ps.setInt(1, userId);

                ResultSet rs =
                        ps.executeQuery();

                if (rs.next()) {

                    totalBattles =
                            rs.getInt("battles");

                    totalWins =
                            rs.getInt("wins");

                    totalPoints =
                            rs.getInt("points");
                }

                rs.close();
            }

            userRs.close();
            userPs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public DashboardGUI(String username) {

        this.username = username;
        loadDashboardStats();

        setTitle(
                "Robot Battle Arena - Dashboard"
        );

        setSize(
                1536,
                864
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);


        // =================================================
        // MAIN BACKGROUND
        // =================================================

        BackgroundPanel mainPanel =
                new BackgroundPanel();

        mainPanel.setLayout(
                new BorderLayout()
        );


        // =================================================
        // SIDEBAR
        // =================================================

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(
                        245,
                        864
                )
        );

        sidebar.setBackground(
                sidebarColor
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );


        // =================================================
        // LOGO
        // =================================================

        JLabel logo =
                new JLabel(
                        "🤖  RBA"
                );

        logo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        28
                )
        );

        logo.setForeground(
                gold
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        sidebar.add(
                Box.createVerticalStrut(30)
        );

        sidebar.add(logo);

        sidebar.add(
                Box.createVerticalStrut(45)
        );


        // =================================================
        // MENU BUTTONS
        // =================================================

        JButton dashboardButton =
                createMenuButton(
                        "🏠  Dashboard"
                );

        JButton robotsButton =
                createMenuButton(
                        "🤖  My Robots"
                );

        JButton battleButton =
                createMenuButton(
                        "⚔  Battle"
                );
        
        battleButton.addActionListener(e -> {

            new BattleArenaGUI().setVisible(true);

            dispose();

        });

        JButton weaponsButton =
                createMenuButton(
                        "🔫  Weapons"
                );
        
        weaponsButton.addActionListener(e -> {

            new WeaponGUI().setVisible(true);

            dispose();

        });

        JButton historyButton =
                createMenuButton(
                        "📜  History"
                );
        
        historyButton.addActionListener(e -> {

            new HistoryGUI().setVisible(true);

            dispose();

        });

        JButton rankingButton =
                createMenuButton(
                        "🏆  Ranking"
                );
        
        rankingButton.addActionListener(e -> {

            new RankingGUI().setVisible(true);

            dispose();

        });

        JButton settingsButton =
                createMenuButton(
                        "⚙  Settings"
                );
        
        settingsButton.addActionListener(e -> {

            new SettingsGUI().setVisible(true);

            dispose();

        });


        sidebar.add(
                dashboardButton
        );

        sidebar.add(
                robotsButton
        );

        sidebar.add(
                battleButton
        );

        sidebar.add(
                weaponsButton
        );

        sidebar.add(
                historyButton
        );

        sidebar.add(
                rankingButton
        );

        sidebar.add(
                settingsButton
        );
        
        robotsButton.addActionListener(e -> {

            new MyRobotsGUI(username).setVisible(true);

            dispose();

        });


        // =================================================
        // SPACE
        // =================================================

        sidebar.add(
                Box.createVerticalGlue()
        );


        // =================================================
        // LOGOUT
        // =================================================

        JButton logoutButton =
                createMenuButton(
                        "🚪  Logout"
                );

        sidebar.add(
                logoutButton
        );

        sidebar.add(
                Box.createVerticalStrut(25)
        );


        // =================================================
        // CONTENT PANEL
        // =================================================

        JPanel content =
                new JPanel(
                        new BorderLayout()
                );

        content.setOpaque(
                false
        );


        // =================================================
        // TOP BAR
        // =================================================

        JPanel topBar =
                new JPanel(
                        new BorderLayout()
                );

        topBar.setPreferredSize(
                new Dimension(
                        0,
                        90
                )
        );

        topBar.setOpaque(
                false
        );

        topBar.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        10,
                        30
                )
        );


        // PAGE TITLE

        JLabel pageTitle =
                new JLabel(
                        "COMMAND CENTER"
                );

        pageTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        pageTitle.setForeground(
                textColor
        );


        // =================================================
        // USER PANEL
        // =================================================

        JPanel userPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                5
                        )
                );

        userPanel.setOpaque(
                false
        );


        JLabel userIcon =
                new JLabel(
                        "👤"
                );

        userIcon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        28
                )
        );


        JLabel userLabel =
                new JLabel(
                        username
                );

        userLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        userLabel.setForeground(
                textColor
        );


        userPanel.add(
                userIcon
        );

        userPanel.add(
                userLabel
        );


        topBar.add(
                pageTitle,
                BorderLayout.WEST
        );

        topBar.add(
                userPanel,
                BorderLayout.EAST
        );


        content.add(
                topBar,
                BorderLayout.NORTH
        );


        // =================================================
        // DASHBOARD AREA
        // =================================================

        JPanel dashboard =
                new JPanel();


        dashboard.setOpaque(
                false
        );


        dashboard.setLayout(
                new BoxLayout(
                        dashboard,
                        BoxLayout.Y_AXIS
                )
        );


        dashboard.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        30,
                        30,
                        30
                )
        );


        // =================================================
        // WELCOME
        // =================================================

        JLabel welcome =
                new JLabel(
                        "WELCOME BACK, "
                        + username.toUpperCase()
                        + " ⚡"
                );

        welcome.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        welcome.setForeground(
                gold
        );


        dashboard.add(
                welcome
        );


        dashboard.add(
                Box.createVerticalStrut(
                        8
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Prepare your robots. "
                        + "Enter the arena. "
                        + "Become the champion."
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                secondaryText
        );


        dashboard.add(
                subtitle
        );


        dashboard.add(
                Box.createVerticalStrut(
                        30
                )
        );


        // =================================================
        // STAT CARDS
        // =================================================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        statsPanel.setOpaque(
                false
        );


        statsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        140
                )
        );


        statsPanel.add(
                createStatCard(
                        "⚔",
                        "BATTLES",
                        String.valueOf(totalBattles)
                )
        );

        statsPanel.add(
                createStatCard(
                        "🏆",
                        "WINS",
                        String.valueOf(totalWins)
                )
        );

        statsPanel.add(
                createStatCard(
                        "⭐",
                        "POINTS",
                        String.valueOf(totalPoints)
                )
        );


        dashboard.add(
                statsPanel
        );


        dashboard.add(
                Box.createVerticalStrut(
                        30
                )
        );


        // =================================================
        // QUICK ACTION TITLE
        // =================================================

        JLabel quickTitle =
                new JLabel(
                        "QUICK ACTIONS"
                );

        quickTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        quickTitle.setForeground(
                textColor
        );


        dashboard.add(
                quickTitle
        );


        dashboard.add(
                Box.createVerticalStrut(
                        15
                )
        );


        // =================================================
        // ACTION CARDS
        // =================================================

        JPanel actions =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        actions.setOpaque(
                false
        );


        actions.add(
                createActionCard(
                        "🤖",
                        "MY ROBOTS",
                        "Manage and upgrade your robots."
                )
        );


        actions.add(
                createActionCard(
                        "⚔",
                        "BATTLE ARENA",
                        "Choose your opponent and fight."
                )
        );


        actions.add(
                createActionCard(
                        "🔫",
                        "WEAPONS",
                        "Equip powerful weapons."
                )
        );


        dashboard.add(
                actions
        );


        dashboard.add(
                Box.createVerticalGlue()
        );


        // =================================================
        // ADD CONTENT
        // =================================================

        content.add(
                dashboard,
                BorderLayout.CENTER
        );


        // =================================================
        // ADD SIDEBAR + CONTENT
        // =================================================

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        mainPanel.add(
                content,
                BorderLayout.CENTER
        );


        setContentPane(
                mainPanel
        );


        // =================================================
        // BUTTON ACTIONS
        // =================================================


        dashboardButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "You are already on Dashboard."
                    );

                }
        );


        robotsButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "My Robots screen will be connected next."
                    );

                }
        );


        battleButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Battle Arena will be connected next."
                    );

                }
        );


        weaponsButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Weapons screen will be connected next."
                    );

                }
        );


        historyButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Battle History will be connected next."
                    );

                }
        );


        rankingButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Leaderboard will be connected next."
                    );

                }
        );


        settingsButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Settings will be added later."
                    );

                }
        );


        // =================================================
        // LOGOUT
        // =================================================

        logoutButton.addActionListener(
                e -> {

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Do you want to logout?",
                                    "Logout",
                                    JOptionPane.YES_NO_OPTION
                            );


                    if (choice ==
                            JOptionPane.YES_OPTION) {

                        new LoginGUI()
                                .setVisible(true);

                        dispose();
                    }

                }
        );
    }


    // =====================================================
    // SIDEBAR BUTTON
    // =====================================================

    private JButton createMenuButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );


        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );


        button.setPreferredSize(
                new Dimension(
                        220,
                        55
                )
        );


        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );


        button.setForeground(
                new Color(
                        200,
                        210,
                        225
                )
        );


        button.setBackground(
                new Color(
                        10,
                        16,
                        28,
                        235
                )
        );


        button.setFocusPainted(
                false
        );


        button.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        25,
                        0,
                        10
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =====================================================
    // STAT CARD
    // =====================================================

    private JPanel createStatCard(
            String icon,
            String title,
            String value) {

        JPanel card =
                new JPanel();


        card.setBackground(
                cardColor
        );


        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );


        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        70,
                                        85,
                                        105
                                ),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                18,
                                22,
                                18,
                                22
                        )
                )
        );


        JLabel iconLabel =
                new JLabel(
                        icon
                );


        iconLabel.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        28
                )
        );


        iconLabel.setForeground(
                gold
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );


        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );


        titleLabel.setForeground(
                secondaryText
        );


        JLabel valueLabel =
                new JLabel(
                        value
                );


        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );


        valueLabel.setForeground(
                textColor
        );


        card.add(
                iconLabel
        );


        card.add(
                Box.createVerticalStrut(
                        5
                )
        );


        card.add(
                titleLabel
        );


        card.add(
                Box.createVerticalStrut(
                        2
                )
        );


        card.add(
                valueLabel
        );


        return card;
    }


    // =====================================================
    // ACTION CARD
    // =====================================================

    private JPanel createActionCard(
            String icon,
            String title,
            String description) {

        JPanel card =
                new JPanel();


        card.setBackground(
                cardColor
        );


        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        70,
                                        85,
                                        105
                                ),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );


        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel iconLabel =
                new JLabel(
                        icon
                );


        iconLabel.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        35
                )
        );


        iconLabel.setForeground(
                gold
        );


        card.add(
                iconLabel
        );


        card.add(
                Box.createVerticalStrut(
                        10
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );


        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );


        titleLabel.setForeground(
                textColor
        );


        card.add(
                titleLabel
        );


        card.add(
                Box.createVerticalStrut(
                        8
                )
        );


        JLabel descriptionLabel =
                new JLabel(
                        "<html>"
                        + description
                        + "</html>"
                );


        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        descriptionLabel.setForeground(
                secondaryText
        );


        card.add(
                descriptionLabel
        );


        return card;
    }


    // =====================================================
    // CINEMATIC BACKGROUND
    // =====================================================

    class BackgroundPanel
            extends JPanel {

        private Image backgroundImage;


        public BackgroundPanel() {

            backgroundImage =
                    new ImageIcon(
                            getClass()
                                    .getResource(
                                            "/resources/robot_dashboard_bg.png"
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
                    backgroundImage.getWidth(
                            this
                    );


            int imageHeight =
                    backgroundImage.getHeight(
                            this
                    );


            // =========================================
            // SCALE IMAGE TO COVER FULL SCREEN
            // =========================================

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


            // =========================================
            // DARK CINEMATIC OVERLAY
            // =========================================

            g2.setColor(
                    new Color(
                            0,
                            0,
                            0,
                            145
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


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    new DashboardGUI(
                            "Mano"
                    ).setVisible(true);

                }
        );
    }
}