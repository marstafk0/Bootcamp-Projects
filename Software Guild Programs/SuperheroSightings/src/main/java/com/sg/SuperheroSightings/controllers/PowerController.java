/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.controllers;

import com.sg.SuperheroSightings.ServiceLayers.PowerServiceLayer;
import com.sg.SuperheroSightings.entities.Power;
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
public class PowerController {
    
    @Autowired
    PowerServiceLayer powerDao;
    
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Power>> violations = new HashSet<>();
    Set<ConstraintViolation<Power>> violations2 = new HashSet<>();
    
    @GetMapping("powers")
    public String displayPowers(Model model) {
        List<Power> powers = powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        model.addAttribute("errors", violations); 
        return "powers";
    }
    
    @PostMapping("addPower")
    public String addPower(HttpServletRequest request) {
        String name = request.getParameter("name");
 
        Power power = new Power();
        power.setName(name);
        
        violations = validate.validate(power);
        
        if (violations.isEmpty()) {
            powerDao.addPower(power);
        }
        return "redirect:/powers";
    }
    
    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
            
        powerDao.deletePowerById(id);
        return "redirect:/powers";
    }
    
    @GetMapping("editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Power power = powerDao.getPowerById(id);
        model.addAttribute("power", power);
        model.addAttribute("errors", violations2); 
        return "editPower"; 
    }
    
    @PostMapping("editPower")
    public String performEditPower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Power power = powerDao.getPowerById(id);
        
        power.setName(request.getParameter("name"));
        
        violations2 = validate.validate(power);
        
        if (violations2.isEmpty()) {
            powerDao.updatePower(power);
        } else {
            return "redirect:/editPower?id=" + power.getId(); 
        }
        return "redirect:/powers";
    }
    
}
