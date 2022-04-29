/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.guessthenumber.data;

import com.sg.marstafk.guessthenumber.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boss_
 */
@Repository
public class GameDaoDB implements GameDao {
    
    @Autowired 
    JdbcTemplate jdbc;

    @Override
    public List<Game> getAllGames() {
        final String GET_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(GET_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int gameId) {
        try {
            final String GET_GAME_BY_ID = "SELECT * FROM game WHERE gameId = ?";
            return jdbc.queryForObject(GET_GAME_BY_ID, new GameMapper(), gameId); 
        } catch (DataAccessException ex) {
            return null;
        } 
    }

    @Override
    @Transactional
    public Game addGame(Game game) {
        final String ADD_GAME = "INSERT INTO game(answer) VALUES (?)";
        jdbc.update(ADD_GAME, game.getAnswer());
        
        int newGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newGameId);
        return game;
    }

    @Override
    public Game updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET finished = ? WHERE gameId = ?";
        jdbc.update(UPDATE_GAME, game.isFinished(), game.getGameId());
        return game;
    }
    
    public static final class GameMapper implements RowMapper<Game> {
        
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
        
    }
    
}

