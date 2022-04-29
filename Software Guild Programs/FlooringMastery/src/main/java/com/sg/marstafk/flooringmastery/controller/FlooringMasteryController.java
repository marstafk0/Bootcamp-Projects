/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.controller;

import com.sg.marstafk.flooringmastery.mode1.Order;
import com.sg.marstafk.flooringmastery.mode1.Product;
import com.sg.marstafk.flooringmastery.mode1.StateTax;
import com.sg.marstafk.flooringmastery.service.FlooringMasteryService;
import com.sg.marstafk.flooringmastery.service.InvalidEntryException;
import com.sg.marstafk.flooringmastery.service.PersistenceException;
import com.sg.marstafk.flooringmastery.ui.FlooringMasteryView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author boss_
 */
public class FlooringMasteryController {
    
    FlooringMasteryView view;
    FlooringMasteryService service;
    boolean testOrNot = false;
    
    public FlooringMasteryController(FlooringMasteryView view,
            FlooringMasteryService service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() throws PersistenceException, InvalidEntryException{
        
        service.load(testOrNot);
        boolean keepGoing = true;
            
        do {
            
            int selection = view.printMenuAndGetSelection();
            
            switch (selection) {
                case 1 -> displayAllOrdersByDate();
                case 2 -> addOrder();
                case 3 -> editOrder();
                case 4 -> removeOrder();
                case 5 -> exportData();
                case 6 -> {
                    view.exit();
                    System.exit(0);
                }
            }
            
        } while (keepGoing);
            
        
        }
    
    // Menu 
    private void displayAllOrdersByDate() throws PersistenceException, 
            InvalidEntryException{
        
        boolean cont;       
        view.displayOrders();
        do {  
           LocalDate date = view.getExistingOrderDate();
        cont = view.displayAllOrders(service.orderByDate(date));  
        } while (cont);
        view.enter(); 
    }
    
    private void addOrder() throws PersistenceException, InvalidEntryException{      
 
        boolean stateBool = true;
        boolean prodBool = true;
        StateTax state = null;
        Product product = null;
        String stateName;
        String prodName;
        view.displayAddOrder();
        LocalDate date = view.getOrderDate();
        String name = view.getCustomerName();
        while (stateBool) {
            stateName = view.getState(); 
        try {                   
            state = service.getStateTax(stateName);
            stateBool = false;
        } catch (InvalidEntryException e) {
             view.print("We don't sell to that state yet!");
             stateBool = true;
        }        
        }
        while (prodBool) {
            view.print(service.listProducts().toString());
            prodName = view.getProduct(); 
        try {                   
            product = service.getProduct(prodName);
            prodBool = false;
        } catch (InvalidEntryException e) {
             view.print("We don't have that product yet!");
             prodBool = true;
        }        
        }
        BigDecimal area = view.getArea();
        String answer = view.confirmAdd();
          if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            service.addOrder(name, state, product, area, date);  
            view.saved();
            service.write(testOrNot);
            view.enter();
        } else {
            view.addCancelled();
            view.enter();
        }               
    }
    
    private void editOrder() throws PersistenceException, InvalidEntryException{                   
        
        boolean prodBool = true;
        boolean stateBool = true;
        boolean cont;
        boolean keepGoing;
        Order order;
        int orderNum;
        StateTax state = null;
        String upState;
        Product product = null;
        String upMat;
        
        view.displayEditOrder();
        do {  
        LocalDate date = view.getExistingOrderDate();
        cont = view.displayAllOrders(service.orderByDate(date));  
        } while (cont);
        do {
        orderNum = view.getOrderNum();
        order = service.listOrder(orderNum);
        keepGoing = view.displayOrder(orderNum, order);  
        } while(keepGoing);       
        String answer = view.answer();
        
        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("yes")) {
            
            view.display(order.getCustomerName());
            String upName = view.getUpdatedCustomerName();
            if (upName.isBlank()) {
                upName = order.getCustomerName();
            } 
            view.displayState(order.getStateTax());
            
            while (stateBool) {
            upState = view.getUpdatedState(); 
            try {                   
            if (upState.isBlank()) {
                state = order.getStateTax();
            } else {
                state = service.getStateTax(upState);
            }
            stateBool = false;
            } catch (InvalidEntryException e) {
             view.print("We don't sell to that state yet!");
             stateBool = true;
            }        
            }
            while (prodBool) {
            view.print(service.listProducts().toString());
            view.displayProduct(order.getProduct());
            upMat = view.getUpdatedProduct(); 
            try {                   
            if (upMat.isBlank()) {
                product = order.getProduct();
            } else {
                product = service.getProduct(upMat);
            }
            prodBool = false;
            } catch (InvalidEntryException e) {
             view.print("We don't have that product yet!");
             prodBool = true;
            }        
            }           
            view.displayArea(order.getArea());
            String areaS = view.getUpdatedArea();
            BigDecimal area;
            if (areaS.isBlank()) {                
                area = order.getArea();
            } else {
                area = new BigDecimal(areaS).setScale(2, 
                        RoundingMode.HALF_UP);
            }
            LocalDate upDate = order.getDate();
            Order updatedOrder = new Order(upName, state, product, area, upDate);
            view.displayOrder(orderNum, updatedOrder);
            String save = view.confirmEdit();
            if (save.equalsIgnoreCase("y") || save.equalsIgnoreCase("yes")) {
                updatedOrder.setCreationDate(order.getCreationDate());
                updatedOrder.setOrderNumber(order.getOrderNumber());
                service.editOrder(orderNum, updatedOrder);
                view.saved();
                service.write(testOrNot);
                view.enter();
            } else {
                view.editCancelled();
                view.enter();
            }           
        } else {     
            view.editCancelled();
            view.enter();
        }       
    }
    
    private void removeOrder() throws PersistenceException, 
            InvalidEntryException {
        
        boolean cont;
        boolean keepGoing;
        Order order;
        int orderNum;
        
        view.displayRemoveOrder();
        do {  
        LocalDate date = view.getExistingOrderDate();
        cont = view.displayAllOrders(service.orderByDate(date));  
        } while (cont);
        do {
        orderNum = view.getOrderNum();
        order = service.listOrder(orderNum);
        keepGoing = view.displayOrder(orderNum, order);  
        } while(keepGoing);
        String answer = view.confirmDelete();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            service.removeOrder(orderNum);
            view.saved();
            service.write(testOrNot);
            view.enter();
        }  else {
            view.removeCancelled();
            view.enter();
        }
    }
    
    private void exportData() throws PersistenceException {
        
        view.displayExportData();
        service.exportData(false);
        view.saved();
        view.enter();
    }
    
}
