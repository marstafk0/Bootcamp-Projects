/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.entities.Power;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface PowerDao {
    
    Power getPowerById(int id);
    List<Power> getAllPowers();
    Power addPower(Power power);
    void updatePower(Power power);
    void deletePowerById(int id);
    
    List<Power> getAllPowersForSuperhero(int id);
    
}
