/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.Family;
import com.marstafk.IHMtrackerTool.repositories.FamilyRepository;
import com.marstafk.IHMtrackerTool.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author boss_
 */
@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    FamilyRepository familyRepository;

    @Override
    public Family getFamilyById(Long id) {
        try {
            return familyRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            Family family = new Family();
            family.setId(0);
            return family;
        }
    }

    @Override
    public List<Family> getAllFamilies(boolean active) {
        return familyRepository.findAllByActive(active);
    }

    @Override
    public void saveFamily(Family family) {
        familyRepository.save(family);
    }

    @Override
    public Family getFamilyByPersonId(Long id) {
        try {
            return familyRepository.findByPersonId(id);
        } catch (NoSuchElementException e) {
            Family family = new Family();
            family.setId(0);
            return family;
        }
    }

}
