/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.*;
import com.marstafk.IHMtrackerTool.repositories.UserRepository;
import com.marstafk.IHMtrackerTool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author boss_
 */
@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PledgeService pledgeService;

    @Autowired
    JogathonMasterService jogathonMasterService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    GradeService gradeService;

    @Autowired
    PersonService personService;
    
    @GetMapping("index")
    public String displayIndex(Model model) {
        // Total money for current Jog-a-thon
        BigDecimal total = new BigDecimal(0);
        for (Pledge p : pledgeService.getAllPledges(true)) {
            total = total.add(p.getTotal());
        }

        // Highest classroom
        long classId = 0;
        BigDecimal classTotal = new BigDecimal(0);
        HashMap<Long, BigDecimal> classTotals = new HashMap<>();
        for (Classroom c : classroomService.getAllClassrooms(true)) {
            for (Pledge pl : pledgeService.getAllByClassroom(c.getId())) {
                classTotal = classTotal.add(pl.getTotal());
            }
            classTotals.put(c.getId(), classTotal);
            classTotal = new BigDecimal(0);
        }
        classTotals = sortByValue(classTotals);
        Long[] arrayKeys1 = classTotals.keySet().toArray( new Long[ classTotals.size() ] );
        classId = arrayKeys1[arrayKeys1.length - 1];
        BigDecimal finalClassTotal = classTotals.get(classId);

        // Highest student
        long studentId = 0;
        BigDecimal studentTotal = new BigDecimal(0);
        HashMap<Long, BigDecimal> studentsTotals = new HashMap<>();
        for (Person p : personService.getAllPeopleByType("Student", true)) {
            for (Pledge pl : pledgeService.getAllPledgesByStudentId(p.getId())) {
                studentTotal = studentTotal.add(pl.getTotal());
            }
            studentsTotals.put(p.getId(), studentTotal);
            studentTotal = new BigDecimal(0);
        }
        studentsTotals = sortByValue(studentsTotals);
        Long[] arrayKeys = studentsTotals.keySet().toArray( new Long[ studentsTotals.size() ] );
        studentId = arrayKeys[arrayKeys.length - 1];

        model.addAttribute("total", total);
        model.addAttribute("classTotal", finalClassTotal);
        model.addAttribute("classroom", classroomService.getClassroomById(classId));
        model.addAttribute("student", personService.getPersonById(studentId));
        model.addAttribute("studentGrade", gradeService.getGradeByPersonId(studentId));
        model.addAttribute("user", new User());
        return "index";
    }

//    @GetMapping("register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//
//        return "signup_form";
//    }

    @PostMapping("process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "redirect:/index";
    }

    @GetMapping("chartData")
    @ResponseBody
    public List<List<BigDecimal>> chartData() {
        List<List<BigDecimal>> data = new ArrayList<>();
        JogathonMaster jogathonMaster = jogathonMasterService.getActiveJogathon(true);

        Set<Integer> weeks = new HashSet<>();
        for (Pledge p : pledgeService.getAllByCurrentJog()) {
            weeks.add(p.getWeek());
        }
        List<BigDecimal> weeksList = new ArrayList<>();
        for (Integer i : weeks) {
            weeksList.add(new BigDecimal(i));
        }

        List<BigDecimal> moneyPerWeek = new ArrayList<>();
        for (Integer p : weeks) {
            BigDecimal total = new BigDecimal(0);
            for (Pledge pl : pledgeService.getAllByWeekCurrent(p)) {
                total = total.add(pl.getTotal());
            }
            moneyPerWeek.add(total);
        }

        List<BigDecimal> previousMoneyPerWeek = new ArrayList<>();
        try {
            JogathonMaster previousJogathon = jogathonMasterService.getJogathonById(jogathonMaster.getId() - 1);
            for (Integer p : weeks) {
                BigDecimal total = new BigDecimal(0);
                for (Pledge pl : pledgeService.getAllbyJogIdWeekInactive(previousJogathon.getId(), p)) {
                    total = total.add(pl.getTotal());
                }
                previousMoneyPerWeek.add(total);
            }
        } catch (NullPointerException e) {

        }
        data.add(weeksList);
        data.add(moneyPerWeek);
        data.add(previousMoneyPerWeek);
        return data;
    }

    @GetMapping("chartGradeData")
    @ResponseBody
    public List<List<String>> chartGradeData() {
        List<List<String>> data = new ArrayList<>();

        List<String> grades = new ArrayList<>();
        for (Grade g : gradeService.getAllGrades()) {
            grades.add(g.getGradeName());
        }

        List<String> moneyPerGrade = new ArrayList<>();
        for (Grade g : gradeService.getAllGrades()) {
            BigDecimal total = new BigDecimal(0);
            for (Pledge p : pledgeService.getAllByGradeId(g.getId())) {
                total = total.add(p.getTotal());
            }
            moneyPerGrade.add(total.toString());
        }
        data.add(grades);
        data.add(moneyPerGrade);
        return data;
    }

    private HashMap<Long, BigDecimal> sortByValue(HashMap<Long, BigDecimal> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Long, BigDecimal> > list =
                new LinkedList<Map.Entry<Long, BigDecimal> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Long, BigDecimal> >() {
            public int compare(Map.Entry<Long, BigDecimal> o1,
                               Map.Entry<Long, BigDecimal> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Long, BigDecimal> temp = new LinkedHashMap<Long, BigDecimal>();
        for (Map.Entry<Long, BigDecimal> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
