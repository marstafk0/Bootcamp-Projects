/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Grade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author boss_
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GradeRepositoryTest {

    @Autowired
    GradeRepository gradeRepository;

    /**
     * Test of findByPersonId method, of class GradeRepository.
     */
    @Test
    public void testFindByPersonId() {

        long personId = 1;
        Grade grade = gradeRepository.findByPersonId(personId);

        assertEquals(2, grade.getId());
        assertEquals("1st", grade.getGradeName());
    }

    /**
     * Test of findAllGradesByClassroomId method, of class GradeRepository.
     */
    @Test
    public void testFindAllGradesByClassroomId() {

        long classId = 11;
        List<Grade> grades = gradeRepository.findAllGradesByClassroomId(classId);

        assertEquals(2, grades.size());
        assertEquals(12, grades.get(0).getId());
        assertEquals(13, grades.get(1).getId());
        assertEquals("11th", grades.get(0).getGradeName());
        assertEquals("12th", grades.get(1).getGradeName());

    }

}
