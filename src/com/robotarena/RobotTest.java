package com.robotarena;

public class RobotTest {

    public static void main(String[] args) {

        Robot robot1 = new Robot(
                1, 1, "Thunder",
                "Attack", 100, 80,
                50, 70, 1, 0, 0
        );

        Robot robot2 = new Robot(
                2, 2, "Titan",
                "Defense", 100, 60,
                70, 40, 1, 0, 0
        );

        System.out.println("===== ROBOT BATTLE ARENA =====");

        System.out.println("Robot 1: " + robot1.getRobotName());
        System.out.println("Health: " + robot1.getHealth());

        System.out.println();

        System.out.println("Robot 2: " + robot2.getRobotName());
        System.out.println("Health: " + robot2.getHealth());

        System.out.println();
        System.out.println("----- BATTLE START -----");

        robot1.attack(robot2);

        System.out.println();
        System.out.println("Robot 2 Health After Attack: "
                + robot2.getHealth());

        System.out.println("===== TEST COMPLETED =====");
    }
}