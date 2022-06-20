/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author boss_
 */
public interface PersonTypeRepository extends JpaRepository<PersonType, Long>{

    public PersonType findByStatusName(String statusName);

    @Query(value = "SELECT p.* FROM person_type p ORDER BY p.status_name ASC", nativeQuery = true)
    public List<PersonType> findAllOrderByAsc();

    @Query(value = "SELECT pt.* FROM person_type pt JOIN person_type_person ptp ON ptp.person_type_id = pt.id JOIN " +
            "person p ON p.id = ptp.person_id WHERE p.id = :id AND p.active = true AND pt.active = true", nativeQuery = true)
    public List<PersonType> findAllByPersonId(long id);
    
}