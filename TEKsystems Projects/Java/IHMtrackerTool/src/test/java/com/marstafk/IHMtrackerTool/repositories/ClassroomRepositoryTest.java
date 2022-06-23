/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Classroom;
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
public class ClassroomRepositoryTest {

    @Autowired
    ClassroomRepository classroomRepository;

    /**
     * Test of findAllByActive method, of class ClassroomRepository.
     */
    @Test
    public void testFindAllByActive() {

        List<Classroom> classrooms = classroomRepository.findAllByActive(true);

        for (Classroom classroom : classrooms) {
            assertEquals(true, classroom.isActive());
        }

    }

    /**
     * Test of findByGradeId method, of class ClassroomRepository.
     */
    @Test
    public void testFindByGradeId() {

        long gradeId = 3;
        Classroom classroom = classroomRepository.findByGradeId(gradeId);

        assertEquals("St. Dominic Sovio", classroom.getClassName());

    }

}
