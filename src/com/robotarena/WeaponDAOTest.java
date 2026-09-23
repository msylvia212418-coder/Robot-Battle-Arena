package com.robotarena;

public class WeaponDAOTest {

    public static void main(String[] args) {

        Weapon weapon = new Weapon(
                0,
                "Plasma Cannon",
                "Energy",
                40,
                1,
                500
        );

        WeaponDAO dao = new WeaponDAO();

        dao.addWeapon(weapon);
    }
}