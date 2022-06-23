/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtrackerTool.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author boss_
 */
//Object for a family
@Data
@NoArgsConstructor
@Entity
@Table(name = "family")
public class Family implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Family name can't be empty.")
    @Column(name = "fam_name", nullable = false)
    private String familyName;

    @Size(max = 100, message = "Can't be more than 100 characters.")
    @Column(name = "address_one", nullable = false)
    private String addressOne;

    @Size(max = 100, message = "Can't be more than 100 characters.")
    @Column(name = "address_two")
    private String addressTwo;

    @Size(max = 100, message = "Can't be more than 100 characters.")
    @Column(name = "city")
    private String city;

    @Size(max = 100, message = "Can't be more than 100 characters.")
    @Column(name = "state_of")
    private String stateOf;

    @Size(max = 20, message = "Can't be more than 20 characters.")
    @Column(name = "zip")
    private String zip;

    @Size(max = 50, message = "Can't be more than 50 characters.")
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
