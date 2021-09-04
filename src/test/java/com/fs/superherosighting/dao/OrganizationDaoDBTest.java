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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author farhanshahbaz
 */
@SpringBootTest
public class OrganizationDaoDBTest {
    
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
    
    public OrganizationDaoDBTest() {
        
    }
   
//    //IF TIME CHANGE TO CONSTRUCTORS!!!!!
    @Test
    public void testAddAndGetorganization() throws ParseException {
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
       
        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        
        
        List<Sighting> sightings = new ArrayList<Sighting>();
        //superhero
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
       // superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string); //fix
        //sighting.setLocation(location);
        sighting.setLocation_id(location.getLocation_id());
        
        sighting = sightingDao.addSighting(sighting);
        
        //Might have to change
        
        sightings.add(sighting);
        
       superHero.setSightings(sightings);
       
       superHeroVillanDao.updateSuperHeroVillan(superHero);
       

        //SuperHeroVillan superHero = new SuperHeroVillan(1, "Test Name", "Test Description", "Test ID", true, sightings, superpowers);
        List<SuperHeroVillan> superheros = new ArrayList<SuperHeroVillan>();
        superheros.add(superHero);
                
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress(("Test Address"));
        organization.setContact("Test Contact");
        organization.setIsHero("yes");
        organization.setSupers(superheros);
        
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getOrganization_id());
        
        assertEquals(organization, fromDao);
    }

    @Test
    public void testGetAllorganizations() throws ParseException {
        //Organization 1
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
       
        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = new ArrayList<Superpower>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<Sighting>();
        //superhero
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
        //superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string); //fix
        //sighting.setLocation(location);
        sighting.setLocation_id(location.getLocation_id());
        
        sighting = sightingDao.addSighting(sighting);
        
        //Might have to change
        
        sightings.add(sighting);
        
       superHero.setSightings(sightings);
       
       superHeroVillanDao.updateSuperHeroVillan(superHero);
       

        //SuperHeroVillan superHero = new SuperHeroVillan(1, "Test Name", "Test Description", "Test ID", true, sightings, superpowers);
        List<SuperHeroVillan> superheros = new ArrayList<SuperHeroVillan>();
        superheros.add(superHero);
                
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress(("Test Address"));
        organization.setContact("Test Contact");
        organization.setIsHero("yes");
        organization.setSupers(superheros);
        
        organization = organizationDao.addOrganization(organization);
        
        //Organization 2
        //Location
        Location location2 = new Location();
        location2.setName("Test Name");
        location2.setLatitude("112323");
        location2.setLongitude(("11231243"));
        location2.setDescription("Test Description");
        location2.setAddress("Test Address");
        location2 = locationDao.addLocation(location2);
       
        //superpower
        Superpower superpower2 = new Superpower();
        superpower2.setName("Test name");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        List<Sighting> sightings2 = new ArrayList<Sighting>();
        //superhero
        SuperHeroVillan superHero2 = new SuperHeroVillan();
        superHero2.setName("Test Name");
        superHero2.setDescription("Test Description");
        superHero2.setSuperpower_id(superpower.getSuperpower_id());
        superHero2.setIsHero(true);
        superHero2.setSightings(sightings);
        superHero2.setSuperpowers(superpower2);
        superHero2 = superHeroVillanDao.addSuperHeroVillan(superHero2);
        
        String date_string2 = "26-10-1989";
       
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date2 = formatter.parse(date_string2);  
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(date_string2); //fix
        //sighting.setLocation(location);
        sighting2.setLocation_id(location2.getLocation_id());
        
        sighting2 = sightingDao.addSighting(sighting2);
        
        //Might have to change
        
        sightings.add(sighting2);
        
       superHero.setSightings(sightings2);
       
       superHeroVillanDao.updateSuperHeroVillan(superHero2);
       

        //SuperHeroVillan superHero = new SuperHeroVillan(1, "Test Name", "Test Description", "Test ID", true, sightings, superpowers);
        List<SuperHeroVillan> superheros2 = new ArrayList<SuperHeroVillan>();
        superheros.add(superHero2);
                
        Organization organization2 = new Organization();
        organization2.setName("Test Name 2");
        organization2.setDescription("Test Description 2");
        organization2.setAddress(("Test Address 2"));
        organization2.setContact("Test Contact 2");
        organization2.setIsHero("yes");
        organization2.setSupers(superheros2);
        
        organization2 = organizationDao.addOrganization(organization2);
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        
        assertEquals(2, organizations.size());
        //PROBLEM WIth gEt ALL BELOW
        //assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));
    }

    @Test
    public void testUpdateorganization() throws ParseException {
        //Organization 1
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
       
        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = new ArrayList<Superpower>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<Sighting>();
        //superhero
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
        superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string); //fix
        //sighting.setLocation(location);
        sighting.setLocation_id(location.getLocation_id());
        
        sighting = sightingDao.addSighting(sighting);
        
        //Might have to change
        
        sightings.add(sighting);
        
       superHero.setSightings(sightings);
       
       superHeroVillanDao.updateSuperHeroVillan(superHero);
       

        //SuperHeroVillan superHero = new SuperHeroVillan(1, "Test Name", "Test Description", "Test ID", true, sightings, superpowers);
        List<SuperHeroVillan> superheros = new ArrayList<SuperHeroVillan>();
        superheros.add(superHero);
                
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress(("Test Address"));
        organization.setContact("Test Contact");
        organization.setIsHero("yes");
        organization.setSupers(superheros);
        
        organization = organizationDao.addOrganization(organization);
        Organization fromDao = organizationDao.getOrganizationById(organization.getOrganization_id());
        
        assertEquals(organization.getOrganization_id(),fromDao.getOrganization_id());
        organization.setName("New Name");
        organization.setDescription("NEw Description");
        organization.setAddress(("New Address"));
        organization.setContact("New Contact");
         
        organizationDao.updateOrganization(organization);
        assertNotEquals(organization,fromDao);
        
        fromDao = organizationDao.getOrganizationById(organization.getOrganization_id());
        assertEquals(organization.getOrganization_id(),fromDao.getOrganization_id());
        

        
    }
//
    @Test
    public void testDeleteorganizationById() throws ParseException {
        //Organization 1
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
       
        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = new ArrayList<Superpower>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<Sighting>();
        //superhero
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
        superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string); //fix
        //sighting.setLocation(location);
        sighting.setLocation_id(location.getLocation_id());
        
        sighting = sightingDao.addSighting(sighting);
        
        //Might have to change
        
        sightings.add(sighting);
        
       superHero.setSightings(sightings);
       
       superHeroVillanDao.updateSuperHeroVillan(superHero);
       

        //SuperHeroVillan superHero = new SuperHeroVillan(1, "Test Name", "Test Description", "Test ID", true, sightings, superpowers);
        List<SuperHeroVillan> superheros = new ArrayList<SuperHeroVillan>();
        superheros.add(superHero);
                
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress(("Test Address"));
        organization.setContact("Test Contact");
        organization.setIsHero("yes");
        organization.setSupers(superheros);
        
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getOrganization_id());
        assertEquals(organization.getOrganization_id(),fromDao.getOrganization_id());
        
        organizationDao.deleteOrganizationById(organization.getOrganization_id());
        
        fromDao = organizationDao.getOrganizationById(organization.getOrganization_id());
        assertNull(fromDao);
    }

    //Another Method
    @Test
    public void getOrganizationForSuperHeroVillan() throws ParseException{
        //Organization 1
        //Location
        Location location = new Location();
        location.setName("Test Name");
        location.setLatitude("112323");
        location.setLongitude(("11231243"));
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location = locationDao.addLocation(location);
       
        //superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = new ArrayList<Superpower>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<Sighting>();
        //superhero
        SuperHeroVillan superHero = new SuperHeroVillan();
        superHero.setName("Test Name");
        superHero.setDescription("Test Description");
        superHero.setSuperpower_id(superpower.getSuperpower_id());
        superHero.setIsHero(true);
        superHero.setSightings(sightings);
        superHero.setSuperpowers(superpower);
        superHero = superHeroVillanDao.addSuperHeroVillan(superHero);
        
        String date_string = "26-09-1989";
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date = formatter.parse(date_string);  
        
        Sighting sighting = new Sighting();
        sighting.setDate(date_string); //fix
        //sighting.setLocation(location);
        sighting.setLocation_id(location.getLocation_id());
        
        sighting = sightingDao.addSighting(sighting);
        
        //Might have to change
        
        sightings.add(sighting);
        
       superHero.setSightings(sightings);
       
       superHeroVillanDao.updateSuperHeroVillan(superHero);
       

        //SuperHeroVillan superHero = new SuperHeroVillan(1, "Test Name", "Test Description", "Test ID", true, sightings, superpowers);
        List<SuperHeroVillan> superheros = new ArrayList<SuperHeroVillan>();
        superheros.add(superHero);
                
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress(("Test Address"));
        organization.setContact("Test Contact");
        organization.setIsHero("yes");
        organization.setSupers(superheros);
        
        organization = organizationDao.addOrganization(organization);
        
        //Organization #2
        
        //Organization 2
        //Location
        Location location2 = new Location();
        location2.setName("Test Name2");
        location2.setLatitude("11223323");
        location2.setLongitude(("1123231243"));
        location2.setDescription("Test Description2");
        location2.setAddress("Test Address2");
        location2 = locationDao.addLocation(location2);
       
        //superpower
        Superpower superpower2 = new Superpower();
        superpower2.setName("Test name");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers2 = new ArrayList<Superpower>();
        superpowers2.add(superpower);
        
        List<Sighting> sightings2 = new ArrayList<Sighting>();
        //superhero
        SuperHeroVillan superHero2 = new SuperHeroVillan();
        superHero2.setName("Test Name2");
        superHero2.setDescription("Test Description2");
        superHero2.setSuperpower_id(superpower.getSuperpower_id());
        superHero2.setIsHero(true);
        superHero2.setSightings(sightings2);
        superHero2.setSuperpowers(superpower);
        superHero2 = superHeroVillanDao.addSuperHeroVillan(superHero2);
        
        String date_string2 = "26-11-1989";
       
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
       //Parsing the given String to Date object
        Date date2 = formatter.parse(date_string2);  
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(date_string2); //fix
        //sighting.setLocation(location);
        sighting2.setLocation_id(location2.getLocation_id());
        
        sighting2 = sightingDao.addSighting(sighting2);
        
        //Might have to change
        
        sightings2.add(sighting2);
        
       superHero2.setSightings(sightings2);
       
       superHeroVillanDao.updateSuperHeroVillan(superHero2);
       

        //SuperHeroVillan superHero = new SuperHeroVillan(1, "Test Name", "Test Description", "Test ID", true, sightings, superpowers);
        List<SuperHeroVillan> superheros2 = new ArrayList<SuperHeroVillan>();
        superheros2.add(superHero2);
                
        Organization organization2 = new Organization();
        organization2.setName("Test Name 2");
        organization2.setDescription("Test Description 2");
        organization2.setAddress(("Test Address 2"));
        organization2.setContact("Test Contact 2");
        organization2.setIsHero("no");
        organization2.setSupers(superheros2);
        
        organization2 = organizationDao.addOrganization(organization2);
        
        List<Organization> fromDao = organizationDao.getOrganizationForSuperHeroVillan(superHero);
        //assertTrue(fromDao.contains(organization));
        assertFalse(fromDao.contains(organization2));
    }
}

    

