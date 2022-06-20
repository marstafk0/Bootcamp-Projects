/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.*;
import com.marstafk.IHMtrackerTool.service.JogathonMasterService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.marstafk.IHMtrackerTool.service.PledgeService;
import com.marstafk.IHMtrackerTool.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author boss_
 */
@Controller
public class JogathonMasterController {
    
    @Autowired
    JogathonMasterService jMasterService;

    @Autowired
    PledgeService pledgeService;

    @Autowired
    RunService runService;
    
    @GetMapping("jogathons")
    public String displayJogathons(Model model) {
        model.addAttribute("jogathons", jMasterService.getActiveJogathon(true));
        return "jogathons";
    }

    @GetMapping("inactiveJogathons")
    public String displayInactiveJogathons(Model model) {
        model.addAttribute("jogathons", jMasterService.getActiveJogathon(false));
        return "jogathons";
    }

    @GetMapping("getJogathon")
    @ResponseBody
    public JogathonMaster displayJogathonDetails(@RequestParam("id") long id) {
        return jMasterService.getJogathonById(id);
    }

    @PostMapping("addJogathon")
    public String addJogathon(HttpServletRequest request) {
        JogathonMaster jogathonMaster = new JogathonMaster();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
        jogathonMaster.setRunDate(LocalDate.parse(request.getParameter("runDate"), formatter));
        jogathonMaster.setComments(request.getParameter("comments"));
        switch(request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                jogathonMaster.setActive(false);
            }
            default -> {
                jogathonMaster.setActive(true);
            }
        }
        jMasterService.saveJogathon(jogathonMaster);
        return "redirect:/jogathons";
    }

    @PostMapping("editJogathon")
    public String editJogathon(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        JogathonMaster jogathonMaster = jMasterService.getJogathonById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
        jogathonMaster.setRunDate(LocalDate.parse(request.getParameter("runDate"), formatter));
        jogathonMaster.setComments(request.getParameter("comments"));
        switch(request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                jogathonMaster.setActive(false);
            }
            default -> {
                jogathonMaster.setActive(true);
            }
        }
        jMasterService.saveJogathon(jogathonMaster);
        return "redirect:/jogathons";
    }

    @PostMapping("confirmDeactivateJogathon")
    public String confirmDeactivateJogathon(HttpServletRequest request) {
        JogathonMaster jogathonMaster = jMasterService.getJogathonById(Long.parseLong(request.getParameter("id")));
        jogathonMaster.setActive(false);
        jMasterService.saveJogathon(jogathonMaster);
        for (Pledge p : pledgeService.getAllPledges(true)) {
            p.setActive(false);
            pledgeService.savePledge(p);
        }
        return "redirect:/jogathons";
    }
    
}
