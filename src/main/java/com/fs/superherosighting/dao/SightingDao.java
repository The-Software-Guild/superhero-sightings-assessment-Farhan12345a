/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.entities.Sighting;
import java.util.Date;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface SightingDao {
    //CRUD Operations
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting Sighting);
    void updateSighting(Sighting Sighting);
    void deleteSightingById(int id);
    
    //NEED TO ADD MORE METHODS
    //The system must be able to report all sightings (hero and
    //location) for a particular date.
    List<Sighting> getSightingForDate(Date date);

}
