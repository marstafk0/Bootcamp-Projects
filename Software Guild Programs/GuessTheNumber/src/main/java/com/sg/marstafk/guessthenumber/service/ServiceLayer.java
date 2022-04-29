/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.guessthenumber.service;

import com.sg.marstafk.guessthenumber.data.GameDao;
import com.sg.marstafk.guessthenumber.data.RoundDao;
import com.sg.marstafk.guessthenumber.model.Game;
import com.sg.marstafk.guessthenumber.model.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author boss_
 */
@Service
public class ServiceLayer {
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        games.forEach(game -> {
            if (!game.isFinished()) {
                game.setAnswer("****");
            }
        });
        return games;
    }
    
    public Game getGameById(int gameId) {
        Game game = gameDao.getGameById(gameId);
        if (!game.isFinished()) {
            game.setAnswer("****");
        }
        return game;
    }
    
    public List<Round> getAllRoundsByGameId(int gameId) {
        return roundDao.getAllRoundsByGameId(gameId);
    }
    
    public int newGame() {
        Game game = new Game();
        game.setAnswer(randomNewAnswer());
        gameDao.addGame(game);
        return game.getGameId();
    }
    
    public Round makeGuess(Round round) {
        String answer = gameDao.getGameById(round.getGameId()).getAnswer();
        String guess = round.getGuess();
        String result = verifyGuess(guess, answer);
        round.setResult(result);
        
        if (guess.equals(answer)) {
            Game game = getGameById(round.getGameId());
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        return roundDao.addRound(round);
    }
    
    private String randomNewAnswer() {
        Random rnd = new Random();
        int one = rnd.nextInt(10);
        int two = rnd.nextInt(10);
        while (two == one) {
            two = rnd.nextInt(10);
        }
        int three = rnd.nextInt(10);
        while (three == one || three == two) {
            three = rnd.nextInt(10);
        }
        int four = rnd.nextInt(10);
        while (four == three || four == two || four == one) {
            four = rnd.nextInt(10);
        }
        String answer = String.valueOf(one) + String.valueOf(two) + String.valueOf(three) 
                + String.valueOf(four);
        
        return answer;
    }
    
    private String verifyGuess(String guess, String answer) {
        
        char[] guessChar = guess.toCharArray();
        char[] answerChar = answer.toCharArray();
        int exact = 0;
        int partial = 0;
        
        for (int i = 0; i < guessChar.length; i++) {
            if (answer.indexOf(guessChar[i]) > -1) {
                if (guessChar[i] == answerChar[i]) {
                    exact++;
                } else {
                    partial++;
                }
            }
        }        
        String result = "e:" + exact + ":p:" + partial;       
        return result;
    }
    
}
