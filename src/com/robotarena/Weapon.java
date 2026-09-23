package com.robotarena;

public class Weapon {

    // Weapon attributes
    private int weaponId;
    private String weaponName;
    private String weaponType;
    private int damage;
    private int level;
    private int price;

    // Constructor
    public Weapon(int weaponId, String weaponName,
                  String weaponType, int damage,
                  int level, int price) {

        this.weaponId = weaponId;
        this.weaponName = weaponName;
        this.weaponType = weaponType;
        this.damage = damage;
        this.level = level;
        this.price = price;
    }

	public int getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(int weaponId) {
		this.weaponId = weaponId;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}