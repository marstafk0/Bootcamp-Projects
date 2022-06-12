package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.models.PersonType;
import com.marstafk.IHMtrackerTool.models.PledgeType;
import com.marstafk.IHMtrackerTool.service.PersonService;
import com.marstafk.IHMtrackerTool.service.PersonTypeService;
import com.marstafk.IHMtrackerTool.service.PledgeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PersonPledgeController {

    @Autowired
    PersonTypeService personTypeService;

    @Autowired
    PersonService personService;

    @Autowired
    PledgeTypeService pledgeTypeService;

    // --------------------------- PERSON TYPES ------------------------------- //

    @GetMapping("types")
    public String displayPersonTypes(Model model) {
        model.addAttribute("personTypes", personTypeService.getAllPersonTypes());
        model.addAttribute("pledgeTypes", pledgeTypeService.getAllPledgeTypes(true));
        return "types";
    }

    @GetMapping("getPersonType")
    @ResponseBody
    public PersonType getPersonType(@RequestParam("id") long id) {
        PersonType personType = personTypeService.getPersonTypeById(id);
        return personType;
    }

    @GetMapping("associatedPeople")
    @ResponseBody
    public List<Person> getAssociatedPeople(@RequestParam("id") long id) {
        return personService.getAllPeopleByType(personTypeService.getPersonTypeById(id).getStatusName(), true);
    }

    @PostMapping("addPersonType")
    public String addPersonType(HttpServletRequest request) {
        PersonType personType = new PersonType();
        personType.setStatusName(request.getParameter("statusName"));
        switch (request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                personType.setActive(false);
            }
            default -> {
                personType.setActive(true);
            }
        }
        personTypeService.savePersonType(personType);
        return "redirect:/types";
    }

    @PostMapping("editPersonType")
    public String editPersonType(PersonType personType) {
        PersonType oldPerson = personTypeService.getPersonTypeById(personType.getId());
        try{
            personType.setPersons(oldPerson.getPersons());
        } catch (NullPointerException e) {

        }
        personTypeService.savePersonType(personType);
        return "redirect:/types";
    }

    @PostMapping("confirmDeactivatePersonType")
    public String confirmDeactivatePersonType(HttpServletRequest request) {
        PersonType personType = personTypeService.getPersonTypeById(Long.parseLong(request.getParameter("id")));
        personType.setActive(false);
        personTypeService.savePersonType(personType);
        return "redirect:/types";
    }

    // -------------------------- PLEDGE TYPES ---------------------------------- //

    @GetMapping("getPledgeType")
    @ResponseBody
    public PledgeType getPledgeType(@RequestParam("id") long id) {
        PledgeType pledgeType = pledgeTypeService.getPledgeTypeById(id);
        return pledgeType;
    }

    @PostMapping("addPledgeType")
    public String addPledgeType(HttpServletRequest request) {
        PledgeType pledgeType = new PledgeType();
        pledgeType.setPledgeName(request.getParameter("pledgeName"));
        switch (request.getParameter("active")) {
            case "inactive", "false", "no" -> {
                pledgeType.setActive(false);
            }
            default -> {
                pledgeType.setActive(true);
            }
        }
        pledgeTypeService.savePledgeType(pledgeType);
        return "redirect:/types";
    }

    @PostMapping("editPledgeType")
    public String editPledgeType(PledgeType pledgeType) {
        PledgeType oldPledge = pledgeTypeService.getPledgeTypeById(pledgeType.getId());
        try{
            pledgeType.setPledges(oldPledge.getPledges());
        } catch (NullPointerException e) {

        }
        pledgeTypeService.savePledgeType(pledgeType);
        return "redirect:/types";
    }

    @PostMapping("confirmDeactivatePledgeType")
    public String confirmDeactivatePledgeType(HttpServletRequest request) {
        PledgeType pledgeType = pledgeTypeService.getPledgeTypeById(Long.parseLong(request.getParameter("id")));
        pledgeType.setActive(false);
        pledgeTypeService.savePledgeType(pledgeType);
        return "redirect:/types";
    }

}
