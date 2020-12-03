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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jtooleyful
 */
@Component
public class GuessTheNumberServiceImpl implements GuessTheNumberService {

    GameDao gameDao;
    RoundDao roundDao;

    public GuessTheNumberServiceImpl() {

    }

    @Autowired
    public GuessTheNumberServiceImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @Override
    public Game addGame(Game game) {
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
        game.setStatus(false);
        gameDao.addGame(game);
        return game;
    }

    @Override
    public Game getGame(int gameId) {
        Game game = gameDao.getGame(gameId);

        if (game.isStatus() == false) {
            game.setGeneratedNumber("****");
        }

        return game;
    }

    @Override
    public List<Game> getAllGames() {

        List<Game> games = gameDao.getAllGames();

        for (Game game : games) {
            if (game.isStatus() == false) {
                game.setGeneratedNumber("****");
            }
        }

        return games;

    }

    private boolean updateStatus(int exactResults) {

        if (exactResults == 4) {
            return true;
        }
        return false;
    }

    @Override
    public Round addRound(int gameId, String guess) {
        Round round = new Round();

        round = getResults(gameId, guess);
        roundDao.addRound(round, gameId);

        return round;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        List<Round> rounds = roundDao.getRoundsByGameId(gameId);

        return rounds;
    }

    @Override
    public Round getResults(int gameId, String guess) {
        Game game = gameDao.getGame(gameId);
        Round round = new Round();
        String guessPart1 = game.getGeneratedNumber().substring(0, 1);
        String guessPart2 = game.getGeneratedNumber().substring(1, 2);
        String guessPart3 = game.getGeneratedNumber().substring(2, 3);
        String guessPart4 = game.getGeneratedNumber().substring(3, 4);
        String roundPart1 = guess.substring(0, 1);
        String roundPart2 = guess.substring(1, 2);
        String roundPart3 = guess.substring(2, 3);
        String roundPart4 = guess.substring(3, 4);
        String[] randomNumber = {guessPart1, guessPart2, guessPart3, guessPart4};
        String[] prediction = {roundPart1, roundPart2, roundPart3, roundPart4};
        int exactCounter = 0;
        int partialCounter = 0;

        for (int i = 0; i < randomNumber.length; i++) {
            for (int j = 0; j < prediction.length; j++) {
                if (randomNumber[i].equals(prediction[j])) {
                    partialCounter++;
                }
            }
            if (randomNumber[i].equals(prediction[i])) {
                exactCounter++;
                
                if (partialCounter > 0){
                    partialCounter--;
                }
            }

        }
        
        
        round.setNumberGuess(guess);
        round.setTime(Timestamp.valueOf(LocalDateTime.now()));
        round.setGuessResultExact("e:" + exactCounter);
        round.setGuessResultPartial("p:" + partialCounter);

        game.setStatus(updateStatus(exactCounter));
        gameDao.updateStatus(game);

        return round;
    }
}
