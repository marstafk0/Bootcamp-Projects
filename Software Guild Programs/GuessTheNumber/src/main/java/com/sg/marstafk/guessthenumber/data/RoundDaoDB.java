/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.guessthenumber.data;

import com.sg.marstafk.guessthenumber.model.Round;
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
 * @author boss_
 */
@Repository
public class RoundDaoDB implements RoundDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
        try {
            final String GET_ALL_ROUNDS = "SELECT * FROM round WHERE gameId = ?"
                + "ORDER BY guessTime";
            return jdbc.query(GET_ALL_ROUNDS, new RoundMapper(), gameId); 
        } catch (DataAccessException ex) {
            return null;
        }        
    }

    @Override
    public Round getRoundById(int roundId) {
        try {
            final String GET_ROUND_BY_ID = "SELECT * FROM round WHERE roundId = ?";
            return jdbc.queryForObject(GET_ROUND_BY_ID, new RoundMapper(), roundId);
        } catch (DataAccessException ex) {
            return null;
        }            
    }

    @Override
    @Transactional
    public Round addRound(Round round) {
        try {
            final String ADD_ROUND = "INSERT INTO round(gameId, guess, result) VALUES(?,?,?)";
            jdbc.update(ADD_ROUND, round.getGameId(), round.getGuess(), round.getResult());
           // final String EDIT = "UPDATE round SET result = ? WHERE roundId = ?";
           // jdbc.update(EDIT, round.getResult(), round.getRoundId());
            
            int newRoundId = jdbc.queryForObject("SELECT LAST_INSERT_IS()", Integer.class);
            round.setRoundId(newRoundId);
            return getRoundById(newRoundId);
        } catch (DataAccessException ex) {
            return null;
        }  
    }
    
    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            round.setGuess(rs.getString("guess"));
            round.setGuessTime(rs.getTimestamp("guessTime").toLocalDateTime());
            round.setResult(rs.getString("result"));
            return round;
        }
        
    }
    
}
