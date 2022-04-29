/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.dao;

import com.sg.marstafk.flooringmastery.service.PersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author boss_
 */
public class FlooringMasteryAuditDaoFileImpl implements FlooringMasteryAuditDao{
    
    public static final String AUDIT_FILE = "audit.txt";
    
    // Audit
    @Override
    public void writeAuditEntry(String entry) throws PersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new PersistenceException("Could not persist audit "
                    + "information.", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
}
