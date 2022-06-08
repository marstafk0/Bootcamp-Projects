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
// Type of pledge, ie. One time donation or per lap.
@Data
@NoArgsConstructor
@Entity
@Table(name="pledge_type")
public class PledgeType implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pledgeName", nullable = false)
    private String pledgeName;

    @Column(name = "active", nullable = false)
    private boolean active = true;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "pledge_type_pledge",
            joinColumns = {
                @JoinColumn(name = "pledge_type_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "pledge_id")})
    private List<Pledge> pledges;

    public PledgeType(String pledgeName, List<Pledge> pledges, boolean active) {
        this.pledgeName = pledgeName;
        this.pledges = pledges;
        this.active = active;
    }

}
