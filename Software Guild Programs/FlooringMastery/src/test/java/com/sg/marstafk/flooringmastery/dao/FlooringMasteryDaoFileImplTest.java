/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery.dao;

import com.sg.marstafk.flooringmastery.mode1.Order;
import com.sg.marstafk.flooringmastery.mode1.Product;
import com.sg.marstafk.flooringmastery.mode1.StateTax;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author boss_
 */
public class FlooringMasteryDaoFileImplTest {
    
    FlooringMasteryDao dao;
    
    public FlooringMasteryDaoFileImplTest() {
        
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
        dao = new FlooringMasteryDaoFileImpl();
        dao.loadAll(true);
    }

    /**
     * Test of ordersByDate method, addOrder, getProduct and getState
     * @throws java.lang.Exception
     */
    @org.junit.jupiter.api.Test
    public void testOrdersByDateAndAddOrderAndGetProdState() throws Exception {
        
        String name = "Bobby Jones";
        StateTax state = dao.getState("MN");
        Product product = dao.getProduct("Tile");
        BigDecimal area = new BigDecimal(120);
        LocalDate date = LocalDate.parse("2025-10-10");
        Order order = new Order(name, state, product, area, date);
        
        
        dao.addOrder(order);
        LocalDate ld = LocalDate.now();
        List<Order> retrievedOrder = dao.ordersByDate(ld);
        BigDecimal taxrate = new BigDecimal(25.00).setScale(2);
        BigDecimal persqft = new BigDecimal(5.00).setScale(2);
        BigDecimal lpersqft = new BigDecimal(5.00).setScale(2);
        BigDecimal mat = new BigDecimal(600.00).setScale(2);
        BigDecimal l = new BigDecimal(600.00).setScale(2);
        BigDecimal tax = new BigDecimal(300.00).setScale(2);
        BigDecimal total = new BigDecimal(1500.00).setScale(2);
        dao.writeAll(true);
        
        assertEquals(order.getCustomerName(), "Bobby Jones");
        assertEquals(order.getStateTax().getStateName(), "MN");
        assertEquals(order.getStateTax().getTaxRate(), taxrate);
        assertEquals(order.getProduct().getProductType(), "Tile");
        assertEquals(order.getArea(), area);
        assertEquals(order.getProduct().getCostPerSquareFoot(), persqft);
        assertEquals(order.getProduct().getLaborCostPerSquareFoot(), lpersqft);
        assertEquals(order.getMaterialCost(), mat);
        assertEquals(order.getLaborCost(), l);
        assertEquals(order.getTax(), tax);
        assertEquals(order.getTotal(), total);
        assertEquals(order.getDate(), date);
        assertEquals(order.getCreationDate(), ld);
        assertFalse(retrievedOrder.isEmpty(), "Should be false");
        assertTrue(retrievedOrder.contains(order));
    }

    /**
     * Test of editOrder method, listByNum and listProducts
     * @throws java.lang.Exception
     */
    @org.junit.jupiter.api.Test
    public void testEditOrderAndListByNum() throws Exception {
        
        String name = "Jill Jack";
        StateTax state = dao.getState("CA");
        Product product = dao.getProduct("Hardwood");
        BigDecimal area = new BigDecimal(200);
        LocalDate date = LocalDate.parse("2022-05-05");
        Order order = new Order(name, state, product, area, date);
        
        dao.addOrder(order);
        LocalDate ld = LocalDate.now();
        List<Order> retrievedOrder = dao.ordersByDate(ld);
        
        assertFalse(retrievedOrder.isEmpty(), "Should be false");
        assertTrue(retrievedOrder.contains(order));
        assertEquals(order.getCustomerName(), "Jill Jack");
        dao.writeAll(true);
        
        String upname = "Jill Johnson";
        StateTax upstate = dao.getState("CA");
        Product upproduct = dao.getProduct("Tile");
        BigDecimal uparea = new BigDecimal(250);
        Order neworder = new Order(upname, upstate, upproduct, uparea, date);
        
        dao.editOrder(order.getOrderNumber(), neworder);
        Order updated = dao.listOrder(order.getOrderNumber());
        
        assertEquals(updated, neworder);
        assertEquals(updated.getCustomerName(), "Jill Johnson");
        
        assertNotNull(dao.listProducts());
        
    }

    /**
     * Test of exportData method, of class FlooringMasteryDaoFileImpl.
     * @throws java.lang.Exception
     */
    @org.junit.jupiter.api.Test
    public void testExportData() throws Exception {
        
        dao.exportData(true);
        File file = new File("C:\\Users\\boss_\\Documents\\NetBeansProjects"
                + "\\FlooringMastery\\TestBackup\\TestDataExport.txt");
        
        assertTrue(file.exists());
        
    }
    
}
