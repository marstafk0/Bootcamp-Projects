/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Pledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author boss_
 */
public interface PledgeRepository extends JpaRepository<Pledge, Long> {

    List<Pledge> findAllByActiveAndDeletion(boolean active, boolean deletion);

    @Query(value = "SELECT pl.* FROM pledge pl JOIN person_pledge pp ON pl.id = pp.pledges_id JOIN person p ON p.id = pp.person_id " +
            "WHERE p.id = :id AND pl.active = true AND p.active = true AND pl.deletion = false", nativeQuery = true)
    List<Pledge> findAllByPersonId(long id);

    @Query(value = "SELECT p.* FROM pledge p JOIN jogathon_master_pledge jmp ON jmp.pledge_id = p.id JOIN jogathon_master jm " +
            "ON jm.id = jmp.jogathon_master_id WHERE jm.active = true AND p.active = true AND jm.deletion = false AND p.deletion = false", nativeQuery = true)
    List<Pledge> findAllbyCurrentJog();

    @Query(value = "SELECT p.* FROM pledge p JOIN jogathon_master_pledge jmp ON jmp.pledge_id = p.id JOIN jogathon_master jm " +
            "ON jm.id = jmp.jogathon_master_id WHERE jm.id = :id AND p.week = :week AND jm.active = false AND p.active = false AND p.deletion = false", nativeQuery = true)
    List<Pledge> findAllbyJogIdWeekInactive(long id, int week);

    @Query(value = "SELECT p.* FROM pledge p JOIN jogathon_master_pledge jmp ON jmp.pledge_id = p.id JOIN jogathon_master jm " +
            "ON jm.id = jmp.jogathon_master_id WHERE p.week = :week AND p.active = true AND jm.active = true AND p.deletion = false", nativeQuery = true)
    List<Pledge> findAllByWeekCurrent(int week);

    @Query(value = "SELECT pl.* FROM pledge pl JOIN person_pledge pp ON pl.id = pp.pledges_id JOIN person p ON p.id = pp.person_id JOIN grade_person gp ON gp.person_id = p.id " +
            "JOIN grade g ON g.id = gp.grade_id WHERE  pl.active = true AND p.active = true AND g.active = true AND g.id = :id AND pl.deletion = false", nativeQuery = true)
    List<Pledge> findAllByGradeId(long id);

    @Query(value = "SELECT pl.* FROM pledge pl JOIN person_pledge pp ON pl.id = pp.pledges_id JOIN person p ON p.id = pp.person_id JOIN grade_person gp ON gp.person_id = p.id " +
            "JOIN grade g ON g.id = gp.grade_id JOIN classroom_grade cg ON cg.grade_id = g.id JOIN classroom c ON c.id = cg.classroom_id " +
            "WHERE pl.active = true AND p.active = true AND g.active = true AND c.active = true AND c.id = :id AND pl.deletion = false", nativeQuery = true)
    List<Pledge> findAllByClassroomId(long id);

}