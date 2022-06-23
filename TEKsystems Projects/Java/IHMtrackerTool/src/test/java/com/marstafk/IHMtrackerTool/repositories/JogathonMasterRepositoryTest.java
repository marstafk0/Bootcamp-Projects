/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.JogathonMaster;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author boss_
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JogathonMasterRepositoryTest {

    @Autowired
    JogathonMasterRepository jogathonMasterRepository;

    /**
     * Test of findAllByActiveAndDeletion method, of class JogathonMasterRepository.
     */
    @Test
    public void testFindByActiveAndDeletion() {

        JogathonMaster jogathonMaster = jogathonMasterRepository.findByActiveAndDeletion(true, false);

        assertTrue(jogathonMaster.isActive());
        assertFalse(jogathonMaster.isDeletion());
        assertEquals(2, jogathonMaster.getId());
        assertEquals("Walmarts are open to collaborating this year.", jogathonMaster.getComments());

    }

}
