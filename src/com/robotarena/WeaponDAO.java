package com.robotarena;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class WeaponDAO {

    public void addWeapon(Weapon weapon) {

        String sql = "INSERT INTO weapons "
                + "(weapon_name, weapon_type, damage, level, price) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, weapon.getWeaponName());
            ps.setString(2, weapon.getWeaponType());
            ps.setInt(3, weapon.getDamage());
            ps.setInt(4, weapon.getLevel());
            ps.setInt(5, weapon.getPrice());

            ps.executeUpdate();

            System.out.println("Weapon saved successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Failed to save weapon!");
            e.printStackTrace();
        }
    }
}