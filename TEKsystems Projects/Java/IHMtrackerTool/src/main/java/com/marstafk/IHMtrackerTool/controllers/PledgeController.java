package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.models.Pledge;
import com.marstafk.IHMtrackerTool.models.PledgeType;
import com.marstafk.IHMtrackerTool.models.Sponsor;
import com.marstafk.IHMtrackerTool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class PledgeController {

    @Autowired
    PledgeService pledgeService;

    @Autowired
    PersonService personService;

    @Autowired
    PledgeTypeService pledgeTypeService;

    @Autowired
    SponsorService sponsorService;

    @Autowired
    PersonLapsService personLapsService;

    @GetMapping("pledges")
    public String displayPledges(Model model) {
        model.addAttribute("pledges", pledgeService.getAllPledges(true));
        model.addAttribute("persons", personService.getAllPeople(true));
        model.addAttribute("pledgeTypes", pledgeTypeService.getAllPledgeTypes(true));
        model.addAttribute("sponsors", sponsorService.getAllSponsors());
        return "pledges";
    }

    @GetMapping("getPledge")
    @ResponseBody
    public List<Object> getPledge(@RequestParam("id") long id) {
        List<Object> objects = new ArrayList<>();
        objects.add(pledgeService.getPledgeById(id));
        objects.add(personService.getPersonByPledgeId(id));
        objects.add(pledgeTypeService.getPledgeTypeByPledgeId(id));
        try {
            objects.add(sponsorService.getSponsorByPledgeId(id));
        } catch (NullPointerException e) {

        }
        return objects;
    }

    @PostMapping("addPledge")
    public String addPledge(HttpServletRequest request) {
        Pledge pledge = new Pledge();
        pledge.setOneTime(new BigDecimal(request.getParameter("oneTime")).setScale(2, BigDecimal.ROUND_HALF_EVEN));
        pledge.setPerLap(new BigDecimal(request.getParameter("perLap")).setScale(2, BigDecimal.ROUND_HALF_EVEN));
        pledge.setWeek(Integer.parseInt(request.getParameter("week")));
        switch (request.getParameter("active")) {
            case "false" -> pledge.setActive(false);
            default -> pledge.setActive(true);
        }
        switch (request.getParameter("collected")) {
            case "false" -> pledge.setCollected(false);
            default -> pledge.setCollected(true);
        }
        switch (request.getParameter("receipt")) {
            case "false" -> pledge.setReceipt(false);
            default -> pledge.setReceipt(true);
        }

        Person person = personService.getPersonById(Long.parseLong(request.getParameter("personId")));

        if (pledge.isCollected() && personLapsService.getByPersonId(person.getId()) != null) {
            pledge.setTotal(pledge.getOneTime().add((pledge.getPerLap().multiply(new BigDecimal(personLapsService.getByPersonId(person.getId()).getLaps())))));
        } else {
            pledge.setTotal(pledge.getOneTime());
        }

        pledgeService.savePledge(pledge);

        person.addPledges(pledge);
        personService.savePerson(person);

        PledgeType pledgeType = pledgeTypeService.getPledgeTypeById(Long.parseLong(request.getParameter("pledgeTypeId")));
        pledgeType.addPledge(pledge);
        pledgeTypeService.savePledgeType(pledgeType);

        long sponId = Long.parseLong(request.getParameter("sponsorId"));
        try {
            Sponsor sponsor = sponsorService.getSponsorById(sponId);
            sponsor.addPledges(pledge);
            sponsorService.saveSponsor(sponsor);
        } catch (NoSuchElementException e) {

        }
        return "redirect:/pledges";
    }

    @PostMapping("editPledge")
    public String editPledge(Pledge pledge, HttpServletRequest request) {
        //remove old fields
        Pledge oldPledge = pledgeService.getPledgeById(pledge.getId());
        Person oldPerson = personService.getPersonByPledgeId(pledge.getId());
        PledgeType oldPledgeType = pledgeTypeService.getPledgeTypeByPledgeId(pledge.getId());

        try {
            Sponsor oldSponsor = sponsorService.getSponsorByPledgeId(pledge.getId());
            List<Pledge> sponsorPledge = oldSponsor.getPledges();
            sponsorPledge.remove(oldPledge);
            oldSponsor.setPledges(sponsorPledge);
            sponsorService.saveSponsor(oldSponsor);
        } catch (NullPointerException e) {

        }

        List<Pledge> personPledges = oldPerson.getPledges();
        personPledges.remove(oldPledge);
        oldPerson.setPledges(personPledges);
        personService.savePerson(oldPerson);

        List<Pledge> pledgeTypePledge = oldPledgeType.getPledges();
        pledgeTypePledge.remove(oldPledge);
        oldPledgeType.setPledges(pledgeTypePledge);
        pledgeTypeService.savePledgeType(oldPledgeType);

        // save new fields
        Person person = personService.getPersonById(Long.parseLong(request.getParameter("personId")));
        person.addPledges(pledge);
        personService.savePerson(person);

        PledgeType pledgeType = pledgeTypeService.getPledgeTypeById(Long.parseLong(request.getParameter("pledgeTypeId")));
        pledgeType.addPledge(pledge);
        pledgeTypeService.savePledgeType(pledgeType);

        long sponId = Long.parseLong(request.getParameter("sponsorId"));
        try {
            Sponsor sponsor = sponsorService.getSponsorById(sponId);
            sponsor.addPledges(pledge);
            sponsorService.saveSponsor(sponsor);
        } catch (NoSuchElementException e) {

        }

        if (pledge.isCollected() && personLapsService.getByPersonId(person.getId()) != null) {
            pledge.setTotal(pledge.getOneTime().add((pledge.getPerLap().multiply(new BigDecimal(personLapsService.getByPersonId(person.getId()).getLaps())))));
        } else {
            pledge.setTotal(pledge.getOneTime());
        }

        pledgeService.savePledge(pledge);
        return "redirect:/pledges";
    }

    @PostMapping("deactivatePledge")
    public String deactivatePledge(@RequestParam("id") long id) {
        Pledge pledge = pledgeService.getPledgeById(id);
        pledge.setActive(false);
        pledgeService.savePledge(pledge);
        return "redirect:/pledges";
    }

}
