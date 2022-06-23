/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Person;
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
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonTypeRepository personTypeRepository;

    @Autowired
    GradeRepository gradeRepository;

    /**
     * Test of findAllByStatusName method, of class PersonRepository.
     */
    @Test
    public void testFindAllByStatusName() {

        List<Person> students = personRepository.findAllByStatusName("Student", true);
        List<Person> teachers = personRepository.findAllByStatusName("Teacher", true);
        List<Person> members = personRepository.findAllByStatusName("Member", true);

        for (Person s : students) {
            assertEquals("Student", personTypeRepository.findByPersonId(s.getId()).getStatusName());
        }
        for (Person t : teachers) {
            assertEquals("Teacher", personTypeRepository.findByPersonId(t.getId()).getStatusName());
        }
        for (Person m : members) {
            assertEquals("Member", personTypeRepository.findByPersonId(m.getId()).getStatusName());
        }

    }

    /**
     * Test of findAllByOrderByLastNameAsc method, of class PersonRepository.
     */
    @Test
    public void testFindAllByOrderByLastNameAsc() {

        List<Person> students = personRepository.findAllByOrderByLastNameAsc("Student");

        assertEquals("Fitzpatrick", students.get(0).getLastName());
        assertEquals("Stafki", students.get(10).getLastName());

    }

    /**
     * Test of findAllByOrderByFirstNameAsc method, of class PersonRepository.
     */
    @Test
    public void testFindAllByOrderByFirstNameAsc() {

        List<Person> students = personRepository.findAllByOrderByFirstNameAsc("Student");

        assertEquals("Amy", students.get(0).getFirstName());
        assertEquals("William", students.get(10).getFirstName());

    }

    /**
     * Test of findAllGradesByType method, of class PersonRepository.
     */
    @Test
    public void testFindAllGradesByType() {

        List<Person> students = personRepository.findAllGradesByType();

        assertEquals("1st", gradeRepository.findByPersonId(students.get(0).getId()).getGradeName());
        assertEquals("12th", gradeRepository.findByPersonId(students.get(10).getId()).getGradeName());

    }

    /**
     * Test of findAllByActive method, of class PersonRepository.
     */
    @Test
    public void testFindAllByActive() {

        List<Person> students = personRepository.findAllByActive(true);

        for (Person s : students) {
            assertTrue(s.isActive());
        }

    }

    /**
     * Test of findAllStudentsByClassroomId method, of class PersonRepository.
     */
    @Test
    public void testFindAllStudentsByClassroomId() {

        List<Person> students = personRepository.findAllStudentsByClassroomId(2);

        assertEquals(1, students.get(0).getId());
        assertEquals("Charlie", students.get(0).getFirstName());

    }

    /**
     * Test of findByPledgeId method, of class PersonRepository.
     */
    @Test
    public void testFindByPledgeId() {

        Person person = personRepository.findByPledgeId(3);

        assertEquals("Charlie", person.getFirstName());
        assertEquals(1, person.getId());
        assertEquals("Stafki", person.getLastName());

    }

    /**
     * Test of findByRunId method, of class PersonRepository.
     */
    @Test
    public void testFindByRunId() {

        Person person = personRepository.findByRunId(1);

        assertEquals("Charlie", person.getFirstName());
        assertEquals(1, person.getId());
        assertEquals("Stafki", person.getLastName());

    }

}
