/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.doorsandlocksoop;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author boss_
 */
public class Key implements Cloneable, Comparable<Key> {
    
    private UUID key;
    private Keyshape shape = Keyshape.randomEnum();
    
    //Default
    public Key() {
        key = UUID.randomUUID();
    }
    
    public Key(Keyshape shape) {
        this();
        this.shape = shape;
    }
    
    //Copy
    public Key(Key original) {
        key = original.key;
        shape = original.shape;
    }
    
    public Keyshape shape() {
        return shape;
    }
    
    @Override
    public Object clone() {
        return new Key(this);
    }

    @Override
    public String toString() {
        return key.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Key other = (Key) obj;
        return Objects.equals(this.key, other.key);
    }

    @Override
    public int compareTo(Key o) {
        return key.compareTo(o.key);
    }
    
}
