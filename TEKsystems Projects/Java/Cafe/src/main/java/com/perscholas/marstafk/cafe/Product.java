/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.cafe;

/**
 *
 * @author boss_
 */
public abstract class Product {
    
    private double uPrice;
    private String name;
    private double price;
    private String description;
    private int quantity;
    
    public Product() {
        
    }
    
    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.uPrice = price;
        this.description = description;
    }
    
    public abstract double calculateProductTotal();
    
    public abstract void addOptions(String option, String option2);
    
    public abstract String printOptions();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getuPrice() {
        return uPrice;
    }

    public void setuPrice(double uPrice) {
        this.uPrice = uPrice;
    }
    
}
