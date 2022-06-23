/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Grade;

import java.util.List;

/**
 * @author boss_
 */
public interface GradeService {

    List<Grade> getAllGrades();

    List<Grade> getAllGradesByClassroomId(long id);

    Grade getGradeById(Long id) throws ObjectNotFoundException;

    void saveGrade(Grade grade);

    Grade getGradeByPersonId(Long id);

}
