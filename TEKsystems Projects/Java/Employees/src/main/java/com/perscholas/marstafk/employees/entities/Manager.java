/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.employees.entities;

/**
 *
 * @author boss_
 */
public class Manager extends Employee {

    public Manager(long id, String name, String address, long phone, double salary) {
        super(id, name, address, phone, salary);
    }
    
    @Override
    public double calculateTransportAllowance() {
        return 15*basicSalary/100;
    }

    
}
