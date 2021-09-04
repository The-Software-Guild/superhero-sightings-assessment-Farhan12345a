/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.entities.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SuperpowerDaoDB implements SuperpowerDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Superpower getSuperpowerById(int superpower_id) {
        try {
            final String SELECT_SUPERPOWER_BY_ID = "SELECT * FROM superpower WHERE superpower_id = ?";
            return jdbc.queryForObject(SELECT_SUPERPOWER_BY_ID, new SuperpowerMapper(), superpower_id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String SELECT_ALL_SUPERPOWERS = "SELECT * FROM superpower";
        return jdbc.query(SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superpower) {
        final String INSERT_SUPERPOWER = "INSERT INTO superpower (name) "
                + "VALUES (? )";
        jdbc.update(INSERT_SUPERPOWER,
                superpower.getName());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setSuperpower_id(newId);
        return superpower;
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE superpower SET name = ?"
                + " WHERE superpower_id = ?";
        jdbc.update(UPDATE_SUPERPOWER,
                superpower.getName(),
                superpower.getSuperpower_id());
    }

    @Override
    @Transactional
    public void deleteSuperpowerById(int superpower_id) {
        
        final String DELETE_COURSE_STUDENT = "DELETE FROM superhero_supervillan WHERE superpower_id = ?";
        jdbc.update(DELETE_COURSE_STUDENT, superpower_id);
        
        final String DELETE_SUPERPOWER = "DELETE FROM superpower WHERE superpower_id = ?";
        jdbc.update(DELETE_SUPERPOWER, superpower_id);
    }

   
    
    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpower_id(rs.getInt("superpower_id"));
            superpower.setName(rs.getString("name"));

            return superpower;
        }
    }
}
