/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.Family;
import com.marstafk.IHMtrackerTool.models.JogathonMaster;
import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.models.Sponsor;
import com.marstafk.IHMtrackerTool.service.JogathonMasterService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    
    private List<JogathonMaster> jogathons = new ArrayList<>();
    
    @GetMapping("jogathons")
    public String displayJogathons(Model model) {
        jogathons.clear();
        
        jogathons = jMasterService.getAllJogathonsByActive(true);
        model.addAttribute("jogathons", jogathons);
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
    public String editJogathon(JogathonMaster jogathonMaster) {
        jMasterService.saveJogathon(jogathonMaster);
        return "redirect:/jogathons";
    }

    @PostMapping("confirmDeactivateJogathon")
    public String confirmDeactivateJogathon(HttpServletRequest request) {
        JogathonMaster jogathonMaster = jMasterService.getJogathonById(Long.parseLong(request.getParameter("id")));
        jogathonMaster.setActive(false);
        jMasterService.saveJogathon(jogathonMaster);
        return "redirect:/jogathons";
    }
    
}
