/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FactoryConnection {
	private final String driverClassName ="com.mysql.cj.jdbc.Driver";
	private final String url = "jdbc:mysql://--/classicmodels?serverTimezone=America/Chicago&useSSL=false&allowPublicKeyRetrieval=true";
	private final String user = "root";
	private final String pass = "password!";
 
	private static FactoryConnection factoryConnection = null;
	
	private FactoryConnection() {
		try {
			Class.forName(driverClassName);
		}catch(ClassNotFoundException e) {
			System.out.println("Exception in FactoryConnection class");
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}
	
	public static FactoryConnection getInstance() {
		if(factoryConnection == null ) {
			factoryConnection = new FactoryConnection();
		}
		return factoryConnection;
	}	
}