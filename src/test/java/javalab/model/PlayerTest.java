/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package javalab.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Dominik Uszok
 */
public class PlayerTest {

    public PlayerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of setPlayerStrength method, of class Player.
     */
    @Test
    public void testSetPlayerStrength() {
        Player instance = new Player("test", 0);
        instance.setPlayerStrength(-5);
        assertTrue(instance.getPlayerStrength() >= 0);
    }

    /**
     * Test of constructor, of class Player.
     */
    @Test
    public void testPlayerConstructor() {
        Player instance = new Player("test", -5);
        assertTrue(instance.getPlayerStrength() >= 0);
    }

}
