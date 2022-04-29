/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.connections;

public class EmployeeModel {
	private int employeeNumber;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String extention;
	
	public EmployeeModel() {
		
	}

	public EmployeeModel(int employeeNumber, String firstName, String lastName, String jobTitle, String extention) {

		this.employeeNumber = employeeNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.extention = extention;
	}
	

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

    @Override
    public String toString() {
        return "EmployeeModel{" + "employeeNumber=" + employeeNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", jobTitle=" + jobTitle + ", extention=" + extention + '}';
    }
        
}
