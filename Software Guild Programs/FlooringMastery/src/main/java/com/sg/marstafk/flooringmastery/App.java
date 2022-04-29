/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.flooringmastery;

import com.sg.marstafk.flooringmastery.controller.FlooringMasteryController;
import com.sg.marstafk.flooringmastery.service.InvalidEntryException;
import com.sg.marstafk.flooringmastery.service.PersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author boss_
 */
public class App {

    /**
     * @param args the command line arguments
     * @throws com.sg.marstafk.flooringmastery.service.PersistenceException
     * @throws com.sg.marstafk.flooringmastery.service.InvalidEntryException
     */
    public static void main(String[] args) throws PersistenceException, 
            InvalidEntryException{
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller",
                FlooringMasteryController.class);
        controller.run();              
    }
    
}
