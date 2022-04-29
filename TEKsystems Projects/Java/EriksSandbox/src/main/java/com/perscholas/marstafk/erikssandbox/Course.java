/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.erikssandbox;

/**
 *
 * @author boss_
 */
public class Course {
	String code;
	String name; 
	String instructor;
	
	public Course(String code, String name, String instructor) {
		this.code = code;
		this.name = name;
		this.instructor = instructor;
	}
	

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getInstructor() {
		return instructor;
	}
}
