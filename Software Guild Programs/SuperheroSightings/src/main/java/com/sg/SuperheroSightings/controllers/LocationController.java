/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.controllers;

import com.sg.SuperheroSightings.ServiceLayers.LocationServiceLayer;
import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Superhero;
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
public class LocationController {
    
    @Autowired
    LocationServiceLayer locationDao;
    
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    Set<ConstraintViolation<Location>> violations2 = new HashSet<>();
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations); 
        return "locations";
    }
    
    @GetMapping("locationDetail")
    public String locationDetail(Integer id, Model model) {
        Location location = locationDao.getLocationById(id);
        List<Superhero> supe = locationDao.getAllSuperheroesForLocation(id);
        model.addAttribute("location", location);
        model.addAttribute("superhero", supe); 
        return "locationDetail";
    }
    
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        
        Location location = new Location();
        location.setName(name);
        location.setDescription(description);
        location.setAddress(address);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        
        violations = validate.validate(location);
        
        if (violations.isEmpty()) {
            locationDao.addLocation(location);
        }
        
        return "redirect:/locations";
    }
    
    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        locationDao.deleteLocationById(id);
        return "redirect:/locations";
    }
    
    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);
        
        model.addAttribute("location", location);
        model.addAttribute("errors", violations2);
        return "editLocation";
    }
    
    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);
        
        location.setName(request.getParameter("name"));
        location.setDescription(request.getParameter("description"));
        location.setAddress(request.getParameter("address"));
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        
        violations2 = validate.validate(location);
        
        if (violations2.isEmpty()) {
            locationDao.updateLocation(location);
        } else {
            return "redirect:/editLocation?id=" + location.getId(); 
        }
        return "redirect:/locations";
    }
    
}
