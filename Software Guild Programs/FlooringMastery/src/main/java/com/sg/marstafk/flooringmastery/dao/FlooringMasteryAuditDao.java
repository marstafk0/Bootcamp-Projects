/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.dao;

import com.sg.marstafk.flooringmastery.service.PersistenceException;

/**
 *
 * @author boss_
 */
public interface FlooringMasteryAuditDao {
    
    // Audit class
    public void writeAuditEntry(String entry) throws PersistenceException;
    
}
