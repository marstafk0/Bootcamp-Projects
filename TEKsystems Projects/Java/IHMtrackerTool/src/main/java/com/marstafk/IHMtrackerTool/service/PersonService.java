package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Person;

import java.util.List;

public interface PersonService {
    // Get all students
    List<Person> getAllPeopleByType(String type, boolean active);
    // Get all active students
    List<Person> getAllActiveStudents();
    // Get all inactive students
    List<Person> getAllInactiveStudents();
    // Sorted A-Z last name
    List<Person> getAllStudentsAZlastName();
    // Sorted A-Z first name
    List<Person> getAllStudentsAZfirstName();
    // Sorted K-12th
    List<Person> getAllStudentsGrade();
    // Sorted Donations Min-Max
    List<Person> getAllStudentsMoney();

    List<Person> getAllTeachers();

    List<Person> getAllPeople(boolean active);

    Person getPersonById(long id);

    void updatePerson(Person person);

    void addPerson(Person person);

    void deletePerson(long id);

}
