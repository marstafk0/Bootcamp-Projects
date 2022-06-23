/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author boss_
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name can't be empty.")
    @Column(name = "fname", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name can't be empty.")
    @Column(name = "lname", nullable = false)
    private String lastName;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "person_pledge",
            joinColumns = {
                    @JoinColumn(name = "person_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "pledges_id")})
    private List<Pledge> pledges;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "person_run",
            joinColumns = {
                    @JoinColumn(name = "person_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "run_id")})
    private List<Run> runs;

    public Person(String firstName, String lastName, String contact, List<Pledge> pledges, List<Run> laps, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.pledges = pledges;
        this.runs = laps;
        this.active = active;
    }

    public void addPledges(Pledge pledge) {
        this.pledges.add(pledge);
    }

    public void addRuns(Run run) {
        this.runs.add(run);
    }

}
