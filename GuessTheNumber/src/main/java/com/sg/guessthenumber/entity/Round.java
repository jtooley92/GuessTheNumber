/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.entity;

import java.time.LocalDateTime;

/**
 *
 * @author Jtooleyful
 */
public class Round {
    private int roundId;
    private Guess guess;
    private LocalDateTime time;
    private String guessResult;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
        this.guess = guess;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }
    
    
}
