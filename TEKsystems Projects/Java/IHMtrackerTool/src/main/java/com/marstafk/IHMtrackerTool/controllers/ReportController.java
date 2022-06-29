package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.models.Pledge;
import com.marstafk.IHMtrackerTool.models.Report;
import com.marstafk.IHMtrackerTool.service.JogathonMasterService;
import com.marstafk.IHMtrackerTool.service.PersonService;
import com.marstafk.IHMtrackerTool.service.PledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

@Controller
public class ReportController {

    @Autowired
    JogathonMasterService jogathonMasterService;

    @Autowired
    PledgeService pledgeService;

    @Autowired
    PersonService personService;

    private List<Pledge> selectedJ = new ArrayList<>();

    @GetMapping("reports")
    public String getReports(Model model) {
        model.addAttribute("jogathons", jogathonMasterService.getAllJogathons());
        return "reports";
    }

    @GetMapping("getWeeks")
    @ResponseBody
    public Set<Integer> getWeeks(@RequestParam("id") long id) {
        selectedJ.clear();
        Set<Integer> weeks = new HashSet<>();
        for (Pledge p : pledgeService.getAllByJogathonId(id)) {
            weeks.add(p.getWeek());
            selectedJ.add(p);
        }

        weeks.stream().sorted();
        return weeks;
    }

    @GetMapping("getPledges")
    @ResponseBody
    public List<Report> getPledges(@RequestParam("id") long id) {
        if(id == 0) {
            return assPledgeStudent(selectedJ);
        }
        List<Pledge> byWeek = new ArrayList<>();
        for (Pledge p : selectedJ) {
            if(p.getWeek() == id) {
                byWeek.add(p);
            }
        }
        return assPledgeStudent(byWeek);
    }

    private List<Report> assPledgeStudent(List<Pledge> pledges) {
        pledges.sort(Comparator.comparing(Pledge :: getTotal));
        List<Report> reports = new ArrayList<>();

        for (Pledge p : pledges) {
            Person person = new Person();
            try {
                person = personService.getPersonByPledgeId(p.getId());
            } catch (ObjectNotFoundException e) {
                System.out.println("Error");
            }
            Report report = new Report(person.getId(), person.getFirstName() + " " + person.getLastName(), p.getTotal());
            reports.add(report);
        }

        Set<Long> ids = new HashSet<>();
        for (Report r : reports) {
            ids.add(r.getStudentId());
        }

        List<Long> uIds = ids.stream().toList();
        List<Report> finalReport = new ArrayList<>();

        for(int i = 0; i < uIds.size(); i++) {
            BigDecimal tot = new BigDecimal(0);
            for (Report r : reports) {
                if(r.getStudentId() == uIds.get(i)) {
                    tot = tot.add(r.getTotal());
                }
            }
            finalReport.add(new Report(uIds.get(i), reports.get(i).getStudent(), tot));
        }
        return finalReport;
    }

}
