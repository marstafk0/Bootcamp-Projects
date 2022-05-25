/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtracker.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author boss_
 */
@Data
@Entity
@Table(name="sponsor")
public class Sponsor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "fname", nullable = false)
    private String firstName;
    
    @Column(name = "lname")
    private String lastName;
    
    @Column(name = "addressOne")
    private String addressOne;
    
    @Column(name = "addressTwo")
    private String addressTwo;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "stateOf")
    private String stateOf;
    
    @Column(name = "zip")
    private String zip;
    
    @Column(name = "phone")
    private String phone;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "sponsor_pledge",
            joinColumns = {
                @JoinColumn(name = "pledge_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "sponsor_id")})
    private List<Pledge> pledges;

}
