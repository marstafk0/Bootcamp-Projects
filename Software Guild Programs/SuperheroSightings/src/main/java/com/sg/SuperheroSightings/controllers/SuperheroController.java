/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.controllers;

import com.sg.SuperheroSightings.ServiceLayers.LocationServiceLayer;
import com.sg.SuperheroSightings.ServiceLayers.OrganizationServiceLayer;
import com.sg.SuperheroSightings.ServiceLayers.PowerServiceLayer;
import com.sg.SuperheroSightings.ServiceLayers.SuperheroServiceLayer;
import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Power;
import com.sg.SuperheroSightings.entities.Superhero;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author boss_
 */
@Controller
public class SuperheroController {
    
    @Autowired
    SuperheroServiceLayer heroDao;
    
    @Autowired
    OrganizationServiceLayer organDao;
    
    @Autowired
    LocationServiceLayer locDao;
    
    @Autowired
    PowerServiceLayer powerDao;
    
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Superhero>> violations = new HashSet<>();
    List<String> viola = new ArrayList();
    
    Set<ConstraintViolation<Superhero>> violations2 = new HashSet<>();
    List<String> viola2 = new ArrayList();

    @GetMapping("superheroes")
    public String displaySuperheroes(Model model) {
        List<Superhero> superheroes = heroDao.getAllSuperheroes();
        List<Organization> organ = organDao.getAllOrganizations();
        List<Power> power = powerDao.getAllPowers();
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("organizations", organ);
        model.addAttribute("powers", power);
        model.addAttribute("errors", violations);    
        model.addAttribute("errors2", viola); 
        return "superheroes"; 
    }
    
    @GetMapping("superheroDetail")
    public String superheroDetail(Integer id, Model model) {
        Superhero superhero = heroDao.getSuperheroById(id);
        List<Organization> organ = heroDao.getAllOrganizationsForSuperhero(id);
        List<Location> location = heroDao.getAllLocationsForSuperhero(id);
        List<Power> power = heroDao.getAllPowersForSuperhero(id);
        
        model.addAttribute("organization", organ);
        model.addAttribute("location", location);
        model.addAttribute("superhero", superhero); 
        model.addAttribute("power", power);
        return "superheroDetail"; 
    }
    
    @PostMapping("addSuperhero")
    public String addSuperhero(HttpServletRequest request, @RequestParam("image") MultipartFile file) throws IOException { 
        viola.clear();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] power = request.getParameterValues("powerId");
        String[] organs = request.getParameterValues("organizationId");
        
        byte[] byt = file.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(byt);
 
        Superhero superhero = new Superhero();
        superhero.setName(name);
        superhero.setDescription(description);
        superhero.setImage(encodedString);
        
        violations = validate.validate(superhero);
        
        if (violations.isEmpty()) {
            
            if (power != null) {
               heroDao.addSuperhero(superhero); 

                for (String powerIds : power) {
                    heroDao.addPowerToSupe(superhero.getId(), Integer.parseInt(powerIds));
                }

                if (organs[0].equalsIgnoreCase("none") || organs == null) {
                    return "redirect:/superheroes";
                } else {
                    for (String organIds : organs) {
                        heroDao.addSupeToOrgan(superhero.getId(), Integer.parseInt(organIds));
                    }
                } 
            } else {
                String PowerError = "Super power cannot be empty!";
                viola.add(PowerError);
            }                    
        }   
        return "redirect:/superheroes"; 
    }
    
    @GetMapping("deleteSuperhero")
    public String deleteSuperhero(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
            
        heroDao.deleteSuperheroById(id);
        return "redirect:/superheroes";
    }
    
    @GetMapping("editSuperhero")
    public String editSuperhero(Integer id, Model model) {
        Superhero superhero = heroDao.getSuperheroById(id);
        List<Organization> organ = organDao.getAllOrganizations();
        List<Power> powers = powerDao.getAllPowers();
        List<Power> selectedPowers = heroDao.getAllPowersForSuperhero(id);
        List<Organization> selectedOrganizations = heroDao.getAllOrganizationsForSuperhero(id);
        model.addAttribute("selectedPowers", selectedPowers);
        model.addAttribute("selectedOrganizations", selectedOrganizations);
        model.addAttribute("organizations", organ);
        model.addAttribute("superhero", superhero);
        model.addAttribute("powers", powers);
        model.addAttribute("errors", violations2);    
        model.addAttribute("errors2", viola2); 
        return "editSuperhero";     
    }
    
    @PostMapping("editSuperhero")
    public String performEditSuperhero(HttpServletRequest request, @RequestParam("image") MultipartFile file) throws IOException {
        viola2.clear();
        Superhero superhero = heroDao.getSuperheroById(Integer.parseInt(request.getParameter("id")));
        superhero.setName(request.getParameter("name"));
        superhero.setDescription(request.getParameter("description"));
        String[] power = request.getParameterValues("powerId");
        String[] organ = request.getParameterValues("organizationId");
        
        byte[] byt = file.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(byt);
        
        if (encodedString.equals("")) {
            encodedString = superhero.getImage();
        }
        
        superhero.setImage(encodedString);

        violations2 = validate.validate(superhero);
        
        if (violations2.isEmpty()) {
            
            if (power != null) {
                
                heroDao.updateSuperhero(superhero); 

                heroDao.updatePowerSupe(superhero.getId());   
                for (String powerIds : power) {
                    heroDao.addPowerToSupe(superhero.getId(), Integer.parseInt(powerIds));
                }

                if (organ[0].equalsIgnoreCase("none") || organ == null) {
                    heroDao.updateSupeOrgan(superhero.getId());
                    return "redirect:/superheroes";
                } else {
                    heroDao.updateSupeOrgan(superhero.getId());
                    for (String organIds : organ) {
                        heroDao.addSupeToOrgan(superhero.getId(), Integer.parseInt(organIds));
                    } 
                }
            } else {
                String PowerError = "Super power cannot be empty!";
                viola2.add(PowerError);
                return "redirect:/editSuperhero?id=" + superhero.getId();
            }                    
        } else {
            return "redirect:/editSuperhero?id=" + superhero.getId();
        }
        return "redirect:/superheroes";
    }
    
}
