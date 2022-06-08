/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Pledge;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author boss_
 */
public interface PledgeRepository extends JpaRepository<Pledge, Long>{}