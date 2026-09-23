package com.robotarena;

import java.util.Random;

public class Battle {

    private int battleId;
    private Robot robot1;
    private Robot robot2;
    private Robot winner;

    public Battle(int battleId, Robot robot1, Robot robot2) {

        this.battleId = battleId;
        this.robot1 = robot1;
        this.robot2 = robot2;
    }

    public void startBattle() {

        System.out.println("===== BATTLE STARTED =====");

        System.out.println(
                robot1.getRobotName()
                + " VS "
                + robot2.getRobotName()
        );

        System.out.println();

        // Randomly choose who attacks first
        Random random = new Random();

        Robot attacker;
        Robot defender;

        if (random.nextBoolean()) {

            attacker = robot1;
            defender = robot2;

        } else {

            attacker = robot2;
            defender = robot1;
        }

        System.out.println(
                "First Attacker: "
                + attacker.getRobotName()
        );

        System.out.println();


        // ================= BATTLE LOOP =================

        while (
                robot1.getHealth() > 0
                && robot2.getHealth() > 0
        ) {

            // Calculate damage
            int baseDamage =
                    attacker.getAttackPower()
                    - defender.getDefensePower();

            // Minimum damage
            if (baseDamage < 5) {
                baseDamage = 5;
            }

            // Random damage variation
            int randomDamage =
                    random.nextInt(21) - 10;

            int damage =
                    baseDamage + randomDamage;

            if (damage < 1) {
                damage = 1;
            }

            // Apply damage
            defender.setHealth(
                    defender.getHealth() - damage
            );

            System.out.println(
                    attacker.getRobotName()
                    + " attacked "
                    + defender.getRobotName()
                    + " → "
                    + damage
                    + " damage"
            );

            System.out.println(
                    defender.getRobotName()
                    + " Health: "
                    + Math.max(
                            defender.getHealth(),
                            0
                    )
            );

            System.out.println();


            // Check winner
            if (defender.getHealth() <= 0) {
                break;
            }


            // Swap attacker and defender
            Robot temp = attacker;

            attacker = defender;
            defender = temp;
        }


        // ================= FIND WINNER =================

        if (robot1.getHealth() > 0) {

            winner = robot1;

        } else {

            winner = robot2;
        }


        System.out.println(
                "===== BATTLE OVER ====="
        );

        System.out.println(
                "🏆 Winner: "
                + winner.getRobotName()
        );

        System.out.println(
                "Remaining Health: "
                + winner.getHealth()
        );
    }


    public Robot getWinner() {

        return winner;
    }


    public Robot getRobot1() {

        return robot1;
    }


    public Robot getRobot2() {

        return robot2;
    }
}