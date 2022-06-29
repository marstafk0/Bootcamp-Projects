package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Pledge;

import java.util.List;

public interface PledgeService {

    Pledge getPledgeById(long id) throws ObjectNotFoundException;

    List<Pledge> getAllPledges(boolean active, boolean deletion);

    List<Pledge> getAllPledgesByStudentId(long id);

    void savePledge(Pledge pledge);

    List<Pledge> getAllByCurrentJog();

    List<Pledge> getAllbyJogIdWeekInactive(long id, int week);

    List<Pledge> getAllByWeekCurrent(int week);

    List<Pledge> getAllByGradeId(long id);

    List<Pledge> getAllByClassroom(long id);

    List<Pledge> getAllByJogathonId(long id);

}
