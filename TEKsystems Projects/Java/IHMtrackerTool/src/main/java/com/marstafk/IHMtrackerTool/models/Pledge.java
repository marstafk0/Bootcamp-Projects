/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtrackerTool.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author boss_
 */
// Pledge info
@Data
@NoArgsConstructor
@Entity
@Table(name="pledge")
public class Pledge implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "per_lap", nullable = false)
    private BigDecimal perLap = new BigDecimal(0.00);
    
    @Column(name = "total", nullable = false)
    private BigDecimal total = new BigDecimal(0.00);
    
    @Column(name = "one_time", nullable = false)
    private BigDecimal oneTime = new BigDecimal(0.00);

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "week", nullable = false)
    private int week;

    @Column(name = "collected", nullable = false)
    private boolean collected;

    @Column(name = "receipt", nullable = false)
    private boolean receipt = false;

    public Pledge(BigDecimal perLap, BigDecimal total, BigDecimal oneTime, boolean active, int week, boolean collected, boolean receipt) {
        this.perLap = perLap;
        this.total = total;
        this.oneTime = oneTime;
        this.active = active;
        this.week = week;
        this.collected = collected;
        this.receipt = receipt;
    }

}
