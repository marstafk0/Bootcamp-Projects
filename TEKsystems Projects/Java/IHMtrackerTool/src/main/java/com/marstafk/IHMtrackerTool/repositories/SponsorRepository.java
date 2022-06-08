/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author boss_
 */
public interface SponsorRepository extends JpaRepository<Sponsor, Long>{

    public List<Sponsor> findAllByActive(boolean active);

}
