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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author boss_
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    
    private File ORDER_FILE;
    private final String STATETAX_FILE = "state.txt";
    private final String PRODUCTS_FILE = "products.txt";
    private final String DELIMETER = ",";
    
    public FlooringMasteryDaoFileImpl() {
        
    }
    
    private final Map<Integer, Order> orders = new HashMap<>();
    private final Map<String, StateTax> taxes = new HashMap<>();
    private final Map<String, Product> products = new HashMap<>();
    private final Map<String, StateTax> taxesTwo = new HashMap<>();

    @Override
    public List<Order> ordersByDate(LocalDate date) throws PersistenceException{ 
        
        return orders.values().stream().filter(d -> d.getCreationDate()
                .equals(date)).collect(Collectors.toList());
               
    }

    @Override
    public void addOrder(Order order) throws PersistenceException{
        
        order.setOrderNumber(getNewOrderNum());
        order.setCreationDate(LocalDate.now());
        orders.put(order.getOrderNumber(), order);

    }

    @Override
    public void editOrder(int orderNumber, Order order) 
            throws PersistenceException{
        
        orders.replace(orderNumber, order);
      
    }

    @Override
    public Product getProduct(String productType) throws PersistenceException{
        
        return products.get(productType);
        
    }

    @Override
    public StateTax getState(String stateName) throws PersistenceException{
        
        if (taxes.containsKey(stateName)) {
            return taxes.get(stateName);
        } else {
            return taxesTwo.get(stateName);
        }     
    }

    private int getNewOrderNum() {
        
        int orderNum = 0;
        
        for(int n : orders.keySet()) {
            if (n > orderNum) {
                orderNum = n;
            }
        }
        orderNum++;
        return orderNum;
    }

    @Override
    public Order listOrder(int orderNum) throws PersistenceException{
        
        return orders.get(orderNum);
        
    }
    
    @Override
    public Collection<Product> listProducts() throws PersistenceException {
        
        return products.values();
        
    }
    
    @Override
    public void removeOrder(int orderNumber) throws PersistenceException{
        
        orders.remove(orderNumber);

    }
    
    @Override
    public void exportData(boolean testOrNot) throws PersistenceException {     
         
        File UPDATE_FILE;
        
        if (testOrNot) {
        UPDATE_FILE = new File("C:\\Users\\boss_\\Documents\\NetBeansProjects"
                + "\\FlooringMastery\\TestBackup\\TestDataExport.txt");
        } else {
        UPDATE_FILE = new File("C:\\Users\\boss_\\Documents\\NetBeansProjects"
                + "\\FlooringMastery\\Backup\\DataExport.txt");     
        }
                 
        PrintWriter out;      
        
        try {
            out = new PrintWriter(new FileWriter(UPDATE_FILE));
        } catch (IOException e) {
            throw new PersistenceException("Could not save order data", e);
        }
        List<Order> orderList = new ArrayList(orders.values());
                          
        //Marshal edited order data
        out.println("Order Number|CustomerName|StateName|StateTaxRate|"
                + "Material|Area|CostPerSqFt|CostLaborPerSqFt|"
                + "MaterialCost|LaborCost|Tax|Total|DateOfOrder|"
                + "CreationDate|");
        orderList.forEach(currentOrder -> {
            out.println(currentOrder.getOrderNumber() + DELIMETER 
                    + currentOrder.getCustomerName() + DELIMETER
                    + currentOrder.getStateTax().getStateName() + DELIMETER
                    + currentOrder.getStateTax().getTaxRate() + DELIMETER
                    + currentOrder.getProduct().getProductType() + DELIMETER
                    + currentOrder.getArea() + DELIMETER 
                    + currentOrder.getProduct().getCostPerSquareFoot() + 
                    DELIMETER
                    + currentOrder.getProduct().getLaborCostPerSquareFoot() +
                    DELIMETER + currentOrder.getMaterialCost() + DELIMETER
                    + currentOrder.getLaborCost() + DELIMETER
                    + currentOrder.getTax() + DELIMETER 
                    + currentOrder.getTotal() + DELIMETER
                    + currentOrder.getDate() + DELIMETER + 
                    currentOrder.getCreationDate());      
            out.flush();
        });
        out.close();
    }
  
    //load ALL orders
    private void loadOrders(boolean testOrNot) throws PersistenceException{

        Scanner scanner;
        loadProducts();
        loadStateTax();
        File folder;
        File[] listOfFiles;
        
            if (testOrNot) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyy");
            ORDER_FILE = new File
                ("C:\\Users\\boss_\\Documents\\NetBeansProjects\\FlooringMastery\\TestOrders\\"
                        + "TestOrders_" + dateFormat.format(date) + ".txt");                      
            } else {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyy");
            ORDER_FILE = new File
                ("C:\\Users\\boss_\\Documents\\NetBeansProjects\\FlooringMastery\\Orders\\Orders_"
                        + dateFormat.format(date) + ".txt"); 
            }
            
            try {
                if (!ORDER_FILE.exists()) {
               ORDER_FILE.createNewFile();
            }
            } catch (IOException e) {
                throw new PersistenceException("Could not create file", e);
            }
            
            if (testOrNot) {
            folder = new File
                ("C:\\Users\\boss_\\Documents\\NetBeansProjects\\FlooringMastery\\TestOrders\\");
            listOfFiles = folder.listFiles();
            } else {
            folder = new File
                ("C:\\Users\\boss_\\Documents\\NetBeansProjects\\FlooringMastery\\Orders\\");
            listOfFiles = folder.listFiles();    
            }            
            
            for (File file : listOfFiles) {
                if (file.isFile()) {
                   try{
                       scanner = new Scanner(new BufferedReader
                            (new FileReader(file)));
                    } catch (IOException e) {
                        throw new PersistenceException
                            ("Could not load Order", e);
                    }
        
                        String currentLine;
                        String[] currentTokens;

                        while (scanner.hasNextLine()) {
                            currentLine = scanner.nextLine();
                            currentTokens = currentLine.split(DELIMETER);

                            Order currentOrder = new Order(currentTokens[1], 
                                    getState(currentTokens[2]), 
                                    getProduct(currentTokens[4]),
                                    new BigDecimal(currentTokens[5]), 
                                    LocalDate.parse(
                                    currentTokens[12]));

                            currentOrder.setOrderNumber(Integer.parseInt
                            (currentTokens[0]));
                            currentOrder.setCreationDate(LocalDate.parse
                            (currentTokens[13]));
                            orders.put(currentOrder.getOrderNumber(), 
                                    currentOrder);
                        }
                        scanner.close();
                   } 
                }
            }
    
    //load state file
    private void loadStateTax() throws PersistenceException{
        
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader
                (STATETAX_FILE)));
        } catch (IOException e) {
            throw new PersistenceException("Could not load State file", e);
        }
        
        String currentLine;
        String[] currentTokens;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMETER);
            
            StateTax currentState = new StateTax(currentTokens[0], 
                    new BigDecimal(currentTokens[2]));  
            StateTax currentStateTwo = new StateTax(currentTokens[1],
                    new BigDecimal(currentTokens[2]));
            
            taxes.put(currentState.getStateName(), currentState);
            taxesTwo.put(currentStateTwo.getStateName(), currentStateTwo);
        }
        scanner.close();
    }
    
    //Load products file
    private void loadProducts() throws PersistenceException{
        
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader
                (PRODUCTS_FILE)));
        } catch (IOException e) {
            throw new PersistenceException("Could not load Products file", e);
        }
        
        String currentLine;
        String[] currentTokens;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMETER);
            
            Product currentProduct = new Product(currentTokens[0],
                    new BigDecimal(currentTokens[1]), new BigDecimal(
                    currentTokens[2]));
            
            products.put(currentProduct.getProductType(), currentProduct);
        }
        scanner.close();
    }
    
    //Writes orders to their respective dates of creation
    private void writeOrder(boolean testOrNot) throws 
            PersistenceException{
        
        for (Order o : orders.values()) {
        File UPDATE_FILE;
        
        if (testOrNot) {
        String formattedDate = o.getCreationDate().format
        (DateTimeFormatter.ofPattern("MMddyyy"));
        UPDATE_FILE = new File("C:\\Users\\boss_\\Documents\\NetBeansProjects"
                + "\\FlooringMastery\\TestOrders\\TestOrders_" 
                + formattedDate + ".txt");
        } else {
        String formattedDate = o.getCreationDate().format
        (DateTimeFormatter.ofPattern("MMddyyy"));
        UPDATE_FILE = new File("C:\\Users\\boss_\\Documents\\NetBeansProjects"
                + "\\FlooringMastery\\Orders\\Orders_" + formattedDate + ".txt");
        }
                                    
        if (orders.values().isEmpty()) { 
            UPDATE_FILE.delete();
        } 
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(UPDATE_FILE));
        } catch (IOException e) {
            throw new PersistenceException("Could not save order data", e);
        }
        List<Order> orderList = new ArrayList(orders.values().stream()
                .filter(d -> d.getCreationDate().equals(o.getCreationDate()))
                .collect(Collectors.toList()));
                          
        //Marshal edited order data
        orderList.forEach(currentOrder -> {
            out.println(currentOrder.getOrderNumber() + DELIMETER 
                    + currentOrder.getCustomerName() + DELIMETER
                    + currentOrder.getStateTax().getStateName() + DELIMETER
                    + currentOrder.getStateTax().getTaxRate() + DELIMETER
                    + currentOrder.getProduct().getProductType() + DELIMETER
                    + currentOrder.getArea() + DELIMETER 
                    + currentOrder.getProduct().getCostPerSquareFoot() + 
                    DELIMETER
                    + currentOrder.getProduct().getLaborCostPerSquareFoot() +
                    DELIMETER + currentOrder.getMaterialCost() + DELIMETER
                    + currentOrder.getLaborCost() + DELIMETER
                    + currentOrder.getTax() + DELIMETER 
                    + currentOrder.getTotal() + DELIMETER
                    + currentOrder.getDate() + DELIMETER + 
                    currentOrder.getCreationDate());      
            out.flush();
        });
        out.close();
       } 
    }
    
    //For loading all Objects Files
    @Override
    public void loadAll(boolean testOrNot) throws PersistenceException{

        this.loadOrders(testOrNot);
        this.loadStateTax();
        this.loadProducts();
        
    }
    
    //For writing all Object files
    @Override
    public void writeAll(boolean testOrNot) throws PersistenceException{
        
        this.writeOrder(testOrNot);
        
    }
    
}
