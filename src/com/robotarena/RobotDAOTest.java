package com.robotarena;

public class RobotDAOTest {

    public static void main(String[] args) {

        Robot robot = new Robot(
                0,
                1,
                "Blaze",
                "Attack",
                100,
                80,
                50,
                70,
                1,
                0,
                0
        );

        RobotDAO dao = new RobotDAO();

        dao.addRobot(robot);
    }
}