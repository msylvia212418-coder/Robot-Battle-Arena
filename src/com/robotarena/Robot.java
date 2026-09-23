package com.robotarena;

public class Robot {

    // Robot attributes
    private int robotId;
    private int userId;
    private String robotName;
    private String robotType;
    private int health;
    private int attackPower;
    private int defensePower;
    private int speed;
    private int level;
    private int wins;
    private int losses;

    // Constructor
    public Robot(int robotId, int userId, String robotName,
                 String robotType, int health, int attackPower,
                 int defensePower, int speed, int level,
                 int wins, int losses) {

        this.robotId = robotId;
        this.userId = userId;
        this.robotName = robotName;
        this.robotType = robotType;
        this.health = health;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.speed = speed;
        this.level = level;
        this.wins = wins;
        this.losses = losses;
    }

	public int getRobotId() {
		return robotId;
	}

	public void setRobotId(int robotId) {
		this.robotId = robotId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRobotName() {
		return robotName;
	}

	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}

	public String getRobotType() {
		return robotType;
	}

	public void setRobotType(String robotType) {
		this.robotType = robotType;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getDefensePower() {
		return defensePower;
	}

	public void setDefensePower(int defensePower) {
		this.defensePower = defensePower;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}
	public void attack(Robot opponent) {

	    int damage = this.attackPower - opponent.getDefensePower();

	    if (damage < 0) {
	        damage = 0;
	    }

	    opponent.setHealth(opponent.getHealth() - damage);

	    System.out.println(
	        this.robotName + " attacked " +
	        opponent.getRobotName() +
	        " and caused " + damage + " damage!"
	    );
	}
}



