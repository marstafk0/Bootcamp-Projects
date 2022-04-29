/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.ui;

import com.sg.marstafk.flooringmastery.mode1.Order;
import com.sg.marstafk.flooringmastery.mode1.Product;
import com.sg.marstafk.flooringmastery.mode1.StateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author boss_
 */
public class FlooringMasteryView {
    
    UserIO io = new UserIOConsoleImpl();
    
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    
    
    public int printMenuAndGetSelection() {
        
        io.print("""
                   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                    * <<Flooring Program>>
                    * 1. Display Orders
                    * 2. Add an Order
                    * 3. Edit an Order
                    * 4. Remove an Order
                    * 5. Export All Data
                    * 6. Quit
                    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                 """);
        
        return io.readInt("Please select between 1 through 6:", 1, 6);       
    }
    
    public LocalDate getOrderDate() throws DateTimeParseException {   
        boolean cont = true;
        LocalDate ld = null;
        while (cont) {
            try {
                String orderDate = io.readString("Please enter the order date. (Must be in the future!) (MM/dd/yyyy):");
                ld = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                LocalDate current = LocalDate.now();
                if (ld.isAfter(current) || ld.isEqual(current)) {
                    cont = false;
                } else {
                    io.print("Invalid Date!");
                }
            } catch (DateTimeParseException e){
                io.print("Invalid Date!");
            }      
        }
        return ld;
    }
    
    public String getCustomerName() {
        boolean cont = true; 
        String name = "";
        while (cont) {
            name = io.readString("\nPlease enter Customer name: ");
            if (!name.isBlank()) {
                cont = false;
            } else {
                io.print("Invalid entry!");
            }
        }
        return name;
    }
    
    public String getState() {
        boolean cont = true;
        String state = ""; 
        while (cont) {
            state = io.readString("\nPlease enter the State purchasing in: ");
            if (!state.isBlank()) {
                cont = false;
            } else {
                io.print("Invalid entry!");
            }
        }
        if (state.length() == 2) {
            return state.toUpperCase();
        } else {
         String firstLtr = state.substring(0, 1);
        String remStr = state.substring(1);
        firstLtr = firstLtr.toUpperCase();
        state = firstLtr + remStr;  
        return state;
        }
    }
    
    public String getProduct() {
        boolean cont = true;
        String prod = "";
        while (cont) {
            prod = io.readString("\nPlease enter Product you want to purchase:");
            if (!prod.isBlank()) {
                cont = false;
            } else {
                io.print("Invalid entry!");
            }
        }
        String firstLtr = prod.substring(0, 1);
        String remStr = prod.substring(1);
        firstLtr = firstLtr.toUpperCase();
        prod = firstLtr + remStr;
        return prod;
    }
    
    public BigDecimal getArea() {
        boolean cont = true; 
        BigDecimal area = null;
        while (cont) {
        String areaS = io.readString("\nPlease enter Area size needed: "
                + "(100sqft min.)");    
        try {
           area = new BigDecimal(areaS).setScale(2, RoundingMode.HALF_UP);       
            if (areaS.isBlank()) {
                io.print("Invalid area, must be more than 100");
            } else if (area.compareTo(new BigDecimal("100")) == -1) {
                io.print("Invalid entry!");
            } else {
                cont = false;
            } 
        } catch (NumberFormatException e) {
            io.print("Incorrect format try again!");
            cont = true;
        }
        
        }
        
        return area;
    }
    
    public boolean displayAllOrders(List<Order> list){
        boolean cont;
        if(!list.isEmpty()){

            list.forEach(order -> {
                io.print("Order Number: "+ order.getOrderNumber()+
                        " | Customer Name: " + order.getCustomerName() +
                        ", Order Date: " + order.getDate()
                        + ", Material: " + order.getProduct().getProductType() +
                        ", Area: " + order.getArea() + ", State: " +
                        order.getStateTax().getStateName()
                        + ", Total: " + order.getTotal());
            });
            cont = false;
       } else {
            io.print("No Orders were placed for that date.");
            cont = true;
        }
        return cont;
    }
    
    public LocalDate getExistingOrderDate() {
        boolean cont = true;
        LocalDate ld = null;
        while (cont) {
            try {
                String orderDate = io.readString("Please enter the date for "
                        + "the order you placed: (MM/dd/yyyy)");
                ld = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern
                        ("MM/dd/yyyy")); 
                cont = false;
            } catch (DateTimeParseException e){
                io.print("Invalid Date!");
            }  
        }
        return ld;
    }
    
    public int getOrderNum() {
        
        return io.readInt("Please enter Order number:");
        
    }
    
    public boolean displayOrder(int orderNum, Order order) {
        boolean cont;
        if (order != null) {            
            io.print("|Order Number: " + orderNum +
                    order.toString());
            cont = false;
        } else {                        
            io.print("No order exists.");
            cont = true;
        }        
        return cont;
    }
    
    public String getUpdatedCustomerName() {
        return io.readString("Please enter new Customer Name: ");
    }
    
    public String getUpdatedState() {
        String state = io.readString("Please enter new State: ");
        switch (state.length()) {
            case 2 -> {
                return state.toUpperCase();
            }
            case 0 -> {
                return state;
            }
            default -> {
                String firstLtr = state.substring(0, 1);
                String remStr = state.substring(1);
                firstLtr = firstLtr.toUpperCase();
                state = firstLtr + remStr;
                return state;        
            }
        }
    }
    
    public String getUpdatedProduct() {
        String prod = io.readString("Please enter new Material: ");       
        if (prod.length() == 0) {
            return prod;
        } else {
          String firstLtr = prod.substring(0, 1);
        String remStr = prod.substring(1);
        firstLtr = firstLtr.toUpperCase();
        prod = firstLtr + remStr;
        return prod;  
        }     
    }
    
    public String getUpdatedArea() {
        return io.readString("Please enter new Area: (100sqft min.)");
    }
    
    public String answer() {
       return io.readString("Do you wish to continue edit? (Y or Yes)");
    }
    
    public String confirmEdit() {
       return io.readString("Do you wish to save changes? (Y or Yes)");
    }
    
    public String confirmDelete() {
       return io.readString("Do you wish to confirm deletion? (Y or Yes)");
    }
    
    public String confirmAdd() {
        return io.readString("Do you wish to add order? (Y or Yes)");
    }
    
    public void display(String string) {
        io.print("Previous field: " + string);
    }
    
    public void displayState(StateTax state) {
        io.print("Previous State: " + state.toString());
    }
    
    public void displayProduct(Product prod) {
        io.print("Previous Material: " + prod.toString());
    }
    
    public void displayArea(BigDecimal area) {
        io.print("Previous Area: " + area.toString());
    }
    
    public void editCancelled() {
        io.print("Edit cancelled.");
    }
    
    public void removeCancelled() {
        io.print("Remove cancelled.");
    }
    
    public void addCancelled() {
        io.print("Add cancelled.");
    }
    
    public void displayOrders() {
        io.print("\n---Display Orders---\n");
    }
    
    public void displayAddOrder() {
        io.print("\n---Add Order---\n"); 
    }
    
    public void displayEditOrder() {
        io.print("\n---Edit Order---\n");
    }
    
    public void displayRemoveOrder() {
        io.print("\n---Remove Order---\n");
    }
    
    public void displayExportData() {
        io.print("\n---Export Data---\n");
    }
    
    public void saved() {
        io.print("Changes Saved!");
    }
    
    public void exit() {
        io.print("GoodBye!");
    }
    
    public void enter() {
        io.readString("Please press enter to continue:");
    }
    
    public void print(String string) {
        io.print(string);
    }
    
}
