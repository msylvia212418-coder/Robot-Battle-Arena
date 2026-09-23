package com.robotarena;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Random;

public class BattleArenaGUI extends JFrame {

    // =========================================================
    // ROBOTS
    // =========================================================

    private Robot blueRobot;
    private Robot redRobot;

    // =========================================================
    // ROBOT IMAGE LABELS
    // =========================================================

    private JLabel blueRobotImage;
    private JLabel redRobotImage;

    // =========================================================
    // GAME VARIABLES
    // =========================================================

    private boolean playerTurn = true;
    private boolean battleFinished = false;
    private boolean defending = false;

    private int blueEnergy = 100;
    private int redEnergy = 100;

    private int blueDamage = 0;
    private int redDamage = 0;

    private int blueCombo = 0;
    private int redCombo = 0;

    private int round = 1;

    private Random random = new Random();

    // =========================================================
    // HUD
    // =========================================================

    private JProgressBar blueHPBar;
    private JProgressBar redHPBar;

    private JProgressBar blueEnergyBar;
    private JProgressBar redEnergyBar;

    private JLabel blueDamageLabel;
    private JLabel redDamageLabel;

    private JLabel blueComboLabel;
    private JLabel redComboLabel;

    private JLabel roundLabel;
    private JLabel turnLabel;

    private JTextPane combatFeed;

    private JButton attackButton;
    private JButton specialButton;
    private JButton defendButton;
    private JButton surrenderButton;

    // =========================================================
    // EFFECTS
    // =========================================================

    private JLabel attackEffect;
    private JLabel damagePopup;
    
    private JLabel hitEffect;
    private JLabel hitDamagePopup;

    // =========================================================
    // ROBOT POSITIONS
    // =========================================================

    private final int blueStartX = 120;
    private final int blueStartY = 300;

    private final int redStartX = 1110;
    private final int redStartY = 300;

    // =========================================================
    // ROBOT SIZE
    // =========================================================

    private final int robotWidth = 300;
    private final int robotHeight = 380;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BattleArenaGUI() {

        setTitle(
                "Robot Battle Arena - Battle Arena"
        );

        setSize(
                1536,
                864
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        // =====================================================
        // ROBOT DATA
        // =====================================================

        blueRobot = new Robot(
                2,
                1,
                "Blaze",
                "Attack",
                100,
                80,
                50,
                70,
                5,
                0,
                0
        );

        redRobot = new Robot(
                3,
                1,
                "Titan",
                "Defense",
                100,
                60,
                70,
                40,
                5,
                0,
                0
        );

        // =====================================================
        // BACKGROUND
        // =====================================================

        BackgroundPanel background =
                new BackgroundPanel();

        background.setLayout(null);

        setContentPane(background);

        // =====================================================
        // LOAD ROBOTS
        // =====================================================

        loadRobotImages();

        // =====================================================
        // BLUE ROBOT
        // =====================================================

        blueRobotImage =
                new JLabel(
                        blueRobotIcon
                );

        blueRobotImage.setOpaque(false);

        blueRobotImage.setBounds(
                blueStartX,
                blueStartY,
                robotWidth,
                robotHeight
        );

        background.add(
                blueRobotImage
        );

        // =====================================================
        // RED ROBOT
        // =====================================================

        redRobotImage =
                new JLabel(
                        redRobotIcon
                );

        redRobotImage.setOpaque(false);

        redRobotImage.setBounds(
                redStartX,
                redStartY,
                robotWidth,
                robotHeight
        );

        background.add(
                redRobotImage
        );

        // =====================================================
        // HUD
        // =====================================================

        createPlayerHUD(
                background,
                true
        );

        createPlayerHUD(
                background,
                false
        );

        // =====================================================
        // ROUND
        // =====================================================

        roundLabel =
                new JLabel(
                        "ROUND 01",
                        SwingConstants.CENTER
                );

        roundLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        roundLabel.setForeground(
                Color.WHITE
        );

        roundLabel.setBounds(
                618,
                125,
                300,
                40
        );

        background.add(
                roundLabel
        );

        // =====================================================
        // VS
        // =====================================================

        JLabel vsLabel =
                new JLabel(
                        "VS",
                        SwingConstants.CENTER
                );

        vsLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        55
                )
        );

        vsLabel.setForeground(
                new Color(
                        255,
                        190,
                        60
                )
        );

        vsLabel.setBounds(
                650,
                165,
                240,
                70
        );

        background.add(
                vsLabel
        );

        // =====================================================
        // TURN
        // =====================================================

        turnLabel =
                new JLabel(
                        "YOUR TURN",
                        SwingConstants.CENTER
                );

        turnLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        turnLabel.setForeground(
                new Color(
                        80,
                        210,
                        255
                )
        );

        turnLabel.setBounds(
                600,
                235,
                340,
                40
        );

        background.add(
                turnLabel
        );

        // =====================================================
        // ATTACK EFFECT
        // =====================================================

        attackEffect =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        attackEffect.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        65
                )
        );

        attackEffect.setVisible(
                false
        );

        attackEffect.setBounds(
                640,
                330,
                250,
                100
        );

        background.add(
                attackEffect
        );

        // =====================================================
        // DAMAGE POPUP
        // =====================================================

        damagePopup =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        damagePopup.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        damagePopup.setForeground(
                new Color(
                        255,
                        70,
                        70
                )
        );

        damagePopup.setVisible(
                false
        );

        damagePopup.setBounds(
                1050,
                300,
                250,
                60
        );

        background.add(
                damagePopup
        );
        
     // =====================================================
     // HIT EFFECT
     // =====================================================

     hitEffect = new JLabel(
             "✦",
             SwingConstants.CENTER
     );

     hitEffect.setFont(
             new Font(
                     "Segoe UI",
                     Font.BOLD,
                     90
             )
     );

     hitEffect.setForeground(
             new Color(
                     255,
                     210,
                     50
             )
     );

     hitEffect.setVisible(false);

     hitEffect.setBounds(
             760,
             300,
             180,
             120
     );

     background.add(
             hitEffect
     );


     // =====================================================
     // FLOATING DAMAGE
     // =====================================================

     hitDamagePopup = new JLabel(
             "",
             SwingConstants.CENTER
     );

     hitDamagePopup.setFont(
             new Font(
                     "Segoe UI",
                     Font.BOLD,
                     30
             )
     );

     hitDamagePopup.setForeground(
             new Color(
                     255,
                     80,
                     80
             )
     );

     hitDamagePopup.setVisible(false);

     hitDamagePopup.setBounds(
             760,
             370,
             180,
             50
     );

     background.add(
             hitDamagePopup
     );

        // =====================================================
        // COMBAT FEED TITLE
        // =====================================================

        JLabel feedTitle =
                new JLabel(
                        "LIVE COMBAT FEED"
                );

        feedTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        feedTitle.setForeground(
                Color.WHITE
        );

        feedTitle.setBounds(
                570,
                470,
                300,
                30
        );

        background.add(
                feedTitle
        );

        // =====================================================
        // COMBAT FEED
        // =====================================================

        combatFeed =
                new JTextPane();

        combatFeed.setEditable(
                false
        );

        combatFeed.setBackground(
                new Color(
                        5,
                        10,
                        18
                )
        );

        combatFeed.setForeground(
                Color.WHITE
        );

        combatFeed.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        combatFeed
                );

        scrollPane.setBounds(
                570,
                505,
                400,
                145
        );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                80,
                                180,
                                255
                        ),
                        1
                )
        );

        background.add(
                scrollPane
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        attackButton =
                createGameButton(
                        "⚔ ATTACK",
                        470,
                        700
                );

        specialButton =
                createGameButton(
                        "⚡ SPECIAL",
                        670,
                        700
                );

        defendButton =
                createGameButton(
                        "🛡 DEFEND",
                        870,
                        700
                );

        surrenderButton =
                createGameButton(
                        "🏳 SURRENDER",
                        1070,
                        700
                );

        background.add(
                attackButton
        );

        background.add(
                specialButton
        );

        background.add(
                defendButton
        );

        background.add(
                surrenderButton
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        attackButton.addActionListener(
                e -> attack()
        );

        specialButton.addActionListener(
                e -> specialAttack()
        );

        defendButton.addActionListener(
                e -> defend()
        );

        surrenderButton.addActionListener(
                e -> surrender()
        );

        // =====================================================
        // INITIAL HUD
        // =====================================================

        updateHUD();

        addFeed(
                "SYSTEM",
                "BATTLE STARTED!",
                new Color(
                        255,
                        200,
                        70
                )
        );

        addFeed(
                "SYSTEM",
                "BLAZE VS TITAN",
                Color.WHITE
        );

        addFeed(
                "SYSTEM",
                "YOUR TURN",
                new Color(
                        80,
                        210,
                        255
                )
        );

        setVisible(
                true
        );
    }

    // =========================================================
    // LOAD ROBOT IMAGES
    // =========================================================

    private ImageIcon blueRobotIcon;
    private ImageIcon redRobotIcon;

    private void loadRobotImages() {

        // -----------------------------------------------------
        // BLUE ROBOT
        // -----------------------------------------------------

        ImageIcon blueOriginal =
                new ImageIcon(
                        getClass().getResource(
                                "/resources/blue_robot.png"
                        )
                );

        Image blueImage =
                blueOriginal
                        .getImage()
                        .getScaledInstance(
                                robotWidth,
                                robotHeight,
                                Image.SCALE_SMOOTH
                        );

        blueRobotIcon =
                new ImageIcon(
                        blueImage
                );

        // -----------------------------------------------------
        // RED ROBOT
        // -----------------------------------------------------

        ImageIcon redOriginal =
                new ImageIcon(
                        getClass().getResource(
                                "/resources/red_robot.png"
                        )
                );

        Image redImage =
                redOriginal
                        .getImage()
                        .getScaledInstance(
                                robotWidth,
                                robotHeight,
                                Image.SCALE_SMOOTH
                        );

        redRobotIcon =
                new ImageIcon(
                        redImage
                );
    }

    // =========================================================
    // PLAYER HUD
    // =========================================================

    private void createPlayerHUD(
            JPanel background,
            boolean blue
    ) {

        int x;

        if (blue) {
            x = 40;
        } else {
            x = 1060;
        }

        JPanel panel =
                new JPanel();

        panel.setLayout(
                null
        );

        panel.setBackground(
                new Color(
                        5,
                        10,
                        18,
                        220
                )
        );

        panel.setBorder(
                BorderFactory.createLineBorder(
                        blue
                                ? new Color(
                                        50,
                                        170,
                                        255
                                )
                                : new Color(
                                        255,
                                        70,
                                        70
                                ),
                        2
                )
        );

        panel.setBounds(
                x,
                35,
                430,
                150
        );

        background.add(
                panel
        );

        // =====================================================
        // PLAYER
        // =====================================================

        JLabel playerLabel =
                new JLabel(
                        blue
                                ? "BLUE PLAYER"
                                : "RED PLAYER"
                );

        playerLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        playerLabel.setForeground(
                blue
                        ? new Color(
                                80,
                                200,
                                255
                        )
                        : new Color(
                                255,
                                90,
                                90
                        )
        );

        playerLabel.setBounds(
                15,
                10,
                180,
                25
        );

        panel.add(
                playerLabel
        );

        // =====================================================
        // NAME
        // =====================================================

        JLabel nameLabel =
                new JLabel(
                        blue
                                ? "BLAZE"
                                : "TITAN"
                );

        nameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        nameLabel.setForeground(
                Color.WHITE
        );

        nameLabel.setBounds(
                15,
                35,
                180,
                35
        );

        panel.add(
                nameLabel
        );

        // =====================================================
        // LEVEL
        // =====================================================

        JLabel levelLabel =
                new JLabel(
                        "LEVEL 05"
                );

        levelLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        levelLabel.setForeground(
                new Color(
                        180,
                        180,
                        190
                )
        );

        levelLabel.setBounds(
                15,
                70,
                100,
                        20
        );

        panel.add(
                levelLabel
        );

        // =====================================================
        // HP
        // =====================================================

        JProgressBar hpBar =
                new JProgressBar(
                        0,
                        100
                );

        hpBar.setValue(
                100
        );

        hpBar.setStringPainted(
                true
        );

        hpBar.setString(
                "HP 100 / 100"
        );

        hpBar.setBounds(
                130,
                45,
                270,
                25
        );

        hpBar.setForeground(
                blue
                        ? new Color(
                                40,
                                180,
                                255
                        )
                        : new Color(
                                255,
                                70,
                                70
                        )
        );

        hpBar.setBackground(
                new Color(
                        40,
                        40,
                        45
                )
        );

        panel.add(
                hpBar
        );

        // =====================================================
        // ENERGY
        // =====================================================

        JProgressBar energyBar =
                new JProgressBar(
                        0,
                        100
                );

        energyBar.setValue(
                100
        );

        energyBar.setStringPainted(
                true
        );

        energyBar.setString(
                "ENERGY 100%"
        );

        energyBar.setBounds(
                130,
                80,
                270,
                20
        );

        energyBar.setForeground(
                new Color(
                        255,
                        190,
                        50
                )
        );

        energyBar.setBackground(
                new Color(
                        40,
                        40,
                        45
                )
        );

        panel.add(
                energyBar
        );

        // =====================================================
        // DAMAGE
        // =====================================================

        JLabel damageLabel =
                new JLabel(
                        "DMG 0"
                );

        damageLabel.setFont(
                new Font(
                        "Consolas",
                        Font.BOLD,
                        12
                )
        );

        damageLabel.setForeground(
                Color.WHITE
        );

        damageLabel.setBounds(
                130,
                108,
                90,
                20
        );

        panel.add(
                damageLabel
        );

        // =====================================================
        // COMBO
        // =====================================================

        JLabel comboLabel =
                new JLabel(
                        "COMBO x0"
                );

        comboLabel.setFont(
                new Font(
                        "Consolas",
                        Font.BOLD,
                        12
                )
        );

        comboLabel.setForeground(
                new Color(
                        255,
                        200,
                        70
                )
        );

        comboLabel.setBounds(
                230,
                108,
                100,
                20
        );

        panel.add(
                comboLabel
        );

        // =====================================================
        // SAVE REFERENCES
        // =====================================================

        if (blue) {

            blueHPBar =
                    hpBar;

            blueEnergyBar =
                    energyBar;

            blueDamageLabel =
                    damageLabel;

            blueComboLabel =
                    comboLabel;

        } else {

            redHPBar =
                    hpBar;

            redEnergyBar =
                    energyBar;

            redDamageLabel =
                    damageLabel;

            redComboLabel =
                    comboLabel;
        }
    }

    // =========================================================
    // GAME BUTTON
    // =========================================================

    private JButton createGameButton(
            String text,
            int x,
            int y
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                new Color(
                        15,
                        25,
                        40
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                80,
                                180,
                                255
                        ),
                        2
                )
        );

        button.setBounds(
                x,
                y,
                180,
                55
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // ATTACK
    // =========================================================

    private void attack() {

        if (
                battleFinished
                || !playerTurn
        ) {
            return;
        }

        playerTurn =
                false;

        disableButtons();

        turnLabel.setText(
                "BLAZE ATTACKING..."
        );

        turnLabel.setForeground(
                new Color(
                        80,
                        210,
                        255
                )
        );

        // -----------------------------------------------------
        // MOVE BLUE ROBOT FORWARD
        // -----------------------------------------------------

        animateRobot(
                blueRobotImage,
                blueStartX,
                blueStartX + 250,
                450,
                () -> {

                    int damage =
                            calculateDamage(
                                    blueRobot,
                                    redRobot
                            );

                    redRobot.setHealth(
                            Math.max(
                                    0,
                                    redRobot.getHealth()
                                            - damage
                            )
                    );

                    blueDamage +=
                            damage;

                    blueCombo++;

                    blueEnergy =
                            Math.min(
                                    100,
                                    blueEnergy + 5
                            );

                    // -------------------------------------------------
                    // IMPACT
                    // -------------------------------------------------

                    attackEffect.setText(
                            "💥"
                    );

                    attackEffect.setForeground(
                            new Color(
                                    80,
                                    210,
                                    255
                            )
                    );

                    attackEffect.setVisible(
                            true
                    );

                    damagePopup.setText(
                            "-" +
                            damage +
                            " HP"
                    );

                    damagePopup.setForeground(
                            new Color(
                                    255,
                                    70,
                                    70
                            )
                    );

                    damagePopup.setVisible(
                            true
                    );
                    
                 // SHAKE THE RED ROBOT
                    shakeRobot(
                            redRobotImage,
                            redStartX,
                            redStartY
                    );
                    
                 // ATTACK IMPACT FLASH
                    attackEffect.setText("⚡💥");
                    attackEffect.setFont(
                            new Font(
                                    "Segoe UI Emoji",
                                    Font.BOLD,
                                    70
                            )
                    );

                    attackEffect.setVisible(true);

                    Timer impactTimer = new Timer(
                            250,
                            e -> {
                                attackEffect.setVisible(false);
                            }
                    );

                    impactTimer.setRepeats(false);
                    impactTimer.start();

                    addFeed(
                            "BLAZE",
                            "ATTACK  -" +
                            damage +
                            " HP",
                            new Color(
                                    80,
                                    200,
                                    255
                            )
                    );

                    updateHUD();

                    // -------------------------------------------------
                    // RETURN BLUE ROBOT
                    // -------------------------------------------------

                    Timer returnTimer =
                            new Timer(
                                    700,
                                    e -> {

                                        attackEffect
                                                .setVisible(
                                                        false
                                                );

                                        damagePopup
                                                .setVisible(
                                                        false
                                                );

                                        animateRobot(
                                                blueRobotImage,
                                                blueRobotImage
                                                        .getX(),
                                                blueStartX,
                                                350,
                                                () -> {

                                                    if (
                                                            checkWinner()
                                                    ) {
                                                        return;
                                                    }

                                                    opponentMove();
                                                }
                                        );
                                    }
                            );

                    returnTimer.setRepeats(
                            false
                    );

                    returnTimer.start();
                }
        );
    }

    // =========================================================
    // SPECIAL ATTACK
    // =========================================================

    private void specialAttack() {

        if (
                battleFinished
                || !playerTurn
        ) {
            return;
        }

        if (
                blueEnergy < 30
        ) {

            addFeed(
                    "SYSTEM",
                    "NOT ENOUGH ENERGY!",
                    new Color(
                            255,
                            90,
                            90
                    )
            );

            return;
        }

        playerTurn =
                false;

        disableButtons();

        turnLabel.setText(
                "⚡ SPECIAL ATTACK!"
        );

        turnLabel.setForeground(
                new Color(
                        255,
                        210,
                        60
                )
        );

        animateRobot(
                blueRobotImage,
                blueStartX,
                blueStartX + 280,
                350,
                () -> {

                    int damage =
                            30 +
                            random.nextInt(
                                    21
                            );

                    redRobot.setHealth(
                            Math.max(
                                    0,
                                    redRobot.getHealth()
                                            - damage
                            )
                    );

                    blueDamage +=
                            damage;

                    blueCombo +=
                            2;

                    blueEnergy -=
                            30;

                    // -------------------------------------------------
                    // SPECIAL EFFECT
                    // -------------------------------------------------

                    attackEffect.setText(
                            "⚡💥"
                    );

                    attackEffect.setFont(
                            new Font(
                                    "Segoe UI Emoji",
                                    Font.BOLD,
                                    60
                            )
                    );

                    attackEffect.setForeground(
                            new Color(
                                    255,
                                    200,
                                    40
                            )
                    );

                    attackEffect.setVisible(
                            true
                    );

                    damagePopup.setText(
                            "CRITICAL  -" +
                            damage +
                            " HP"
                    );

                    damagePopup.setForeground(
                            new Color(
                                    255,
                                    200,
                                    40
                            )
                    );

                    damagePopup.setVisible(
                            true
                    );

                    addFeed(
                            "BLAZE",
                            "SPECIAL CRITICAL  -" +
                            damage +
                            " HP",
                            new Color(
                                    255,
                                    200,
                                    40
                            )
                    );

                    updateHUD();

                    Timer returnTimer =
                            new Timer(
                                    900,
                                    e -> {

                                        attackEffect
                                                .setVisible(
                                                        false
                                                );

                                        damagePopup
                                                .setVisible(
                                                        false
                                                );

                                        animateRobot(
                                                blueRobotImage,
                                                blueRobotImage
                                                        .getX(),
                                                blueStartX,
                                                350,
                                                () -> {

                                                    if (
                                                            checkWinner()
                                                    ) {
                                                        return;
                                                    }

                                                    opponentMove();
                                                }
                                        );
                                    }
                            );

                    returnTimer.setRepeats(
                            false
                    );

                    returnTimer.start();
                }
        );
    }

    // =========================================================
    // DEFEND
    // =========================================================

    private void defend() {

        if (
                battleFinished
                || !playerTurn
        ) {
            return;
        }

        defending =
                true;

        blueCombo =
                0;

        addFeed(
                "BLAZE",
                "DEFENSE MODE ACTIVATED",
                new Color(
                        100,
                        220,
                        255
                )
        );

        turnLabel.setText(
                "🛡 DEFENSE MODE"
        );

        turnLabel.setForeground(
                new Color(
                        100,
                        220,
                        255
                )
        );

        updateHUD();

        playerTurn =
                false;

        disableButtons();

        Timer timer =
                new Timer(
                        700,
                        e -> {

                            if (
                                    checkWinner()
                            ) {
                                return;
                            }

                            opponentMove();
                        }
                );

        timer.setRepeats(
                false
        );

        timer.start();
    }

    // =========================================================
    // OPPONENT MOVE
    // =========================================================

    private void opponentMove() {

        if (
                battleFinished
        ) {
            return;
        }

        turnLabel.setText(
                "TITAN'S TURN..."
        );

        turnLabel.setForeground(
                new Color(
                        255,
                        90,
                        90
                )
        );

        // -----------------------------------------------------
        // RED ROBOT MOVES TOWARD BLUE
        // -----------------------------------------------------

        animateRobot(
                redRobotImage,
                redStartX,
                redStartX - 250,
                450,
                () -> {

                    int damage =
                            calculateDamage(
                                    redRobot,
                                    blueRobot
                            );

                    if (
                            defending
                    ) {

                        damage =
                                Math.max(
                                        1,
                                        damage / 2
                                );

                        defending =
                                false;

                        addFeed(
                                "BLAZE",
                                "DEFENSE BLOCKED DAMAGE",
                                new Color(
                                        100,
                                        220,
                                        255
                                )
                        );
                    }

                    blueRobot.setHealth(
                            Math.max(
                                    0,
                                    blueRobot.getHealth()
                                            - damage
                            )
                    );
                    
                    shakeRobot(
                            blueRobotImage,
                            blueStartX,
                            blueStartY
                    );

                    redDamage +=
                            damage;

                    redCombo++;

                    redEnergy =
                            Math.min(
                                    100,
                                    redEnergy + 5
                            );

                    // -------------------------------------------------
                    // RED ATTACK EFFECT
                    // -------------------------------------------------

                    attackEffect.setText(
                            "💥"
                    );

                    attackEffect.setForeground(
                            new Color(
                                    255,
                                    70,
                                    70
                            )
                    );

                    attackEffect.setVisible(
                            true
                    );

                    damagePopup.setText(
                            "-" +
                            damage +
                            " HP"
                    );

                    damagePopup.setForeground(
                            new Color(
                                    255,
                                    80,
                                    80
                            )
                    );

                    damagePopup.setVisible(
                            true
                    );

                    addFeed(
                            "TITAN",
                            "ATTACK  -" +
                            damage +
                            " HP",
                            new Color(
                                    255,
                                    80,
                                    80
                            )
                    );

                    updateHUD();

                    // -------------------------------------------------
                    // RETURN RED ROBOT
                    // -------------------------------------------------

                    Timer returnTimer =
                            new Timer(
                                    700,
                                    e -> {

                                        attackEffect
                                                .setVisible(
                                                        false
                                                );

                                        damagePopup
                                                .setVisible(
                                                        false
                                                );

                                        animateRobot(
                                                redRobotImage,
                                                redRobotImage
                                                        .getX(),
                                                redStartX,
                                                350,
                                                () -> {

                                                    if (
                                                            checkWinner()
                                                    ) {
                                                        return;
                                                    }

                                                    round++;

                                                    roundLabel
                                                            .setText(
                                                                    String.format(
                                                                            "ROUND %02d",
                                                                            round
                                                                    )
                                                            );

                                                    playerTurn =
                                                            true;

                                                    turnLabel
                                                            .setText(
                                                                    "YOUR TURN"
                                                            );

                                                    turnLabel
                                                            .setForeground(
                                                                    new Color(
                                                                            80,
                                                                            210,
                                                                            255
                                                                    )
                                                            );

                                                    enableButtons();
                                                }
                                        );
                                    }
                            );

                    returnTimer.setRepeats(
                            false
                    );

                    returnTimer.start();
                }
        );
    }

    // =========================================================
    // ROBOT ANIMATION
    // =========================================================

    private void animateRobot(

            JLabel robot,

            int startX,

            int targetX,

            int duration,

            Runnable afterAnimation

    ) {

        int actualStartX = robot.getX();

        int distance =
                targetX - actualStartX;

        int steps = 25;

        int delay =
                Math.max(
                        10,
                        duration / steps
                );

        final int[] currentStep = {0};

        Timer timer =
                new Timer(
                        delay,
                        null
                );

        timer.addActionListener(
                e -> {

                    currentStep[0]++;

                    double progress =
                            (double) currentStep[0]
                            / steps;

                    if (progress > 1) {
                        progress = 1;
                    }

                    double smoothProgress =
                            progress
                            * progress
                            * (3 - 2 * progress);

                    int newX =
                            actualStartX
                            + (int)
                            (
                                    distance
                                    * smoothProgress
                            );

                    robot.setLocation(
                            newX,
                            robot.getY()
                    );

                    robot.repaint();

                    if (currentStep[0] >= steps) {

                        robot.setLocation(
                                targetX,
                                robot.getY()
                        );

                        timer.stop();

                        if (afterAnimation != null) {

                            afterAnimation.run();

                        }
                    }
                }
        );

        timer.start();
    }
    // =========================================================
    // DAMAGE CALCULATION
    // =========================================================

    private int calculateDamage(
            Robot attacker,
            Robot defender
    ) {

        int baseDamage =
                attacker.getAttackPower()
                -
                defender.getDefensePower();

        if (
                baseDamage < 5
        ) {
            baseDamage = 5;
        }

        int randomDamage =
                random.nextInt(
                        21
                ) - 10;

        int damage =
                baseDamage
                +
                randomDamage;

        if (
                damage < 1
        ) {
            damage = 1;
        }

        return damage;
    }

    // =========================================================
    // WINNER CHECK
    // =========================================================

    private boolean checkWinner() {

        if (
                blueRobot.getHealth() <= 0
                ||
                redRobot.getHealth() <= 0
        ) {

            battleFinished =
                    true;

            disableButtons();

            if (
                    blueRobot.getHealth() > 0
            ) {

                turnLabel.setText(
                        "🏆 VICTORY!"
                );

                turnLabel.setForeground(
                        new Color(
                                255,
                                210,
                                50
                        )
                );

                addFeed(
                        "SYSTEM",
                        "🏆 BLAZE WINS!",
                        new Color(
                                255,
                                210,
                                50
                        )
                );

            } else {

                turnLabel.setText(
                        "💀 DEFEAT"
                );

                turnLabel.setForeground(
                        new Color(
                                255,
                                70,
                                70
                        )
                );

                addFeed(
                        "SYSTEM",
                        "💀 TITAN WINS!",
                        new Color(
                                255,
                                70,
                                70
                        )
                );
            }

            addFeed(
                    "SYSTEM",
                    "BATTLE OVER!",
                    Color.WHITE
            );

            return true;
        }

        return false;
    }

    // =========================================================
    // SURRENDER
    // =========================================================

    private void surrender() {

        if (
                battleFinished
        ) {
            return;
        }

        battleFinished =
                true;

        disableButtons();

        turnLabel.setText(
                "💀 DEFEAT"
        );

        turnLabel.setForeground(
                new Color(
                        255,
                        70,
                        70
                )
        );

        addFeed(
                "SYSTEM",
                "BLAZE SURRENDERED!",
                new Color(
                        255,
                        70,
                        70
                )
        );

        addFeed(
                "SYSTEM",
                "🏆 TITAN WINS!",
                new Color(
                        255,
                        200,
                        50
                )
        );
    }

    // =========================================================
    // UPDATE HUD
    // =========================================================

    private void updateHUD() {

        int blueHP =
                Math.max(
                        0,
                        blueRobot.getHealth()
                );

        int redHP =
                Math.max(
                        0,
                        redRobot.getHealth()
                );

        // BLUE HP
        blueHPBar.setValue(
                blueHP
        );

        blueHPBar.setString(
                "HP " +
                blueHP +
                " / 100"
        );

        // RED HP
        redHPBar.setValue(
                redHP
        );

        redHPBar.setString(
                "HP " +
                redHP +
                " / 100"
        );

        // BLUE ENERGY
        blueEnergyBar.setValue(
                blueEnergy
        );

        blueEnergyBar.setString(
                "ENERGY " +
                blueEnergy +
                "%"
        );

        // RED ENERGY
        redEnergyBar.setValue(
                redEnergy
        );

        redEnergyBar.setString(
                "ENERGY " +
                redEnergy +
                "%"
        );

        // DAMAGE
        blueDamageLabel.setText(
                "DMG " +
                blueDamage
        );

        redDamageLabel.setText(
                "DMG " +
                redDamage
        );

        // COMBO
        blueComboLabel.setText(
                "COMBO x" +
                blueCombo
        );

        redComboLabel.setText(
                "COMBO x" +
                redCombo
        );
    }

    // =========================================================
    // COMBAT FEED
    // =========================================================

    private void addFeed(
            String source,
            String message,
            Color color
    ) {

        StyledDocument document =
                combatFeed.getStyledDocument();

        Style style =
                combatFeed.addStyle(
                        "feedStyle"
                                +
                                System.nanoTime(),
                        null
                );

        StyleConstants.setForeground(
                style,
                color
        );

        StyleConstants.setFontFamily(
                style,
                "Consolas"
        );

        StyleConstants.setFontSize(
                style,
                14
        );

        try {

            document.insertString(
                    document.getLength(),
                    "[" +
                    source +
                    "] " +
                    message +
                    "\n",
                    style
            );

            combatFeed.setCaretPosition(
                    document.getLength()
            );

        } catch (
                BadLocationException e
        ) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // DISABLE BUTTONS
    // =========================================================

    private void disableButtons() {

        attackButton.setEnabled(
                false
        );

        specialButton.setEnabled(
                false
        );

        defendButton.setEnabled(
                false
        );

        surrenderButton.setEnabled(
                false
        );
    }

    // =========================================================
    // ENABLE BUTTONS
    // =========================================================

    private void enableButtons() {

        if (
                battleFinished
        ) {
            return;
        }

        attackButton.setEnabled(
                true
        );

        specialButton.setEnabled(
                blueEnergy >= 30
        );

        defendButton.setEnabled(
                true
        );

        surrenderButton.setEnabled(
                true
        );
    }

    // =========================================================
    // BACKGROUND PANEL
    // =========================================================

    class BackgroundPanel
            extends JPanel {

        private Image backgroundImage;

        public BackgroundPanel() {

            ImageIcon icon =
                    new ImageIcon(
                            getClass()
                                    .getResource(
                                            "/resources/battle_arena_bg_new.png"
                                    )
                    );

            backgroundImage =
                    icon.getImage();
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(
                    g
            );

            Graphics2D g2 =
                    (Graphics2D)
                    g.create();

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
    
    private void shakeRobot(
            JLabel robot,
            int originalX,
            int originalY
    ) {

        final int[] count = {0};

        Timer shakeTimer = new Timer(
                40,
                null
        );

        shakeTimer.addActionListener(e -> {

            if (count[0] >= 10) {

                robot.setLocation(
                        originalX,
                        originalY
                );

                shakeTimer.stop();

                return;
            }

            int offset;

            if (count[0] % 2 == 0) {
                offset = 15;
            } else {
                offset = -15;
            }

            robot.setLocation(
                    originalX + offset,
                    originalY
            );

            count[0]++;
        });

        shakeTimer.start();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> new BattleArenaGUI()
        );
    }
}