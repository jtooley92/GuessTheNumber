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
public class RoundDaoDbIT {
    
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;
    
    public RoundDaoDbIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         List<Round> rounds = new ArrayList();
        Game game = new Game("5432",rounds,false);
        final String INSERT_GAME = "INSERT INTO Game(GameId, GeneratedNumber, Status) VALUES(?, ?, ?)";
        jdbc.update(INSERT_GAME, game.getGameId(), game.getGeneratedNumber(), game.isStatus());
        
    }
    
    @After
    public void tearDown() {
        final String DELETE_ROUND = "DELETE FROM Round";
        jdbc.update(DELETE_ROUND);
        

    }

    @Test
    public void testAddRound() {
        //line 70 is used to find the last game id made in the test SQL database
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Game game = gameDao.getGame(id);
        
        Round round = new Round(Timestamp.valueOf(LocalDateTime.now()), "5431", "e;3", "p;0");
        Round addedRound = roundDao.addRound(round, game.getGameId() );
        
        assertEquals(addedRound, round);
    }

    @Test
    public void testGetRoundsByGameId() {
        
         int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Game game = gameDao.getGame(id);
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        time.setNanos(0);
        
        Round round1 = new Round(time, "5431", "e;3", "p;0");
        Round addedRound1 = roundDao.addRound(round1, game.getGameId() );
        
        time = Timestamp.valueOf(LocalDateTime.now());
        time.setNanos(0);
        
        Round round2 = new Round(time, "6548", "e;0", "p;1");
        Round addedRound2 = roundDao.addRound(round2, game.getGameId() );
        
        List<Round> rounds = roundDao.getRoundsByGameId(game.getGameId());
        
        assertEquals(rounds.get(0), addedRound1);
        assertEquals(rounds.get(1), addedRound2);
        
    }
    
}
