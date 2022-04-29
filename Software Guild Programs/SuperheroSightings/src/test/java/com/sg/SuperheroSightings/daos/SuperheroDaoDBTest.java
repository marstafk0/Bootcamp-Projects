/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Power;
import com.sg.SuperheroSightings.entities.Sighting;
import com.sg.SuperheroSightings.entities.Superhero;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class SuperheroDaoDBTest {
   
    @Autowired
    SuperheroDao supe;
    
    @Autowired
    SightingDao sight;
    
    @Autowired
    OrganizationDao organ;
    
    @Autowired
    LocationDao loc;
    
    @Autowired
    PowerDao po;
    
    public SuperheroDaoDBTest() {
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
        
        List<Power> powers = po.getAllPowers();
        powers.forEach(power -> {
            po.deletePowerById(power.getId());
        });
        
    }

    /**
     * Test of getSuperheroById method, of class SuperheroDaoDB.
     */
    @Test
    public void testAddGetSuperhero() {
        
        Superhero hero = new Superhero();
        hero.setName("Superman");
        hero.setDescription("Strongest hero");
        
        supe.addSuperhero(hero);
        
        Superhero fromDao = supe.getSuperheroById(hero.getId());
        
        assertEquals(hero, fromDao);
        
    }

    /**
     * Test of getAllSuperheroes method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetAllSuperheroes() {
        
        Superhero hero = new Superhero();
        hero.setName("Superman");
        hero.setDescription("Strongest hero");
        
        supe.addSuperhero(hero);
        
        Superhero hero2 = new Superhero();
        hero2.setName("Wonder woman");
        hero2.setDescription("Amazon");
        
        supe.addSuperhero(hero2);
        
        List<Superhero> supes = supe.getAllSuperheroes();
        
        assertEquals(2, supes.size());
        assertTrue(supes.contains(hero));
        assertTrue(supes.contains(hero2));
        
    }

    /**
     * Test of addSupeToOrgan method, of class SuperheroDaoDB.
     */
    @Test
    public void testAddGetSupeToOrgan() {
        
        Superhero hero = new Superhero();
        hero.setName("Superman");
        hero.setDescription("Strongest hero");
        
        Organization org = new Organization();
        org.setName("evil justice");
        org.setDescription("evil");
        org.setAddress("somewhere");
        org.setContact("cell");
        
        Organization org2 = new Organization();
        org2.setName("justice");
        org2.setDescription("good");
        org2.setAddress("somewhere");
        org2.setContact("cell");
        
        supe.addSuperhero(hero);
        organ.addOrganization(org);
        organ.addOrganization(org2);
        supe.addSupeToOrgan(hero.getId(), org.getId());
        
        List<Organization> orgs = supe.getAllOrganizationsForSuperhero(hero.getId());
        
        assertTrue(orgs.contains(org));
        
        supe.updateSupeOrgan(hero.getId());
        supe.addSupeToOrgan(hero.getId(), org2.getId());
        
        orgs = supe.getAllOrganizationsForSuperhero(hero.getId());
        
        assertTrue(orgs.contains(org2));
        
    }

    /**
     * Test of updateSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testUpdateSuperhero() {
        
        Superhero hero = new Superhero();
        hero.setName("Superman");
        hero.setDescription("Strongest hero");
        
        supe.addSuperhero(hero);
        
        Superhero so = supe.getSuperheroById(hero.getId());
        
        assertEquals("Strongest hero", so.getDescription());
        
        hero.setDescription("Strongest superhero");
        
        supe.updateSuperhero(hero);
        
        Superhero s = supe.getSuperheroById(hero.getId());
        
        assertEquals("Strongest superhero", s.getDescription());
        
    }

    /**
     * Test of deleteSuperheroById method, of class SuperheroDaoDB.
     */
    @Test
    public void testDeleteSuperheroById() {
        
        Superhero hero = new Superhero();
        hero.setName("Superman");
        hero.setDescription("Strongest hero");
        
        supe.addSuperhero(hero);
        
        Superhero fromDao = supe.getSuperheroById(hero.getId());
        
        assertEquals(hero, fromDao);
        
        supe.deleteSuperheroById(fromDao.getId());
        
        assertNull(supe.getSuperheroById(hero.getId()));
        
    }

    /**
     * Test of getAllLocationsForSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetAllLocationsForSuperhero() {
        
        Superhero hero = new Superhero();
        hero.setName("Superman");
        hero.setDescription("Strongest hero");

        supe.addSuperhero(hero);
        
        Location loca = new Location();
        loca.setName("somwhere");
        loca.setDescription("somehting");
        loca.setAddress("place");
        loca.setLongitude("123");
        loca.setLatitude("123");
        
        loc.addLocation(loca);
        
        Sighting si = new Sighting();
        si.setSuperhero(hero);
        si.setLocation(loca);
        si.setDate("2020-01-01");
        
        sight.addSighting(si);
        
        List<Location> locss = supe.getAllLocationsForSuperhero(hero.getId());
        
        assertTrue(locss.contains(loca));
        
    }
    
}
