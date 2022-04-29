/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.mode1;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author boss_
 */
public class StateTax {
    
    private final String stateName;
    private final BigDecimal taxRate;
    
    public StateTax(String stateName, BigDecimal taxRate) {
        this.stateName = stateName;
        this.taxRate = taxRate.setScale(2);
    }

    public String getStateName() {
        return stateName;
    }
    
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.stateName);
        hash = 71 * hash + Objects.hashCode(this.taxRate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StateTax other = (StateTax) obj;
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        return Objects.equals(this.taxRate, other.taxRate);
    }

    @Override
    public String toString() {
        return "|State: " + stateName + ", |Tax rate: " +
                taxRate;
    }
    
}
