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
// Type of person, ie. Teacher, Student, Parent, Guardian etc.
@Data
@NoArgsConstructor
@Entity
@Table(name="person_type")
public class PersonType implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "status_name", nullable = false)
    private String statusName;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "person_type_person",
            joinColumns = {
                @JoinColumn(name = "person_type_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "person_id")})
    private List<Person> persons;

    public PersonType(String statusName, List<Person> persons, boolean active) {
        this.statusName = statusName;
        this.persons = persons;
        this.active = active;
    }
    
    public void addPersons(Person person) {
        this.persons.add(person); 
    }
    
}
