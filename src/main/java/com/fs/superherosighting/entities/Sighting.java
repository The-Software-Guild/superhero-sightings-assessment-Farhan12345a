/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.superherosighting.entities;

import java.util.Date;
import java.util.Objects;


public class Sighting {
    private int sighting_id;
    private String date;
    private int location_id;
    private Location location;

    public Sighting() {
    }

    
    public Sighting(int sighting_id, String date, int location_id, Location location) {
        this.sighting_id = sighting_id;
        this.date = date;
        this.location_id = location_id;
        this.location = location;
    }

    
    public int getSighting_id() {
        return sighting_id;
    }

    public void setSighting_id(int sighting_id) {
        this.sighting_id = sighting_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.sighting_id;
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + this.location_id;
        hash = 67 * hash + Objects.hashCode(this.location);
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
        final Sighting other = (Sighting) obj;
        if (this.sighting_id != other.sighting_id) {
            return false;
        }
        if (this.location_id != other.location_id) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }
    
    

    
    
}
