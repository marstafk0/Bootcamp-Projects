/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.ServiceLayers;

import com.sg.SuperheroSightings.daos.LocationDao;
import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Superhero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author boss_
 */
@Service
public class LocationServiceLayer {
    
    @Autowired
    LocationDao loca;
    
    public Location getLocationById(int id) {
        
        return loca.getLocationById(id);
        
    }
    
    public List<Location> getAllLocations() {
        
        return loca.getAllLocations();
        
    }
    
    public Location addLocation(Location location) {
        
        return loca.addLocation(location);
        
    }
    
    public void updateLocation(Location location) {
        
        loca.updateLocation(location);
        
    }
    
    public void deleteLocationById(int id) {
        
        loca.deleteLocationById(id);
        
    }
    
    public List<Superhero> getAllSuperheroesForLocation(int locationId) {
        
        return loca.getAllSuperheroesForLocation(locationId);
        
    }
    
}
