/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.controllers;

import com.sg.SuperheroSightings.ServiceLayers.LocationServiceLayer;
import com.sg.SuperheroSightings.ServiceLayers.SightingServiceLayer;
import com.sg.SuperheroSightings.ServiceLayers.SuperheroServiceLayer;
import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Sighting;
import com.sg.SuperheroSightings.entities.Superhero;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author boss_
 */
@Controller
public class SightingController {
    
    @Autowired
    SightingServiceLayer sightingDao;
    
    @Autowired
    SuperheroServiceLayer heroDao;
    
    @Autowired
    LocationServiceLayer locDao;
    
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();
    Set<ConstraintViolation<Sighting>> violations2 = new HashSet<>();
    List<String> viola = new ArrayList();
    List<String> viola1 = new ArrayList();
    
    @GetMapping("sightings")
    public String displaySightings(Model model){
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Superhero> superheroes = heroDao.getAllSuperheroes();
        List<Location> locations = locDao.getAllLocations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations); 
        model.addAttribute("errors1", viola1);
        model.addAttribute("errors2", viola); 
        return "sightings"; 
    }
    
    @GetMapping("sightingDetail")
    public String sightingDetail(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        Superhero superhero = heroDao.getSuperheroById(sighting.getSuperhero().getId());
        Location location = locDao.getLocationById(sighting.getLocation().getId());
        
        model.addAttribute("location", location);
        model.addAttribute("sighting", sighting); 
        model.addAttribute("superhero", superhero);
        return "sightingDetail"; 
    }
    
    @GetMapping("sightingsByDate")
    public String displaySightingsByDate(HttpServletRequest request, Model model) {
        viola.clear();
        String date = request.getParameter("searchDate");
        
        if (date == null) {
            viola.add("Please enter a date.");
            return "redirect:/sightings";
        } else if (!isValid(date)) {
            viola.add("Please enter date in specified format.");
            return "redirect:/sightings";
        }
        List<Sighting> sightings = sightingDao.getSightingsByDate(date);
        List<Superhero> superheroes = heroDao.getAllSuperheroes();
        List<Location> locations = locDao.getAllLocations();   
        model.addAttribute("sightings", sightings);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("locations", locations); 
        return "sightingsByDate";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) throws ParseException{
        viola1.clear();
        String superheroId = request.getParameter("superheroId");
        String locationId = request.getParameter("locationId");
        String userDate = request.getParameter("date");
        
        Sighting sighting = new Sighting();
        sighting.setSuperhero(heroDao.getSuperheroById(Integer.parseInt(superheroId)));
        sighting.setLocation(locDao.getLocationById(Integer.parseInt(locationId)));
        
        if (userDate == null) {
            viola1.add("Please enter a date.");
            return "redirect:/sightings";
        } else if (!isValid(userDate)) {
            viola1.add("Please enter date in specified format.");
            return "redirect:/sightings";
        }
        
        sighting.setDate(userDate);
        
        violations = validate.validate(sighting);
        
        if (violations.isEmpty()) {
           sightingDao.addSighting(sighting); 
        }
        return "redirect:/sightings";
    }
    
    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }
    
    @GetMapping("editSighting")
    public String editSighting(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightingDao.getSightingById(id);
        
        model.addAttribute("sighting", sighting);
        model.addAttribute("errors", violations2); 
        model.addAttribute("errors2", viola);
        return "editSighting";
    } 
    
    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request) {
        viola.clear();
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightingDao.getSightingById(id);
        
        String superheroId = request.getParameter("superheroId");
        sighting.setSuperhero(heroDao.getSuperheroById(Integer.parseInt(superheroId)));
        String locationId = request.getParameter("locationId");
        sighting.setLocation(locDao.getLocationById(Integer.parseInt(locationId))); 
        String userDate = request.getParameter("date");
        
        if (userDate == null) {
            viola.add("Please enter a date.");
            return "redirect:/sightings";
        } else if (!isValid(userDate)) {
            viola.add("Please enter date in specified format.");
            return "redirect:/sightings";
        }
        
        sighting.setDate(userDate);
        
        violations2 = validate.validate(sighting);
        
        if (violations2.isEmpty()) {
            sightingDao.updateSighting(sighting);
        } else {
            return "redirect:/editSighting?id=" + sighting.getId();
        }        
   
        return "redirect:/sightings"; 
    }

    private boolean isValid(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            formatter.format(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
}
