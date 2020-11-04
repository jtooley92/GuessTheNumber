/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Jtooleyful
 */
public class RoundDaoDb implements RoundDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Round addRound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public static final class RoundMapper implements RowMapper<Round>{
         @Override
         public Round mapRow(ResultSet rs, int index) throws SQLException{
             Round round = new Round();
             round.setRoundId(rs.getInt("RoundId"));
             round.setNumberGuess(rs.getInt("NumberGuess"));
             round.setGuessResultExact(rs.getString("GuessResultExact"));
             round.setGuessResultPartial(rs.getString("GuessResultPartial"));
             round.setTime(rs.getTimestamp("Time").toLocalDateTime());
             
             return round;
         }
}
}