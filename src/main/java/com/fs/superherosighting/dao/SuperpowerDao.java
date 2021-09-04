/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.dao;

import com.fs.superherosighting.entities.Superpower;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface SuperpowerDao {
    //CRUD Operations
    Superpower getSuperpowerById(int superpower_id);
    List<Superpower> getAllSuperpowers();
    Superpower addSuperpower(Superpower superpower);
    void updateSuperpower(Superpower superpower);
    void deleteSuperpowerById(int superpower_id);
    
}
