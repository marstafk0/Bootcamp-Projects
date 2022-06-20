/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Family;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface FamilyService {
    
    public Family getFamilyById(Long id);
    public List<Family> getAllFamilies(boolean active);
    public Family getFamilyByPersonId(Long id);
    public void saveFamily(Family family);
    
}
