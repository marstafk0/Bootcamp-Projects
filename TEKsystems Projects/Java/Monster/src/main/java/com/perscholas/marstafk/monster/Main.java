/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.monster;

import com.perscholas.marstafk.monster.etities.FireMonster;
import com.perscholas.marstafk.monster.etities.Monster;
import com.perscholas.marstafk.monster.etities.StoneMonster;
import com.perscholas.marstafk.monster.etities.WaterMonster;

/**
 *
 * @author boss_
 */



public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Program at the &quot;interface&quot; defined in the superclass.
        // Declare instances of the superclass, substituted by subclasses.
        Monster m1 = new FireMonster("r2u2"); // upcast
        Monster m2 = new WaterMonster("u2r2"); // upcast
        Monster m3 = new StoneMonster("r2r2"); // upcast
        // Invoke the actual implementation
        System.out.println(m1.attack()); // Run FireMonster&#39;s attack()
        System.out.println(m2.attack()); // Run WaterMonster&#39;s attack()
        System.out.println(m3.attack()); // Run StoneMonster&#39;s attack()
        // m1 dies, generate a new instance and re-assign to m1.
        m1 = new StoneMonster("a2b2"); // upcast
        System.out.println(m1.attack()); // Run StoneMonster&#39;s attack()
        // We have a problem here!!!
        Monster m4 = new Monster("u2u2");
        System.out.println(m4.attack()); // garbage!!!
        
       
    }
    
}
