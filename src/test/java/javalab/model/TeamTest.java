/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package javalab.model;

import javalab.exception.WrongPlayerPositionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Dominik Uszok
 */
public class TeamTest {

    Team testTeam;

    public TeamTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        testTeam = new Team("SKT1", 1);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of countMissingPlayers method, of class Team with valid position values.
     * Test outdated - now always initialize all players with name "Player" and strength - 0.
     * @param players number of players added
     */
//    @ParameterizedTest
//    @ValueSource(ints = {0, 1, 2, 3, 4})
//    public void testCountMissingPlayers(int players) {
//        try {
//            for (int i = 0; i < players; i++) {
//                testTeam.addPlayer(i, "testPlayer", 0);
//            }
//        } catch (WrongPlayerPositionException e) {
//            e.getMessage();
//        }
//        int expResult = 5 - players;
//        int result = testTeam.countMissingPlayers();
//        assertEquals(expResult, result);
//    }

    /**
     * Test of countMissingPlayers method, of class Team with invalid position values.
     * @param position position of player to add
     */
    @ParameterizedTest
    @ValueSource(ints = {-3, -2, -1, 5, 6, 7, 9999})
    public void testCountMissingPlayersInvalidValues(int position) {
        try {
            testTeam.addPlayer(position, "testPlayer", 0);
            fail();
        } catch (WrongPlayerPositionException e) {
            e.getMessage();
        }
    }

    /**
     * Test of calculateTeamStrength method, of class Team.
     *
     * @param number strength value
     */
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 20, -5, 0})
    public void testCalculateTeamStrength(int number) throws Exception {
        try {
            testTeam.addPlayer(0, "testPlayer", number);
            testTeam.addPlayer(1, "testPlayer2", number);
            testTeam.addPlayer(2, "testPlayer3", number);
            testTeam.addPlayer(3, "testPlayer4", number);
            testTeam.addPlayer(4, "testPlayer5", number);
        } catch (WrongPlayerPositionException e) {
            e.getMessage();
        }
        if (number < 0) {
            number = 0;
        }
        int expResult = number * 5;
        int result = testTeam.calculateTeamStrength();
        assertEquals(expResult, result);
    }

    /**
     * Test of addPlayer method, of class Team with valid position values.
     *
     * @param number player position
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testAddValidPlayer(int number) throws Exception {
        try {
            testTeam.addPlayer(number, "testPlayer", 0);
        } catch (WrongPlayerPositionException e) {
            e.getMessage();
        }
    }

    /**
     * Test of addPlayer method, of class Team with invalid position values.
     * 
     * @param number player position
     */
    @ParameterizedTest
    @ValueSource(ints = {-1000, -5, -1, 5, 999999})
    public void testAddInvalidPlayer(int number) throws Exception {
        try {
            testTeam.addPlayer(number, "testPlayer", 0);
            fail("Should throw exception");
        } catch (WrongPlayerPositionException e) {
            e.getMessage();
        }
    }

}
