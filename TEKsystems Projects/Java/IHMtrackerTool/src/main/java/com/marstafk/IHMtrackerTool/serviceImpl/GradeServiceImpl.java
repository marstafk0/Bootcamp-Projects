/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.Grade;
import com.marstafk.IHMtrackerTool.repositories.GradeRepository;
import com.marstafk.IHMtrackerTool.service.GradeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author boss_
 */
@Service
public class GradeServiceImpl implements GradeService{
    
    @Autowired
    GradeRepository gradeRepository;

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public List<Grade> getAllGradesByClassroomId(long id) {
        return gradeRepository.findAllGradesByClassroomId(id);
    }

    @Override
    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).get();
    }

    @Override
    public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    public Grade getGradeByPersonId(Long id) {
        return gradeRepository.findByPersonId(id);
    }
    
}
