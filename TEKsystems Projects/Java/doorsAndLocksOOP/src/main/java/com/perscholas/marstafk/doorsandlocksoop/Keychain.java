/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.doorsandlocksoop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author boss_
 */
public class Keychain {
    
    private HashMap<Keyshape, ArrayList<Key>> keys = new HashMap<Keyshape, ArrayList<Key>>();
    
    public Keychain() {}
    
    public Keychain(Collection<Key> keys) {
        keys.forEach(k -> addKey(k));
    }
    
    public boolean addKey(Key key) {
        if (!keys.containsKey(key.shape())) keys.put(key.shape(), new ArrayList<>());
        keys.get(key.shape()).add(key);
        return true;
    }
    
    public boolean removeKey(Key key) {
        if (keys.containsKey(key.shape())) return keys.get(key.shape()).remove(key);
        return false;
    }
    
    public int keyCount() {
        int keyCount = 0;
        keyCount = keys.keySet().stream().map(keyshape -> keys.get(keyshape).size()).reduce(keyCount, Integer::sum);
        return keyCount;
    }
    
    public boolean lock(Door door) {
        if (door.isLocked()) return true;
        if (!door.hasKey()) return false;
        if (!keys.containsKey(door.key().shape())) return false;
        return keys.get(door.key().shape()).stream().anyMatch(k -> (door.lock(k)));
    }
    
    public boolean unlock(Door door) {
        if (!door.isLocked()) return true;
        if (!door.hasKey()) return true;
        if (!keys.containsKey(door.key().shape())) return false;
        return keys.get(door.key().shape()).stream().anyMatch(k -> (door.unlock(k)));
    }
    
}
