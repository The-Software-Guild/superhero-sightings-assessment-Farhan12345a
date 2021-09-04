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
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface SuperHeroVillanDao {
    //CRUD Operations
    SuperHeroVillan getSuperHeroVillanById(int id);
    List<SuperHeroVillan> getAllSuperHeroVillans();
    SuperHeroVillan addSuperHeroVillan(SuperHeroVillan superHeroVillan);
    void updateSuperHeroVillan(SuperHeroVillan superHeroVillan);
    void deleteSuperHeroVillanById(int id);
    
   
    //NEED TO ADD MORE METHODS
    List<SuperHeroVillan> getSuperHeroVillanForOrganization(Organization organization);


    //The system must be able to report all of the superheroes sighted at a 
    //particular location.
    List<SuperHeroVillan> getSuperHeroVillanForLocation(Location location);
    
    //not cessary CHANGE!!
    SuperHeroVillan getHeroForSighting(Sighting sighting);
    //A user must be able to record a superhero/supervillain sighting for a 
    //particular location and date.
    
}
