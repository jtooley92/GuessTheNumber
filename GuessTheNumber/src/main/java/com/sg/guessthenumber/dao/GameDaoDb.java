/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Jtooleyful
 */
public class GameDaoDb implements GameDao {

    @Autowired
    private JdbcTemplate jdbc;
    
    private static final class GameMapper implements RowMapper<Game>{
         @Override
         public Game mapRow(ResultSet rs, int index) throws SQLException{
             Game game = new Game();
             game.setGameId(rs.getInt("GameId"));
             game.setGeneratedNumber(rs.getInt("GeneratedNumber"));
            
         }
    }
}
