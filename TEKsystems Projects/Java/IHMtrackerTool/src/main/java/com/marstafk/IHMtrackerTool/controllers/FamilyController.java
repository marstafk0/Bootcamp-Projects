package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Family;
import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.service.FamilyService;
import com.marstafk.IHMtrackerTool.service.PersonService;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class FamilyController {

    @Autowired
    FamilyService familyService;

    @Autowired
    PersonService personService;

    private Set<ConstraintViolation<Family>> violations = new HashSet<>();
    private Set<String> violationsString = new HashSet<>();

    @GetMapping("families")
    public String displayFamilies(Model model) {
        List<Person> personList = new ArrayList<>();
        for (Person p : personService.getAllPeople(true)) {
            if (familyService.getFamilyByPersonId(p.getId()) == null) {
                personList.add(p);
            }
        }
        model.addAttribute("persons", personList);
        model.addAttribute("families", familyService.getAllFamilies(true));
        model.addAttribute("errors", violations);
        model.addAttribute("errorsString", violationsString);
        return "families";
    }

    @GetMapping("inactiveFamilies")
    public String displayInactiveFamilies(Model model) {
        model.addAttribute("persons", personService.getAllPeople(true));
        model.addAttribute("families", familyService.getAllFamilies(false));
        model.addAttribute("errors", violations);
        model.addAttribute("errorsString", violationsString);
        return "families";
    }

    @GetMapping("getFamily")
    @ResponseBody
    public List<List<Object>> getFamily(@RequestParam("id") long id) {
        List<Object> families = new ArrayList<>();
        List<Object> persons = new ArrayList<>();
        List<Object> availPersons = new ArrayList<>();
        Family family = familyService.getFamilyById(id);
        families.add(family);
        for (Person p : family.getPersons()) {
            persons.add(p);
        }
        for (Person p : personService.getAllPeople(true)) {
            if (familyService.getFamilyByPersonId(p.getId()) == null) {
                availPersons.add(p);
            }
        }
        for (Object p : persons) {
            availPersons.add(p);
        }
        List<List<Object>> listOfLists = new ArrayList<>();
        listOfLists.add(families);
        listOfLists.add(persons);
        listOfLists.add(availPersons);
        return listOfLists;
    }

    @PostMapping("addFamily")
    public String addFamily(HttpServletRequest request) {
        violations.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Family family = new Family();
        family.setFamilyName(request.getParameter("familyName"));
        family.setPhone(request.getParameter("phone"));
        family.setAddressOne(request.getParameter("addressOne"));
        family.setAddressTwo(request.getParameter("addressTwo"));
        family.setCity(request.getParameter("city"));
        family.setStateOf(request.getParameter("stateOf"));
        family.setZip(request.getParameter("zip"));
        family.setActive(true);
        String[] personIds = request.getParameterValues("personId");
        List<Person> people = new ArrayList<>();
        try {
            if (personIds != null) {
                for (String s : personIds) {
                    Person person = personService.getPersonById(Long.parseLong(s));
                    people.add(person);
                }
                family.setPersons(people);
            }
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        violations = validator.validate(family);

        if (!violations.isEmpty() || !violationsString.isEmpty()) {
            return "redirect:/families";
        }
        familyService.saveFamily(family);
        return "redirect:/families";
    }

    @PostMapping("editFamily")
    public String editFamily(Family family, HttpServletRequest request) {
        violations.clear();
        violationsString.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        String[] personIds = request.getParameterValues("personId");
        if (personIds != null) {
            List<Person> persons = new ArrayList<>();
            try {
                for (String s : personIds) {
                    Person person = personService.getPersonById(Long.parseLong(s));
                    persons.add(person);
                }
            } catch (ObjectNotFoundException e) {
                violationsString.add(e.getMessage());
            }
            List<Person> emptyList = new ArrayList<>();
            if (!persons.isEmpty()) {
                for (Person p : persons) {
                    emptyList.add(p);
                }
            }
            family.setPersons(emptyList);

            violations = validator.validate(family);
            if (violations.isEmpty()) {
                familyService.saveFamily(family);
            }
            return "redirect:/families";
        }
        List<Person> nullList = new ArrayList<>();
        family.setPersons(nullList);

        violations = validator.validate(family);
        if (violations.isEmpty()) {
            familyService.saveFamily(family);
        }
        return "redirect:/families";
    }

    @PostMapping("confirmDeactivateFamily")
    public String confirmDeactivateFamily(HttpServletRequest request) {
        Family family = familyService.getFamilyById(Long.parseLong(request.getParameter("id")));
        family.setActive(false);
        familyService.saveFamily(family);
        return "families";
    }

}
