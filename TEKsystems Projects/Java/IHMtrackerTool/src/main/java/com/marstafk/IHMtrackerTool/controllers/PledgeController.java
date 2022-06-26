package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.*;
import com.marstafk.IHMtrackerTool.service.*;
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
import java.math.BigDecimal;
import java.util.*;

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
    RunService runService;

    @Autowired
    JogathonMasterService jogathonMasterService;

    private Set<ConstraintViolation<Pledge>> violations = new HashSet<>();
    private Set<String> violations2 = new HashSet<>();

    @GetMapping("pledges")
    public String displayPledges(Model model) {
        model.addAttribute("pledges", associatePledgeToDisplay(pledgeService.getAllPledges(true, false)));
        model.addAttribute("persons", personService.getAllPeople(true));
        model.addAttribute("pledgeTypes", pledgeTypeService.getAllPledgeTypes(true));
        model.addAttribute("sponsors", sponsorService.getAllSponsors());
        model.addAttribute("errors", violations);
        model.addAttribute("errors2", violations2);
        return "pledges";
    }

    @GetMapping("minmax")
    public String minMax(Model model) {
        List<Pledge> pledges = pledgeService.getAllPledges(true, false);
        pledges.sort(Comparator.comparing(Pledge::getTotal).reversed());
        model.addAttribute("pledges", associatePledgeToDisplay(pledges));
        model.addAttribute("persons", personService.getAllPeople(true));
        model.addAttribute("pledgeTypes", pledgeTypeService.getAllPledgeTypes(true));
        model.addAttribute("sponsors", sponsorService.getAllSponsors());
        model.addAttribute("errors", violations);
        model.addAttribute("errors2", violations2);
        return "pledges";
    }

    @GetMapping("weekPledges")
    public String weekPledges(Model model) {
        List<Pledge> pledges = pledgeService.getAllPledges(true, false);
        pledges.sort(Comparator.comparing(Pledge::getWeek));
        model.addAttribute("pledges", associatePledgeToDisplay(pledges));
        model.addAttribute("persons", personService.getAllPeople(true));
        model.addAttribute("pledgeTypes", pledgeTypeService.getAllPledgeTypes(true));
        model.addAttribute("sponsors", sponsorService.getAllSponsors());
        model.addAttribute("errors", violations);
        model.addAttribute("errors2", violations2);
        return "pledges";
    }

    @GetMapping("getPledge")
    @ResponseBody
    public List<Object> getPledge(@RequestParam("id") long id) {
        List<Object> objects = new ArrayList<>();
        try {
            objects.add(pledgeService.getPledgeById(id));
            objects.add(personService.getPersonByPledgeId(id));
            objects.add(pledgeTypeService.getPledgeTypeByPledgeId(id));
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
        }

        try {
            objects.add(sponsorService.getSponsorByPledgeId(id));
        } catch (NullPointerException e) {

        }
        return objects;
    }

    @PostMapping("addPledge")
    public String addPledge(HttpServletRequest request) {
        violations.clear();
        violations2.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        try {
            Pledge pledge = new Pledge();
            try {
                pledge.setOneTime(new BigDecimal(request.getParameter("oneTime")).setScale(2, BigDecimal.ROUND_HALF_EVEN));
                pledge.setPerLap(new BigDecimal(request.getParameter("perLap")).setScale(2, BigDecimal.ROUND_HALF_EVEN));
                pledge.setWeek(Integer.parseInt(request.getParameter("week")));
            } catch (NumberFormatException e) {
                violations2.add("Please enter a valid number.");
            }
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

            Run run = runService.getByPersonId(person.getId());
            if (run == null || run.getLaps() == 0) {
                violations2.add("Please add laps before marking as collected.");
            } else {
                if (pledge.isCollected()) {
                    pledge.setTotal(pledge.getOneTime().add((pledge.getPerLap().multiply(new BigDecimal(run.getLaps())))));
                } else {
                    pledge.setTotal(pledge.getOneTime());
                }
            }
            violations = validator.validate(pledge);
            JogathonMaster currJ = jogathonMasterService.getActiveAndDeletion(true, false);
            if (currJ == null) {
                violations2.add("Please start a Jogathon before adding pledges.");
            }
            if (violations.isEmpty() && violations2.isEmpty()) {
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
                } catch (NoSuchElementException | ObjectNotFoundException e) {

                }
                JogathonMaster jogathonMaster = jogathonMasterService.getActiveAndDeletion(true, false);
                if (jogathonMaster.getPledges() == null) {
                    jogathonMaster.setPledges(new ArrayList<>());
                }
                jogathonMaster.addPledges(pledge);
                jogathonMasterService.saveJogathon(jogathonMaster);
            }
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
        }
        return "redirect:/pledges";
    }

    @PostMapping("editPledge")
    public String editPledge(Pledge pledge, HttpServletRequest request) {
        violations.clear();
        violations2.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        try {
            try {
                BigDecimal oneTime = new BigDecimal(request.getParameter("oneTime"));
                BigDecimal perLap = new BigDecimal(request.getParameter("perLap"));
                int week = Integer.parseInt(request.getParameter("week"));
            } catch (NumberFormatException e) {
                violations2.add("Please enter a valid number.");
            }

            violations = validator.validate(pledge);
            if (violations.isEmpty() && violations2.isEmpty()) {
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
                } catch (NoSuchElementException | ObjectNotFoundException e) {

                }

                if (pledge.isCollected() && runService.getByPersonId(person.getId()) != null) {
                    pledge.setTotal(pledge.getOneTime().add((pledge.getPerLap().multiply(new BigDecimal(runService.getByPersonId(person.getId()).getLaps())))));
                } else {
                    pledge.setTotal(pledge.getOneTime());
                }

                JogathonMaster jogathonMaster = jogathonMasterService.getActiveAndDeletion(true, false);
                jogathonMaster.getPledges().removeIf(pledge1 -> pledge1.getId() == pledge.getId());
                jogathonMaster.addPledges(pledge);
                jogathonMasterService.saveJogathon(jogathonMaster);

                pledgeService.savePledge(pledge);
            }
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
        }
        return "redirect:/pledges";
    }

    @PostMapping("deactivatePledge")
    public String deactivatePledge(@RequestParam("id") long id) {
        violations2.clear();
        try {
            Pledge pledge = pledgeService.getPledgeById(id);
            pledge.setDeletion(true);
            pledgeService.savePledge(pledge);
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
        }
        return "redirect:/pledges";
    }

    private List<DisplayPledge> associatePledgeToDisplay(List<Pledge> pledges) {
        List<DisplayPledge> displayPledges = new ArrayList<>();
        for (Pledge p : pledges) {
            String name;
            String nameTwo = "None";
            try {
                Person person = personService.getPersonByPledgeId(p.getId());
                name = person.getFirstName() + " " + person.getLastName();
            } catch (ObjectNotFoundException e) {
                name = "None";
            }
            Sponsor sponsor = sponsorService.getSponsorByPledgeId(p.getId());
            if (sponsor != null) {
                nameTwo = sponsor.getFirstName() + " " + sponsor.getLastName();
            }
            displayPledges.add(new DisplayPledge(p.getId(), name, nameTwo, p.getTotal(), p.getOneTime(), p.getPerLap(), p.getWeek(), p.isCollected(), p.isReceipt()));
        }
        return displayPledges;
    }

}
