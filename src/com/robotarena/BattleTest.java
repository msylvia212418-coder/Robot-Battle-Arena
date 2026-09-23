package com.robotarena;

public class BattleTest {

    public static void main(String[] args) {

    	Robot robot1 = new Robot(
    	        2, 1, "Blaze",
    	        "Attack", 100, 80,
    	        50, 70, 1, 0, 0
    	);

    	Robot robot2 = new Robot(
    	        3, 1, "Titan",
    	        "Defense", 100, 60,
    	        70, 40, 1, 0, 0
    	);

        Battle battle = new Battle(
                1,
                robot1,
                robot2
        );

        battle.startBattle();

        System.out.println();

        System.out.println("Battle Winner: "
                + battle.getWinner().getRobotName());

        // Save battle result to MySQL
        BattleDAO dao = new BattleDAO();

        dao.saveBattle(battle);
    }
}