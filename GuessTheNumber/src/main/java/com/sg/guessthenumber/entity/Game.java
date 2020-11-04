/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.entity;

import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public class Game {
    private int gameId;
    private String generatedNumber;
    private Round round;
    private boolean status;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGeneratedNumber() {
        return generatedNumber;
    }

    public void setGeneratedNumber(String generatedNumber) {
        this.generatedNumber = generatedNumber;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
