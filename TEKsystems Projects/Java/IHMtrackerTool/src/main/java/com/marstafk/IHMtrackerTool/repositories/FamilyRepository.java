/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author boss_
 */
public interface FamilyRepository extends JpaRepository<Family, Long> {

    @Query(value = "SELECT f.* FROM family f JOIN family_person fp ON f.id = fp.family_id JOIN "
            + "person p ON fp.person_id = p.id WHERE p.id = :id AND p.active = true AND f.active = true", nativeQuery = true)
    public Family findByPersonId(Long id);

    public List<Family> findAllByActive(boolean active);

}
