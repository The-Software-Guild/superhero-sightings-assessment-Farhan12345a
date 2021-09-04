/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author farhanshahbaz
 */
public class SuperHeroVillan {
    private int super_id;
    private String name;
    private String description;
    private int superpower_id;
    private boolean isHero;
    private List<Sighting> sightings;
    private Superpower superpower;

    public SuperHeroVillan() {
    }

    public SuperHeroVillan(int super_id, String name, String description, int superpower_id, boolean isHero, List<Sighting> sightings, Superpower superpower) {
        this.super_id = super_id;
        this.name = name;
        this.description = description;
        //this.superpower_id = superpower_id;
        this.isHero = isHero;
        this.sightings = sightings;
        this.superpower = superpower;
    }

    

    
    public List<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

   
    public Superpower getSuperpowers() {
        return superpower;
    }

    public void setSuperpowers(Superpower superpower) {
        this.superpower = superpower;
    }
    
    

    public int getSuper_id() {
        return super_id;
    }

    public void setSuper_id(int super_id) {
        this.super_id = super_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSuperpower_id() {
        return superpower_id;
    }

    public void setSuperpower_id(int superpower_id) {
        this.superpower_id = superpower_id;
    }

    public boolean isIsHero() {
        return isHero;
    }

    public void setIsHero(boolean isHero) {
        this.isHero = isHero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.super_id;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.superpower_id);
        hash = 97 * hash + (this.isHero ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuperHeroVillan other = (SuperHeroVillan) obj;
        if (this.super_id != other.super_id) {
            return false;
        }
        if (this.isHero != other.isHero) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superpower_id, other.superpower_id)) {
            return false;
        }
        return true;
    }

    
    
    
}
