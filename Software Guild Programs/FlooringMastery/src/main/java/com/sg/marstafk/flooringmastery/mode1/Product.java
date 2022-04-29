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
public class Product {
    
    private String productType;
    private BigDecimal costPerSquareFoot, laborCostPerSquareFoot;
    
    public Product(String productType) {
        this.productType = productType;
    }
    
    public Product(String productType, BigDecimal costPerSquareFoot, BigDecimal 
            laborCostPerSquareFoot) {
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.productType);
        hash = 29 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 29 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        return Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot);
    }
    
    
    @Override
    public String toString() {
        return "\n|Material type: " + productType + " |Cost per square foot: $" +
                costPerSquareFoot + " |Labor cost per square foot: $" +
                laborCostPerSquareFoot;
    }
    
}
