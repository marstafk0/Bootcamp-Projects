/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtracker.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author boss_
 */
// Pledge info
@Data
@Entity
@Table(name="pledge")
public class Pledge implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "perLap", nullable = false)
    private BigDecimal perLap;
    
    @Column(name = "total", nullable = false)
    private BigDecimal total;
    
    @Column(name = "oneTime", nullable = false)
    private BigDecimal oneTime;
}
