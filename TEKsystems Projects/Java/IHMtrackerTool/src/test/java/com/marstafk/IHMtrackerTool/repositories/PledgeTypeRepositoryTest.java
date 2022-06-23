/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.PledgeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author boss_
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PledgeTypeRepositoryTest {

    @Autowired
    PledgeTypeRepository pledgeTypeRepository;

    /**
     * Test of findByActive method, of class PledgeTypeRepository.
     */
    @Test
    public void testFindByActive() {

        List<PledgeType> pledgeTypes = pledgeTypeRepository.findByActive(true);

        for (PledgeType p : pledgeTypes) {
            assertTrue(p.isActive());
        }

    }

    /**
     * Test of findByPledgeId method, of class PledgeTypeRepository.
     */
    @Test
    public void testFindByPledgeId() {

        PledgeType pledgeType = pledgeTypeRepository.findByPledgeId(1);

        assertEquals(1, pledgeType.getId());

    }

}
