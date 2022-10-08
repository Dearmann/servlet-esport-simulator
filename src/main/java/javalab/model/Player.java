/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab.model;

/**
 * Class that defines e-sports player.
 *
 * @author Dominik Uszok
 * @version 1.0
 */
public class Player {

    /**
     * Enumerated type for assigning player's position.
     */
    public enum Position {
        TOP,
        JUNGLE,
        MID,
        BOT,
        SUPPORT,
        UNDEFINED
    }

    /**
     * Name of a player.
     */
    private String playerName;

    /**
     * Strength of a player.
     */
    private int playerStrength = 0;

    /**
     * Position of a player.
     */
    private Position position = Position.UNDEFINED;

    /**
     * Position getter.
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Position setter
     *
     * @param position player position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Player constructor with initialized fields.
     *
     * @param playerName name of a player
     * @param playerStrength strength of a player
     */
    public Player(String playerName, int playerStrength) {
        this.playerName = playerName;
        if (playerStrength < 0) {
            playerStrength = 0;
        }
        this.playerStrength = playerStrength;
    }

    /**
     * Name getter.
     *
     * @return player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Name setter.
     *
     * @param playerName player name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Player strength getter.
     *
     * @return player strength
     */
    public int getPlayerStrength() {
        return playerStrength;
    }

    /**
     * Player strength setter.
     *
     * @param playerStrength player strength
     */
    public void setPlayerStrength(int playerStrength) {
        if (playerStrength < 0) {
            playerStrength = 0;
        }
        this.playerStrength = playerStrength;
    }
}
