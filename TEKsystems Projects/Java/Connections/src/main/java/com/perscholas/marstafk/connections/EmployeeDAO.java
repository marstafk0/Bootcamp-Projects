/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {
	
	Connection connection = null;
	PreparedStatement ps =null;
	ResultSet resultSet =null; 
	
	public EmployeeDAO() {
		
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn;
		conn = FactoryConnection.getInstance().getConnection();
		return conn;
	}
	
	
	public ArrayList<EmployeeModel> getAllEmployees() {
		
		ArrayList<EmployeeModel> map = new ArrayList<>();
                
		try {
			String queryString = " SELECT * FROM employees ";
			connection = getConnection();
			ps=connection.prepareStatement(queryString);
			
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                            EmployeeModel employee = new EmployeeModel();
                            employee.setEmployeeNumber(resultSet.getInt("employeeNumber"));
                            employee.setFirstName(resultSet.getString("firstName"));
                            employee.setLastName(resultSet.getString("lastName"));
                            employee.setJobTitle(resultSet.getString("jobTitle"));
                            map.add(employee);
			}
                        connection.close();
		} catch (SQLException e) {
		}
                
		return map;
	}
        
        public void addEmployee(int id, String lname, String fname, String ext, String email, String ofcode, int reportsTo, String jobTitle) {
            
            try{
                
                String INSERT = "INSERT INTO employees(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES("
                        + "?,?,?,?,?,?,?,?)";
                connection = getConnection();
                ps = connection.prepareStatement(INSERT);
                ps.setInt(1, id);
                ps.setString(2, lname);
                ps.setString(3, fname);
                ps.setString(4, ext);
                ps.setString(5, email);
                ps.setString(6, ofcode);
                ps.setInt(7, reportsTo);
                ps.setString(8, jobTitle);
                ps.execute();
                
            } catch (SQLException e) {
                
            }
            
        }
}
