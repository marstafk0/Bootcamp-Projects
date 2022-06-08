/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
@Data
@NoArgsConstructor
@Entity
@Table(name="person")
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "fname", nullable = false)
    private String firstName;
    
    @Column(name = "lname", nullable = false)
    private String lastName;
    
    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "person_pledges",
            joinColumns = {
                    @JoinColumn(name = "person_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "pledges_id")})
    private List<Pledge> pledges;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "person_person_laps",
            joinColumns = {
                    @JoinColumn(name = "person_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "person_laps_id")})
    private List<PersonLaps> laps;

    public Person(String firstName, String lastName, String contact, List<Pledge> pledges, List<PersonLaps> laps, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.pledges = pledges;
        this.laps = laps;
        this.active = active;
    }
    
}
