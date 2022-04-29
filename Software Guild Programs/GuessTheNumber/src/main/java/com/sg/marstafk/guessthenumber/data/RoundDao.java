/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.guessthenumber.data;

import com.sg.marstafk.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface RoundDao {
    
    List<Round> getAllRoundsByGameId(int gameId);
    Round getRoundById(int roundId);
    Round addRound(Round round);
    
}
