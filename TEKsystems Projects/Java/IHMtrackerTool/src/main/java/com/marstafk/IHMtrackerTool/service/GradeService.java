/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Grade;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface GradeService {
    
    List<Grade> getAllGrades();
    
    Grade getGradeById(Long id);
    
    void saveGrade(Grade grade);
    
    Grade getGradeByPersonId(Long id);
    
}
