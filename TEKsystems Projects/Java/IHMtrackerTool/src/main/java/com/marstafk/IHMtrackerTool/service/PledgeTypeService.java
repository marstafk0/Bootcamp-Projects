package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.PledgeType;

import java.util.List;

public interface PledgeTypeService {

    public List<PledgeType> getAllPledgeTypes(boolean active);
    public PledgeType getPledgeTypeById(long id);
    public void savePledgeType(PledgeType pledgeType);

    public PledgeType getPledgeTypeByPledgeId(long id);

}
