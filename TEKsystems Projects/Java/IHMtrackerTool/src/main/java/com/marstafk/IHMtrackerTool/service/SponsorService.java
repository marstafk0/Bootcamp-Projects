package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Sponsor;

import java.util.List;

public interface SponsorService {

    public Sponsor getSponsorById(long id) throws ObjectNotFoundException;

    public List<Sponsor> getAllSponsors();

    public void saveSponsor(Sponsor sponsor);

    public Sponsor getSponsorByPledgeId(long id);

}
