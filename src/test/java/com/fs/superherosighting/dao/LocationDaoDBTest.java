/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.entities.Location;
import com.fs.superherosighting.entities.Organization;
import com.fs.superherosighting.entities.Sighting;
import com.fs.superherosighting.entities.SuperHeroVillan;
import com.fs.superherosighting.entities.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author farhanshahbaz
 */
@SpringBootTest
public class LocationDaoDBTest {
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperHeroVillanDao superHeroVillanDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    public LocationDaoDBTest() {
    }
    
   
    
    @BeforeEach 
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocationById(location.getLocation_id());
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getOrganization_id());
        }
        
        List<SuperHeroVillan> superHeroVillans = superHeroVillanDao.getAllSuperHeroVillans();
        for(SuperHeroVillan superHeroVillan : superHeroVillans) {
            superHeroVillanDao.deleteSuperHeroVillanById(superHeroVillan.getSuper_id());
        }
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getSuperpower_id());
        }
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSighting_id());
        }
        
    }

    
    @Test
    public void testAddAndGetlocation() {
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocation_id());
        
        assertEquals(location, fromDao);
    }

    @Test
    public void testGetAlllocations() {
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
        
        Location location2 = new Location();
        location2.setName("Test Name 2");
        location2.setLatitude("1123212123");
        location2.setLongitude(("11234431243"));
        location2.setDescription("Test Description  2");
        location2.setAddress("Test Address 2");
        location2 = locationDao.addLocation(location2);
        
        List<Location> locations = locationDao.getAllLocations();
        
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }

    @Test
    public void testUpdatelocation() {
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocation_id());
        assertEquals(location, fromDao);
        
        location.setAddress("New Test Address");
        locationDao.updateLocation(location);
        
        assertNotEquals(location, fromDao);
        
        fromDao = locationDao.getLocationById(location.getLocation_id());
        
        assertEquals(location, fromDao);
    }

    @Test
    public void testDeletelocationById() {
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocation_id());
        assertEquals(location, fromDao);
        
        locationDao.deleteLocationById(location.getLocation_id());
        
        fromDao = locationDao.getLocationById(location.getLocation_id());
        assertNull(fromDao);
    }
    
    
}
