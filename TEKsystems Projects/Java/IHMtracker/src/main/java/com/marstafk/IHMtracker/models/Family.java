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
//Object for a family
@Data
@Entity
@Table(name="family")
public class Family implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "famName", nullable = false)
    private String familyName;
    
    @Column(name = "addressOne", nullable = false)
    private String addressOne;
    
    @Column(name = "addressTwo")
    private String addressTwo;
    
    @Column(name = "city", nullable = false)
    private String city;
    
    @Column(name = "stateOf", nullable = false)
    private String stateOf;
    
    @Column(name = "zip", nullable = false)
    private String zip;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "family_person",
            joinColumns = {
                @JoinColumn(name = "person_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "family_id")})
    private List<Person> persons;

}
