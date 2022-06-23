package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Person;

import java.util.List;

public interface PersonService {
    // Get all students
    List<Person> getAllPeopleByType(String type, boolean active);

    List<Person> getAllStudentsAZfirstName(String type);

    List<Person> getAllStudentsAZlastName(String type);

    List<Person> getAllStudentsGrade();

    List<Person> getAllPeople(boolean active);

    Person getPersonById(long id) throws ObjectNotFoundException;

    List<Person> getAllPersonsByClassroomId(long id);

    Person getPersonByPledgeId(long id) throws ObjectNotFoundException;

    Person getPersonByRunId(long id);

    void savePerson(Person person);

}
