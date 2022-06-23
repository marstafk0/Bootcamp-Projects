package com.marstafk.IHMtrackerTool.services;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.*;
import com.marstafk.IHMtrackerTool.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceTests {

    @Autowired
    PersonService personService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    FamilyService familyService;

    @Autowired
    GradeService gradeService;

    @Autowired
    JogathonMasterService jogathonMasterService;

    @Autowired
    PersonTypeService personTypeService;

    @Autowired
    RunService runService;

    @Autowired
    PledgeService pledgeService;

    @Autowired
    PledgeTypeService pledgeTypeService;

    @Autowired
    SponsorService sponsorService;

    /**
     * Test of getPersonByPledgeId method, of class PersonService.
     */
    @Test
    public void testGetPersonByPledgeId() {

        try {
            Person person = personService.getPersonByPledgeId(1);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get Person by Pledge");
        }
        try {
            Person person1 = personService.getPersonByPledgeId(999);
        } catch (ObjectNotFoundException e) {

        }
    }

    /**
     * Test of getClassroomById method, of class ClassroomService.
     */
    @Test
    public void testGetClassroomById() {

        try {
            Classroom classroom = classroomService.getClassroomById(1);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get Classroom");
        }
        try {
            Classroom classroom = classroomService.getClassroomById(999);
        } catch (ObjectNotFoundException e) {

        }
    }

    /**
     * Test of getFamilyById method, of class FamilyService.
     */
    @Test
    public void testGetFamilyById() {

        long id = 1;
        Family fam = familyService.getFamilyById(id);

        assertEquals(1, fam.getId());

        long id2 = 999;
        Family family = familyService.getFamilyById(id2);

        assertEquals(0, family.getId());
    }

    /**
     * Test of getGradeById method, of class GradeService.
     */
    @Test
    public void testGetGradeById() {

        try {
            long id = 1;
            Grade grade = gradeService.getGradeById(id);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get Grade");
        }
        try {
            long id = 999;
            Grade grade = gradeService.getGradeById(id);
        } catch (ObjectNotFoundException e) {

        }

    }

    /**
     * Test of getJogathonById method, of class JogathonMasterService.
     */
    @Test
    public void testGetJogathonById() {

        try {
            JogathonMaster master = jogathonMasterService.getJogathonById(1);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get Jogathon");
        }
        try {
            JogathonMaster master = jogathonMasterService.getJogathonById(999);
        } catch (ObjectNotFoundException e) {

        }
    }

    /**
     * Test of getPersonTypeById method, of class PersonTypeService.
     */
    @Test
    public void testGetPersonTypeById() {

        try {
            PersonType p = personTypeService.getPersonTypeById(1);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get PersonType");
        }
        try {
            PersonType p = personTypeService.getPersonTypeById(999);
        } catch (ObjectNotFoundException e) {

        }
    }

    /**
     * Test of getPledgeById method, of class PledgeService.
     */
    @Test
    public void testGetPledgeById() {

        try {
            Pledge p = pledgeService.getPledgeById(1);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get Pledge");
        }
        try {
            Pledge p = pledgeService.getPledgeById(999);
        } catch (ObjectNotFoundException e) {

        }
    }

    /**
     * Test of getPledgeTypeById method, of class PledgeTypeService.
     */
    @Test
    public void testGetPledgeTypeById() {

        try {
            PledgeType p = pledgeTypeService.getPledgeTypeById(1);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get PledgeType");
        }
        try {
            PledgeType p = pledgeTypeService.getPledgeTypeById(999);
        } catch (ObjectNotFoundException e) {

        }
    }

    /**
     * Test of getRunById method, of class RunService.
     */
    @Test
    public void testGetRunById() {

        try {
            Run t = runService.getByPersonId(1);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get Run");
        }
        try {
            Run t = runService.getByPersonId(999);
        } catch (ObjectNotFoundException e) {

        }
    }

    /**
     * Test of getSponsorById method, of class SponsorService.
     */
    @Test
    public void testGetSponsorById() {

        try {
            Sponsor s = sponsorService.getSponsorById(1);
        } catch (ObjectNotFoundException e) {
            fail("No exception should be thrown from get Run");
        }
        try {
            Sponsor s = sponsorService.getSponsorById(999);
        } catch (ObjectNotFoundException e) {

        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,Charlie,Stafki,parents,true",
    })
    void testGetPersonById(ArgumentsAccessor arguments) throws ArgumentAccessException, SQLException, ObjectNotFoundException {

        Person expected = new Person();
        expected.setId(arguments.getLong(0));
        expected.setFirstName(arguments.getString(1));
        expected.setLastName(arguments.getString(2));
        expected.setContact(arguments.getString(3));
        expected.setActive(arguments.getBoolean(4));

        Person actual = personService.getPersonById(1);
        Person actual2 = new Person();
        actual2.setId(actual.getId());
        actual2.setFirstName(actual.getFirstName());
        actual2.setLastName(actual.getLastName());
        actual2.setContact(actual.getContact());
        actual2.setActive(actual.isActive());

        assertEquals(expected, actual2);
    }

}
