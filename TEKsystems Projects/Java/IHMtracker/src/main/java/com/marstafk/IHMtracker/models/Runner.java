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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author boss_
 */
// Runner for the Jog-A-Thon, can be anyone.
@Data
@Entity
@Table(name="runner")
public class Runner implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne(cascade=CascadeType.ALL)//one-to-one
    @JoinColumn(name="person_id")
    private Person person;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "runner_pledge",
            joinColumns = {
                @JoinColumn(name = "pledge_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "runner_id")})
    private List<Pledge> pledges;

}
