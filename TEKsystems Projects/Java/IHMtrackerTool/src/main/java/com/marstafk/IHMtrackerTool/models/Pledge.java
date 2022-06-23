/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtrackerTool.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author boss_
 */
// Pledge info
@Data
@NoArgsConstructor
@Entity
@Table(name = "pledge")
public class Pledge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0, message = "Per lap must be a positive number.")
    @Column(name = "per_lap", nullable = false)
    private BigDecimal perLap = new BigDecimal(0.00);

    @Column(name = "total", nullable = false)
    private BigDecimal total = new BigDecimal(0.00);

    @Min(value = 0, message = "One time must be a positive number.")
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

    @Column(name = "deletion", nullable = false)
    private boolean deletion;

}
