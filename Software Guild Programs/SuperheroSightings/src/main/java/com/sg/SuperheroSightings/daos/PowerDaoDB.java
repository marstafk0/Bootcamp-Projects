/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.entities.Power;
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
public class PowerDaoDB implements PowerDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Power getPowerById(int id) {
        try {
            final String GET_POWER_BY_ID = "SELECT * FROM power WHERE id = ?";
            return jdbc.queryForObject(GET_POWER_BY_ID, new PowerMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        final String GET_ALL_POWERS = "SELECT * FROM power";
        return jdbc.query(GET_ALL_POWERS, new PowerMapper());
    }

    @Override
    @Transactional
    public Power addPower(Power power) {
        final String INSERT_POWER = "INSERT INTO power(name) " +
                "VALUES(?)";
        jdbc.update(INSERT_POWER,
                power.getName());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setId(newId);
        return power;
    }

    @Override
    public void updatePower(Power power) {
        final String UPDATE_POWER = "UPDATE power SET name = ? WHERE id = ?";
        jdbc.update(UPDATE_POWER,
                power.getName(),
                power.getId());
    }

    @Override
    @Transactional
    public void deletePowerById(int id) {
        final String DELETE_SUPERHERO_POWER = "DELETE FROM superhero_power "
                + "WHERE powerId = ?";
        jdbc.update(DELETE_SUPERHERO_POWER, id);
        
        final String DELETE_POWER = "DELETE FROM power WHERE id = ?";
        jdbc.update(DELETE_POWER, id);
    }

    @Override
    public List<Power> getAllPowersForSuperhero(int id) {
        final String SELECT_POWERS_FOR_SUPERHERO = "SELECT p.* FROM power p JOIN "
                + "superhero_power sp ON sp.powerId = p.Id WHERE so.superheroId = ?";
        List<Power> power = jdbc.query(SELECT_POWERS_FOR_SUPERHERO, 
                new PowerMapper(), id);
        return power;
    }
    
    public static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int index) throws SQLException {
            Power power = new Power(); 
            power.setId(rs.getInt("id"));
            power.setName(rs.getString("name"));
            
            return power;
        }
    }
    
}
