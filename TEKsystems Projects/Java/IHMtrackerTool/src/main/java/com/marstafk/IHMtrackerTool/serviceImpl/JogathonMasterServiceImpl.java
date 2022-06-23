/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.JogathonMaster;
import com.marstafk.IHMtrackerTool.repositories.JogathonMasterRepository;
import com.marstafk.IHMtrackerTool.service.JogathonMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author boss_
 */
@Service
public class JogathonMasterServiceImpl implements JogathonMasterService {

    @Autowired
    JogathonMasterRepository jMasterRepo;

    @Override
    public JogathonMaster getJogathonById(long id) throws ObjectNotFoundException {
        try {
            return jMasterRepo.findById(id).get();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Could not retrieve Jogathon");
        }
    }

    @Override
    public void saveJogathon(JogathonMaster jogathonMaster) {
        jMasterRepo.save(jogathonMaster);
    }

    @Override
    public JogathonMaster getActiveAndDeletion(boolean active, boolean deletion) {
        return jMasterRepo.findByActiveAndDeletion(active, deletion);
    }

}
