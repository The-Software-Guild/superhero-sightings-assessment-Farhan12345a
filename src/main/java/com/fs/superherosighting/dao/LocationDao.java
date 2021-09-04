/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.entities.Location;
import com.fs.superherosighting.entities.Organization;
import com.fs.superherosighting.entities.SuperHeroVillan;
import java.util.List;


public interface LocationDao {
    //CRUD Operations
    Location getLocationById(int location_id);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationById(int id);
    
    
    //NEED TO ADD MORE METHODS
    //The system must be able to report all of the superheroes 
    //sighted at a particular location.
    List<Location> getLocationForSuperHeroVillan(SuperHeroVillan superHeroVillan);

    
    //The system must be able to report all of the superheroes sighted at a 
    //particular location.
    List<SuperHeroVillan> getSuperHeroVillanForLocation(Location location);
    
}
