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
    private LocalDateTime time;
    private String numberGuess;
    private String guessResultExact;
    private String guessResultPartial;
    

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getNumberGuess() {
        return numberGuess;
    }

    public void setNumberGuess(String numberGuess) {
        this.numberGuess = numberGuess;
    }

    public String getGuessResultExact() {
        return guessResultExact;
    }

    public void setGuessResultExact(String guessResultExact) {
        this.guessResultExact = guessResultExact;
    }

    public String getGuessResultPartial() {
        return guessResultPartial;
    }

    public void setGuessResultPartial(String guessResultPartial) {
        this.guessResultPartial = guessResultPartial;
    }
      
}
