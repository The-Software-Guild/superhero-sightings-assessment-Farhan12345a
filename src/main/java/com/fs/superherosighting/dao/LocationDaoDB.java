/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.entities.Location;
import com.fs.superherosighting.entities.SuperHeroVillan;
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
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Location getLocationById(int location_id) {
        try {
            final String GET_Location_BY_ID = "SELECT * FROM location WHERE location_id = ?";
            return jdbc.queryForObject(GET_Location_BY_ID, new LocationMapper(), location_id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String GET_ALL_LocationS = "SELECT * FROM location";
        return jdbc.query(GET_ALL_LocationS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_Location = "INSERT INTO location(name, latitude, longitude, description, address) " +
                "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_Location,
                location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getDescription(),
                location.getAddress());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocation_id(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_Location = "UPDATE Location SET name = ?, description = ?, " +
                "address = ?,latitude = ?, longitude = ?  WHERE location_id = ?";
        jdbc.update(UPDATE_Location,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocation_id());
    }

    @Override
    @Transactional
    public void deleteLocationById(int location_id) {
        //Might not be right!!!
        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE location_id = ?";
        jdbc.update(DELETE_SIGHTING, location_id);
        
        final String DELETE_LOCATION = "DELETE FROM location WHERE location_id = ?";
        jdbc.update(DELETE_LOCATION, location_id);
        
        
    }

    @Override
    public List<Location> getLocationForSuperHeroVillan(SuperHeroVillan superHeroVillan) {
        final String SELECT_SUPER_FOR_LOCATION = "select \n" +
                        "location.name\n" +
                        "from location\n" +
                        "inner join sighting on location.location_id = sighting.location_id\n" +
                        "inner join super_sighting on super_sighting.super_id = sighting.sighting_id\n" +
                        "where super_sighting.super_id = ?;";
         List<Location> locations = jdbc.query(SELECT_SUPER_FOR_LOCATION, new LocationMapper(), superHeroVillan.getSuper_id());
         //MIGHT NEED HELPER (CHECK CODE-ALONG)
         
         return locations;
    
    }

    @Override
    public List<SuperHeroVillan> getSuperHeroVillanForLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocation_id(rs.getInt("location_id"));
            location.setName(rs.getString("name"));
            location.setLatitude(rs.getString("latitude"));
            location.setLongitude(rs.getString("longitude"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            
            
            return location;
        }
    }
}
