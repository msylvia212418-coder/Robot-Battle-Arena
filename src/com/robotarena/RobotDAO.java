package com.robotarena;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RobotDAO {

    public void addRobot(Robot robot) {

        String sql = "INSERT INTO robots "
                + "(user_id, robot_name, robot_type, health, "
                + "attack_power, defense_power, speed, level, wins, losses) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, robot.getUserId());
            ps.setString(2, robot.getRobotName());
            ps.setString(3, robot.getRobotType());
            ps.setInt(4, robot.getHealth());
            ps.setInt(5, robot.getAttackPower());
            ps.setInt(6, robot.getDefensePower());
            ps.setInt(7, robot.getSpeed());
            ps.setInt(8, robot.getLevel());
            ps.setInt(9, robot.getWins());
            ps.setInt(10, robot.getLosses());

            ps.executeUpdate();

            System.out.println("Robot saved successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Failed to save robot!");
            e.printStackTrace();
        }
    }
}