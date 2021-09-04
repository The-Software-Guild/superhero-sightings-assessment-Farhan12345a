/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.controller;

import com.fs.superherosighting.dao.LocationDao;
import com.fs.superherosighting.dao.OrganizationDao;
import com.fs.superherosighting.dao.SightingDao;
import com.fs.superherosighting.dao.SuperHeroVillanDao;
import com.fs.superherosighting.dao.SuperpowerDao;
import com.fs.superherosighting.entities.Location;
import com.fs.superherosighting.entities.Sighting;
import com.fs.superherosighting.entities.SuperHeroVillan;
import com.fs.superherosighting.service.SightingService;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author farhanshahbaz
 */
@Controller
public class SightingController {
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
    
    SightingService sightingService;

    //change around propbably
    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        
        //Change
       // HashMap<Sighting,SuperHeroVillan> heroSightings = sightingService.mapHeroSightings(sightings);
        //Get locations
        List<Location> locations = locationDao.getAllLocations();
        
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations",locations);
        //model.addAttribute("heroSightings", heroSightings);
        return "sightings";
    }
    
    
//    @GetMapping("addSighting")
//    public String displayAddSightings(Model model) {
//        
//        
//        List<Location> locations = locationDao.getAllLocations();
//        model.addAttribute("locations", locations);
//        
//        
//        
//       // model.addAttribute("dateError", dateError);
//        
//        return "/sightings/addSighting";
//    }
    
    
    
    //Might need getmapping for addSighting???
    @PostMapping("addSighting")
    public String addSighting(Sighting sighting, HttpServletRequest request){
        String locationId = request.getParameter("location_id");
        String date= request.getParameter("date");
    
        //Date date = Date.valueOf(dateString);
        
        sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));
                
        
        sighting.setDate(date);
        
        
        sightingDao.addSighting(sighting);
        
        return "redirect:/sightings";
    }
    
    //DETAILS PAGE
    @GetMapping("sightingDetail")
    public String sightingDetail(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "sightingDetail";
    }
    
    //Delete Sighting
    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }
    
    //HAVENT INCLUDED EDIT********
}
