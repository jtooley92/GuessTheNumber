/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Guess;

/**
 *
 * @author Jtooleyful
 */
public interface GuessDao {
    Guess addGuess();
    Guess getGuess(int guessId);
}
