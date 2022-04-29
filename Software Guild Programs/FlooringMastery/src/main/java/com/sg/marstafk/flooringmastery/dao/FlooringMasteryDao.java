/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.dao;

import com.sg.marstafk.flooringmastery.mode1.Order;
import com.sg.marstafk.flooringmastery.mode1.Product;
import com.sg.marstafk.flooringmastery.mode1.StateTax;
import com.sg.marstafk.flooringmastery.service.PersistenceException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface FlooringMasteryDao {
    
    List<Order> ordersByDate(LocalDate date)throws PersistenceException;
    
    Product getProduct(String productType) throws PersistenceException;
    
    StateTax getState(String stateName) throws PersistenceException;

    Order listOrder(int orderNum) throws PersistenceException;
    
    Collection<Product> listProducts() throws PersistenceException;
    
    void addOrder(Order order)throws PersistenceException;
    
    void editOrder(int orderNumber, Order order)throws PersistenceException;
    
    void removeOrder(int orderNumber)throws PersistenceException;
    
    void exportData(boolean testOrNot) throws PersistenceException;
    
    void loadAll(boolean testOrNot) throws PersistenceException;
    
    void writeAll(boolean testOrNot) throws PersistenceException;
    
    
}
