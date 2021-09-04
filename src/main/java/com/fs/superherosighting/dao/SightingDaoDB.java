/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.dao.LocationDaoDB.LocationMapper;
import com.fs.superherosighting.entities.Location;
import com.fs.superherosighting.entities.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class SightingDaoDB implements SightingDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Sighting getSightingById(int sighting_id) {
        try {
            final String GET_SIGHTING_BY_ID = "SELECT * FROM sighting WHERE sighting_id = ?";
            Sighting sighting =  jdbc.queryForObject(GET_SIGHTING_BY_ID, new SightingMapper(), sighting_id);
            //Associate location
            sighting.setLocation(getParticularLocationSighting(sighting_id));
            return sighting;
        
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
//     private Location getLocation(int id){
//         //check query
//        final String SELECT_LOCATION = "SELECT loc.* FROM location loc "
//                + "JOIN sighting s ON s.LocationId = loc.LocationId WHERE s.SightingId = ?";
//        return jdbc.queryForObject(SELECT_LOCATION, new LocationDaoDB.LocationMapper(), id);
//    }

    private Location getParticularLocationSighting(int sighting_id){
        //Check query again (NOT TESTED)
        final String SELECT_LOCATIONS = "SELECT loc.* FROM location loc "
                + "JOIN sighting sight ON sight.location_id = loc.location_id WHERE sight.sighting_id = ?";
        return jdbc.queryForObject(SELECT_LOCATIONS, new LocationMapper(), sighting_id);
                //new LocationDaoDB.LocationMapper()
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String GET_ALL_SIGHTINGS = "SELECT * FROM sighting";
         List<Sighting> sightings = jdbc.query(GET_ALL_SIGHTINGS, new SightingMapper());
         associateLocations(sightings);
       
        return sightings;
    }
    
    //private?
     public void associateLocations(List<Sighting> sighthings){
        for (Sighting sighthing : sighthings) {
            sighthing.setLocation(getParticularLocationSighting(sighthing.getSighting_id()));
        }
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sighting(date, location_id) " +
                "VALUES(?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getDate(),
                sighting.getLocation_id());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSighting_id(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_Sighting = "UPDATE Sighting SET date = ?, location_id = ? " +
                "WHERE sighting_id = ?";
        jdbc.update(UPDATE_Sighting,
                sighting.getDate(),
                sighting.getLocation().getLocation_id(),//changed
                sighting.getSighting_id());
    }

    @Override
    public void deleteSightingById(int sighting_id) {
        final String DELETE_SUPER_SIGHTING = ("DELETE from super_sighting WHERE sighting_id = ? ");
        jdbc.update(DELETE_SUPER_SIGHTING, sighting_id);
       
        
        final String DELETE_Sighting = "DELETE FROM sighting WHERE sighting_id = ?";
        jdbc.update(DELETE_Sighting, sighting_id);
    }

    //Have COde for location, not DATE
    @Override
    public List<Sighting> getSightingForDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSighting_id(rs.getInt("sighting_id"));
            //Might have to change format
            sighting.setDate(rs.getString("date"));
            sighting.setLocation_id(rs.getInt("location_id"));
      
            return sighting;
        }
    }
}
