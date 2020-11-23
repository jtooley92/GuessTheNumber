/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Round;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public interface RoundDao {
    Round addRound(Round round, int gameId);
    List<Round> getRoundsByGameId(int gameId);
}
