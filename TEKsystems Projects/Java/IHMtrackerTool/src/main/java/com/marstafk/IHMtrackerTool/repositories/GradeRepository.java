/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author boss_
 */
public interface GradeRepository extends JpaRepository<Grade, Long>{

    @Query(value = "SELECT g.* FROM grade g JOIN grade_person gp ON g.id = gp.grade_id"
            + " JOIN person p ON p.id = gp.person_id WHERE p.id = :id", nativeQuery = true)
    public Grade findByPersonId(Long id);

}
