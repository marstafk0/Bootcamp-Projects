package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Sponsor;

import java.util.List;

public interface SponsorService {

    public Sponsor getSponsorById(long id);
    public List<Sponsor> getAllSponsors(boolean active);
    public void saveSponsor(Sponsor sponsor);

}
