package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Pledge;
import com.marstafk.IHMtrackerTool.repositories.PledgeRepository;
import com.marstafk.IHMtrackerTool.service.PledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PledgeServiceImpl implements PledgeService {

    @Autowired
    PledgeRepository pledgeRepository;


    @Override
    public Pledge getPledgeById(long id) throws ObjectNotFoundException {
        try {
            return pledgeRepository.findById(id).get();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Could not retrieve Pledge");
        }
    }

    @Override
    public List<Pledge> getAllPledges(boolean active, boolean deletion) {
        return pledgeRepository.findAllByActiveAndDeletion(active, deletion);
    }

    @Override
    public List<Pledge> getAllPledgesByStudentId(long id) {
        return pledgeRepository.findAllByPersonId(id);
    }

    @Override
    public void savePledge(Pledge pledge) {
        pledgeRepository.save(pledge);
    }

    @Override
    public List<Pledge> getAllByCurrentJog() {
        return pledgeRepository.findAllbyCurrentJog();
    }

    @Override
    public List<Pledge> getAllbyJogIdWeekInactive(long id, int week) {
        return pledgeRepository.findAllbyJogIdWeekInactive(id, week);
    }

    @Override
    public List<Pledge> getAllByWeekCurrent(int week) {
        return pledgeRepository.findAllByWeekCurrent(week);
    }

    @Override
    public List<Pledge> getAllByGradeId(long id) {
        return pledgeRepository.findAllByGradeId(id);
    }

    @Override
    public List<Pledge> getAllByClassroom(long id) {
        return pledgeRepository.findAllByClassroomId(id);
    }

    @Override
    public List<Pledge> getAllByJogathonId(long id) {
        return pledgeRepository.findAllByJogathonId(id);
    }
}
