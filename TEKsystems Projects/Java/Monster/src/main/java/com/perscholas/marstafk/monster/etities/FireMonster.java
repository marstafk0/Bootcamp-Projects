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
public class FireMonster extends Monster{
    
    @Override
    public String attack() {
        return "Attack with Fire!";
    }

    public FireMonster(String name) {
        super(name);
    }
   
}
