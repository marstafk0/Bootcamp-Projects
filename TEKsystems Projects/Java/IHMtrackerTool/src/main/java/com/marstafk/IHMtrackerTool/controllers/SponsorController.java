package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.models.Sponsor;
import com.marstafk.IHMtrackerTool.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SponsorController {

    @Autowired
    SponsorService sponsorService;

    @GetMapping("sponsors")
    public String displaySponsors(Model model) {
        model.addAttribute("sponsors", sponsorService.getAllSponsors(true));
        return "sponsors";
    }

    @GetMapping("inactiveSponsors")
    public String displayInactiveSponsors(Model model) {
        model.addAttribute("sponsors", sponsorService.getAllSponsors(false));
        return "sponsors";
    }

    @GetMapping("getSponsor")
    @ResponseBody
    public Sponsor getSponsor(@RequestParam("id") long id) {
        return sponsorService.getSponsorById(id);
    }

    @PostMapping("addSponsor")
    public String addSponsor(HttpServletRequest request) {
        Sponsor sponsor = new Sponsor();

        sponsor.setFirstName(request.getParameter("firstName"));
        sponsor.setLastName(request.getParameter("lastName"));
        sponsor.setPhone(request.getParameter("phone"));
        sponsor.setAddressOne(request.getParameter("addressOne"));
        sponsor.setAddressTwo(request.getParameter("addressTwo"));
        sponsor.setCity(request.getParameter("city"));
        sponsor.setStateOf(request.getParameter("state"));
        sponsor.setZip(request.getParameter("zip"));
        switch(request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                sponsor.setActive(false);
            }
            default -> {
                sponsor.setActive(true);
            }
        }

        sponsorService.saveSponsor(sponsor);
        return "redirect:/sponsors";
    }

    @PostMapping("editSponsor")
    public String editSponsor(Sponsor sponsor) {
        sponsorService.saveSponsor(sponsor);
        return "redirect:/sponsors";
    }

    @PostMapping("confirmDeactivateSponsor")
    public String confirmDeactivateSponsor(HttpServletRequest request) {
        Sponsor sponsor = sponsorService.getSponsorById(Long.parseLong(request.getParameter("id")));
        sponsor.setActive(false);
        sponsorService.saveSponsor(sponsor);
        return "redirect:/sponsors";
    }

}
