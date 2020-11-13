/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public interface GuessTheNumberService {
    Game addGame(Game game);
    Game getGame(int gameId);
    List<Game> getAllGames();
    Game updateStatus(Game game);
    Round addRound(Round round);
    List<Round> getRoundsByGameId(int gameId);
    Round getResults(Game game);
}
