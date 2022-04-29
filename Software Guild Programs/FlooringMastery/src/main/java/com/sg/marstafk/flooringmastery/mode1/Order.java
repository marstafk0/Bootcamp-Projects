/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.mode1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author boss_
 */
public class Order {
    
    private int orderNumber;
    private String customerName;
    private BigDecimal  area, materialCost, laborCost, tax, total;
    private LocalDate date;
    private LocalDate creationDate;
    private StateTax stateTax;
    private Product product;
    
    public Order(String customerName, StateTax stateTax, 
            Product product, BigDecimal area, LocalDate date) {
        
        this.date = date;
        this.customerName = customerName;
        this.stateTax = stateTax;
        this.product = product;
        this.area = area;
        //material cost
        this.materialCost = product.getCostPerSquareFoot()
                .multiply(area).setScale(2, RoundingMode.HALF_UP);
        //labor cost
        this.laborCost = product.getLaborCostPerSquareFoot()
                .multiply(area).setScale(2, RoundingMode.HALF_UP);
        //tax
        this.tax = this.materialCost.add(this.laborCost).multiply
        (this.stateTax.getTaxRate().divide(new BigDecimal(100)))
                .setScale(2, RoundingMode.HALF_UP);
        //total
        this.total = this.materialCost.add(this.laborCost).add(this.tax);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StateTax getStateTax() {
        return stateTax;
    }

    public void setStateTax(StateTax stateTax) {
        this.stateTax = stateTax;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.orderNumber;
        hash = 97 * hash + Objects.hashCode(this.customerName);
        hash = 97 * hash + Objects.hashCode(this.area);
        hash = 97 * hash + Objects.hashCode(this.materialCost);
        hash = 97 * hash + Objects.hashCode(this.laborCost);
        hash = 97 * hash + Objects.hashCode(this.tax);
        hash = 97 * hash + Objects.hashCode(this.total);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.creationDate);
        hash = 97 * hash + Objects.hashCode(this.stateTax);
        hash = 97 * hash + Objects.hashCode(this.product);
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
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate)) {
            return false;
        }
        if (!Objects.equals(this.stateTax, other.stateTax)) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    @Override
    public String toString() {
        return " |Customer Name: " + customerName + " |State: " + 
                this.stateTax.getStateName() + " |Material: " + 
                this.product.getProductType() + " |Area: " + 
                this.area + " |Date: " + this.date + " |Total: $" + 
                this.total;
    }
    
    
    
}
