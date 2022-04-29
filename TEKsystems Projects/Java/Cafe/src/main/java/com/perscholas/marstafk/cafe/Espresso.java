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
public class Espresso extends Product {
    
    private boolean extraShot;
    private boolean macchiato;
    
    public Espresso() {
        this.extraShot = false;
        this.macchiato = false;
    }
    
    public Espresso(String name, double price, String description) {
        this.setName(name);
        this.setPrice(price);
        this.setuPrice(price);
        this.setDescription(description);
    }

    @Override
    public double calculateProductTotal() {
        if(this.extraShot == true) {
            this.setPrice(this.getPrice() + 2);
        }
        if(this.macchiato == true) {
            this.setPrice(this.getPrice() + 1);
        }
        return this.getPrice() * this.getQuantity();
    }

    public boolean isExtraShot() {
        return extraShot;
    }

    public void setExtraShot(boolean extraShot) {
        this.extraShot = extraShot;
    }

    public boolean isMacchiato() {
        return macchiato;
    }

    public void setMacchiato(boolean macchiato) {
        this.macchiato = macchiato;
    }

    @Override
    public void addOptions(String option, String option2) {
        this.extraShot = option.equalsIgnoreCase("Yes");
        this.macchiato = option2.equalsIgnoreCase("Yes");
    }

    @Override
    public String printOptions() {
        String res;
        if(this.extraShot == true) {
            res = "Extra Shot: Yes (Add $2) ";
        } else {
            res = "Extra Shot: No ";
        }
        if (this.macchiato == true) {
            res += "Macchiato: Yes (Add $1) "; 
        } else {
            res += "Macchiato: No ";
        }
        return res;
    }
    
}
