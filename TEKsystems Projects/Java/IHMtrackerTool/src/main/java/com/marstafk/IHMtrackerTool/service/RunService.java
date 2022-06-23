package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Run;

public interface RunService {

    Run getByPersonId(long id) throws ObjectNotFoundException;

    void saveRun(Run run);

}
