/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public class GuessTheNumberServiceImpl implements GuessTheNumberService {

    GameDao gameDao;
    RoundDao roundDao;

    public GuessTheNumberServiceImpl() {

    }

    public GuessTheNumberServiceImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @Override
    public Game addGame(Game game) {
        gameDao.addGame(game);

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for (int i = 0; i < 4; i++) {
            result += numbers.get(i).toString();
        }
        game.setGeneratedNumber(result);
        addRound(game.getRound());
        updateStatus(game);
        return game;
    }

    @Override
    public Game getGame(int gameId) {
        Game game = gameDao.getGame(gameId);

        return game;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();

        return games;

    }

    @Override
    public Game updateStatus(Game game) {
        gameDao.updateStatus(game);

        if (game.getGeneratedNumber().equals(game.getRound().getNumberGuess())) {
            game.setStatus(true);
        }

        return game;
    }

    @Override
    public Round addRound(Round round) {
        round = roundDao.addRound(round);

        return round;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        List<Round> rounds = roundDao.getRoundsByGameId(gameId);

        return rounds;
    }

    @Override
    public Round getResults(Game game) {
        Round round = game.getRound();
        String guessPart1 = game.getGeneratedNumber().substring(0, 1);
        String guessPart2 = game.getGeneratedNumber().substring(1, 2);
        String guessPart3 = game.getGeneratedNumber().substring(2, 3);
        String guessPart4 = game.getGeneratedNumber().substring(3, 4);
        String roundPart1 = round.getNumberGuess().substring(0, 1);
        String roundPart2 = round.getNumberGuess().substring(1, 2);
        String roundPart3 = round.getNumberGuess().substring(2, 3);
        String roundPart4 = round.getNumberGuess().substring(3, 4);
        
        
        
    return game.getRound();
    }
}
