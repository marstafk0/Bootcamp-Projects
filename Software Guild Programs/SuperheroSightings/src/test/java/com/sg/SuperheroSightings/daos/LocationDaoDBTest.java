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
public class LocationDaoDBTest {
    
    @Autowired
    SuperheroDao supe;
    
    @Autowired
    SightingDao sight;
    
    @Autowired
    OrganizationDao organ;
    
    @Autowired
    LocationDao loc;
    
    public LocationDaoDBTest() {
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
     * Test of getLocationById method, of class LocationDaoDB.
     */
    @Test
    public void testAddGetAllLocationById() {
        
        Location loca = new Location();
        loca.setName("somwhere");
        loca.setDescription("somehting");
        loca.setAddress("place");
        loca.setLongitude("123");
        loca.setLatitude("123");
        
        loc.addLocation(loca);
        
        Location loca2 = new Location();
        loca2.setName("somwhere2");
        loca2.setDescription("somehting2");
        loca2.setAddress("place2");
        loca2.setLongitude("1232");
        loca2.setLatitude("1232");
        
        loc.addLocation(loca2);
        
        Location lo = loc.getLocationById(loca.getId());
        
        assertEquals(loca.getId(), lo.getId());
        
        List<Location> l = loc.getAllLocations();
        
        assertTrue(l.contains(lo));
        assertTrue(l.contains(loca2));
        
    }

    /**
     * Test of updateLocation method, of class LocationDaoDB.
     */
    @Test
    public void testUpdateLocation() {
        
        Location loca = new Location();
        loca.setName("somwhere");
        loca.setDescription("somehting");
        loca.setAddress("place");
        loca.setLongitude("123");
        loca.setLatitude("123");
        
        loc.addLocation(loca);
        
        Location lo = loc.getLocationById(loca.getId());
        
        assertEquals("somwhere", lo.getName());
        
        loca.setName("somewhere again");
        
        loc.updateLocation(loca);
        
        lo = loc.getLocationById(loca.getId());
        
        assertEquals("somewhere again", lo.getName());
        
    }

    /**
     * Test of deleteLocationById method, of class LocationDaoDB.
     */
    @Test
    public void testDeleteLocationById() {
        
        Location loca = new Location();
        loca.setName("somwhere");
        loca.setDescription("somehting");
        loca.setAddress("place");
        loca.setLongitude("123");
        loca.setLatitude("123");
        
        loc.addLocation(loca);
        
        Location lo = loc.getLocationById(loca.getId());
        
        assertNotNull(lo);
        
        loc.deleteLocationById(lo.getId());
        
        assertNull(loc.getLocationById(lo.getId()));
        
    }

    /**
     * Test of getAllSuperheroesForLocation method, of class LocationDaoDB.
     */
    @Test
    public void testGetAllSuperheroesForLocation() {
        
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
        
        List<Superhero> s = loc.getAllSuperheroesForLocation(loca.getId());
        
        assertTrue(s.contains(hero));
        
    }
    
}
