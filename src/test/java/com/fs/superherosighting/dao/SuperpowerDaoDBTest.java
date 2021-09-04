/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;


import com.fs.superherosighting.entities.Organization;
import com.fs.superherosighting.entities.Sighting;
import com.fs.superherosighting.entities.SuperHeroVillan;
import com.fs.superherosighting.entities.Superpower;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author farhanshahbaz
 */
@SpringBootTest
public class SuperpowerDaoDBTest {
    @Autowired
    SuperpowerDao superpowerDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperHeroVillanDao superHeroVillanDao;
    
    
    
    public SuperpowerDaoDBTest() {
    }
    
    @BeforeEach 
    public void setUp() {
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getSuperpower_id());
        }
        
//        List<Organization> organizations = organizationDao.getAllOrganizations();
//        for(Organization organization : organizations) {
//            organizationDao.deleteOrganizationById(organization.getOrganization_id());
//        }
//        
//        List<SuperHeroVillan> superHeroVillans = superHeroVillanDao.getAllSuperHeroVillans();
//        for(SuperHeroVillan superHeroVillan : superHeroVillans) {
//            superHeroVillanDao.deleteSuperHeroVillanById(superHeroVillan.getSuper_id());
//        }
//        
//        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
//        for(Superpower superpower : superpowers) {
//            superpowerDao.deleteSuperpowerById(superpower.getSuperpower_id());
//        }
//        
//        List<Sighting> sightings = sightingDao.getAllSightings();
//        for(Sighting sighting : sightings) {
//            sightingDao.deleteSightingById(sighting.getSighting_id());
//        }
        
    }
    
    
     @Test
    public void testAddAndGetSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Test Name");
        
        
        superpower = superpowerDao.addSuperpower(superpower);

        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpower_id());
        
        assertEquals(superpower, fromDao);
    }

    @Test
    public void testGetAllSuperpowers() {
        Superpower superpower = new Superpower();
        superpower.setName("Test Name");
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Test Name 2");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        
        assertEquals(2, superpowers.size());
        assertTrue(superpowers.contains(superpower));
        assertTrue(superpowers.contains(superpower2));
    }

    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Test Name");
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpower_id());
        assertEquals(superpower, fromDao);
        
        superpower.setName("Update Name");
        superpowerDao.updateSuperpower(superpower);
        
        assertNotEquals(superpower, fromDao);
        
        fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpower_id());
        
        assertEquals(superpower, fromDao);
    }

    @Test
    public void testDeletesuperpowerById() {
        Superpower superpower = new Superpower();
        superpower.setName("Test Name");
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpower_id());
        assertEquals(superpower, fromDao);
        
        superpowerDao.deleteSuperpowerById(superpower.getSuperpower_id());
        
        fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpower_id());
        assertNull(fromDao);
    }
    
}
