/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.dao.SightingDaoDB.SightingMapper;
import com.fs.superherosighting.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.fs.superherosighting.entities.Location;
import com.fs.superherosighting.entities.Organization;
import com.fs.superherosighting.entities.Sighting;
import com.fs.superherosighting.entities.SuperHeroVillan;
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
public class SuperHeroVillanDaoDB implements SuperHeroVillanDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    SightingDaoDB sightingDao;
    
    @Override
    public SuperHeroVillan getSuperHeroVillanById(int super_id) {
        try {
            final String GET_SuperHeroVillan_BY_ID = "SELECT * FROM superhero_supervillan WHERE super_id = ?";
            SuperHeroVillan superHero = jdbc.queryForObject(GET_SuperHeroVillan_BY_ID, new SuperHeroVillanMapper(), super_id);
              superHero.setSuperpowers(getSuperpowersForHero(super_id));
              superHero.setSightings(getSightingsForSuperHeroVillan(super_id));
            return superHero;
        
        
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    public Superpower getSuperpowersForHero(int id) {
        //Check query on mysql
        final String SELECT_POWRS_FOR_SUPER = "SELECT super.* FROM superpower super "
                + "JOIN superhero_supervillan sv ON sv.superpower_id = super.superpower_id WHERE sv.super_id = ?";
        return jdbc.queryForObject(SELECT_POWRS_FOR_SUPER, new SuperpowerMapper(), id);
    }

    
    public List<Sighting> getSightingsForSuperHeroVillan(int id) {
        final String SELECT_SIGHTINGS_FOR_SUPER = "SELECT \n" +
                                        "	sighting.* \n" +
                                        "FROM sighting \n" +
                                        "JOIN super_sighting ON super_sighting.sighting_id = sighting.sighting_id\n" +
                                        "WHERE super_sighting.super_id = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_SUPER, new SightingMapper(), id);
        sightingDao.associateLocations(sightings); //might be prob
        return sightings;
    }


    @Override
    public List<SuperHeroVillan> getAllSuperHeroVillans() {
        final String GET_ALL_SuperHeroVillans = "SELECT * FROM superhero_supervillan";
        List<SuperHeroVillan> supers = jdbc.query(GET_ALL_SuperHeroVillans, new SuperHeroVillanMapper());
        associatePowersAndSightings(supers);
        return supers;
    }
    
    public void associatePowersAndSightings(List<SuperHeroVillan> supers) {
        for (SuperHeroVillan superHero : supers) {
            superHero.setSuperpowers(getSuperpowersForHero(superHero.getSuper_id()));
            superHero.setSightings(getSightingsForSuperHeroVillan(superHero.getSuper_id()));
        }
    }
    
//    @Override
//    public List<Course> associateHelper(Teacher teacher) {
//        final String SELECT_COURSES_FOR_TEACHER = "SELECT * FROM course WHERE teacherId = ?";
//        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_TEACHER, 
//                new CourseMapper(), teacher.getId());
//        associateTeacherAndStudents(courses);
//        return courses;
//    }

    @Override
    @Transactional
    public SuperHeroVillan addSuperHeroVillan(SuperHeroVillan superHeroVillan) {
        final String INSERT_SuperHeroVillan = "INSERT INTO superhero_supervillan(name, description, superpower_id, ishero ) " +
                "VALUES(?,?,?,?)";
        jdbc.update(INSERT_SuperHeroVillan,
                superHeroVillan.getName(),
                superHeroVillan.getDescription(),
                superHeroVillan.getSuperpowers().getSuperpower_id(),
                superHeroVillan.isIsHero());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superHeroVillan.setSuper_id(newId);
        //insertHeroSighting(superHeroVillan);
        return superHeroVillan;
    }
    
    private void insertHeroSighting(SuperHeroVillan superHeroVillan){
        final String INSERT_SUPER_SIGHTING = "INSERT INTO "
                + "super_sighting(super_id, sighting_id) VALUES(?,?)";
        for(Sighting sighting : superHeroVillan.getSightings()) {
            jdbc.update(INSERT_SUPER_SIGHTING, 
                    superHeroVillan.getSuper_id(),
                    sighting.getSighting_id());
        }
    }
    
    

    @Override
    public void updateSuperHeroVillan(SuperHeroVillan superHeroVillan) {
         final String UPDATE_SuperHeroVillan = "UPDATE superhero_supervillan SET name = ?, description = ?, " +
                "superpower_id = ?, ishero = ? WHERE super_id = ?";
        jdbc.update(UPDATE_SuperHeroVillan,
                superHeroVillan.getName(),
                superHeroVillan.getDescription(),
                superHeroVillan.getSuperpowers().getSuperpower_id(), 
                superHeroVillan.isIsHero(),
                superHeroVillan.getSuper_id());
        
        //change this up, might have to
        final String DELETE_SUPER_SIGHTING= "DELETE FROM super_sighting WHERE super_id = ?";
        jdbc.update(DELETE_SUPER_SIGHTING, superHeroVillan.getSuper_id());
        //check method specific
        insertHeroSighting(superHeroVillan);
        
        //misisng method

    }

    //Make sure this is right!!!!
    @Override
    @Transactional
    public void deleteSuperHeroVillanById(int super_id) {
        //FIGURE OUT RIGHT WAY TO DO THIS BASED OFF DIAGRAM!!! 
        //ERD DIAGRAM
        final String DELETE_SUPER_SIGHTING = "DELETE FROM super_sighting WHERE super_id = ?";
        jdbc.update(DELETE_SUPER_SIGHTING, super_id);
        
        final String DELETE_SUPER_ORGANIZATION= "DELETE FROM superhero_supervillan_organization WHERE super_id = ?";
        jdbc.update(DELETE_SUPER_ORGANIZATION, super_id);
        
        //NEED TO DELETE FROM SUPER POWER TABLE
        
        
        final String DELETE_SuperHeroVillan = "DELETE FROM superhero_supervillan WHERE super_id = ?";
        jdbc.update(DELETE_SuperHeroVillan, super_id);
    }

    @Override
    public List<SuperHeroVillan> getSuperHeroVillanForOrganization(Organization organization) {
        //QUERY NOT CHECKED!!!!
        final String SELECT_SUPERS_FOR_ORGANIZATION = "SELECT superVill.* FROM superhero_supervillan superVill "
                + "JOIN superhero_supervillan_organization sso ON sso.super_id = superVill.super_id "
                + "WHERE sso.organization_id = ?";
        
         List<SuperHeroVillan> supers = jdbc.query(SELECT_SUPERS_FOR_ORGANIZATION, 
                new SuperHeroVillanMapper(), organization.getOrganization_id());
         //Association
         associatePowersAndSightings(supers);
         return supers;
    }

    @Override
    public List<SuperHeroVillan> getSuperHeroVillanForLocation(Location location) {
        //QUERY NOT CHECKED!!!!
        final String SELECT_SUPERS_FOR_LOCATION = "SELECT super.* FROM superhero_supervillan super "
                + "JOIN super_sighting ss ON ss.super_id = super.super_id "
                + "JOIN sighting s ON s.sighting_id = ss.super_id "
                + "WHERE s.location_id = ?";
        
         List<SuperHeroVillan> supers = jdbc.query(SELECT_SUPERS_FOR_LOCATION, 
                new SuperHeroVillanMapper(), location.getLocation_id());
         //Association
         associatePowersAndSightings(supers);
         return supers;
    }
    
    //CHANGE!!!
    @Override
    public SuperHeroVillan getHeroForSighting(Sighting sighting) {
        final String SELECT_HEROS_FOR_SIGHTING = "SELECT h.* FROM superhero_supervillan hero JOIN "
                + "super_sighting s ON s.super_id = hero.super_id WHERE s.sighting_id = ?";
        
        SuperHeroVillan superHero = jdbc.queryForObject(SELECT_HEROS_FOR_SIGHTING, 
                new SuperHeroVillanMapper(), sighting.getSighting_id());
        
        superHero.setSuperpowers(getSuperpowersForHero(superHero.getSuper_id()));
        superHero.setSightings(getSightingsForSuperHeroVillan(superHero.getSuper_id()));
        return superHero;
    }

    
    public static final class SuperHeroVillanMapper implements RowMapper<SuperHeroVillan> {

        @Override
        public SuperHeroVillan mapRow(ResultSet rs, int index) throws SQLException {
            SuperHeroVillan superHeroVillan = new SuperHeroVillan();
            superHeroVillan.setSuper_id(rs.getInt("super_id"));
            superHeroVillan.setName(rs.getString("name"));
            superHeroVillan.setDescription(rs.getString("description"));
            //superHeroVillan.setSuperpower_id(rs.getInt("superpower_id"));
            superHeroVillan.setIsHero(rs.getBoolean("ishero"));
            
            return superHeroVillan;
        }
    }
    
}
