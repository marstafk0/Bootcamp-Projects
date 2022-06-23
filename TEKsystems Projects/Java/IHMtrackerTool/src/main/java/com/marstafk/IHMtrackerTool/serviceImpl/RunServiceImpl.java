package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Run;
import com.marstafk.IHMtrackerTool.repositories.RunRepository;
import com.marstafk.IHMtrackerTool.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunServiceImpl implements RunService {

    @Autowired
    RunRepository runRepository;

    @Override
    public Run getByPersonId(long id) throws ObjectNotFoundException {
        try {
            return runRepository.findByPersonId(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Could not retrieve Run by Person ID");
        }
    }

    @Override
    public void saveRun(Run run) {
        runRepository.save(run);
    }
}
