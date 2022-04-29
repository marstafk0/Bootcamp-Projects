/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.doorsandlocksoop;

import java.util.Random;

/**
 *
 * @author boss_
 */
public enum Keyshape {
    Square,
    Rectangle,
    Circular,
    Oval,
    TrapezoidUp,
    TrapezoidDown,
    Irregular,
    Unkown;
    
    public static Keyshape randomEnum() {
        int pick = new Random().nextInt(Keyshape.values().length);
        return Keyshape.values()[pick];
    }
}
