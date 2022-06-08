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
    public List<Person> getAllStudentsAZlastName() {
        return personRepository.findAllByOrderByLastNameAsc(); 
    }

    @Override
    public List<Person> getAllStudentsGrade() {
        return personRepository.findAllByGrade();
    }

    @Override
    public List<Person> getAllStudentsMoney() {
        return null;
    }

    @Override
    public List<Person> getAllTeachers() {
        return null;
    }

    @Override
    public List<Person> getAllPeople(boolean active) {return personRepository.findAllByActive(active);}

    @Override
    public Person getPersonById(long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public void updatePerson(Person person) {
        personRepository.updatePerson(person.getFirstName(), person.getLastName(), person.getContact(), person.isActive(), person.getId());
    }

    @Override
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePerson(long id) {

    }

    @Override
    public List<Person> getAllStudentsAZfirstName() {
        return personRepository.findAllByOrderByFirstNameAsc(); 
    }

    @Override
    public List<Person> getAllActiveStudents() {
        
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<Person> pagedResult = personRepository.findAllByActive(paging);
//
//        return pagedResult.toList();
        return personRepository.findAllByActive();
    }

    @Override
    public List<Person> getAllInactiveStudents() {
        return personRepository.findAllByInactive();
    }
}
