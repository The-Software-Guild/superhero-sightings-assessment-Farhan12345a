/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.service;


import com.fs.superherosighting.dao.LocationDao;
import com.fs.superherosighting.dao.OrganizationDao;
import com.fs.superherosighting.dao.SightingDao;
import com.fs.superherosighting.dao.SuperHeroVillanDao;
import com.fs.superherosighting.dao.SuperpowerDao;
import com.fs.superherosighting.entities.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author farhanshahbaz
 */
@Service
public class LocationService {
    @Autowired
    SuperHeroVillanDao superLocationVillanDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    //Pass through functions
    public Location getLocationById(int id){
        return locationDao.getLocationById(id);      
    }
    public List<Location> getAllLocations(){
        return locationDao.getAllLocations();
    }
    public Location addLocation(Location Location){
        return locationDao.addLocation(Location);
    }
    public void updateLocation(Location Location){
        locationDao.updateLocation(Location);
    }
    public void deleteLocationById(int id){
        locationDao.deleteLocationById(id);
    }
}
