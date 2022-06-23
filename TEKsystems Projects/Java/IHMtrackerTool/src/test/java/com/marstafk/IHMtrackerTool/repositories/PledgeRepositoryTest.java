/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Pledge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author boss_
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PledgeRepositoryTest {

    @Autowired
    PledgeRepository pledgeRepository;

    /**
     * Test of findAllByActiveAndDeletion method, of class PledgeRepository.
     */
    @Test
    public void testFindAllByActiveAndDeletion() {

        List<Pledge> pledges = pledgeRepository.findAllByActiveAndDeletion(true, false);

        for (Pledge p : pledges) {
            assertTrue(p.isActive());
            assertFalse(p.isDeletion());
        }

    }

    /**
     * Test of findAllByPersonId method, of class PledgeRepository.
     */
    @Test
    public void testFindAllByPersonId() {

        List<Pledge> pledges = pledgeRepository.findAllByPersonId(1);

        assertEquals(3, pledges.get(0).getId());
        assertEquals("15.00", pledges.get(0).getOneTime().toString());
        assertEquals("1.50", pledges.get(0).getPerLap().toString());
        assertEquals("30.00", pledges.get(0).getTotal().toString());
        assertTrue(pledges.get(0).isActive());
        assertTrue(pledges.get(0).isCollected());
        assertFalse(pledges.get(0).isDeletion());
        assertFalse(pledges.get(0).isReceipt());
    }

    /**
     * Test of findAllbyCurrentJog method, of class PledgeRepository.
     */
    @Test
    public void testFindAllbyCurrentJog() {

        List<Pledge> pledges = pledgeRepository.findAllbyCurrentJog();

        for (Pledge p : pledges) {
            assertFalse(p.isDeletion());
            assertTrue(p.isActive());
        }

    }

    /**
     * Test of findAllbyJogIdWeekInactive method, of class PledgeRepository.
     */
    @Test
    public void testFindAllbyJogIdWeekInactive() {

        List<Pledge> pledges = pledgeRepository.findAllbyJogIdWeekInactive(1, 1);
        Pledge pledge = pledges.get(0);

        assertEquals(2, pledge.getId());
        assertEquals("50.00", pledge.getOneTime().toString());
        assertEquals("0.00", pledge.getPerLap().toString());
        assertEquals("50.00", pledge.getTotal().toString());
        assertFalse(pledge.isActive());
        assertTrue(pledge.isCollected());
        assertFalse(pledge.isDeletion());
        assertFalse(pledge.isReceipt());

    }

    /**
     * Test of findAllByWeekCurrent method, of class PledgeRepository.
     */
    @Test
    public void testFindAllByWeekCurrent() {

        List<Pledge> pledges = pledgeRepository.findAllByWeekCurrent(1);
        Pledge pledge = pledges.get(0);

        assertEquals(1, pledge.getId());
        assertEquals("24.99", pledge.getOneTime().toString());
        assertEquals("0.00", pledge.getPerLap().toString());
        assertEquals("24.99", pledge.getTotal().toString());
        assertTrue(pledge.isActive());
        assertTrue(pledge.isCollected());
        assertFalse(pledge.isDeletion());
        assertFalse(pledge.isReceipt());

    }

    /**
     * Test of findAllByGradeId method, of class PledgeRepository.
     */
    @Test
    public void testFindAllByGradeId() {

        List<Pledge> pledges = pledgeRepository.findAllByGradeId(10);
        Pledge pledge = pledges.get(0);

        assertEquals(1, pledge.getId());
        assertEquals("24.99", pledge.getOneTime().toString());
        assertEquals("0.00", pledge.getPerLap().toString());
        assertEquals("24.99", pledge.getTotal().toString());
        assertTrue(pledge.isActive());
        assertTrue(pledge.isCollected());
        assertFalse(pledge.isDeletion());
        assertFalse(pledge.isReceipt());

    }

    /**
     * Test of findAllByClassroomId method, of class PledgeRepository.
     */
    @Test
    public void testFindAllByClassroomId() {

        List<Pledge> pledges = pledgeRepository.findAllByClassroomId(10);
        Pledge pledge = pledges.get(0);

        assertEquals(1, pledge.getId());
        assertEquals("24.99", pledge.getOneTime().toString());
        assertEquals("0.00", pledge.getPerLap().toString());
        assertEquals("24.99", pledge.getTotal().toString());
        assertTrue(pledge.isActive());
        assertTrue(pledge.isCollected());
        assertFalse(pledge.isDeletion());
        assertFalse(pledge.isReceipt());
    }

}
