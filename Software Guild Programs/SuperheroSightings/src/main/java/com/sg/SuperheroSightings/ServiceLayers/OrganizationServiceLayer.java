/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.ServiceLayers;

import com.sg.SuperheroSightings.daos.OrganizationDao;
import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Superhero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author boss_
 */
@Service
public class OrganizationServiceLayer {
    
    @Autowired
    OrganizationDao organ;
    
    public Organization getOrganizationById(int id) {
        
        return organ.getOrganizationById(id);
        
    }
    
    public List<Organization> getAllOrganizations() {
        
        return organ.getAllOrganizations();
        
    }
    
    public Organization addOrganization(Organization organization) {
        
        return organ.addOrganization(organization);
        
    }
    
    public void updateOrganization(Organization organization) {
        
        organ.updateOrganization(organization);
        
    }
    
    public void updateSupeOrgan(int supeId, int organId) {
        
        organ.updateSupeOrgan(supeId, organId);
        
    }
    
    public void deleteOrganizationById(int id) {
        
        organ.deleteOrganizationById(id);
        
    }
    
    public List<Superhero> getAllSuperheroesForOrganization(int organId) {
        
        return organ.getAllSuperheroesForOrganization(organId);
        
    }
    
}
