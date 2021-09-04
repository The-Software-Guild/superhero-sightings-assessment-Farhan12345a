/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.entities.Organization;
import com.fs.superherosighting.entities.SuperHeroVillan;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface OrganizationDao {
    //CRUD Operations
    Organization getOrganizationById(int organization_id);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int organization_id);
    
    List<Organization> getOrganizationForSuperHeroVillan(SuperHeroVillan superHeroVillan);
}
