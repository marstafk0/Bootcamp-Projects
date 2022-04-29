/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.ServiceLayers;

import com.sg.SuperheroSightings.daos.SuperheroDao;
import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Power;
import com.sg.SuperheroSightings.entities.Superhero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author boss_
 */
@Service
public class SuperheroServiceLayer {
    
    @Autowired
    SuperheroDao hero;
    
    public Superhero getSuperheroById(int id) {
        
        return hero.getSuperheroById(id);
        
    }
    
    public List<Superhero> getAllSuperheroes() {
        
        return hero.getAllSuperheroes();
        
    }
    
    public Superhero addSuperhero(Superhero superhero) {
        
        return hero.addSuperhero(superhero);
        
    }
    
    public void addSupeToOrgan(int supeId, int organId) {
        
        hero.addSupeToOrgan(supeId, organId);
        
    }
    
    public void addPowerToSupe(int supeId, int powerId) {
        
        hero.addPowerToSupe(supeId, powerId);
        
    }
    
    public void updateSupeOrgan(int supeId) {
        
        hero.updateSupeOrgan(supeId);
        
    }
    
    public void updatePowerSupe(int supeId) {
        
        hero.updatePowerSupe(supeId);
        
    }
    
    public void updateSuperhero(Superhero superhero) {
        
        hero.updateSuperhero(superhero);
        
    }
    
    public void deleteSuperheroById(int id) {
        
        hero.deleteSuperheroById(id);
        
    }
    
    public List<Location> getAllLocationsForSuperhero(int id) {
        
        return hero.getAllLocationsForSuperhero(id);
        
    }
    
    public List<Organization> getAllOrganizationsForSuperhero(int id) {
        
        return hero.getAllOrganizationsForSuperhero(id);
        
    }
    
    public List<Power> getAllPowersForSuperhero(int id) {
        
        return hero.getAllPowersForSuperhero(id);
        
    }
    
}
