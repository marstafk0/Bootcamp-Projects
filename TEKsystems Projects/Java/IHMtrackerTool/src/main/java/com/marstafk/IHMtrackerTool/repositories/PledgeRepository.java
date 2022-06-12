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
 *
 * @author boss_
 */
public interface PledgeRepository extends JpaRepository<Pledge, Long>{

    List<Pledge> findAllByActive(boolean active);

    @Query(value = "SELECT p.* FROM pledge p JOIN pledge_type_pledge ptp ON p.id = ptp.pledge_id JOIN pledge_type pt ON" +
            " pt.id = ptp.pledge_type_id WHERE pt.pledge_name = :type AND p.active = true", nativeQuery = true)
    List<Pledge> findAllByType(String type);

    @Query(value = "SELECT pl.* FROM pledge pl JOIN person_pledge pp ON pl.id = pp.pledge_id JOIN person p ON p.id = pp.person_id " +
            "WHERE pl.id = :id AND pl.active = true", nativeQuery = true)
    List<Pledge> findAllByPersonId(long id);

}