/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jtooleyful
 */
public class Game {

    private int gameId;
    private String generatedNumber;
    private List<Round> round;
    private boolean status;

    public Game() {

    }

    public Game(String generatedNumber, boolean status) {
        this.generatedNumber = generatedNumber;
        this.status = status;
    }

    public Game(String generatedNumber, List<Round> round, boolean status) {
        this.generatedNumber = generatedNumber;
        this.round = round;
        this.status = status;
    }

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

    public List<Round> getRounds() {
        return round;
    }

    public void setRounds(List<Round> round) {
        this.round = round;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.gameId;
        hash = 97 * hash + Objects.hashCode(this.generatedNumber);
        hash = 97 * hash + Objects.hashCode(this.round);
        hash = 97 * hash + (this.status ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.generatedNumber, other.generatedNumber)) {
            return false;
        }
        if (!Objects.equals(this.round, other.round)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", generatedNumber=" + generatedNumber + ", round=" + round + ", status=" + status + '}';
    }


}
