/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.perscholas.marstafk.sms.jpa.dao;

import com.perscholas.marstafk.sms.jpa.entitymodels.Course;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface CourseDao {
    
    List<Course> getAllCourses();
    
}
