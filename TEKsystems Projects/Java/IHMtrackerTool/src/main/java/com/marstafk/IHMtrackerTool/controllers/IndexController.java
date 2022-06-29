/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.*;
import com.marstafk.IHMtrackerTool.repositories.RoleRepository;
import com.marstafk.IHMtrackerTool.repositories.UserRepository;
import com.marstafk.IHMtrackerTool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author boss_
 */
@Controller
public class IndexController {

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

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @GetMapping(value = {"index", ""})
    public String displayIndex(Model model) throws ObjectNotFoundException {
        // Total money for current Jog-a-thon
        BigDecimal total = new BigDecimal(0);
        for (Pledge p : pledgeService.getAllPledges(true, false)) {
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
        Long[] arrayKeys1 = classTotals.keySet().toArray(new Long[classTotals.size()]);
        try {
            classId = arrayKeys1[arrayKeys1.length - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            classId = 0;
        }
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
        Long[] arrayKeys = studentsTotals.keySet().toArray(new Long[studentsTotals.size()]);
        try {
            studentId = arrayKeys[arrayKeys.length - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            studentId = 0;
        }
        Classroom classroom;
        Person person;
        Grade grade;
        try {
            classroom = classroomService.getClassroomById(classId);
        } catch (ObjectNotFoundException e) {
            classroom = new Classroom("None", new ArrayList<>(), true);
        }
        try {
            person = personService.getPersonById(studentId);
        } catch (ObjectNotFoundException e) {
            person = new Person("None", "None", "None", new ArrayList<>(), new ArrayList<>(), true);
        }
        grade = gradeService.getGradeByPersonId(studentId);
        if(grade == null) {
            grade = new Grade("None", new ArrayList<>(), true);
        }
        model.addAttribute("total", total);
        model.addAttribute("classTotal", finalClassTotal);
        model.addAttribute("classroom", classroom);
        model.addAttribute("student", person);
        model.addAttribute("studentGrade", grade);
        //model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("login")
    public String showLogin(Model model) {
        //model.addAttribute("user", new User());
        return "login2";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @GetMapping("chartData")
    @ResponseBody
    public List<List<BigDecimal>> chartData() {
        List<List<BigDecimal>> data = new ArrayList<>();
        JogathonMaster jogathonMaster = jogathonMasterService.getActiveAndDeletion(true, false);

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
        } catch (NoSuchElementException | NullPointerException | ObjectNotFoundException ex) {
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

    private HashMap<Long, BigDecimal> sortByValue(HashMap<Long, BigDecimal> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Long, BigDecimal>> list =
                new LinkedList<Map.Entry<Long, BigDecimal>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Long, BigDecimal>>() {
            public int compare(Map.Entry<Long, BigDecimal> o1,
                               Map.Entry<Long, BigDecimal> o2) {
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
