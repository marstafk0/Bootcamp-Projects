/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Power;
import com.sg.SuperheroSightings.entities.Superhero;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface SuperheroDao {
    
    Superhero getSuperheroById(int id);
    List<Superhero> getAllSuperheroes();
    Superhero addSuperhero(Superhero superhero);
    void updateSuperhero(Superhero superhero);
    void deleteSuperheroById(int id);
    void addSupeToOrgan(int supeId, int organId);
    void addPowerToSupe(int supeId, int powerId);
    void updateSupeOrgan(int supeId);
    void updatePowerSupe(int supeId);
    
    List<Location> getAllLocationsForSuperhero(int id);
    List<Organization> getAllOrganizationsForSuperhero(int id);
    List<Power> getAllPowersForSuperhero(int id);
    
}
