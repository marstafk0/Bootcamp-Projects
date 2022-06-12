package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.Pledge;
import com.marstafk.IHMtrackerTool.repositories.PledgeRepository;
import com.marstafk.IHMtrackerTool.service.PledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PledgeServiceImpl implements PledgeService{

    @Autowired
    PledgeRepository pledgeRepository;


    @Override
    public Pledge getPledgeById(long id) {
        return pledgeRepository.findById(id).get();
    }

    @Override
    public List<Pledge> getAllPledges(boolean active) {
        return pledgeRepository.findAllByActive(active);
    }

    @Override
    public List<Pledge> getAllPledgesByType(String type) {
        return pledgeRepository.findAllByType(type);
    }

    @Override
    public List<Pledge> getAllPledgesByStudentId(long id) {
        return pledgeRepository.findAllByPersonId(id);
    }

    @Override
    public void savePledge(Pledge pledge) {
        pledgeRepository.save(pledge);
    }
}
