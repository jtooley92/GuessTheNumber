/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

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
public class GameDaoDbIT {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public GameDaoDbIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        final String DELETE_GAME = "DELETE FROM Game";
        jdbc.update(DELETE_GAME);

    }

    @Test
    public void testAddGame() {

        List<Round> rounds = new ArrayList();

        Game game = new Game("1536", rounds, true);
        Game addedGame = gameDao.addGame(game);

        assertEquals(addedGame, game);

    }

    @Test
    public void testGetGame() {

        List<Round> rounds = new ArrayList();

        Game game1 = new Game("1536", rounds, true);
        Game addedGame1 = gameDao.addGame(game1);
        Game getGame1 = gameDao.getGame(addedGame1.getGameId());

        Game game2 = new Game("1685", rounds, true);
        Game addedGame2 = gameDao.addGame(game2);
        Game getGame2 = gameDao.getGame(addedGame2.getGameId());

        assertEquals(getGame1, game1);
        assertEquals(getGame2, game2);
    }

    @Test
    public void testGetAllGames() {
        
        List<Round> rounds = new ArrayList();

        Game game1 = new Game("1536", rounds, true);
        Game addedGame1 = gameDao.addGame(game1);

        Game game2 = new Game("1685", rounds, true);
        Game addedGame2 = gameDao.addGame(game2);
        
        List<Game> dataBaseGames = gameDao.getAllGames();
        
        assertTrue(dataBaseGames.contains(addedGame1));
        assertTrue(dataBaseGames.contains(addedGame2));
    }

    @Test
    public void testUpdateStatus() {
        List<Round> rounds = new ArrayList();
        
        Game game1 = new Game("1536", rounds, false);
        Game addedGame = gameDao.addGame(game1);
        
        addedGame.setStatus(true);
        gameDao.updateStatus(addedGame);
        
        assertTrue(gameDao.getGame(addedGame.getGameId()).isStatus());
    }

}
