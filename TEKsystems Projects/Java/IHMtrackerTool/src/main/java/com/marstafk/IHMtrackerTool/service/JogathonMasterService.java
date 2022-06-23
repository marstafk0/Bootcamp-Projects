/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.JogathonMaster;

/**
 * @author boss_
 */
public interface JogathonMasterService {
    JogathonMaster getJogathonById(long id) throws ObjectNotFoundException;

    void saveJogathon(JogathonMaster jogathonMaster);

    JogathonMaster getActiveAndDeletion(boolean active, boolean deletion);

}
