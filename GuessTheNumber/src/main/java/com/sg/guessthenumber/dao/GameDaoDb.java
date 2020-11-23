/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dao.RoundDaoDb.RoundMapper;
import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jtooleyful
 */
@Repository
public class GameDaoDb implements GameDao {
    
private final JdbcTemplate jdbc;

    @Autowired
    public GameDaoDb(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    @Transactional
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO Game(GameId, GeneratedNumber, Status) VALUES(?, ?, ?)";
        jdbc.update(INSERT_GAME, game.getGameId(), game.getGeneratedNumber(), game.isStatus());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        
        return game;
    }

    @Override
    public Game getGame(int gameId) {
        try{
            final String SELECT_GAME_BY_ID = "SELECT* FROM Game WHERE GameId = ?";
            Game game = jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
            game.setRounds(getRoundsForGame(game));
            
            return game;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT* FROM Game";
        List<Game> games = jdbc.query(SELECT_ALL_GAMES, new GameMapper());
        addRoundToGames(games);
        
        return games;
    }

    @Override
    public Game updateStatus(int gameId) {
        Game game = new Game( gameId);
        game.setStatus(false);
        
        return game;
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("GameId"));
            game.setGeneratedNumber(rs.getString("GeneratedNumber"));
            game.setStatus(rs.getBoolean("Status"));
            

            return game;
        }

    }

    private List<Round> getRoundsForGame(Game game) {
        final String SELECT_ROUNDS_FOR_GAME = "SELECT r.* FROM Round r"
                + " JOIN Game g ON r.GameId = g.GameId WHERE r.GameId = ?";

        return jdbc.query(SELECT_ROUNDS_FOR_GAME, new RoundMapper(), game.getGameId());
    }
    
    private void addRoundToGames(List<Game> games){
        for(Game game: games){
            game.setRounds(getRoundsForGame(game));
        }
    }
}
