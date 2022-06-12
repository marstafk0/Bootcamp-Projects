package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.*;
import com.marstafk.IHMtrackerTool.service.*;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    GradeService gradeService;

    @Autowired
    PersonTypeService personTypeService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    FamilyService familyService;

    @Autowired
    JogathonMasterService jogathonMasterService;

    @Autowired
    PersonLapsService personLapsService;

    private List<Student> studentAttr = new ArrayList<>();

    @GetMapping("students")
    public String displayStudents(Model model) {
        studentAttr.clear();
        studentAttr = compileAttr(personService.getAllPeopleByType(personTypeService.getPersonTypeById(1).getStatusName(), true));
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        model.addAttribute("families", familyService.getAllFamilies(true));
        return "students";
    }

    @GetMapping("studentsAZlastName")
    public String displayStudentsAZlastName(Model model) {
        studentAttr.clear();
        studentAttr = compileAttr(personService.getAllStudentsAZlastName(personTypeService.getPersonTypeById(1).getStatusName()));
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        model.addAttribute("families", familyService.getAllFamilies(true));
        return "students";
    }

    @GetMapping("studentsAZfirstName")
    public String displayStudentsAZfirstName(Model model) {
        studentAttr.clear();
        studentAttr = compileAttr(personService.getAllStudentsAZfirstName(personTypeService.getPersonTypeById(1).getStatusName()));
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        model.addAttribute("families", familyService.getAllFamilies(true));
        return "students";
    }

    @GetMapping("studentsByGrade")
    public String displayStudentsByGrade(Model model) {
        studentAttr.clear();
        studentAttr = compileAttr(personService.getAllStudentsGrade());
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        model.addAttribute("families", familyService.getAllFamilies(true));
        return "students";
    }

    @GetMapping("studentsInactive")
    public String displayInactiveStudents(Model model) {
        studentAttr.clear();
        studentAttr = compileAttr(personService.getAllInactiveStudents(personTypeService.getPersonTypeById(1).getStatusName()));
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        model.addAttribute("families", familyService.getAllFamilies(true));
        return "students";
    }

    @GetMapping("editStudent")
    @ResponseBody
    public List<List<Object>> editPerson(@RequestParam("id") long id) {
        List<List<Object>> objects = new ArrayList<>();
        List<Object> person = new ArrayList<>();
        List<Object> grade = new ArrayList<>();
        List<Object> family = new ArrayList<>();
        List<Object> laps = new ArrayList<>();
        person.add(personService.getPersonById(id));
        grade.add(gradeService.getGradeByPersonId(id));
        family.add(familyService.getFamilyByStudentId(id));
        laps.add(personLapsService.getByPersonId(id));
        System.out.println(personLapsService.getByPersonId(id));
        objects.add(person);
        objects.add(grade);
        objects.add(family);
        objects.add(laps);
        return objects;
    }

    @GetMapping("studentDetails")
    @ResponseBody
    public List<List<Object>> displayStudentDetails(@RequestParam("id") long id) {
        List<List<Object>> objects = new ArrayList<>();
        List<Object> person = new ArrayList<>();
        List<Object> grade = new ArrayList<>();
        List<Object> classroom = new ArrayList<>();
        List<Object> family = new ArrayList<>();
        List<Object> laps = new ArrayList<>();
        person.add(personService.getPersonById(id));
        grade.add(gradeService.getGradeByPersonId(id));
        classroom.add(classroomService.getClassroomByGradeId(gradeService.getGradeByPersonId(id).getId()));
        family.add(familyService.getFamilyByStudentId(id));
        laps.add(personLapsService.getByPersonId(id));
        objects.add(person);
        objects.add(grade);
        objects.add(classroom);
        objects.add(family);
        objects.add(laps);
        return objects;
    }

    @PostMapping("saveStudent")
    public String savePerson(Person person, @RequestParam("grade") Long id, HttpServletRequest request) {
        // If grade is 0 or below, ignore and leave empty.
        if (id > 0) {
            Grade grade = gradeService.getGradeById(id);
            Grade oldGrade = gradeService.getGradeByPersonId(person.getId());
            // If old grade is null, then there was no previous assigned grade.
            if (oldGrade == null) {

                // If there was an assigned grade, delete it and add the new grade
            } else {
                List<Person> persons = oldGrade.getPersons();
                persons.removeIf(p -> p.getId() == person.getId());
                oldGrade.setPersons(persons);
                gradeService.saveGrade(oldGrade);
            }
            grade.addStudent(person);
            gradeService.saveGrade(grade);
        }
        Family oldFamily = familyService.getFamilyByStudentId(person.getId());
        long famId = 0;
        try {
            famId = Long.parseLong(request.getParameter("familyId"));
        } catch (NumberFormatException e) {

        }
        if (oldFamily != null) {
            if (famId > 0 && oldFamily.getId() != 0) {
                Family family = familyService.getFamilyById(famId);
                List<Person> persons = oldFamily.getPersons();
                persons.removeIf(p -> p.getId() == person.getId());
                oldFamily.setPersons(persons);
                familyService.saveFamily(oldFamily);
                family.addPerson(person);
                familyService.saveFamily(family);
            } else {
                List<Person> persons = oldFamily.getPersons();
                persons.removeIf(p -> p.getId() == person.getId());
                oldFamily.setPersons(persons);
                familyService.saveFamily(oldFamily);
            }
        } else if (famId > 0) {
            Family family = familyService.getFamilyById(famId);
            family.addPerson(person);
            familyService.saveFamily(family);
        }

        int laps;
        try {
            laps = Integer.parseInt(request.getParameter("laps"));
        } catch (Exception e) {
            laps = 0;
        }
        if (laps > 0) {
            List<PersonLaps> emp = new ArrayList<>();
            emp.add(new PersonLaps(laps, true));
            person.setLaps(emp);
        }

        personService.savePerson(person);
        return "redirect:/students";
    }

    @GetMapping("deactivatePerson")
    @ResponseBody
    public Person deactivateStudent(@RequestParam("id") long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("confirmDeactivateStudent")
    public String confirmDeactivateStudent(HttpServletRequest request) {
        Person person = personService.getPersonById(Long.parseLong(request.getParameter("id")));
        person.setActive(false);
        personService.savePerson(person);
        return "redirect:/students";
    }

    @PostMapping("addStudent")
    public String addStudent(HttpServletRequest request, @RequestParam("grade") Long id) {

        Person student = new Person();
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setContact(request.getParameter("contact"));
        long famId;
        try {
            famId = Long.parseLong(request.getParameter("familyId"));
        } catch (NumberFormatException e) {
            famId = 0;
        }
        Family family = familyService.getFamilyById(famId);
        switch (request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                student.setActive(false);
            }
            default -> {
                student.setActive(true);
            }
        }
        int laps = 0;
        try {
            laps = Integer.parseInt(request.getParameter("laps"));
        } catch (NumberFormatException e) {

        }
        if (laps != 0) {
            PersonLaps personLaps = new PersonLaps(laps, true);
            personLapsService.savePersonLaps(personLaps);
            List<PersonLaps> totalLaps = new ArrayList<>();
            totalLaps.add(personLaps);
            JogathonMaster jogathonMaster = jogathonMasterService.getActiveJogathon(true);
            jogathonMaster.addLaps(personLaps);
            jogathonMasterService.saveJogathon(jogathonMaster);
            student.setLaps(totalLaps);
        }
        personService.savePerson(student);

        Grade grade = gradeService.getGradeById(id);
        grade.addStudent(student);
        gradeService.saveGrade(grade);

        PersonType pt = personTypeService.getPersonTypeByName("Student");
        pt.addPersons(student);
        personTypeService.savePersonType(pt);

        try {
            family.addPerson(student);
            familyService.saveFamily(family);
        } catch (NullPointerException e) {

        }
        return "redirect:/students";
    }

    private List<Student> compileAttr(List<Person> peoples) {
        studentAttr.clear();
        for (Person s : peoples) {
            Grade g = gradeService.getGradeByPersonId(s.getId());
            String name;
            try {
                name = classroomService.getClassroomByGradeId(g.getId()).getClassName();
            } catch (NullPointerException e) {
                name = "None";
            }
            Student stud = new Student(s.getId(), s.getFirstName(), s.getLastName(), s.getContact(), g.getGradeName(), name);
            studentAttr.add(stud);
        }
        return studentAttr;
    }

    // --------------------------- TEACHERS -------------------------------//

    private List<Person> teachers = new ArrayList<>();

    @GetMapping("teachers")
    public String displayTeachers(Model model) {
        model.addAttribute("teachers", personService.getAllPeopleByType(personTypeService.getPersonTypeById(2).getStatusName(), true));
        model.addAttribute("families", familyService.getAllFamilies(true));
        return "teachers";
    }

    @GetMapping("inactiveTeachers")
    public String displayInactiveTeachers(Model model) {
        model.addAttribute("teachers", personService.getAllPeopleByType("Teacher", false));
        model.addAttribute("families", familyService.getAllFamilies(true));
        return "teachers";
    }

    @GetMapping("getTeacher")
    @ResponseBody
    public List<Object> editTeacher(@RequestParam("id") long id) {
        List<Object> objects = new ArrayList<>();
        objects.add(personService.getPersonById(id));
        objects.add(familyService.getFamilyByStudentId(id));
        return objects;
    }

    @PostMapping("addTeacher")
    public String addTeacher(HttpServletRequest request) {

        Person teacher = new Person();
        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        teacher.setContact(request.getParameter("contact"));
        long famId;
        try {
            famId = Long.parseLong(request.getParameter("familyId"));
        } catch (NumberFormatException e) {
            famId = 0;
        }
        Family family = familyService.getFamilyById(famId);
        switch (request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                teacher.setActive(false);
            }
            default -> {
                teacher.setActive(true);
            }
        }
        personService.savePerson(teacher);

        PersonType pt = personTypeService.getPersonTypeByName("Teacher");
        pt.addPersons(teacher);
        personTypeService.savePersonType(pt);

        try {
            family.addPerson(teacher);
            familyService.saveFamily(family);
        } catch (NullPointerException e) {

        }
        return "redirect:/teachers";
    }

    @PostMapping("saveTeacher")
    public String saveTeacher(Person person, HttpServletRequest request) {
        Family oldFamily = familyService.getFamilyByStudentId(person.getId());
        long id = 0;
        try {
            id = Long.parseLong(request.getParameter("familyId"));
        } catch (NumberFormatException e) {

        }

        if (oldFamily != null) {
            if (id > 0 && oldFamily.getId() != 0) {
                Family family = familyService.getFamilyById(id);
                List<Person> persons = oldFamily.getPersons();
                persons.removeIf(p -> p.getId() == person.getId());
                oldFamily.setPersons(persons);
                familyService.saveFamily(oldFamily);
                family.addPerson(person);
                familyService.saveFamily(family);
            } else {
                List<Person> persons = oldFamily.getPersons();
                persons.removeIf(p -> p.getId() == person.getId());
                oldFamily.setPersons(persons);
                familyService.saveFamily(oldFamily);
            }
        } else if (id > 0) {
            Family family = familyService.getFamilyById(id);
            family.addPerson(person);
            familyService.saveFamily(family);
        }

        personService.savePerson(person);
        return "redirect:/teachers";
    }

    @PostMapping("confirmDeactivateTeacher")
    public String confirmDeactivateTeacher(HttpServletRequest request) {
        Person person = personService.getPersonById(Long.parseLong(request.getParameter("id")));
        person.setActive(false);
        personService.savePerson(person);
        return "redirect:/teachers";
    }

    // -------------------------------- PERSONS ----------------------------------- //


}
