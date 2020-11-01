/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Guess;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Jtooleyful
 */
public class GuessDaoDb implements GuessDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Guess addGuess() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Guess getGuess(int guessId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class GuessMapper implements RowMapper<Guess> {

        @Override
        public Guess mapRow(ResultSet rs, int index) throws SQLException {
            Guess guess = new Guess();
            guess.setGuessId(rs.getInt("GuessId"));
            guess.setNumberGuess(rs.getInt("NumberGuess"));
            
            return guess;

        }
    }
}
