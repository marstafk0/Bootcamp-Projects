/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.JogathonMaster;
import com.marstafk.IHMtrackerTool.models.Pledge;
import com.marstafk.IHMtrackerTool.models.Run;
import com.marstafk.IHMtrackerTool.service.JogathonMasterService;
import com.marstafk.IHMtrackerTool.service.PledgeService;
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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author boss_
 */
@Controller
public class JogathonMasterController {

    @Autowired
    JogathonMasterService jMasterService;

    @Autowired
    PledgeService pledgeService;

    private Set<ConstraintViolation<JogathonMaster>> violations = new HashSet<>();
    private Set<String> violations2 = new HashSet<>();

    @GetMapping("jogathons")
    public String displayJogathons(Model model) {
        model.addAttribute("jogathons", jMasterService.getActiveAndDeletion(true, false));
        model.addAttribute("errors", violations);
        model.addAttribute("errors2", violations2);
        return "jogathons";
    }

    @GetMapping("inactiveJogathons")
    public String displayInactiveJogathons(Model model) {
        model.addAttribute("jogathons", jMasterService.getActiveAndDeletion(false, false));
        model.addAttribute("errors", violations);
        model.addAttribute("errors2", violations2);
        return "jogathons";
    }

    @GetMapping("getJogathon")
    @ResponseBody
    public JogathonMaster displayJogathonDetails(@RequestParam("id") long id) {
        try {
            return jMasterService.getJogathonById(id);
        } catch (Exception e) {
            violations2.add(e.getMessage());
        }
        return null;
    }

    @PostMapping("addJogathon")
    public String addJogathon(HttpServletRequest request) {
        violations.clear();
        violations2.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // Get the values from the form
        JogathonMaster jogathonMaster = new JogathonMaster();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
            LocalDate date = LocalDate.parse(request.getParameter("runDate"), formatter);
            jogathonMaster.setRunDate(date.toString());
        } catch (DateTimeException e) {
            violations2.add("Wrong date format! Please use: MM/DD/YYYY");
        }
        if (jMasterService.getActiveAndDeletion(true, false) != null) {
            violations2.add("There is already an active jogathon!");
        }

        // Validate and save the jogathon
        violations = validator.validate(jogathonMaster);
        if (violations.isEmpty() && violations2.isEmpty()) {
            jogathonMaster.setComments(request.getParameter("comments"));
            jogathonMaster.setActive(true);
            jMasterService.saveJogathon(jogathonMaster);
        }
        return "redirect:/jogathons";
    }

    @PostMapping("editJogathon")
    public String editJogathon(HttpServletRequest request) {
        violations.clear();
        violations2.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // Get the jogathon to edit
        long id = Long.parseLong(request.getParameter("id"));
        JogathonMaster jogathonMaster = new JogathonMaster();
        try {
            jogathonMaster = jMasterService.getJogathonById(id);
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
        }
        // Set the new values
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
            LocalDate date = LocalDate.parse(request.getParameter("runDate"), formatter);
            jogathonMaster.setRunDate(date.toString());
        } catch (DateTimeException e) {
            violations2.add("Wrong date format! Please use: MM/DD/YYYY");
        }
        jogathonMaster.setComments(request.getParameter("comments"));
        switch (request.getParameter("active").toLowerCase()) {
            case "false" -> {
                jogathonMaster.setActive(false);
            }
            default -> {
                jogathonMaster.setActive(true);
            }
        }

        // Validate and save the jogathon
        violations = validator.validate(jogathonMaster);
        if (violations.isEmpty() && violations2.isEmpty()) {
            jMasterService.saveJogathon(jogathonMaster);
        }
        return "redirect:/jogathons";
    }

    @PostMapping("confirmDeactivateJogathon")
    public String confirmDeactivateJogathon(HttpServletRequest request) {
        violations2.clear();

        // Deactivate the old jogathon
        try {
            JogathonMaster jogathonMaster = jMasterService.getJogathonById(Long.parseLong(request.getParameter("id")));
            jogathonMaster.setActive(false);
            jMasterService.saveJogathon(jogathonMaster);
            for (Pledge p : pledgeService.getAllPledges(true, false)) {
                p.setActive(false);
                pledgeService.savePledge(p);
            }
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
        }
        return "redirect:/jogathons";
    }

}
