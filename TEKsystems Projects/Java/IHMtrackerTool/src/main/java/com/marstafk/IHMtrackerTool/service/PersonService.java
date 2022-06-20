package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Person;

import java.util.List;

public interface PersonService {
    // Get all students
    List<Person> getAllPeopleByType(String type, boolean active);

    List<Person> getAllStudentsAZfirstName(String type);

    List<Person> getAllInactiveStudents(String type);

    List<Person> getAllStudentsAZlastName(String type);

    public List<Person> getAllActiveStudents(String type);

    List<Person> getAllStudentsGrade();

    List<Person> getAllPeople(boolean active);

    Person getPersonById(long id);

    List<Person> getAllPersonsByGradeId(long id);

    List<Person> getAllPersonsByClassroomId(long id);

    Person getPersonByPledgeId(long id);

    Person getPersonByRunId(long id);
    void savePerson(Person person);

}
