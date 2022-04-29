/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.controllers;

import com.sg.SuperheroSightings.ServiceLayers.LocationServiceLayer;
import com.sg.SuperheroSightings.ServiceLayers.SightingServiceLayer;
import com.sg.SuperheroSightings.ServiceLayers.SuperheroServiceLayer;
import com.sg.SuperheroSightings.entities.Sighting;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author boss_
 */
@Controller
public class IndexController {
    
    @Autowired
    SightingServiceLayer sightingDao;
    
    @Autowired
    SuperheroServiceLayer heroDao;
    
    @Autowired
    LocationServiceLayer locDao;
    
    @GetMapping()
    public String displaySightingsIndex(Model model){
        List<Sighting> sightings = sightingDao.getAllSightings();
        Collections.reverse(sightings);
        model.addAttribute("sightings", sightings);
        return "index"; 
    }
    
}
