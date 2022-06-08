/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author boss_
 */
public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

    Classroom findByClassName(String className);

    @Query(value = """
                   SELECT c.* FROM classroom c JOIN classroom_grade cg ON c.id = cg.classroom_id 
                   JOIN grade g ON g.id = cg.grade_id WHERE g.id = :id""", nativeQuery = true)
    public Classroom findByGradeId(Long id); 

}
