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
public class Employee {
    
    private long employeeId;
    private String employeeName;
    private String employeeAddress;
    private long employeePhone;
    protected double basicSalary;
    private double specialAllowance = 250.80;
    private double hra = 1000.50;

    public Employee(long id, String name, String address, long phone, double salary) {
        this.employeeId = id;
        this.employeeName = name;
        this.employeeAddress = address;
        this.employeePhone = phone;
        this.basicSalary = salary;
    }
    
    public double calculateSalary() {
        return basicSalary + ( basicSalary * specialAllowance/100) + ( basicSalary * hra/100);
    }
    
    public double calculateTransportAllowance() {
        return 10*basicSalary/100;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public long getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(long employeePhone) {
        this.employeePhone = employeePhone;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(double specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    public double getHra() {
        return hra;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }
    
}
