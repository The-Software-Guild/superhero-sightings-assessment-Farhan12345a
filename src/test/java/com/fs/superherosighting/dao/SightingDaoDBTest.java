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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class SightingDaoDBTest {
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SuperHeroVillanDao superHeroVillanDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    
    public SightingDaoDBTest() {
    }
    
    
    @BeforeEach 
    public void setUp() {
        List<SuperHeroVillan> superHeroVillans = superHeroVillanDao.getAllSuperHeroVillans();
        for(SuperHeroVillan superHeroVillan : superHeroVillans) {
            superHeroVillanDao.deleteSuperHeroVillanById(superHeroVillan.getSuper_id());
        }
        
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocationById(location.getLocation_id());
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getOrganization_id());
        }
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSighting_id());
        }
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getSuperpower_id());
        }

    }
    
    @Test
    public void testAddAndGetSighting() throws ParseException{

        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = new ArrayList<Superpower>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();    
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        //Superhero/villan
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
        superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string);
        sighting.setLocation_id(location.getLocation_id());//
        sighting.setLocation((location));
        sighting = sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSighting_id());
        
        assertEquals(sighting.getSighting_id(), fromDao.getSighting_id());
    }

    @Test
    public void testGetAllsightings() throws ParseException {
        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = new ArrayList<Superpower>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();    
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        //Superhero/villan
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
        superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string);
        sighting.setLocation_id(location.getLocation_id());//
        sighting.setLocation((location));
        sighting = sightingDao.addSighting(sighting);

        //Sighting #2
        
        //superpower
        Superpower superpower2 = new Superpower();
        superpower2.setName("Test name 2");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        List<Superpower> superpowers2 = new ArrayList<Superpower>();
        superpowers.add(superpower2);
        
        List<Sighting> sightings2 = new ArrayList<>();    
        
        String date_string2 = "26-09-1999";
       
          
       //Parsing the given String to Date object
        Date date2 = formatter.parse(date_string2);  
        
        //Superhero/villan
        SuperHeroVillan superHero2 = new SuperHeroVillan();
        superHero2.setName("Test Name 2");
        superHero2.setDescription("Test Description 2");
        superHero2.setSuperpower_id(superpower2.getSuperpower_id());
        superHero2.setIsHero(true);
        superHero2.setSightings(sightings2);
        superHero2.setSuperpowers(superpower);
        superHero2 = superHeroVillanDao.addSuperHeroVillan(superHero2);
        
        //Location
        Location location2 = new Location();
        location2.setName("Test Name 2");
        location2.setLatitude("1122323");
        location2.setLongitude(("1123343"));
        location2.setDescription("Test Description 2");
        location2.setAddress("Test Address 2");
        location2 = locationDao.addLocation(location2);
        
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(date_string2);
        sighting2.setLocation_id(location2.getLocation_id());//
        sighting2.setLocation((location2));
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> allSight = sightingDao.getAllSightings();
        
        assertEquals(2, allSight.size());
        
        //Fix below if doesn't work
        //assertTrue(allSight.contains(sighting));
        //assertTrue(allSight.contains(sighting2));
    }

    @Test
    public void testUpdateSighting() throws ParseException {
        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = new ArrayList<Superpower>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();    
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        //Superhero/villan
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
        superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string);
        sighting.setLocation_id(location.getLocation_id());//
        sighting.setLocation((location));
        sighting = sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSighting_id());
        
        assertEquals(sighting.getSighting_id(), fromDao.getSighting_id());
        
        //Location 2
        Location location2 = new Location();
        location2.setName("Test Name2");
        location2.setLatitude("1123232332");
        location2.setLongitude(("112312323243"));
        location2.setDescription("Test Description2");
        location2.setAddress("Test Address2");
        location = locationDao.addLocation(location2);
        
        sighting.setLocation(location2);
        sighting.setLocation_id(location2.getLocation_id());
        
        sightingDao.updateSighting(sighting);
        assertNotEquals(sighting,fromDao);
        
        fromDao = sightingDao.getSightingById(sighting.getSighting_id());
        assertEquals(sighting.getSighting_id(),fromDao.getSighting_id());
        
    }

    @Test
    public void testDeletesightingById() throws ParseException {
        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = new ArrayList<Superpower>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();    
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        //Superhero/villan
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
        superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string);
        sighting.setLocation_id(location.getLocation_id());//
        sighting.setLocation((location));
        sighting = sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSighting_id());
        
        assertEquals(sighting.getSighting_id(), fromDao.getSighting_id());
        
        sightingDao.deleteSightingById(sighting.getSighting_id());
        
        fromDao = sightingDao.getSightingById(sighting.getSighting_id());
        assertNull(fromDao);
    }

    //TEST FOR GET SIGHTING FOR DATE!!!!!
 
    
}
