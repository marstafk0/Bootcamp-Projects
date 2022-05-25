/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtracker.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
// Type of pledge, ie. One time donation or per lap.
@Data
@Entity
@Table(name="pledgeType")
public class PledgeType implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pledgeName;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "pledgeType_pledge",
            joinColumns = {
                @JoinColumn(name = "pledge_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "pledgeType_id")})
    private List<Pledge> pledges;

}
