/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.controllers;

import com.sg.SuperheroSightings.ServiceLayers.OrganizationServiceLayer;
import com.sg.SuperheroSightings.ServiceLayers.SuperheroServiceLayer;
import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Superhero;
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
public class OrganizationController {
    
    @Autowired
    OrganizationServiceLayer organDao;
    
    @Autowired
    SuperheroServiceLayer heroDao;
    
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Organization>> violations = new HashSet<>();
    Set<ConstraintViolation<Organization>> violations2 = new HashSet<>();
    
    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organDao.getAllOrganizations();
        List<Superhero> superheroes = heroDao.getAllSuperheroes();
        model.addAttribute("organizations", organizations);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("errors", violations); 
        return "organizations";
    }
    
    @GetMapping("organizationDetail")
    public String organizationDetail(Integer id, Model model) {
        Organization organization = organDao.getOrganizationById(id);
        List<Superhero> superhero = organDao.getAllSuperheroesForOrganization(id);       
        model.addAttribute("organization", organization);
        model.addAttribute("superhero", superhero); 
        return "organizationDetail";
    }
    
    @PostMapping("addOrganization") 
    public String addOrganization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        String[] supes = request.getParameterValues("superheroId");
        
        Organization organs = new Organization(); 
        organs.setName(name);
        organs.setDescription(description);
        organs.setAddress(address);
        organs.setContact(contact);
        
        violations = validate.validate(organs);
        
        if (violations.isEmpty()) {
            
            organDao.addOrganization(organs);
        
            if (supes[0].equalsIgnoreCase("none") || supes == null) {
                return "redirect:/organizations";
            } else {
                for (String supesIds : supes) {
                  heroDao.addSupeToOrgan(Integer.parseInt(supesIds), organs.getId());
                }
            } 
        } 
        return "redirect:/organizations";
    }
    
    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        organDao.deleteOrganizationById(id);
        return "redirect:/organizations";
    }
    
    @GetMapping("editOrganization")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organ = organDao.getOrganizationById(id);
        List<Superhero> superheroes = heroDao.getAllSuperheroes();
        List<Superhero> selected = organDao.getAllSuperheroesForOrganization(id);
        model.addAttribute("selectedSuperheroes", selected);
        model.addAttribute("organization", organ);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("errors", violations2);    
        return "editOrganization";  
    }
    
    @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organ = organDao.getOrganizationById(id);
        
        organ.setName(request.getParameter("name"));
        organ.setDescription(request.getParameter("description"));
        organ.setAddress(request.getParameter("address"));
        organ.setContact(request.getParameter("contact"));
        String[] supes = request.getParameterValues("superheroId");
        
        violations2 = validate.validate(organ); 
        
        if (violations2.isEmpty()) {
            
            organDao.updateOrganization(organ);
            
            if (supes[0].equalsIgnoreCase("none") || supes == null) {
                return "redirect:/organizations";
            } else {
                for (String supesIds : supes) {
                  heroDao.addSupeToOrgan(Integer.parseInt(supesIds), organ.getId());
                }
            }            
        } else {
            return "redirect:/editOrganization?id=" + organ.getId();
        }
        
        return "redirect:/organizations";
    }
    
}
