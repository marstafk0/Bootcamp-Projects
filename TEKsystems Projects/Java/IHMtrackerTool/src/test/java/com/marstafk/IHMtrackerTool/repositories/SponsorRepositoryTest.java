/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Sponsor;
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
public class SponsorRepositoryTest {

    @Autowired
    SponsorRepository sponsorRepository;

    /**
     * Test of findByPledgeId method, of class SponsorRepository.
     */
    @Test
    public void testFindByPledgeId() {

        Sponsor sponsor = sponsorRepository.findByPledgeId(1);

        assertEquals(1, sponsor.getId());
        assertEquals("20022 Shelby Ave", sponsor.getAddressOne());
        assertEquals("Bob", sponsor.getFirstName());
        assertEquals("61243495494", sponsor.getPhone());
        assertEquals("66043", sponsor.getZip());

    }

}
