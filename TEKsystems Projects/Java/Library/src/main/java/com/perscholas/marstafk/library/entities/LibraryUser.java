/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.library.entities;

/**
 *
 * @author boss_
 */
public interface LibraryUser {
    
    void registerAccount() throws UserException;
    void requestBook();
    
}
