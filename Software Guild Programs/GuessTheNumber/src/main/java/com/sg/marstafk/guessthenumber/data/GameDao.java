/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.guessthenumber.data;

import com.sg.marstafk.guessthenumber.model.Game;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface GameDao {
    
    List<Game> getAllGames();
    Game getGameById(int gameId);
    Game addGame(Game game);
    Game updateGame(Game game);
    
}
