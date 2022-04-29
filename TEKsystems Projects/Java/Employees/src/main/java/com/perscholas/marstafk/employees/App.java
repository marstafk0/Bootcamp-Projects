/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.employees;

import com.perscholas.marstafk.employees.entities.Manager;
import com.perscholas.marstafk.employees.entities.Trainee;

/**
 *
 * @author boss_
 */
public class InheritanceActivity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Manager managerOne = new Manager(126534, "Peter", "Chennai India", 237844, 65000);
        
        System.out.println(managerOne.calculateSalary());
        
        Trainee traineeOne = new Trainee(29846, "Jack", "Mumbia India", 442085, 45000);
        
        System.out.println(traineeOne.calculateSalary());
        System.out.println(traineeOne.calculateTransportAllowance());
        System.out.println(managerOne.calculateTransportAllowance());
        
    }
    
}
