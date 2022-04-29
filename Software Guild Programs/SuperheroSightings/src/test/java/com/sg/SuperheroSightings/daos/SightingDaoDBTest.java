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
public class SightingDaoDBTest {
    
    @Autowired
    SuperheroDao supe;
    
    @Autowired
    SightingDao sight;
    
    @Autowired
    OrganizationDao organ;
    
    @Autowired
    LocationDao loc;
    
    public SightingDaoDBTest() {
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
     * Test of getAllSightings method, of class SightingDaoDB.
     */
    @Test
    public void testAddGetAllSightingsById() {
        
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
        
        Sighting si2 = new Sighting();
        si2.setSuperhero(hero);
        si2.setLocation(loca);
        si2.setDate("2020-02-02");
        
        sight.addSighting(si2);
        
        Sighting fromDao = sight.getSightingById(si.getId());
        
        assertEquals(si.getLocation(), fromDao.getLocation());
        
        List<Sighting> allSights = sight.getAllSightings();
        
        assertEquals(si.getId(), allSights.get(0).getId());
        assertEquals(si2.getId(), allSights.get(1).getId());
        
    }

    /**
     * Test of updateSighting method, of class SightingDaoDB.
     */
    @Test
    public void testUpdateSighting() {
        
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
        
        Sighting fromDao = sight.getSightingById(si.getId());
        
        assertEquals("01/01/2020", fromDao.getDate());
        
        si.setSuperhero(hero);
        si.setLocation(loca);
        si.setDate("2020-02-02");
        
        sight.updateSighting(si);
        
        Sighting fromDao2 = sight.getSightingById(si.getId());
        
        assertEquals("02/02/2020", fromDao2.getDate());
        
    }

    /**
     * Test of deleteSightingById method, of class SightingDaoDB.
     */
    @Test
    public void testDeleteSightingById() {
        
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
        
        Sighting fromDao = sight.getSightingById(si.getId());
        
        assertEquals(si.getId(), fromDao.getId());
        
        sight.deleteSightingById(fromDao.getId());
        
        assertNull(sight.getSightingById(fromDao.getId()));
        
    }

    /**
     * Test of getSightingsByDate method, of class SightingDaoDB.
     */
    @Test
    public void testGetSightingsByDate() {
        
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
        
        List<Sighting> sights = sight.getSightingsByDate("2020-01-01");
        
        assertEquals(si.getId(), sights.get(0).getId());
        
    }
    
}
