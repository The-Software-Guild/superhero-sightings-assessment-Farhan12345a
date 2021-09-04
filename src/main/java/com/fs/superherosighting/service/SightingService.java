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
import com.fs.superherosighting.entities.Organization;
import com.fs.superherosighting.entities.Sighting;
import com.fs.superherosighting.entities.SuperHeroVillan;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author farhanshahbaz
 */
@Service
public class SightingService {
    @Autowired
    SuperHeroVillanDao superHeroVillanDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    
    
    //Pass through functions
    public Sighting getSightingById(int id){
        return sightingDao.getSightingById(id);      
    }
    public List<Sighting> getAllSightings(){
        return sightingDao.getAllSightings();
    }
    public Sighting addSighting(Sighting sighting){
        return sightingDao.addSighting(sighting);
    }
    public void updateSighting(Sighting sighting){
        sightingDao.updateSighting(sighting);
    }
    public void deleteSightingById(int id){
        sightingDao.deleteSightingById(id);
    }
//    public HashMap<Sighting, SuperHeroVillan> mapHeroSightings(List<Sighting> sightings){
//        HashMap<Sighting, SuperHeroVillan> heroSightings = new HashMap<>();
//        for(int i=0;i<sightings.size();i++){
//            heroSightings.put(sightings.get(i), getHeroForSighting(sightings.get(i)));
//        }
//        return heroSightings;
//    }
//    
//     public SuperHeroVillan getHeroForSighting(Sighting sighting){
//         //get sighting for hero method
//         
//        return superHeroVillanDao.getHeroForSighting(sighting);
//    }
    
    
}
