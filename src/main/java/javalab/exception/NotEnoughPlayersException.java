/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab.exception;

/**
 * Exception generated when team with not enough players try to match with each
 * other.
 *
 * @author Dominik Uszok
 * @version 1.0
 */
public class NotEnoughPlayersException extends Exception {

    /**
     * Constructor of exception.
     *
     * @param message display exception message
     */
    public NotEnoughPlayersException(String message) {
        super(message);
    }

}
