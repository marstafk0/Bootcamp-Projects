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
public class Coffee extends Product {
    
    private boolean milk;
    private boolean sugar;
    
    public Coffee() {
        this.milk = false;
        this.sugar = false;
    }
    
    public Coffee(String name, double price, String description) {
        this.setName(name);
        this.setPrice(price);
        this.setuPrice(price);
        this.setDescription(description);
    }

    @Override
    public double calculateProductTotal() {
        return this.getPrice() * this.getQuantity();
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isSugar() {
        return sugar;
    }

    public void setSugar(boolean sugar) {
        this.sugar = sugar;
    }

    @Override
    public void addOptions(String option, String option2) {
        this.milk = option.equalsIgnoreCase("Yes");
        this.sugar = option2.equalsIgnoreCase("Yes");
    }

    @Override
    public String printOptions() {
        String res;
        if(this.milk) {
            res = "Milk: Yes ";
        } else {
            res = "Milk: No ";
        }
        if(this.sugar) {
            res += "Sugar: Yes"; 
        } else {
            res += "Sugar: No";
        }
        return res;
    }
    
}
