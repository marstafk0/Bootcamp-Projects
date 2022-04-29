/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.monster.etities;

/**
 *
 * @author boss_
 */
public class Monster {
    
    private String name;
    
    public String attack() {
        return "!^_&amp;^$@+%$* I don&#39;t know how to attack!!";
    }

    public Monster(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
