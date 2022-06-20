package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Pledge;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PledgeService {

    Pledge getPledgeById(long id);
    List<Pledge> getAllPledges(boolean active);
    List<Pledge> getAllPledgesByType(String type);
    List<Pledge> getAllPledgesByStudentId(long id);
    void savePledge(Pledge pledge);
    List<Pledge> getAllByCurrentJog();
    List<Pledge> getAllbyJogIdWeekInactive(long id, int week);
    List<Pledge> getAllByWeekCurrent(int week);
    List<Pledge> getAllByGradeId(long id);
    List<Pledge> getAllByClassroom(long id);

}
