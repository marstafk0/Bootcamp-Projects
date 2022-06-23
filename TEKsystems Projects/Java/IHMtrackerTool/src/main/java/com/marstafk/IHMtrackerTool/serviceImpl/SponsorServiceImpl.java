package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Sponsor;
import com.marstafk.IHMtrackerTool.repositories.SponsorRepository;
import com.marstafk.IHMtrackerTool.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService {

    @Autowired
    SponsorRepository sponsorRepository;

    @Override
    public Sponsor getSponsorById(long id) throws ObjectNotFoundException {
        try {
            return sponsorRepository.findById(id).get();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Could not retrieve Sponsor");
        }

    }

    @Override
    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    @Override
    public void saveSponsor(Sponsor sponsor) {
        sponsorRepository.save(sponsor);
    }

    @Override
    public Sponsor getSponsorByPledgeId(long id) {
        return sponsorRepository.findByPledgeId(id);
    }
}
