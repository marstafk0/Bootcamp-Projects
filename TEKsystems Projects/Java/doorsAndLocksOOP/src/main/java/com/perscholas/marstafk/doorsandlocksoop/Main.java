/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.doorsandlocksoop;


/**
 *
 * @author boss_
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Key key = new Key();
        
        Key sparekey = (Key)key.clone();
        
        Key otherSpareKey = new Key(key);
        
        Warehouse.addDoor();
        
        
    }
    
}
