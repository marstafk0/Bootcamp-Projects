/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Sighting;
import com.sg.SuperheroSightings.entities.Superhero;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author boss_
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrganizationDaoDBTest {
    
    @Autowired
    SuperheroDao supe;
    
    @Autowired
    SightingDao sight;
    
    @Autowired
    OrganizationDao organ;
    
    @Autowired
    LocationDao loc;
    
    public OrganizationDaoDBTest() {
    }
    
    @BeforeEach
    public void setUp() {
        
        List<Superhero> superheroes = supe.getAllSuperheroes();
        superheroes.forEach(superhero -> {
            supe.deleteSuperheroById(superhero.getId());
        });
        
        List<Sighting> sightings = sight.getAllSightings();
        sightings.forEach(sighting -> {
            supe.deleteSuperheroById(sighting.getId());
        });
        
        List<Organization> organizations = organ.getAllOrganizations();
        organizations.forEach(organization -> {
            supe.deleteSuperheroById(organization.getId());
        });
        
        List<Location> locations = loc.getAllLocations();
        locations.forEach(location -> {
            supe.deleteSuperheroById(location.getId());
        });
        
    }

    /**
     * Test of getOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddGetAllOrganizationById() {
        
        Organization org = new Organization();
        org.setName("evil justice");
        org.setDescription("evil");
        org.setAddress("somewhere");
        org.setContact("cell");
        
        organ.addOrganization(org);
        
        Organization org2 = new Organization();
        org2.setName("justice");
        org2.setDescription("good");
        org2.setAddress("somewhere");
        org2.setContact("cell");
        
        organ.addOrganization(org2);
        
        Organization o = organ.getOrganizationById(org.getId());
        
        assertEquals(org.getId(), o.getId());
        
        List<Organization> os = organ.getAllOrganizations();
        
        assertTrue(os.contains(o));
        assertTrue(os.contains(org2));
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
        
        Organization org = new Organization();
        org.setName("evil justice");
        org.setDescription("evil");
        org.setAddress("somewhere");
        org.setContact("cell");
        
        organ.addOrganization(org);
        
        Organization fromDao = organ.getOrganizationById(org.getId());
        
        assertEquals("evil justice", fromDao.getName());
        
        org.setName("cool justice");
        
        organ.updateOrganization(org);
        
        Organization fromDao2 = organ.getOrganizationById(org.getId());
        
        assertEquals("cool justice", fromDao2.getName());
        
    }

    /**
     * Test of updateSupeOgran method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateSupeOrgan() {
        
        Superhero hero = new Superhero();
        hero.setName("Superman");
        hero.setDescription("Strongest hero");
        
        Superhero hero2 = new Superhero();
        hero2.setName("Dude");
        hero2.setDescription("Strongestest hero");
        
        Organization org = new Organization();
        org.setName("evil justice");
        org.setDescription("evil");
        org.setAddress("somewhere");
        org.setContact("cell");

        supe.addSuperhero(hero);
        supe.addSuperhero(hero2);
        organ.addOrganization(org);
        supe.addSupeToOrgan(hero.getId(), org.getId());
        
        List<Superhero> orgs = organ.getAllSuperheroesForOrganization(org.getId());
        
        assertTrue(orgs.contains(hero));
        
        organ.updateSupeOrgan(hero2.getId(), org.getId());
        
        orgs = organ.getAllSuperheroesForOrganization(org.getId());
        
        assertTrue(orgs.contains(hero2));
        
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
        
        Organization org = new Organization();
        org.setName("evil justice");
        org.setDescription("evil");
        org.setAddress("somewhere");
        org.setContact("cell");
        
        organ.addOrganization(org);
        
        Organization o = organ.getOrganizationById(org.getId());
        
        assertEquals(org.getId(), o.getId());
        
        organ.deleteOrganizationById(o.getId());
        
        assertNull(organ.getOrganizationById(o.getId()));
        
    }
    
}
