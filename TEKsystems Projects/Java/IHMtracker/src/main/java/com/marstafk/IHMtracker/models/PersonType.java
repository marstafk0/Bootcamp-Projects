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
// Type of person, ie. Teacher, Student, Parent, Guardian etc.
@Data
@Entity
@Table(name="personType")
public class PersonType implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "statusName", nullable = false)
    private String statusName;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "personType_person",
            joinColumns = {
                @JoinColumn(name = "person_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "personType_id")})
    private List<Person> persons;

}
