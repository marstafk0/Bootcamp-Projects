/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.developerclub;

/**
 *
 * @author boss_
 */
public class ClubMember {
    
    private String name;
    private String city;
    private String state;
    private String favLang;

    public ClubMember(String name, String city, String state, String favLang) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.favLang = favLang;
    }
    
    public ClubMember() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFavLang() {
        return favLang;
    }

    public void setFavLang(String favLang) {
        this.favLang = favLang;
    }

    @Override
    public String toString() {
        return "ClubMember{" + "name=" + name + ", city=" + city + ", state=" + state + ", favLang=" + favLang + '}';
    }
    
}
