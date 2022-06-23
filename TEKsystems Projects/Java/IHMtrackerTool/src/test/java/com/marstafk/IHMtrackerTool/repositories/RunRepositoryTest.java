/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Run;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author boss_
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RunRepositoryTest {

    @Autowired
    RunRepository runRepository;

    /**
     * Test of findByPersonId method, of class RunRepository.
     */
    @Test
    public void testFindByPersonId() {

        Run run = runRepository.findByPersonId(11);

        assertEquals(3, run.getId());
        assertEquals(15, run.getLaps());

    }

}
