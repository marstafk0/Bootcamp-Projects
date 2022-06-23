package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.PledgeType;
import com.marstafk.IHMtrackerTool.repositories.PledgeTypeRepository;
import com.marstafk.IHMtrackerTool.service.PledgeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PledgeTypeServiceImpl implements PledgeTypeService {

    @Autowired
    PledgeTypeRepository pledgeTypeRepository;

    @Override
    public List<PledgeType> getAllPledgeTypes(boolean active) {
        return pledgeTypeRepository.findByActive(active);
    }

    @Override
    public PledgeType getPledgeTypeById(long id) throws ObjectNotFoundException {
        try {
            return pledgeTypeRepository.findById(id).get();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Could not retrieve Pledge Type");
        }
    }

    @Override
    public PledgeType getPledgeTypeByPledgeId(long id) {
        return pledgeTypeRepository.findByPledgeId(id);
    }

    @Override
    public void savePledgeType(PledgeType pledgeType) {
        pledgeTypeRepository.save(pledgeType);
    }
}
