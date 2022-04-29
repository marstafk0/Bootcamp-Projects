/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.ServiceLayers;

import com.sg.SuperheroSightings.daos.PowerDao;
import com.sg.SuperheroSightings.entities.Power;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author boss_
 */
@Service
public class PowerServiceLayer {
    
    @Autowired
    PowerDao powers;
    
    public Power getPowerById(int id) {
        
        return powers.getPowerById(id);
        
    }
    
    public List<Power> getAllPowers() {
        
        return powers.getAllPowers();
        
    }
    
    public Power addPower(Power power) {
        
        return powers.addPower(power);
        
    }
    
    public void updatePower(Power power) {
        
        powers.updatePower(power);
        
    }
    
    public void deletePowerById(int id) {
        
        powers.deletePowerById(id);
        
    }
    
    public List<Power> getAllPowersForSuperhero(int id) {
        
        return powers.getAllPowersForSuperhero(id);
        
    }
    
}
