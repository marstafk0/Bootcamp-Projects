/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.guessthenumber.data;

import com.sg.marstafk.guessthenumber.TestApplicationConfiguration;
import com.sg.marstafk.guessthenumber.model.Game;
import com.sg.marstafk.guessthenumber.model.Round;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author boss_
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoDBTest {
    
    @Autowired 
    JdbcTemplate jd;
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    @BeforeEach
    public void setUp() {
        
        jd.execute("DROP DATABASE IF EXISTS GuessTheNumberDBTest;");
        jd.execute("CREATE DATABASE GuessTheNumberDBTest;");
        jd.execute("USE GuessTheNumberDBTest;");
        jd.execute("CREATE TABLE game (" +
                "gameId INT PRIMARY KEY AUTO_INCREMENT," +
                "answer char(4)," +
                "finished BOOLEAN DEFAULT false);");
        jd.execute("CREATE TABLE round (" +
                "roundId INT PRIMARY KEY AUTO_INCREMENT," +
                "gameId INT NOT NULL," +
                "guessTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "guess CHAR(4)," +
                "result CHAR(7)," +
                "FOREIGN KEY fk_gameId (gameId) REFERENCES game(gameId));");
        
    }
    
    @Test
    public void testAddGetAll() {
        int gameId = 1;
        
        Game game = new Game();
        game.setAnswer("5678");
        game.setFinished(false);
        gameDao.addGame(game);
        
        Round round = new Round();
        round.setGuess("1234");
        round.setResult("e:0:p:0");
        round.setGameId(gameId);
        roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGuess("5678");
        round2.setResult("e:4:p:0");
        round2.setGameId(gameId);
        roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRoundsByGameId(gameId);

        assertEquals(2, rounds.size());
        assertNotNull(round = roundDao.getRoundById(round.getRoundId()));
    }
}