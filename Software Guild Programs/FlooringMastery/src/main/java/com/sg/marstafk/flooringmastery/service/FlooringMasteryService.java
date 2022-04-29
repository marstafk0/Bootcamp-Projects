/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.service;

import com.sg.marstafk.flooringmastery.mode1.Order;
import com.sg.marstafk.flooringmastery.mode1.Product;
import com.sg.marstafk.flooringmastery.mode1.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface FlooringMasteryService {
    
    void addOrder(String name, StateTax state, Product product, BigDecimal area, 
            LocalDate date) throws InvalidEntryException, PersistenceException;
    
    void editOrder(int orderNumber, Order order) throws PersistenceException,
            InvalidEntryException;
    
    StateTax getStateTax(String state) throws PersistenceException,
            InvalidEntryException;
    
    Product getProduct(String material) throws PersistenceException,
            InvalidEntryException;
    
    List<Order> orderByDate(LocalDate date) throws PersistenceException,
            InvalidEntryException;
    
    Collection<Product> listProducts() throws PersistenceException;
    
    Order listOrder(int orderNum) throws PersistenceException,
            InvalidEntryException;
    
    void removeOrder(int orderNum) throws PersistenceException, 
            InvalidEntryException;
    
    void exportData(boolean testOrNot) throws PersistenceException;
    
    void write(boolean testOrNot) throws PersistenceException;
    
    void load(boolean testOrNot) throws PersistenceException;
    
}
