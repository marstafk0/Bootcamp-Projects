/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Superhero;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface OrganizationDao {
    
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int id);
    void updateSupeOrgan(int supeId, int organId);
    
    List<Superhero> getAllSuperheroesForOrganization(int organId);
    
}
