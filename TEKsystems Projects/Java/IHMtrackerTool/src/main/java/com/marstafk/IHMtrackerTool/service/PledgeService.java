package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Pledge;

import java.util.List;

public interface PledgeService {

    Pledge getPledgeById(long id);
    List<Pledge> getAllPledges(boolean active);
    List<Pledge> getAllPledgesByType(String type);
    List<Pledge> getAllPledgesByStudentId(long id);
    void savePledge(Pledge pledge);

}
