/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.daos.LocationDaoDB.LocationMapper;
import com.sg.SuperheroSightings.daos.SuperheroDaoDB.SuperheroMapper;
import com.sg.SuperheroSightings.entities.Location;
import com.sg.SuperheroSightings.entities.Sighting;
import com.sg.SuperheroSightings.entities.Superhero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoDB implements SightingDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired 
    static SuperheroDao sh;
        
    @Autowired
    static LocationDao l;

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String SELECT_COURSE_BY_ID = "SELECT * FROM sightings WHERE id = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_COURSE_BY_ID, new SightingMapper(), id);
            sighting.setSuperhero(getSuperheroForSighting(id));
            sighting.setLocation(getLocationForSighting(id));
            return sighting;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sightings";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateSuperheroesAndLocation(sightings);
        return sightings;
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sightings(superheroId, locationId, dateOf) "
                + "VALUES(?,?,?)";
        
        jdbc.update(INSERT_SIGHTING,
                sighting.getSuperhero().getId(),
                sighting.getLocation().getId(),
                sighting.getDate());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setId(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sightings SET superheroId = ?, locationId = ?,"
                + "dateOf = ? WHERE id = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getSuperhero().getId(),
                sighting.getLocation().getId(),
                sighting.getDate(),
                sighting.getId());
    }

    @Override
    @Transactional
    public void deleteSightingById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE id = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }

    private Superhero getSuperheroForSighting(int id) {
        final String SELECT_SUPERHERO_FOR_SIGHTING = "SELECT s.* FROM superhero s"
                + " JOIN sightings st ON st.superheroId = s.id WHERE st.id = ?";
        return jdbc.queryForObject(SELECT_SUPERHERO_FOR_SIGHTING, new SuperheroMapper(), id);
    }

    private Location getLocationForSighting(int id) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM locations l"
                + " JOIN sightings st ON st.locationId = l.id WHERE st.id = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationMapper(), id);
    }
    
    private void associateSuperheroesAndLocation(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setSuperhero(getSuperheroForSighting(sighting.getId()));
            sighting.setLocation(getLocationForSighting(sighting.getId()));
        }
    }

    @Override
    public List<Sighting> getSightingsByDate(String date) {
        final String SELECT_SIGHTINGS_BY_DATE = "SELECT * FROM sightings WHERE dateOf = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_BY_DATE, 
                new SightingMapper(), date);              
        associateSuperheroesAndLocation(sightings);
        return sightings;
    }
    
    public static final class SightingMapper implements RowMapper<Sighting> {
        
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("id"));
            
            LocalDate date = rs.getDate("dateOf").toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            formatter.format(date);

            sighting.setDate(formatter.format(date));
            
            return sighting;
        }
    }
    
}