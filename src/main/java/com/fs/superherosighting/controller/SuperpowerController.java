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
import com.fs.superherosighting.entities.Superpower;
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
public class SuperpowerController {
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
    
    @GetMapping("superpowers")
    public String displayStudents(Model model) {
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }
    
    @PostMapping("addSuperpower")
    public String addSuperpower(HttpServletRequest request) {
        String name = request.getParameter("name");
        
        
        Superpower superpower = new Superpower();
        superpower.setName(name);
        
        
        superpowerDao.addSuperpower(superpower);
        
        return "redirect:/superpowers";
    }
    
    @GetMapping("superPowerDetail")
    public String courseDetail(Integer id, Model model) {
        Superpower superpowers = superpowerDao.getSuperpowerById(id);
        model.addAttribute("superpower", superpowers);
        return "superPowerDetail";
    }
    
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        superpowerDao.deleteSuperpowerById(id);
        
        return "redirect:/superpowers";
    }
    
    @GetMapping("editSuperpower")
    public String editSuperpower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superpower superpower = superpowerDao.getSuperpowerById(id);
        
        model.addAttribute("superpower", superpower);
        
        model.addAttribute("teacher", superpower);
        return "editSuperpower";
    }
    
    @PostMapping("editSuperpower")
    public String performEditedSuperpower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superpower superpower = superpowerDao.getSuperpowerById(id);
        
        superpower.setName(request.getParameter("name"));
        
        
        superpowerDao.updateSuperpower(superpower);
        
        return "redirect:/superpowers";
    }
}
