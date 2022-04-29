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
public class Door {
    
    private Key key;
    private boolean isLocked;
    
    public Door(Key key) {
        this.key = key;
        isLocked = key != null;
    }
    
    public Key key() {
        return key;
    }
    
    public boolean hasKey() {
        return key != null;
    }
    
    public boolean isLocked() {
        return isLocked;
    }
    
    public boolean lock(Key key) {
        if (this.key == null || this.key.equals(key)) {
            isLocked = false;
        }
        return isLocked;
    }
    
    public boolean unlock(Key key) {
        if (this.key == null || this.key.equals(key)) {
            isLocked = false;
        }
        return !isLocked;
    }

    public Key getKey() {
        return key;
    }
    
}
