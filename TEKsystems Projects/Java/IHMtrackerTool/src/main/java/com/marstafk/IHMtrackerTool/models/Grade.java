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
 * @author boss_
 */
// Grade, ie. 5th grade.
@Data
@NoArgsConstructor
@Entity
@Table(name = "grade")
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "grade_name", nullable = false)
    private String gradeName;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "grade_person",
            joinColumns = {
                    @JoinColumn(name = "grade_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "person_id")})
    private List<Person> persons;

    public Grade(String gradeName, List<Person> persons, boolean active) {
        this.gradeName = gradeName;
        this.persons = persons;
        this.active = active;
    }

    public void addStudent(Person person) {
        this.persons.add(person);
    }

}
