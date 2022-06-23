/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Family;
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
public class FamilyRepositoryTest {

    @Autowired
    FamilyRepository familyRepository;

    /**
     * Test of findByPersonId method, of class FamilyRepository.
     */
    @Test
    public void testFindByPersonId() {

        long personId = 1;
        Family family = familyRepository.findByPersonId(personId);

        assertEquals("Bob Seegar", family.getFamilyName());
        assertEquals(1, family.getId());

    }

    /**
     * Test of findAllByActive method, of class FamilyRepository.
     */
    @Test
    public void testFindAllByActive() {

        List<Family> families = familyRepository.findAllByActive(true);

        for (Family f : families) {
            assertEquals(true, f.isActive());
        }

    }

}
