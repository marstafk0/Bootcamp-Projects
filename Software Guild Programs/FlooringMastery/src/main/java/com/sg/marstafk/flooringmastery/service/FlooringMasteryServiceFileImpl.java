/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.service;

import com.sg.marstafk.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.marstafk.flooringmastery.dao.FlooringMasteryAuditDaoFileImpl;
import com.sg.marstafk.flooringmastery.dao.FlooringMasteryDao;
import com.sg.marstafk.flooringmastery.dao.FlooringMasteryDaoFileImpl;
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
public class FlooringMasteryServiceFileImpl implements FlooringMasteryService{
    
    FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
    FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoFileImpl();
    
    public FlooringMasteryServiceFileImpl(FlooringMasteryDao dao, 
            FlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addOrder(String name, StateTax state, Product product, 
            BigDecimal area, LocalDate date) throws InvalidEntryException,
            PersistenceException {
        
        if (name != null || state != null || product != null || area != null
                || date != null) {
            Order newOrder = new Order(name, state, product, area, date);
            dao.addOrder(newOrder);
            
            auditDao.writeAuditEntry("Order: " + newOrder.getOrderNumber() + 
                " for " + newOrder.getCustomerName() + " CREATED.");
        } else {
            throw new InvalidEntryException("Could not create Order!");
        }              
    }

    @Override
    public void editOrder(int orderNumber, Order order)
            throws PersistenceException, InvalidEntryException {
        
        if (orderNumber > 0 && order != null) {
           dao.editOrder(orderNumber, order); 
           auditDao.writeAuditEntry("Order: " + order.getOrderNumber() + " for " 
                + order.getCustomerName() + "UPDATED.");
        } else {
            throw new InvalidEntryException("Invalid Entry!");
        }      
    }

    @Override
    public List<Order> orderByDate(LocalDate date) throws PersistenceException,
            InvalidEntryException {
        
        if (date != null) {
        return dao.ordersByDate(date);   
        } else {
            throw new InvalidEntryException("Invalid Entry!");
        }
        
    }

    @Override
    public Order listOrder(int orderNum) throws PersistenceException, 
            InvalidEntryException {
        
        if (orderNum > 0) {
          return dao.listOrder(orderNum);   
        } else {
            throw new InvalidEntryException("Invalid Entry!");
        }       
    }
    
    @Override
    public Collection<Product> listProducts() throws PersistenceException {
        
        if (dao.listProducts() != null) {
            return dao.listProducts();
        } else {
            throw new PersistenceException("Could not load Products!");
        }        
    }

    @Override
    public void removeOrder(int orderNum) throws PersistenceException, 
            InvalidEntryException {
        
        if (orderNum > 0) {
          dao.removeOrder(orderNum);  
          auditDao.writeAuditEntry("Order: " + orderNum + "REMOVED.");
        } else {
            throw new InvalidEntryException("Invalid Entry!");
        }           
    }

    @Override
    public void exportData(boolean testOrNot) throws PersistenceException {
        dao.exportData(testOrNot);
        
        auditDao.writeAuditEntry("ALL DATA EXPORTED.");
    }
    
    @Override
    public void write(boolean testOrNot) throws PersistenceException {
        dao.writeAll(testOrNot);
    }
    
    @Override 
    public void load(boolean testOrNot) throws PersistenceException {
        dao.loadAll(testOrNot);
    }

    @Override
    public StateTax getStateTax(String state) throws PersistenceException,
            InvalidEntryException {
        
        if (dao.getState(state) != null) {
          return dao.getState(state);  
        } else {
           throw new InvalidEntryException("Invalid Entry!"); 
        }        
    }

    @Override
    public Product getProduct(String material) throws PersistenceException,
            InvalidEntryException {
        
        if (dao.getProduct(material) != null) {
           return dao.getProduct(material);
        } else {
            throw new InvalidEntryException("Invalid Entry!"); 
        }        
    }
    
    

}
