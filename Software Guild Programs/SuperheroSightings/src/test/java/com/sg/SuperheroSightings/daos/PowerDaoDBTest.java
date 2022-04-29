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
public class PowerDaoDBTest {
    
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
    
    public PowerDaoDBTest() {
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
     * Test of getPowerById method, of class PowerDaoDB.
     */
    @Test
    public void testAddGetPowerById() {
        
        Power power = new Power();
        power.setName("Strength");
        
        po.addPower(power);
        
        Power fromDao = po.getPowerById(power.getId());
        
        assertEquals(power, fromDao);
        
    }

    /**
     * Test of getAllPowers method, of class PowerDaoDB.
     */
    @Test
    public void testGetAllPowers() {
        
        Power power = new Power();
        power.setName("Invincible");
        
        po.addPower(power);
        
        Power power2 = new Power();
        power2.setName("Wonder woman");
        
        po.addPower(power2);
        
        List<Power> powers = po.getAllPowers();
        
        assertEquals(2, powers.size());
        assertTrue(powers.contains(power));
        assertTrue(powers.contains(power2));
        
    }

    /**
     * Test of updatePower method, of class PowerDaoDB.
     */
    @Test
    public void testUpdatePower() {
        
        Power power = new Power();
        power.setName("Super speed");
        
        po.addPower(power);
        
        Power so = po.getPowerById(power.getId());
        
        assertEquals("Super speed", so.getName());
        
        power.setName("Super Speed 2");
        
        po.updatePower(power);
        
        Power s = po.getPowerById(power.getId());
        
        assertEquals("Super Speed 2", s.getName());
        
    }

    /**
     * Test of deletePowerById method, of class PowerDaoDB.
     */
    @Test
    public void testDeletePowerById() {
        
        Power power = new Power();
        power.setName("Invincibility");
        
        po.addPower(power);
        
        Power fromDao = po.getPowerById(power.getId());
        
        assertEquals(power, fromDao);
        
        po.deletePowerById(fromDao.getId());
        
        assertNull(po.getPowerById(power.getId()));
        
    }

    /**
     * Test of getAllPowersForSuperhero method, of class PowerDaoDB.
     */
    @Test
    public void testGetAllPowersForSuperhero() {
    }
    
}
