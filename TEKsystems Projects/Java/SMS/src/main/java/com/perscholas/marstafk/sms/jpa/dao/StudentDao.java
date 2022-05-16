/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.perscholas.marstafk.sms.jpa.dao;

import com.perscholas.marstafk.sms.jpa.entitymodels.Course;
import com.perscholas.marstafk.sms.jpa.entitymodels.Student;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface StudentDao {
    
    List<Student> getAllStudents();
    Student getStudentByEmail(String sEmail);
    boolean validateStudent(String sEmail, String sPassword);
    void registerStudentToCourse(String sEmail, int cId);
    List<Course> getStudentCourses(String sEmail);
    
}
