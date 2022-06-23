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
// Type of pledge, ie. One time donation or per lap.
@Data
@NoArgsConstructor
@Entity
@Table(name = "pledge_type")
public class PledgeType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 100, message = "Pledge name can't be more than 100 characters.")
    @NotEmpty(message = "Pledge name can't be empty.")
    @Column(name = "pledge_name", nullable = false)
    private String pledgeName;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
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

    public void addPledge(Pledge pledge) {
        this.pledges.add(pledge);
    }

}
