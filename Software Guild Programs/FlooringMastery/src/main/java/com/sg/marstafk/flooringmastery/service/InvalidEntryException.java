/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.service;

/**
 *
 * @author boss_
 */
public class InvalidEntryException extends Exception{
    
    public InvalidEntryException(String message) {
        super(message);
    }
    
    public InvalidEntryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
