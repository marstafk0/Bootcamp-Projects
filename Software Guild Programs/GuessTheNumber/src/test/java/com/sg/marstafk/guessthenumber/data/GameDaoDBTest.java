/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.guessthenumber.data;

import com.sg.marstafk.guessthenumber.TestApplicationConfiguration;
import com.sg.marstafk.guessthenumber.model.Game;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
public class GameDaoDBTest {
    
    public GameDaoDBTest() {
    }
    
    @Autowired
    GameDao gameDao;
    
    @Autowired 
    JdbcTemplate jd;
    
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

    /**
     * Test of getAllGames method, of class GameDaoDB.
     */
    @Test
    public void testGetAllGames() {
        
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        gameDao.addGame(game);

        Game game2 = new Game();
        game2.setAnswer("5678");
        game2.setFinished(false);
        gameDao.addGame(game2);

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
        
    }

    /**
     * Test of getGameById method and addGame method, of class GameDaoDB.
     */
    @Test
    public void testGetAddGame() {
        
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);
        
    }

    /**
     * Test of updateGame method, of class GameDaoDB.
     */
    @Test
    public void testUpdateGame() {
        
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        game = gameDao.addGame(game);
        Game fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);

        game.setFinished(true);

        gameDao.updateGame(game);

        assertNotEquals(game, fromDao);

        fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);
        
    }
    
}
