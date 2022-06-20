package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.repositories.PersonRepository;
import com.marstafk.IHMtrackerTool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> getAllPeopleByType(String type, boolean active) {
        return personRepository.findAllByStatusName(type, active);
    }

    @Override
    public List<Person> getAllStudentsAZlastName(String type) {
        return personRepository.findAllByOrderByLastNameAsc(type);
    }

    @Override
    public List<Person> getAllStudentsGrade() {
        return personRepository.findAllGradesByType();
    }

    @Override
    public List<Person> getAllPeople(boolean active) {return personRepository.findAllByActive(active);}

    @Override
    public Person getPersonById(long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Person> getAllStudentsAZfirstName(String type) {
        return personRepository.findAllByOrderByFirstNameAsc(type);
    }

    @Override
    public List<Person> getAllPersonsByGradeId(long id) {
        return personRepository.findAllStudentsByGradeId(id);
    }

    @Override
    public List<Person> getAllActiveStudents(String type) {
        return personRepository.findAllByActive(type);
    }

    @Override
    public List<Person> getAllPersonsByClassroomId(long id) {
        return personRepository.findAllStudentsByClassroomId(id);
    }

    @Override
    public Person getPersonByRunId(long id) { return personRepository.findByRunId(id); }


    @Override
    public Person getPersonByPledgeId(long id) {
        return personRepository.findByPledgeId(id);
    }

    @Override
    public List<Person> getAllInactiveStudents(String type) {
        return personRepository.findAllByInactive(type);
    }
}
