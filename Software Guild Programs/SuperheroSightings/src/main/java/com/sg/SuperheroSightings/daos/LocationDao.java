/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Superhero;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface LocationDao {
    
    Location getLocationById(int id);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationById(int id);
    
    List<Superhero> getAllSuperheroesForLocation(int locationId);
    
}
