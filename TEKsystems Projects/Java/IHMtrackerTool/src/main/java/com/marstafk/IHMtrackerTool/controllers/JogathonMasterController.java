/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.JogathonMaster;
import com.marstafk.IHMtrackerTool.models.Pledge;
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
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

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

        JogathonMaster jogathonMaster = new JogathonMaster();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
            LocalDate date = LocalDate.parse(request.getParameter("runDate"), formatter);
            jogathonMaster.setRunDate(date.toString());
        } catch (DateTimeException e) {
            violations2.add("Wrong date format! Please use: MM/DD/YYYY");
        }
        jogathonMaster.setComments(request.getParameter("comments"));
        jogathonMaster.setActive(true);

        violations = validator.validate(jogathonMaster);
        if (violations.isEmpty() && violations2.isEmpty()) {
            JogathonMaster oldJ = jMasterService.getActiveAndDeletion(true, false);
            if (oldJ != null) {
                oldJ.setActive(false);
                jMasterService.saveJogathon(oldJ);
            }
            jMasterService.saveJogathon(jogathonMaster);
        }
        return "redirect:/jogathons";
    }

    @PostMapping("editJogathon")
    public String editJogathon(HttpServletRequest request) {
        violations.clear();
        violations2.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        long id = Long.parseLong(request.getParameter("id"));
        JogathonMaster jogathonMaster = new JogathonMaster();
        try {
            jogathonMaster = jMasterService.getJogathonById(id);
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
            LocalDate date = LocalDate.parse(request.getParameter("runDate"), formatter);
            jogathonMaster.setRunDate(date.toString());
        } catch (DateTimeException e) {
            violations2.add("Wrong date format! Please use: MM/DD/YYYY");
        }
        jogathonMaster.setComments(request.getParameter("comments"));
        switch (request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                jogathonMaster.setActive(false);
            }
            default -> {
                jogathonMaster.setActive(true);
            }
        }

        violations = validator.validate(jogathonMaster);
        if (violations.isEmpty() && violations2.isEmpty()) {
            jMasterService.saveJogathon(jogathonMaster);
        }
        return "redirect:/jogathons";
    }

    @PostMapping("confirmDeactivateJogathon")
    public String confirmDeactivateJogathon(HttpServletRequest request) {
        violations2.clear();
        try {
            JogathonMaster jogathonMaster = jMasterService.getJogathonById(Long.parseLong(request.getParameter("id")));
            jogathonMaster.setDeletion(true);
            jMasterService.saveJogathon(jogathonMaster);
            for (Pledge p : pledgeService.getAllPledges(true, false)) {
                p.setDeletion(true);
                pledgeService.savePledge(p);
            }
        } catch (ObjectNotFoundException e) {
            violations2.add(e.getMessage());
        }
        return "redirect:/jogathons";
    }

}
