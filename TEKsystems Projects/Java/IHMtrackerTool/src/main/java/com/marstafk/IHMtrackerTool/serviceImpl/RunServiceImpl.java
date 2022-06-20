package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.Run;
import com.marstafk.IHMtrackerTool.repositories.RunRepository;
import com.marstafk.IHMtrackerTool.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunServiceImpl implements RunService {

    @Autowired
    RunRepository runRepository;

    @Override
    public Run getByPersonId(long id) {
        return runRepository.findByPersonId(id);
    }

    @Override
    public Run getById(long id) { return runRepository.findById(id).get(); }

    @Override
    public List<Run> getAllByActive(boolean active) { return runRepository.findAllByActive(active); }

    @Override
    public void saveRun(Run run) { runRepository.save(run); }
}
