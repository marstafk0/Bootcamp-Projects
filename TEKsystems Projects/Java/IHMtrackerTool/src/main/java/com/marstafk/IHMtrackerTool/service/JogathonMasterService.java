/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.JogathonMaster;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface JogathonMasterService {
    public JogathonMaster getJogathonById(long id);
    public void saveJogathon(JogathonMaster jogathonMaster);

    public JogathonMaster getActiveJogathon(boolean active);
    
}
