/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.daos.LocationDaoDB.LocationMapper;
import com.sg.SuperheroSightings.daos.OrganizationDaoDB.OrganizationMapper;
import com.sg.SuperheroSightings.daos.PowerDaoDB.PowerMapper;
import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Organization;
import com.sg.SuperheroSightings.entities.Power;
import com.sg.SuperheroSightings.entities.Superhero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boss_
 */
@Repository
public class SuperheroDaoDB implements SuperheroDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superhero getSuperheroById(int id) {
        try {
            final String GET_SUPERHERO_BY_ID = "SELECT * FROM superhero WHERE id = ?";
            return jdbc.queryForObject(GET_SUPERHERO_BY_ID, new SuperheroMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        final String GET_ALL_SUPERHEROES = "SELECT * FROM superhero";
        return jdbc.query(GET_ALL_SUPERHEROES, new SuperheroMapper());
    }

    @Override
    @Transactional
    public Superhero addSuperhero(Superhero superhero) {
        final String INSERT_SUPERHERO = "INSERT INTO superhero(name, description, image) " +
                "VALUES(?,?,?)";
        jdbc.update(INSERT_SUPERHERO,
                superhero.getName(),
                superhero.getDescription(),
                superhero.getImage());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superhero.setId(newId);
        return superhero;
    }
    
    @Override
    public void addSupeToOrgan(int supeId, int organId) {
        final String INSERT_SUPERHERO_INTO_ORGANIZATION = "INSERT INTO "
                + "superhero_organization(superheroId, organizationId) VALUES"
                + "(?,?)";
        jdbc.update(INSERT_SUPERHERO_INTO_ORGANIZATION, supeId, organId);
    }
    
    @Override
    public void addPowerToSupe(int supeId, int powerId) {
        final String INSERT_POWER_INTO_SUPERHERO = "INSERT INTO "
                + "superhero_power(superheroId, powerId) VALUES(?,?)";
        jdbc.update(INSERT_POWER_INTO_SUPERHERO, supeId, powerId);
    }
    
    @Override
    @Transactional
    public void updateSupeOrgan(int supeId) {
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE so.* FROM superhero_organization so "
                + "WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, supeId);
    }
    
    @Override
    @Transactional
    public void updatePowerSupe(int supeId) {
        final String DELETE_SUPERHERO_POWER = "DELETE sp.* FROM superhero_power sp "
                + "WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_POWER, supeId);
    }

    @Override
    public void updateSuperhero(Superhero superhero) {
        final String UPDATE_SUPERHERO = "UPDATE superhero SET name = ?, description = ?, image = ? " +
                "WHERE id = ?";
        jdbc.update(UPDATE_SUPERHERO,
                superhero.getName(),
                superhero.getDescription(),
                superhero.getImage(),
                superhero.getId());
    }

    @Override
    @Transactional
    public void deleteSuperheroById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE superheroId = ?";
        jdbc.update(DELETE_SIGHTING, id);
        
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE FROM superhero_organization "
                + "WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, id);
        
        final String DELETE_SUPERHERO_POWER = "DELETE FROM superhero_power "
                + "WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_POWER, id);
        
        final String DELETE_SUPERHERO = "DELETE FROM superhero WHERE id = ?";
        jdbc.update(DELETE_SUPERHERO, id);
    }

    @Override
    public List<Location> getAllLocationsForSuperhero(int id) {
        final String SELECT_LOCATIONS_FOR_SUPERHERO = "SELECT l.* FROM locations l JOIN " +
            "sightings st ON st.locationId = l.Id WHERE st.superheroId = ?";
        List<Location> location = jdbc.query(SELECT_LOCATIONS_FOR_SUPERHERO, 
                new LocationMapper(), id); 
        return location;
    }

    @Override
    public List<Organization> getAllOrganizationsForSuperhero(int id) {
        final String SELECT_ORGANIZATIONS_FOR_SUPERHERO = "SELECT o.* FROM organizations o JOIN "
                + "superhero_organization so ON so.organizationId = o.Id WHERE so.superheroId = ?";
        List<Organization> organization = jdbc.query(SELECT_ORGANIZATIONS_FOR_SUPERHERO, 
                new OrganizationMapper(), id);
        return organization;
    }
    
    @Override
    public List<Power> getAllPowersForSuperhero(int id) {
        final String SELECT_POWER_FOR_SUPERHERO = "SELECT p.* FROM power p"
                + " JOIN superhero_power sp ON sp.powerId = p.id WHERE sp.superheroId = ?";
        return jdbc.query(SELECT_POWER_FOR_SUPERHERO, new PowerMapper(), id);
    }
    
    public String getSupeImage(int id) {
        Superhero supe = getSuperheroById(id);
        return supe.getImage();
    }
    
    public static final class SuperheroMapper implements RowMapper<Superhero> {

        @Override
        public Superhero mapRow(ResultSet rs, int index) throws SQLException {
            Superhero superhero = new Superhero(); 
            superhero.setId(rs.getInt("id"));
            superhero.setName(rs.getString("name"));
            superhero.setDescription(rs.getString("description"));
            superhero.setImage(rs.getString("image"));
            
            return superhero;
        }
    }
    
}
