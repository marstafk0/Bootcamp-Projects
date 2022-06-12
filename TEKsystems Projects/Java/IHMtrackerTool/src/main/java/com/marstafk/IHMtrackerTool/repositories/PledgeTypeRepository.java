/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.PledgeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author boss_
 */
public interface PledgeTypeRepository extends JpaRepository<PledgeType, Long>{

    public List<PledgeType> findByActive(boolean active);

    @Query(value = "SELECT pl.* FROM pledge_type pl JOIN pledge_type_pledge ptp ON ptp.pledge_type_id = pl.id JOIN " +
            "pledge p ON p.id = ptp.pledge_id WHERE p.id = :id", nativeQuery = true)
    PledgeType findByPledgeId(long id);

}
