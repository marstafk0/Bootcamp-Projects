/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.PersonType;
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
public class PersonTypeRepositoryTest {

    @Autowired
    PersonTypeRepository personTypeRepository;

    /**
     * Test of findByStatusName method, of class PersonTypeRepository.
     */
    @Test
    public void testFindByStatusName() {

        PersonType personType = personTypeRepository.findByStatusName("Student");

        assertEquals("Student", personType.getStatusName());

    }

    /**
     * Test of findAllOrderByAsc method, of class PersonTypeRepository.
     */
    @Test
    public void testFindAllOrderByAsc() {

        List<PersonType> types = personTypeRepository.findAllOrderByAsc();

        assertEquals("Member", types.get(0).getStatusName());
        assertEquals("Student", types.get(1).getStatusName());
        assertEquals("Teacher", types.get(2).getStatusName());

    }

    @Test
    public void testGetByPersonId() {

        PersonType personType = personTypeRepository.findByPersonId(1);

        assertEquals("Student", personType.getStatusName());

    }

}
