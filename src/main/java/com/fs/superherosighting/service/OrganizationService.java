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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author farhanshahbaz
 */
@Service
public class OrganizationService {
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
    public Organization getLocationById(int id){
        return organizationDao.getOrganizationById(id);      
    }
    public List<Organization> getAllLocations(){
        return organizationDao.getAllOrganizations();
    }
    public Organization addLocation(Organization organization){
        return organizationDao.addOrganization(organization);
    }
    public void updateLocation(Organization organization){
        organizationDao.updateOrganization(organization);
    }
    public void deleteLocationById(int id){
        organizationDao.deleteOrganizationById(id);
    }
}
