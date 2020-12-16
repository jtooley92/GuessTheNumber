/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.GuessTheNumberDaoException;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Jtooleyful
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessTheNumberServiceImplIT {
    
     @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    @Autowired
    GuessTheNumberService testService;

    public GuessTheNumberServiceImplIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        final String DELETE_ROUND = "DELETE FROM Round";
        jdbc.update(DELETE_ROUND);
        final String DELETE_GAME = "DELETE FROM Game";
        jdbc.update(DELETE_GAME);
        
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testAddGame() {
        Game game1 = new Game();

        Game game = testService.addGame(game1);

        assertEquals(game.getGeneratedNumber(), "****");
        assertFalse(game.isStatus());
    }

    @Test
    public void testGetGame() {
        Game game1 = new Game();

        Game game = testService.addGame(game1);

        Game gotGame = testService.getGame(game.getGameId());

        assertEquals(gotGame.getGeneratedNumber(), "****");
        assertFalse(gotGame.isStatus());
    }

    @Test
    public void testGetAllGames() {
        Game game1 = new Game();

        Game addGame1 = testService.addGame(game1);

        Game game2 = new Game();

        Game addGame2 = testService.addGame(game2);

        List<Game> games = testService.getAllGames();

        for (Game game : games) {
            assertEquals(game.getGeneratedNumber(), "****");
            assertFalse(game.isStatus());
        }

    }

    @Test
    public void testAddRound() {
        List<Round> rounds = new ArrayList();
        String guess = "1234";
        Game game2 = new Game("1234", rounds, true);
        
        Game addGame2 = gameDao.addGame(game2);
        
        Game game = testService.getGame(addGame2.getGameId());
        

        try {
            testService.addRound(game.getGameId(), guess);
            fail();
        } catch (GuessTheNumberDaoException e) {

        }

    }

    @Test
    public void testGetResults() {
        List<Round> rounds = new ArrayList();
        String guess = "1234";
        Game game2 = new Game("1234", rounds, false);
        
        Game addGame2 = gameDao.addGame(game2);
        
        int gameId = addGame2.getGameId();
        
        Round round = testService.getResults(gameId, guess);
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        time.setNanos(0);
        assertEquals(round.getNumberGuess(), guess);
        assertEquals(round.getTime(), time);
        assertEquals(round.getGuessResultExact(), "e:4");
        assertEquals(round.getGuessResultPartial(), "p:0");
    }

}
