/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.ServiceLayers;

import com.sg.SuperheroSightings.daos.SightingDao;
import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Sighting;
import com.sg.SuperheroSightings.entities.Superhero;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author boss_
 */
@Service
public class SightingServiceLayer {
    
    @Autowired
    SightingDao sight;
    
    public Sighting getSightingById(int id){
        
        return sight.getSightingById(id);
        
    }
    
    public List<Sighting> getAllSightings(){
        
        return sight.getAllSightings();
        
    }
    
    public Sighting addSighting(Sighting sighting) throws ParseException{
        
        return sight.addSighting(sighting);
        
    }
    
    public void updateSighting(Sighting sighting){
        
        sight.updateSighting(sighting);
        
    }
    
    public void deleteSightingById(int id){
        
        sight.deleteSightingById(id);
        
    }
    
    public List<Sighting> getSightingsByDate(String date){
        
        return sight.getSightingsByDate(date);
        
    }
    
}
