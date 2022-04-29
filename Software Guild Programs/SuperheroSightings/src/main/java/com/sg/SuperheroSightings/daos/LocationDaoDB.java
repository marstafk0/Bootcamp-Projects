/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.daos.SuperheroDaoDB.SuperheroMapper;
import com.sg.SuperheroSightings.entities.Location;
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
public class LocationDaoDB implements LocationDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int id) {
        try {
            final String GET_LOCATION_BY_ID = "SELECT * FROM locations WHERE id = ?";
            return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String GET_ALL_LOCATIONS = "SELECT * FROM locations";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO locations(name, description, address, longitude, latitude) " +
                "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLongitude(),
                location.getLatitude());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE locations SET name = ?, description = ?, " +
                "address = ?, longitude = ?, latitude = ? WHERE id = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLongitude(),
                location.getLatitude(),
                location.getId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {       
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE locationId = ?";
        jdbc.update(DELETE_SIGHTING, id);
        
        final String DELETE_LOCATION = "DELETE FROM locations WHERE id = ?";
        jdbc.update(DELETE_LOCATION, id);
    }
    
    @Override
    public List<Superhero> getAllSuperheroesForLocation(int locationId) {
        final String SELECT_ALL_SUPERHEROES_SEEN_AT_LOCATION = "SELECT s.* FROM superhero s JOIN " +
            "sightings st ON st.superheroId = s.Id WHERE st.locationId = ?";
        List<Superhero> supes = jdbc.query(SELECT_ALL_SUPERHEROES_SEEN_AT_LOCATION, 
                new SuperheroMapper(), locationId);
        return supes;
    }
    
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLongitude(rs.getString("longitude"));
            location.setLatitude(rs.getString("latitude"));
            
            return location;
        }
    }
    
}
