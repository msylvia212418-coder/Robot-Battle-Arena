package com.robotarena;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/robotarena",
                "root",
                "manovishu27"
            );

            System.out.println("================================");
            System.out.println("  ROBOT BATTLE ARENA");
            System.out.println("  Database Connected Successfully!");
            System.out.println("================================");

            con.close();

        } catch (Exception e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }
    }
}