/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.connections;

/**
 *
 * @author boss_
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

		EmployeeDAO employeeOne = new EmployeeDAO();
                
                employeeOne.addEmployee(1704, "Jaquin", "Bobby", "x12345", "jackk@gmail.com", "4", 1102, "Sales Rep");
                
                employeeOne.getAllEmployees().forEach(i -> System.out.println(i));
	}
    
}
