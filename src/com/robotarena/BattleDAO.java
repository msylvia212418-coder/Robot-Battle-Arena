package com.robotarena;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BattleDAO {

    public void saveBattle(Battle battle) {

        String battleSql =
                "INSERT INTO battles "
                + "(robot1_id, robot2_id, winner_id) "
                + "VALUES (?, ?, ?)";

        String resultSql =
                "INSERT INTO results "
                + "(battle_id, winner_id, loser_id, score) "
                + "VALUES (?, ?, ?, ?)";
        
        String winnerUpdateSql =
                "UPDATE robots SET wins = wins + 1 "
                + "WHERE robot_id = ?";

        String loserUpdateSql =
                "UPDATE robots SET losses = losses + 1 "
                + "WHERE robot_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            // Save battle
            PreparedStatement battlePs =
                    con.prepareStatement(
                            battleSql,
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

            battlePs.setInt(
                    1,
                    battle.getRobot1().getRobotId()
            );

            battlePs.setInt(
                    2,
                    battle.getRobot2().getRobotId()
            );

            battlePs.setInt(
                    3,
                    battle.getWinner().getRobotId()
            );

            battlePs.executeUpdate();

            // Get generated battle ID
            var keys = battlePs.getGeneratedKeys();

            int battleId = 0;

            if (keys.next()) {
                battleId = keys.getInt(1);
            }

            // Save result
            PreparedStatement resultPs =
                    con.prepareStatement(resultSql);

            resultPs.setInt(1, battleId);

            resultPs.setInt(
                    2,
                    battle.getWinner().getRobotId()
            );

            Robot loser;

            if (battle.getWinner().getRobotId()
                    == battle.getRobot1().getRobotId()) {

                loser = battle.getRobot2();

            } else {

                loser = battle.getRobot1();
            }

            resultPs.setInt(
                    3,
                    loser.getRobotId()
            );

            resultPs.setInt(
                    4,
                    battle.getWinner().getHealth()
            );

            resultPs.executeUpdate();
            
            PreparedStatement winnerPs =
                    con.prepareStatement(winnerUpdateSql);

            winnerPs.setInt(
                    1,
                    battle.getWinner().getRobotId()
            );

            winnerPs.executeUpdate();

            winnerPs.close();
            
            PreparedStatement loserPs =
                    con.prepareStatement(loserUpdateSql);

            loserPs.setInt(
                    1,
                    loser.getRobotId()
            );

            loserPs.executeUpdate();

            loserPs.close();

            System.out.println(
                    "Battle result saved successfully!"
            );

            resultPs.close();
            battlePs.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Failed to save battle result!"
            );

            e.printStackTrace();
        }
    }
}