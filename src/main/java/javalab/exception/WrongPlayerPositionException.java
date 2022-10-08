/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab.exception;

/**
 * Exception generated when player is set to wrong position.
 *
 * @author Dominik Uszok
 * @version 1.0
 */
public class WrongPlayerPositionException extends Exception {

    /**
     * Constructor of exception.
     *
     * @param message display exception message
     */
    public WrongPlayerPositionException(String message) {
        super(message);
    }

}
