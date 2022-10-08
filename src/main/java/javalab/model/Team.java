/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab.model;

import javalab.exception.NotEnoughPlayersException;
import javalab.exception.WrongPlayerPositionException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that defines e-sports team.
 *
 * @author Dominik Uszok
 * @version 1.0
 */
public class Team {

    /**
     * Counter used to assign team unique number.
     */
    private static int teamCounter = 0;

    /**
     * Team's unique number.
     */
    private int teamNumber;

    /**
     * Team's name.
     */
    private String teamName;

    /**
     * Team's rank.
     */
    private int teamRanking;

    /**
     * Team's players.
     */
    private final List<Player> teamPlayers;

    /**
     * Constructor of team with initialization.
     *
     * @param teamName
     * @param teamRanking
     */
    public Team(String teamName, int teamRanking) {
        this.teamNumber = ++teamCounter;
        this.teamName = teamName;
        this.teamRanking = teamRanking;
        teamPlayers = new ArrayList<>(5);
        teamPlayers.add(0, new Player("Player1", 0));
        teamPlayers.add(1, new Player("Player2", 0));
        teamPlayers.add(2, new Player("Player3", 0));
        teamPlayers.add(3, new Player("Player4", 0));
        teamPlayers.add(4, new Player("Player5", 0));
    }

    /**
     * Team number getter.
     *
     * @return team number
     */
    public int getTeamNumber() {
        return teamNumber;
    }

    /**
     * Team number setter.
     *
     * @param teamNumber team number
     */
    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    /**
     * Team name getter.
     *
     * @return team name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Team name setter
     *
     * @param teamName team name
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Team rank getter.
     *
     * @return team rank
     */
    public int getTeamRanking() {
        return teamRanking;
    }

    /**
     * Team rank setter.
     *
     * @param teamRanking team rank
     */
    public void setTeamRanking(int teamRanking) {
        this.teamRanking = teamRanking;
    }

    /**
     * Team player getter.
     *
     * @return team players
     */
    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }

    /**
     * Calculate players count missing from full roster.
     *
     * @return missing player count
     */
    public int countMissingPlayers() {
        int missingPlayersCount = 0;
        for (Player player : teamPlayers) {
            if (player == null/* || player.getPosition() == Player.Position.UNDEFINED*/) {
                missingPlayersCount++;
            }
        }
        return missingPlayersCount;
    }

    /**
     * Calculates team's strength based on players strength.
     *
     * @return team's strength
     * @throws NotEnoughPlayersException if team is not in full roster
     */
    public int calculateTeamStrength() throws NotEnoughPlayersException {
        int teamStrength = 0;
        if (countMissingPlayers() == 0) {
//            for (Player teamMember : teamPlayers) {
//                teamStrength += teamMember.getPlayerStrength();
//            }
            teamStrength = teamPlayers.stream()
                    .mapToInt(Player::getPlayerStrength)
                    .sum();
        } else {
            throw new NotEnoughPlayersException("Not all players are initialized, missing players: " + countMissingPlayers());
        }
        return teamStrength;
    }

    /**
     * Adds player to team's roster.
     *
     * @param position player position
     * @param playerName player name
     * @param playerStrength player strength
     * @throws WrongPlayerPositionException if player is set on wrong position
     */
    public void addPlayer(int position, String playerName, int playerStrength) throws WrongPlayerPositionException {
        if (position < 0 || position >= 5) {
            throw new WrongPlayerPositionException(position + " is not valid player position. Valid positions: 1-5");
        } else {
            Player newPlayer = new Player(playerName, playerStrength);
            switch (position) {
                case 0:
                    newPlayer.setPosition(Player.Position.TOP);
                    break;
                case 1:
                    newPlayer.setPosition(Player.Position.JUNGLE);
                    break;
                case 2:
                    newPlayer.setPosition(Player.Position.MID);
                    break;
                case 3:
                    newPlayer.setPosition(Player.Position.BOT);
                    break;
                case 4:
                    newPlayer.setPosition(Player.Position.SUPPORT);
                    break;
                default:
                    newPlayer.setPosition(Player.Position.UNDEFINED);
                    break;
            }
            teamPlayers.set(position, newPlayer);
        }
    }
}
