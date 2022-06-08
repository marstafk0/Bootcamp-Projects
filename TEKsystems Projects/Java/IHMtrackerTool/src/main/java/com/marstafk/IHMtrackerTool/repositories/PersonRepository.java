/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author boss_
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT p.* FROM person p " +
            "JOIN person_type_person ptp " +
            "ON ptp.person_id = p.id " +
            "JOIN person_type pt " +
            "ON ptp.person_type_id = pt.id " +
            "WHERE pt.status_name = :type AND p.active = :active", nativeQuery = true)
    public List<Person> findAllByStatusName(String type, boolean active);

    @Query(value = "UPDATE Person SET fname = :firstName, lname = :lastName, contact = :contact, active = :active WHERE id = :id")
    public void updatePerson(String firstName, String lastName, String contact, boolean active, long id);

    @Query(value = "SELECT * FROM person p JOIN person_type_person ptp ON p.id = ptp.person_id "
            + "JOIN person_type pt ON pt.id = ptp.person_type_id WHERE pt.status_name = 'Student' "
            + "AND p.active = true ORDER BY p.lname ASC", nativeQuery = true)
    public List<Person> findAllByOrderByLastNameAsc();
    
    @Query(value = "SELECT * FROM person p JOIN person_type_person ptp ON p.id = ptp.person_id "
            + "JOIN person_type pt ON pt.id = ptp.person_type_id WHERE pt.status_name = 'Student' "
            + "AND p.active = true ORDER BY p.fname ASC", nativeQuery = true)
    public List<Person> findAllByOrderByFirstNameAsc();
    
    @Query(value = "SELECT * FROM person p JOIN person_type_person ptp ON p.id = ptp.person_id "
            + "JOIN person_type pt ON pt.id = ptp.person_type_id WHERE pt.status_name = 'Student' "
            + "AND p.active = true", nativeQuery = true)
    public List<Person> findAllByActive();
    
    @Query(value = "SELECT * FROM person p JOIN person_type_person ptp ON p.id = ptp.person_id "
            + "JOIN person_type pt ON pt.id = ptp.person_type_id WHERE pt.status_name = 'Student' "
            + "AND p.active = false", nativeQuery = true)
    public List<Person> findAllByInactive();
    
    @Query(value = """
                   SELECT * FROM person p JOIN grade_person gp 
                   ON p.id = gp.person_id JOIN grade g 
                   ON g.id = gp.grade_id JOIN person_type_person ptp 
                   ON ptp.person_id = p.id JOIN person_type pt 
                   ON pt.id = ptp.person_type_id WHERE pt.status_name = 'Student' 
                   AND p.active = true ORDER BY g.id ASC""", nativeQuery = true)
    public List<Person> findAllByGrade();

    public List<Person> findAllByActive(boolean active);
    
    //--------------------- TEACHERS ----------------------- //
    
    
} 