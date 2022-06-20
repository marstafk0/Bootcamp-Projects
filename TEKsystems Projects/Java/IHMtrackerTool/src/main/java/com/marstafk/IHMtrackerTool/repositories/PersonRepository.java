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

    @Query(value = "SELECT p.* FROM person p JOIN person_type_person ptp ON p.id = ptp.person_id "
            + "JOIN person_type pt ON pt.id = ptp.person_type_id WHERE pt.status_name = :type "
            + "AND p.active = true ORDER BY p.lname ASC", nativeQuery = true)
    public List<Person> findAllByOrderByLastNameAsc(String type);
    
    @Query(value = "SELECT p.* FROM person p JOIN person_type_person ptp ON p.id = ptp.person_id "
            + "JOIN person_type pt ON pt.id = ptp.person_type_id WHERE pt.status_name = :type "
            + "AND p.active = true ORDER BY p.fname ASC", nativeQuery = true)
    public List<Person> findAllByOrderByFirstNameAsc(String type);
    
    @Query(value = "SELECT p.* FROM person p JOIN person_type_person ptp ON p.id = ptp.person_id "
            + "JOIN person_type pt ON pt.id = ptp.person_type_id WHERE pt.status_name = :type "
            + "AND p.active = true", nativeQuery = true)
    public List<Person> findAllByActive(String type);
    
    @Query(value = "SELECT * FROM person p JOIN person_type_person ptp ON p.id = ptp.person_id "
            + "JOIN person_type pt ON pt.id = ptp.person_type_id WHERE pt.status_name = :type "
            + "AND p.active = false", nativeQuery = true)
    public List<Person> findAllByInactive(String type);
    
    @Query(value = """
                   SELECT p.* FROM person p JOIN grade_person gp 
                   ON p.id = gp.person_id JOIN grade g 
                   ON g.id = gp.grade_id JOIN person_type_person ptp 
                   ON ptp.person_id = p.id JOIN person_type pt 
                   ON pt.id = ptp.person_type_id WHERE pt.status_name = 'Student' 
                   AND p.active = true ORDER BY g.id ASC""", nativeQuery = true)
    public List<Person> findAllGradesByType();

    public List<Person> findAllByActive(boolean active);

    @Query(value = "SELECT p.* FROM person p JOIN grade_person gp \n" +
            "                   ON p.id = gp.person_id JOIN grade g \n" +
            "                   ON g.id = gp.grade_id JOIN person_type_person ptp \n" +
            "                   ON ptp.person_id = p.id JOIN person_type pt \n" +
            "                   ON pt.id = ptp.person_type_id WHERE pt.status_name = \"Student\"\n" +
            "                   AND p.active = true AND g.id = :id", nativeQuery = true)
    public List<Person> findAllStudentsByGradeId(long id);

    @Query(value = "SELECT p.* FROM person p JOIN grade_person gp ON p.id = gp.person_id JOIN grade g ON g.id = gp.grade_id" +
            " JOIN classroom_grade cg ON g.id = cg.grade_id JOIN classroom c ON c.id = cg.classroom_id WHERE c.id = :id " +
            "AND p.active = true", nativeQuery = true)
    public List<Person> findAllStudentsByClassroomId(long id);

    @Query(value = "SELECT p.* FROM person p JOIN person_pledge pp ON pp.person_id = p.id JOIN pledge pl ON pl.id = pp.pledges_id " +
            "WHERE pl.id = :id AND p.active = true", nativeQuery = true)
    public Person findByPledgeId(long id);

    @Query(value = "SELECT p.* FROM person p JOIN person_run pr ON p.id = pr.person_id " +
            "JOIN run r ON r.id = pr.run_id WHERE r.id = :id AND p.active = true", nativeQuery = true)
    public Person findByRunId(long id);
    
    //--------------------- TEACHERS ----------------------- //
    
    
} 