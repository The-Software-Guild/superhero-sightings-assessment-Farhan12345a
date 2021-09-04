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
import com.fs.superherosighting.entities.SuperHeroVillan;
import com.fs.superherosighting.entities.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author farhanshahbaz
 */
@Service
public class SuperheroService {
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
    public SuperHeroVillan getSuperpowerById(int id){
        return superHeroVillanDao.getSuperHeroVillanById(id);      
    }
    public List<SuperHeroVillan> getAllSuperpowers(){
        return superHeroVillanDao.getAllSuperHeroVillans();
    }
    public SuperHeroVillan addHero(SuperHeroVillan superHeroVillan){
        return superHeroVillanDao.addSuperHeroVillan(superHeroVillan);
    }
    public void updateSuperpower(SuperHeroVillan superHeroVillan){
        superHeroVillanDao.updateSuperHeroVillan(superHeroVillan);
    }
    public void deleteSuperHeroVillannById(int id){
        superHeroVillanDao.deleteSuperHeroVillanById(id);
    }
}
