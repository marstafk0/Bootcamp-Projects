/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtrackerTool.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author boss_
 */
//Object for a family
@Data
@NoArgsConstructor
@Entity
@Table(name="family")
public class Family implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "fam_name", nullable = false)
    private String familyName;
    
    @Column(name = "address_one", nullable = false)
    private String addressOne;
    
    @Column(name = "address_two")
    private String addressTwo;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "state_of")
    private String stateOf;
    
    @Column(name = "zip")
    private String zip;
    
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "active", nullable = false)
    private boolean active = true;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "family_person",
            joinColumns = {
                @JoinColumn(name = "family_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "person_id")})
    private List<Person> persons;

    public Family(String familyName, String addressOne, String addressTwo, String city, String stateOf,
                  String zip, String phone, List<Person> persons, boolean active) {
        this.familyName = familyName;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.stateOf = stateOf;
        this.zip = zip;
        this.phone = phone;
        this.active = active;
        this.persons = persons;
    }
    
    public void addPerson(Person person) {
        this.persons.add(person);
    }

}
