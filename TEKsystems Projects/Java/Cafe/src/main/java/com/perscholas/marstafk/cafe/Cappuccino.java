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
public class Cappuccino extends Product {
    
    private boolean peppermint;
    private boolean whippedCream;
    
    public Cappuccino() {
        this.peppermint = false;
        this.whippedCream = false;
    }
    
    public Cappuccino(String name, double price, String description) {
        this.setName(name);
        this.setPrice(price);
        this.setuPrice(price);
        this.setDescription(description);
    }

    @Override
    public double calculateProductTotal() {
        if(this.peppermint == true) {
            this.setPrice(this.getPrice() + 2);
        }
        if(this.whippedCream == true) {
            this.setPrice(this.getPrice() + 1);
        }
        return this.getPrice() * this.getQuantity();
    }

    public boolean isPeppermint() {
        return peppermint;
    }

    public void setPeppermint(boolean peppermint) {
        this.peppermint = peppermint;
    }

    public boolean isWhippedCream() {
        return whippedCream;
    }

    public void setWhippedCream(boolean whippedCream) {
        this.whippedCream = whippedCream;
    }

    @Override
    public void addOptions(String option, String option2) {
        this.peppermint = option.equalsIgnoreCase("Yes");
        this.whippedCream = option2.equalsIgnoreCase("Yes");
    }

    @Override
    public String printOptions() {
        String res;
        if(this.peppermint == true) {
            res = "Peppermint: Yes (Add $2) ";
        } else {
            res = "Peppermint: No ";
        }
        if (this.whippedCream == true) {
            res += "Whipped Cream: Yes (Add $1) "; 
        } else {
            res += "Whipped Cream: No ";
        }
        return res;
    }
    
}
