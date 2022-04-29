/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSightings.daos;

import com.sg.SuperheroSightings.daos.SuperheroDaoDB.SuperheroMapper;
import com.sg.SuperheroSightings.entities.Organization;
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
public class OrganizationDaoDB implements OrganizationDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationById(int id) {
        try {
            final String GET_ORGANIZATION_BY_ID = "SELECT * FROM organizations WHERE id = ?";
            return jdbc.queryForObject(GET_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String GET_ALL_ORGANIZATIONS = "SELECT * FROM organizations";
        return jdbc.query(GET_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO organizations(name, description, address, contact) " +
                "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        return organization;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organizations SET name = ?, description = ?, " +
                "address = ?, contact = ? WHERE id = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact(),
                organization.getId());
    }
    
    @Override
    @Transactional
    public void updateSupeOrgan(int supeId, int organId) {
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE so.* FROM superhero_organization so "
                + "WHERE organizationId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, organId);
        final String UPDATE_SUPERHERO_ORGANIZATION = "INSERT INTO "
                + "superhero_organization(superheroId, organizationId) VALUES"
                + "(?,?)";
        jdbc.update(UPDATE_SUPERHERO_ORGANIZATION, supeId, organId);
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE FROM superhero_organization WHERE organizationId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, id);
        
        final String DELETE_ORGANIZATION = "DELETE FROM organizations WHERE Id = ?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }

    @Override
    public List<Superhero> getAllSuperheroesForOrganization(int organId) {
        final String SELECT_SUPERHEROES_FOR_ORGANIZATION = "SELECT s.* FROM superhero s JOIN "
                + "superhero_organization so ON so.superheroId = s.Id WHERE so.organizationId = ?";
        List<Superhero> superhero = jdbc.query(SELECT_SUPERHEROES_FOR_ORGANIZATION, 
                new SuperheroMapper(), organId);
        return superhero;
    }
    
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            organization.setContact(rs.getString("contact"));
            
            return organization;
        }
    }
    
}
