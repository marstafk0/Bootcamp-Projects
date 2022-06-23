package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
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
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Controller
public class SponsorController {

    @Autowired
    SponsorService sponsorService;

    private Set<ConstraintViolation<Sponsor>> violations = new HashSet<>();
    private Set<String> violations2 = new HashSet<>();

    @GetMapping("sponsors")
    public String displaySponsors(Model model) {
        model.addAttribute("sponsors", sponsorService.getAllSponsors());
        model.addAttribute("errors", violations);
        model.addAttribute("errors2", violations2);
        return "sponsors";
    }

    @GetMapping("getSponsor")
    @ResponseBody
    public Sponsor getSponsor(@RequestParam("id") long id) {
        violations2.clear();
        try {
            return sponsorService.getSponsorById(id);
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
            return null;
        }
    }

    @PostMapping("addSponsor")
    public String addSponsor(HttpServletRequest request) {
        violations.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Sponsor sponsor = new Sponsor();

        sponsor.setFirstName(request.getParameter("firstName"));
        sponsor.setLastName(request.getParameter("lastName"));
        sponsor.setPhone(request.getParameter("phone"));
        sponsor.setAddressOne(request.getParameter("addressOne"));
        sponsor.setAddressTwo(request.getParameter("addressTwo"));
        sponsor.setCity(request.getParameter("city"));
        sponsor.setStateOf(request.getParameter("state"));
        sponsor.setZip(request.getParameter("zip"));

        violations = validator.validate(sponsor);
        if (violations.isEmpty()) {
            sponsorService.saveSponsor(sponsor);
        }
        return "redirect:/sponsors";
    }

    @PostMapping("editSponsor")
    public String editSponsor(Sponsor sponsor) {
        violations.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        violations = validator.validate(sponsor);
        if (violations.isEmpty()) {
            sponsorService.saveSponsor(sponsor);
        }
        return "redirect:/sponsors";
    }

}
