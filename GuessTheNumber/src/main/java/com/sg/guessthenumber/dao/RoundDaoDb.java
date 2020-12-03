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
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jtooleyful
 */
@Repository
public class RoundDaoDb implements RoundDao {

    private final JdbcTemplate jdbc;
    
    @Autowired
     public RoundDaoDb(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Round addRound(Round round, int gameId) {
        final String INSERT_ROUND = "INSERT INTO Round(RoundId, Time, NumberGuess, GuessResultExact, GuessResultPartial, GameId) VALUES(?,?,?,?,?,?)";
        jdbc.update(INSERT_ROUND,
                round.getRoundId(),
                round.getTime(),
                round.getNumberGuess(),
                round.getGuessResultExact(),
                round.getGuessResultPartial(),
                gameId);
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newId);

        return round;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        final String GET_ROUNDS_BY_GAMEID = "SELECT * FROM round r"
                + " JOIN Game g ON r.GameId = g.GameId WHERE r.GameId = ?" + " ORDER BY Time ASC";
        
       return jdbc.query(GET_ROUNDS_BY_GAMEID, new RoundMapper(), gameId);
        
    }

    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("RoundId"));
            round.setNumberGuess(rs.getString("NumberGuess"));
            round.setGuessResultExact(rs.getString("GuessResultExact"));
            round.setGuessResultPartial(rs.getString("GuessResultPartial"));
            round.setTime(rs.getTimestamp("Time"));

            return round;
        }
    }
}
