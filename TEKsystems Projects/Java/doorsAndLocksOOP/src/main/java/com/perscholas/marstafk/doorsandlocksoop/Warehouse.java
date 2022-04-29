/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.doorsandlocksoop;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author boss_
 */
public class Warehouse {
    
    public static ArrayList<String> addDoor() {
            
            ArrayList<String> doors = new ArrayList<>();
            for(int i=0; i < 90; i++) {
                
                final Key key = new Key();
                final Door door = new Door(key);
                door.getKey();
                doors.add(door.toString());
                System.out.println(key.shape());
            }    
            
            for(int i=0; i < 10; i++) {
                final Door door = new Door(null);
                doors.add(door.toString());
                System.out.println("No Shape");
            }    
            return doors;
        }
}
    

