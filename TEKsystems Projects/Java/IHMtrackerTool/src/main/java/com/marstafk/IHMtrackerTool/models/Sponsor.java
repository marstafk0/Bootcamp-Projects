/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtrackerTool.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author boss_
 */
@Data
@NoArgsConstructor
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
    
    @Column(name = "address_one")
    private String addressOne;
    
    @Column(name = "address_two")
    private String addressTwo;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "state_of")
    private String stateOf;
    
    @Column(name = "zip")
    private String zip;
    
    @Column(name = "phone")
    private String phone;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "sponsor_pledge",
            joinColumns = {
                @JoinColumn(name = "sponsor_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "pledge_id")})
    private List<Pledge> pledges;

    public Sponsor(String firstName, String lastName, String addressOne, String addressTwo, String city,
                   String stateOf, String zip, String phone, List<Pledge> pledges) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.stateOf = stateOf;
        this.zip = zip;
        this.phone = phone;
        this.pledges = pledges;
    }

    public void addPledges(Pledge pledge) {
        this.pledges.add(pledge);
    }

}
