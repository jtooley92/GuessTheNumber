/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

/**
 *
 * @author Jtooleyful
 */
public class GuessTheNumberDaoException extends Exception{
    public GuessTheNumberDaoException(String message) {
        super(message);
    }
    
     public GuessTheNumberDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
