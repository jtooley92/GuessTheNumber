/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Jtooleyful
 */
public class Round {
    private int roundId;
    private Timestamp time;
    private String numberGuess;
    private String guessResultExact;
    private String guessResultPartial;
    
    public Round(){
        
    }
    
    public Round(int roundId, Timestamp time, String numberGuess, String guessResultExact, String guessResultPartial ){
        this.roundId = roundId;
        this.time = time;
        this.numberGuess = numberGuess;
        this.guessResultExact = guessResultExact;
        this.guessResultPartial = guessResultPartial;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.roundId;
        hash = 79 * hash + Objects.hashCode(this.time);
        hash = 79 * hash + Objects.hashCode(this.numberGuess);
        hash = 79 * hash + Objects.hashCode(this.guessResultExact);
        hash = 79 * hash + Objects.hashCode(this.guessResultPartial);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (!Objects.equals(this.numberGuess, other.numberGuess)) {
            return false;
        }
        if (!Objects.equals(this.guessResultExact, other.guessResultExact)) {
            return false;
        }
        if (!Objects.equals(this.guessResultPartial, other.guessResultPartial)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", time=" + time + ", numberGuess=" + numberGuess + ", guessResultExact=" + guessResultExact + ", guessResultPartial=" + guessResultPartial + '}';
    }
      
    
}
