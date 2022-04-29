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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author boss_
 */
public class FlooringMasteryServiceFileImplTest {
    
    private final FlooringMasteryService service;
    
    public FlooringMasteryServiceFileImplTest() throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "applicationContext.xml");
        service = ctx.getBean("service", FlooringMasteryServiceFileImpl.class);
    }

    /**
     * Test of addOrder method, of class FlooringMasteryServiceFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddOrder() throws Exception {
        service.load(true);    
        
        StateTax state = service.getStateTax("MN");
        Product prod = service.getProduct("Tile");
        
        try {
        service.addOrder("Bob", state, prod, new BigDecimal(120), 
                LocalDate.parse("2022-05-05"));
        } catch (InvalidEntryException e) {
            fail("No exception should be thrown");
        }            
        try {
            service.addOrder("", state, prod, new BigDecimal(100), 
                    LocalDate.parse("2022-05-05"));
        } catch (InvalidEntryException e) {
            
        }
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testEditOrder() throws Exception {
        service.load(true);
        Product prod = service.getProduct("Tile");
        StateTax state = service.getStateTax("MN");
        Order order = new Order("Bob", state, prod, new BigDecimal(120), 
                LocalDate.parse("2022-12-12"));
        order.setOrderNumber(2);
        Order nullOrder = null;
        
        try {
            service.editOrder(2, order);
        } catch (InvalidEntryException e) {
            fail("No exception should be thrown");
        }      
        try {
            service.editOrder(-1, order);
        } catch (InvalidEntryException e) {
            
        }     
        try {
            service.editOrder(2, nullOrder);
        } catch (InvalidEntryException e) {
            
        }     
    }

    /**
     * Test of orderByDate method, of class FlooringMasteryServiceFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testOrderByDate() throws Exception {
        service.load(true);
        
        try {
            service.orderByDate(LocalDate.parse("2021-10-26"));
        } catch (InvalidEntryException e) {
            fail("No exception should be thrown");
        }      
        try {
            service.orderByDate(LocalDate.parse("2022-10-05"));
        } catch (InvalidEntryException e) {
           
        }      
    }

    /**
     * Test of listOrder method, of class FlooringMasteryServiceFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testListOrder() throws Exception {
        service.load(true); 
        
        try {
            service.listOrder(2);
        } catch (InvalidEntryException e) {
            fail("No exception should be thrown");
        }      
        try {
            service.listOrder(-1);
        } catch (InvalidEntryException e) {
            
        }      
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrder() throws Exception {
        service.load(true);
        
        try {
            service.removeOrder(2);
        } catch (InvalidEntryException e) {
            fail("No exception should be thrown");
        }      
        try {
            service.removeOrder(-1);
        } catch (InvalidEntryException e) {
            
        }      
    }

    /**
     * Test of getStateTax method, of class FlooringMasteryServiceFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetStateTax() throws Exception {
        service.load(true);
        try{
            service.getStateTax("MN");
        } catch (InvalidEntryException e) {
            fail("No exception should be thrown");
        }      
        try { 
            service.getStateTax("");
        } catch (InvalidEntryException e) {
            
        }
    }

    /**
     * Test of getProduct method, of class FlooringMasteryServiceFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetProduct() throws Exception {
        service.load(true);
        try{
            service.getProduct("Tile");
        } catch (InvalidEntryException e) {
            fail("No exception should be thrown");
        }  
        try { 
            service.getProduct("");
        } catch (InvalidEntryException e) {
            
        }
    }
    
    /**
     *Test of listProducts method.
     * @throws Exception
     */
    @Test
    public void testListProducts() throws Exception {
        
        try {
            service.listProducts();
        } catch (PersistenceException e) {
            fail("No exception should be thrown");
        }
    }
    
}
