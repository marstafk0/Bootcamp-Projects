package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.*;
import com.marstafk.IHMtrackerTool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    RunService runService;

    private List<Student> studentAttr = new ArrayList<>();

    private Set<ConstraintViolation<Person>> violations = new HashSet<>();
    private Set<ConstraintViolation<StudentAdd>> violationsStudent = new HashSet<>();
    private Set<String> violationsString = new HashSet<>();

    @GetMapping("students")
    public String displayStudents(Model model) {
        studentAttr.clear();
        violationsString.clear();
        try {
            studentAttr = compileAttr(personService.getAllPeopleByType(personTypeService.getPersonTypeById(1).getStatusName(), true));
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms(true));
        model.addAttribute("families", familyService.getAllFamilies(true));
        model.addAttribute("errors", violations);
        model.addAttribute("errorsRun", violationsString);
        return "students";
    }

    @GetMapping("studentsAZlastName")
    public String displayStudentsAZlastName(Model model) {
        studentAttr.clear();
        violationsString.clear();
        try {
            studentAttr = compileAttr(personService.getAllStudentsAZlastName(personTypeService.getPersonTypeById(1).getStatusName()));
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms(true));
        model.addAttribute("families", familyService.getAllFamilies(true));
        model.addAttribute("errors", violations);
        model.addAttribute("errorsRun", violationsString);
        return "students";
    }

    @GetMapping("studentsAZfirstName")
    public String displayStudentsAZfirstName(Model model) {
        studentAttr.clear();
        violationsString.clear();
        try {
            studentAttr = compileAttr(personService.getAllStudentsAZfirstName(personTypeService.getPersonTypeById(1).getStatusName()));
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms(true));
        model.addAttribute("families", familyService.getAllFamilies(true));
        model.addAttribute("errors", violations);
        model.addAttribute("errorsRun", violationsString);
        return "students";
    }

    @GetMapping("studentsByGrade")
    public String displayStudentsByGrade(Model model) {
        studentAttr.clear();
        studentAttr = compileAttr(personService.getAllStudentsGrade());
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("classrooms", classroomService.getAllClassrooms(true));
        model.addAttribute("families", familyService.getAllFamilies(true));
        model.addAttribute("errors", violations);
        model.addAttribute("errorsRun", violationsString);
        return "students";
    }

    @GetMapping("editStudent")
    @ResponseBody
    public List<List<Object>> editPerson(@RequestParam("id") long id) {
        violationsString.clear();
        List<List<Object>> objects = new ArrayList<>();
        List<Object> person = new ArrayList<>();
        List<Object> grade = new ArrayList<>();
        List<Object> family = new ArrayList<>();
        try {
            person.add(personService.getPersonById(id));
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        grade.add(gradeService.getGradeByPersonId(id));
        family.add(familyService.getFamilyByPersonId(id));
        objects.add(person);
        objects.add(grade);
        objects.add(family);
        return objects;
    }

    @GetMapping("studentDetails")
    @ResponseBody
    public List<List<Object>> displayStudentDetails(@RequestParam("id") long id) throws ObjectNotFoundException {
        List<List<Object>> objects = new ArrayList<>();
        List<Object> person = new ArrayList<>();
        List<Object> grade = new ArrayList<>();
        List<Object> classroom = new ArrayList<>();
        List<Object> family = new ArrayList<>();
        person.add(personService.getPersonById(id));
        grade.add(gradeService.getGradeByPersonId(id));
        classroom.add(classroomService.getClassroomByGradeId(gradeService.getGradeByPersonId(id).getId()));
        family.add(familyService.getFamilyByPersonId(id));
        objects.add(person);
        objects.add(grade);
        objects.add(classroom);
        objects.add(family);
        return objects;
    }

    @PostMapping("saveStudent")
    public String savePerson(Person person, @RequestParam("grade") Long id, HttpServletRequest request) {
        violations.clear();
        violationsString.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        violations = validator.validate(person);
        if (violations.isEmpty()) {
            // Set previous runs
            try {
                Person oldPerson = personService.getPersonById(person.getId());
                List<Run> runs = oldPerson.getRuns();
                person.setRuns(runs);

                // Set previous pledges
                List<Pledge> pledges = oldPerson.getPledges();
                person.setPledges(pledges);
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
            } catch (ObjectNotFoundException e) {
                violationsString.add(e.getMessage());
            }
            Family oldFamily = familyService.getFamilyByPersonId(person.getId());
            long famId = 0;
            try {
                famId = Long.parseLong(request.getParameter("familyId"));
            } catch (NumberFormatException e) {

            }
            // Old family or not
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

            personService.savePerson(person);
        }
        return "redirect:/students";
    }

    @GetMapping("deactivatePerson")
    @ResponseBody
    public Person deactivateStudent(@RequestParam("id") long id) {
        violationsString.clear();
        try {
            return personService.getPersonById(id);
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
            return null;
        }
    }

    @PostMapping("confirmDeactivateStudent")
    public String confirmDeactivateStudent(HttpServletRequest request) {
        violationsString.clear();
        try {
            Person person = personService.getPersonById(Long.parseLong(request.getParameter("id")));
            person.setActive(false);
            personService.savePerson(person);
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        return "redirect:/students";
    }

    @PostMapping("addStudentTest")
    @ResponseBody
    public Set<ConstraintViolation<StudentAdd>> addStudentTest(@RequestBody StudentAdd studentAdd) {
        violationsString.clear();
        violationsStudent.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        violationsStudent = validator.validate(studentAdd);
        if (violationsStudent.isEmpty()) {
            Person student = new Person();
            student.setFirstName(studentAdd.getFirstName());
            student.setLastName(studentAdd.getLastName());
            student.setContact(studentAdd.getContact());
            student.setActive(true);

            long famId;
            try {
                famId = Long.parseLong(studentAdd.getFamilyId());
            } catch (NumberFormatException e) {
                famId = 0;
            }
            Family family = familyService.getFamilyById(famId);

            personService.savePerson(student);

            try {
                Grade grade = gradeService.getGradeById(Long.parseLong(studentAdd.getGradeId()));
                grade.addStudent(student);
                gradeService.saveGrade(grade);
            } catch (ObjectNotFoundException e) {
                violationsString.add(e.getMessage());
            }

            PersonType pt = personTypeService.getPersonTypeByName("Student");
            pt.addPersons(student);
            personTypeService.savePersonType(pt);

            try {
                family.addPerson(student);
                familyService.saveFamily(family);
            } catch (NullPointerException e) {

            }
        }
        return violationsStudent;
    }

    @PostMapping("addStudent")
    public String addStudent(HttpServletRequest request, @RequestParam("grade") Long id) {
        violations.clear();
        violationsString.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Person student = new Person();
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setContact(request.getParameter("contact"));
        student.setActive(true);

        violations = validator.validate(student);
        if (violations.isEmpty()) {
            long famId;
            try {
                famId = Long.parseLong(request.getParameter("familyId"));
            } catch (NumberFormatException e) {
                famId = 0;
            }
            Family family = familyService.getFamilyById(famId);

            personService.savePerson(student);

            try {
                Grade grade = gradeService.getGradeById(id);
                grade.addStudent(student);
                gradeService.saveGrade(grade);
            } catch (ObjectNotFoundException e) {
                violationsString.add(e.getMessage());
            }

            PersonType pt = personTypeService.getPersonTypeByName("Student");
            pt.addPersons(student);
            personTypeService.savePersonType(pt);

            try {
                family.addPerson(student);
                familyService.saveFamily(family);
            } catch (NullPointerException e) {

            }
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
            } catch (NullPointerException | ObjectNotFoundException e) {
                name = "None";
            }
            Student stud = new Student(s.getId(), s.getFirstName(), s.getLastName(), s.getContact(), g.getGradeName(), name);
            studentAttr.add(stud);
        }
        return studentAttr;
    }

    // --------------------------- TEACHERS -------------------------------//

    @GetMapping("teachers")
    public String displayTeachers(Model model) {
        violationsString.clear();
        try {
            model.addAttribute("teachers", personService.getAllPeopleByType(personTypeService.getPersonTypeById(2).getStatusName(), true));
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        model.addAttribute("families", familyService.getAllFamilies(true));
        model.addAttribute("errors", violations);
        model.addAttribute("errors2", violationsString);
        return "teachers";
    }

    @GetMapping("getTeacher")
    @ResponseBody
    public List<Object> editTeacher(@RequestParam("id") long id) {
        violationsString.clear();
        List<Object> objects = new ArrayList<>();
        try {
            objects.add(personService.getPersonById(id));
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        objects.add(familyService.getFamilyByPersonId(id));
        return objects;
    }

    @PostMapping("addTeacher")
    public String addTeacher(HttpServletRequest request) {
        violations.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Person teacher = new Person();
        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        teacher.setContact(request.getParameter("contact"));
        teacher.setActive(true);

        violations = validator.validate(teacher);
        if (violations.isEmpty()) {
            long famId;
            try {
                famId = Long.parseLong(request.getParameter("familyId"));
            } catch (NumberFormatException e) {
                famId = 0;
            }
            Family family = familyService.getFamilyById(famId);

            personService.savePerson(teacher);

            PersonType pt = personTypeService.getPersonTypeByName("Teacher");
            pt.addPersons(teacher);
            personTypeService.savePersonType(pt);

            try {
                family.addPerson(teacher);
                familyService.saveFamily(family);
            } catch (NullPointerException e) {
            }
        }
        return "redirect:/teachers";
    }

    @PostMapping("saveTeacher")
    public String saveTeacher(Person person, HttpServletRequest request) {
        violations.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        violations = validator.validate(person);
        if (violations.isEmpty()) {
            Family oldFamily = familyService.getFamilyByPersonId(person.getId());
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
        }
        return "redirect:/teachers";
    }

    @PostMapping("confirmDeactivateTeacher")
    public String confirmDeactivateTeacher(HttpServletRequest request) {
        violationsString.clear();
        try {
            Person person = personService.getPersonById(Long.parseLong(request.getParameter("id")));
            person.setActive(false);
            personService.savePerson(person);
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        return "redirect:/teachers";
    }

    // -------------------------------- LAPS ----------------------------------- //

    @GetMapping("lapDetails")
    @ResponseBody
    public Run lapDetails(@RequestParam("id") long id) {
        violationsString.clear();
        JogathonMaster currJ = jogathonMasterService.getActiveAndDeletion(true, false);
        if (currJ == null) {
            violationsString.add("Please start a Jogathon before editing laps.");
        }
        Run run = null;
        try {
            if (violationsString.isEmpty()) {
                if (runService.getByPersonId(id) != null) {
                    return runService.getByPersonId(id);
                }

                run = new Run(0);
                runService.saveRun(run);
                Person person = personService.getPersonById(id);
                if (person.getRuns() != null) {
                    person.addRuns(run);
                } else {
                    person.setRuns(new ArrayList<>());
                    person.addRuns(run);
                }
                personService.savePerson(person);
                if (currJ.getRuns() != null) {
                    currJ.addRuns(run);
                } else {
                    currJ.setRuns(new ArrayList<>());
                    currJ.addRuns(run);
                }
                jogathonMasterService.saveJogathon(currJ);
            }
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        return run;
    }

    @PostMapping("editLaps")
    public String editLaps(HttpServletRequest request, @RequestParam("id") long id) {
        violationsString.clear();

        Run run = new Run();
        try {
            run = runService.getByRunId(id);
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        try {
            run.setLaps(Integer.parseInt(request.getParameter("laps")));
        } catch (NumberFormatException e) {
            violationsString.add("Please enter a valid number.");
        }

        JogathonMaster currJ = jogathonMasterService.getActiveAndDeletion(true, false);
        if (currJ == null) {
            violationsString.add("Please start a Jogathon before editing laps.");
        }

        if (violationsString.isEmpty()) {
            Person person = personService.getPersonByRunId(run.getId());

            Run finalRun = run;
            person.getRuns().removeIf(r -> r.getId() == finalRun.getId());
            person.addRuns(run);
            personService.savePerson(person);

            currJ.getRuns().removeIf(r -> r.getId() == finalRun.getId());
            currJ.addRuns(run);
            jogathonMasterService.saveJogathon(currJ);

            runService.saveRun(run);
        }
        PersonType pt = personTypeService.getByPersonId(personService.getPersonByRunId(id).getId());
        switch(pt.getStatusName()) {
            case "Student" -> {
                return "redirect:/students";
            }
            case "Teacher" -> {
                return "redirect:/teachers";
            }
            default -> {
                return "redirect:/persons";
            }
        }
    }

    // ----------------------------- PERSON ----------------------------------- //

    @GetMapping("persons")
    public String displayPersons(Model model) {
        model.addAttribute("persons", personService.getAllPeopleByType("Member", true));
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("personTypes", personTypeService.getAllPersonTypes());
        model.addAttribute("families", familyService.getAllFamilies(true));
        model.addAttribute("errors", violations);
        return "persons";
    }

    @GetMapping("getPerson")
    @ResponseBody
    public List<List<Object>> getPerson(@RequestParam("id") long id) {
        violationsString.clear();
        List<List<Object>> objects = new ArrayList<>();
        List<Object> person = new ArrayList<>();
        List<Object> family = new ArrayList<>();
        try {
            person.add(personService.getPersonById(id));
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        family.add(familyService.getFamilyByPersonId(id));
        objects.add(person);
        objects.add(family);
        return objects;
    }

    @PostMapping("addPerson")
    public String addPerson(HttpServletRequest request) {
        violationsString.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Person person = new Person();
        person.setFirstName(request.getParameter("firstName"));
        person.setLastName(request.getParameter("lastName"));
        person.setContact(request.getParameter("contact"));
        person.setActive(true);

        violations = validator.validate(person);
        if (violations.isEmpty()) {
            long famId;
            try {
                famId = Long.parseLong(request.getParameter("familyId"));
            } catch (NumberFormatException e) {
                famId = 0;
            }
            Family family = familyService.getFamilyById(famId);

            person.setActive(true);
            personService.savePerson(person);

            try {
                try {
                    Grade grade = gradeService.getGradeById(Long.parseLong(request.getParameter("gradeId")));
                    grade.addStudent(person);
                    gradeService.saveGrade(grade);
                } catch (NumberFormatException e) {

                }

                PersonType pt = personTypeService.getPersonTypeById(3);
                pt.addPersons(person);
                personTypeService.savePersonType(pt);

                try {
                    family.addPerson(person);
                    familyService.saveFamily(family);
                } catch (NullPointerException e) {

                }
            } catch (ObjectNotFoundException e) {
                violationsString.add(e.getMessage());
            }
        }
        return "redirect:/persons";
    }

    @PostMapping("editPerson")
    public String editPerson(Person person, HttpServletRequest request) {
        violationsString.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        violations = validator.validate(person);
        if (violations.isEmpty()) {
            Family oldFamily = familyService.getFamilyByPersonId(person.getId());
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

            personService.savePerson(person);
        }
        return "redirect:/persons";
    }

    @PostMapping("confirmDeactivatePerson")
    public String deactivatePerson(@RequestParam("id") long id) {
        violationsString.clear();
        try {
            Person person = personService.getPersonById(id);
            person.setActive(false);
        } catch (ObjectNotFoundException e) {
            violationsString.add(e.getMessage());
        }
        return "redirect:/persons";
    }

}
