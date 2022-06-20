package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Run;

import java.util.List;

public interface RunService {

    Run getByPersonId(long id);

    List<Run> getAllByActive(boolean active);

    void saveRun(Run run);

    Run getById(long id);

}
