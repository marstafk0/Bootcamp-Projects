/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author boss_
 */
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    @Query(value = "SELECT s.* FROM sponsor s JOIN sponsor_pledge sp ON s.id = sp.sponsor_id JOIN pledge p ON p.id = sp.pledge_id " +
            "WHERE p.id = :id", nativeQuery = true)
    Sponsor findByPledgeId(long id);

}
