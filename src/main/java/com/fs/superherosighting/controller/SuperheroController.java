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
import com.fs.superherosighting.entities.Organization;
import com.fs.superherosighting.entities.Sighting;
import com.fs.superherosighting.entities.SuperHeroVillan;
import com.fs.superherosighting.entities.Superpower;
import java.util.ArrayList;
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
public class SuperheroController {
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
    
    @GetMapping("superheros")
    public String displayHeros(Model model) {
        List<SuperHeroVillan> heros = superHeroVillanDao.getAllSuperHeroVillans();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Sighting> sightings = sightingDao.getAllSightings();

        
        model.addAttribute("superheros", heros);
        model.addAttribute("organizations", organizations);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("sightings", sightings);
        
        return "superheros";
    }
    
    @PostMapping("addHero")
    public String addHero(SuperHeroVillan hero, HttpServletRequest request) {
        String superpower_id = request.getParameter("superpower_id");
        //String[] studentIds = request.getParameterValues("studentId");
        
       // List<Superpower> superpowers = new ArrayList<Superpower>();
        hero.setSuperpowers(superpowerDao.getSuperpowerById(Integer.parseInt(superpower_id)));
        
        
//        List<Student> students = new ArrayList<>();
//        for(String studentId : studentIds) {
//            students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
//        }
//
//          String locationId = request.getParameter("location_id");
//        String date= request.getParameter("date");
//    
//        //Date date = Date.valueOf(dateString);
//        
//        sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));
        
     //   course.setStudents(students);
        superHeroVillanDao.addSuperHeroVillan(hero);
        
        return "redirect:/superheros";
    }
    
    @GetMapping("superheroDetail")
    public String courseDetail(Integer id, Model model) {
        SuperHeroVillan hero = superHeroVillanDao.getSuperHeroVillanById(id);
        model.addAttribute("superhero_supervillan", hero);
        return "superheroDetail";
    }
    
    @GetMapping("deleteHero")
    public String deleteHero(Integer id) {
        superHeroVillanDao.deleteSuperHeroVillanById(id);
        return "redirect:/superheros";
    }
    
    
}

    
