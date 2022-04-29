/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.guessthenumber.controllers;

import com.sg.marstafk.guessthenumber.model.Game;
import com.sg.marstafk.guessthenumber.model.Round;
import com.sg.marstafk.guessthenumber.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author boss_
 */
@RestController
@RequestMapping("/api")
public class Controller {
    
    @Autowired
    ServiceLayer service;
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
        return service.newGame();
    }
    
    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Round round) {
        return service.makeGuess(round);   
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable("gameId") int gameId) {
        return service.getGameById(gameId);
    }
    
    @GetMapping("/rounds/{gameId}")
    public List<Round> getRoundsForGame(@PathVariable("gameId") int gameId) {
        return service.getAllRoundsByGameId(gameId);
    }    
    
}
