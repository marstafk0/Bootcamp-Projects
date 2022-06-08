package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.Classroom;
import com.marstafk.IHMtrackerTool.models.Family;
import com.marstafk.IHMtrackerTool.models.Grade;
import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.models.PersonType;
import com.marstafk.IHMtrackerTool.models.Student;
import com.marstafk.IHMtrackerTool.service.ClassroomService;
import com.marstafk.IHMtrackerTool.service.FamilyService;
import com.marstafk.IHMtrackerTool.service.GradeService;
import com.marstafk.IHMtrackerTool.service.PersonService;
import com.marstafk.IHMtrackerTool.service.PersonTypeService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    
    private List<Person> activeStudents = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();
    private List<Student> studentAttr = new ArrayList<>();
    private List<Classroom> classrooms = new ArrayList<>();
    private List<Family> families = new ArrayList<>(); 

    @GetMapping("students")
    public String displayStudents(Model model) {
        studentAttr.clear();
        activeStudents.clear();
        grades.clear();
        classrooms.clear();
        families.clear();
        
        activeStudents = personService.getAllActiveStudents();
        grades = gradeService.getAllGrades();
        classrooms = classroomService.getAllClassrooms();
        families = familyService.getAllFamilies(true);
        studentAttr = compileAttr(activeStudents);
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", grades);
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("families", families);
        return "students";
    }  
    
    @GetMapping("studentsAZlastName")
    public String displayStudentsAZlastName(Model model) {
        studentAttr.clear();
        activeStudents.clear();
        grades.clear();
        classrooms.clear();
        families.clear();
         
        activeStudents = personService.getAllStudentsAZlastName();
        grades = gradeService.getAllGrades();
        classrooms = classroomService.getAllClassrooms();
        families = familyService.getAllFamilies(true);
        studentAttr = compileAttr(activeStudents);
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", grades);
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("families", families);
        return "students";
    }
    
    @GetMapping("studentsAZfirstName")
    public String displayStudentsAZfirstName(Model model) {
        studentAttr.clear();
        activeStudents.clear();
        grades.clear();
        classrooms.clear();
        families.clear();
        
        activeStudents = personService.getAllStudentsAZfirstName();        
        grades = gradeService.getAllGrades();
        classrooms = classroomService.getAllClassrooms();
        families = familyService.getAllFamilies(true);
        studentAttr = compileAttr(activeStudents);
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", grades);
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("families", families);
        return "students"; 
    }
    
    @GetMapping("studentsByGrade")
    public String displayStudentsByGrade(Model model) {
        studentAttr.clear();
        activeStudents.clear();
        grades.clear();
        classrooms.clear();
        families.clear();
        
        activeStudents = personService.getAllStudentsGrade();
        grades = gradeService.getAllGrades();
        classrooms = classroomService.getAllClassrooms();
        families = familyService.getAllFamilies(true);
        studentAttr = compileAttr(activeStudents);
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", grades);
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("families", families);
        return "students";
    }
    
    @GetMapping("studentsInactive")
    public String displayInactiveStudents(Model model) {
        studentAttr.clear();
        activeStudents.clear();
        grades.clear();
        classrooms.clear();
        families.clear();
        
        activeStudents = personService.getAllInactiveStudents();
        grades = gradeService.getAllGrades();
        classrooms = classroomService.getAllClassrooms();
        families = familyService.getAllFamilies(true);
        studentAttr = compileAttr(activeStudents);
        model.addAttribute("students", studentAttr);
        model.addAttribute("grades", grades);
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("families", families);
        return "students";
    }

    @GetMapping("editPerson")
    @ResponseBody
    public List<Object> editPerson(@RequestParam("id") long id) {
        Person person = personService.getPersonById(id);
        Grade grade = gradeService.getGradeByPersonId(id);
        Family family = familyService.getFamilyByStudentId(id);
        List<Object> objects = new ArrayList<>();
        objects.add(person);
        objects.add(grade);
        objects.add(family); 
        return objects;     
    }
    
    @GetMapping("studentDetails")
    @ResponseBody
    public List<Object> displayStudentDetails(@RequestParam("id") long id) {
        Person person = personService.getPersonById(id);
        Grade grade = gradeService.getGradeByPersonId(id);
        Classroom classroom = classroomService.getClassroomByGradeId(gradeService.getGradeByPersonId(id).getId());
        Family family = familyService.getFamilyByStudentId(id);
        List<Object> objects = new ArrayList<>();
        objects.add(person);
        objects.add(grade);
        objects.add(classroom);
        objects.add(family); 
        return objects;     
    }

    @PostMapping("savePerson")
    public String savePerson(Person person, @RequestParam("grade") Long id, @RequestParam("family") Long famId) {
        // If grade is 0 or below, ignore and leave empty.
        if(id > 0){
            Grade grade = gradeService.getGradeById(id);
            Grade oldGrade = gradeService.getGradeByPersonId(person.getId());
            // If old grade is null, then there was no previous assigned grade.
            if(oldGrade == null) {
                grade.addStudent(person);
                gradeService.saveGrade(grade);
                
            // If there was an assigned grade, delete it and add the new grade
            } else {
                List<Person> persons = oldGrade.getPersons();
                persons.removeIf(p -> p.getId() == person.getId());
                oldGrade.setPersons(persons);
                gradeService.saveGrade(oldGrade);
                grade.addStudent(person);
                gradeService.saveGrade(grade);           
            }
        }
        Family oldFamily = familyService.getFamilyByStudentId(person.getId());
        
        if(oldFamily != null) {
            if(famId > 0 && oldFamily.getId() != 0) {
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
        } else if(famId > 0) {
            Family family = familyService.getFamilyById(famId);
            family.addPerson(person);
            familyService.saveFamily(family);
        }  
        
        personService.addPerson(person); 
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
        personService.addPerson(person);
        return "redirect:/students";
    }

    @PostMapping("addStudent")
    public String addStudent(HttpServletRequest request, @RequestParam("grade") Long id, @RequestParam("family") Long famId) {
    
        Person student = new Person();
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setContact(request.getParameter("contact"));
        switch(request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                student.setActive(false);
            }
            default -> {
                student.setActive(true);
            }
        }
        personService.addPerson(student);
        
        Grade grade = gradeService.getGradeById(id);
        grade.addStudent(student);
        gradeService.saveGrade(grade);
        
        PersonType pt = personTypeService.getPersonTypeByName("Student");
        pt.addPersons(student);
        personTypeService.savePersonType(pt);
        
        if(famId > 0) {
            Family family = familyService.getFamilyById(famId);
            family.addPerson(student);
            familyService.saveFamily(family);
        }
        return "redirect:/students";
    }
    
    private List<Student> compileAttr(List<Person> peoples) {
        studentAttr.clear();
        for(Person s : peoples) {
            Grade g = gradeService.getGradeByPersonId(s.getId());
            Student stud = new Student(s.getId(), s.getFirstName(), s.getLastName(), s.getContact(), g.getGradeName(), 
                    classroomService.getClassroomByGradeId(g.getId()).getClassName());
            studentAttr.add(stud);
        }
        return studentAttr;
    }
    
    // --------------------------- TEACHERS -------------------------------//
    
    private List<Person> teachers = new ArrayList<>();
    
    @GetMapping("teachers")
    public String displayTeachers(Model model) {
        teachers.clear();
        families.clear();
        
        families = familyService.getAllFamilies(true);
        teachers = personService.getAllPeopleByType("Teacher", true);
        model.addAttribute("teachers", teachers);
        model.addAttribute("families", families);
        return "teachers";
    }
    
    @GetMapping("inactiveTeachers")
    public String displayInactiveTeachers(Model model) {
        teachers.clear();
        families.clear();
        
        families = familyService.getAllFamilies(true);
        teachers = personService.getAllPeopleByType("Teacher", false);
        model.addAttribute("teachers", teachers);
        model.addAttribute("families", families);
        return "teachers";
    }
    
    @GetMapping("getTeacher")
    @ResponseBody
    public List<Object> editTeacher(@RequestParam("id") long id) {
        Person person = personService.getPersonById(id);
        Family family = familyService.getFamilyByStudentId(id);
        List<Object> objects = new ArrayList<>();
        objects.add(person);
        objects.add(family); 
        return objects;     
    }
    
    @PostMapping("addTeacher")
    public String addTeacher(HttpServletRequest request, @RequestParam("family") Long famId) {
        
        Person teacher = new Person();
        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        teacher.setContact(request.getParameter("contact"));
        switch(request.getParameter("active").toLowerCase()) {
            case "inactive", "false", "no" -> {
                teacher.setActive(false);
            }
            default -> {
                teacher.setActive(true);
            }
        }
        personService.addPerson(teacher);
        
        PersonType pt = personTypeService.getPersonTypeByName("Teacher");
        pt.addPersons(teacher);
        personTypeService.savePersonType(pt);
        
        if(famId > 0) { 
            Family family = familyService.getFamilyById(famId);
            family.addPerson(teacher);
            familyService.saveFamily(family);
        }
        
        return "redirect:/teachers";  
    }
    
    @PostMapping("saveTeacher") 
    public String saveTeacher(Person person, @RequestParam("family") long id) {
        Family oldFamily = familyService.getFamilyByStudentId(person.getId());
        
        if(oldFamily != null) {
            if(id > 0 && oldFamily.getId() != 0) {
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
        } else if(id > 0){
            Family family = familyService.getFamilyById(id);
            family.addPerson(person);
            familyService.saveFamily(family);
        }  
        
        personService.addPerson(person); 
        return "redirect:/teachers";
    }
    
    @PostMapping("confirmDeactivateTeacher") 
    public String confirmDeactivateTeacher(HttpServletRequest request) {
        Person person = personService.getPersonById(Long.parseLong(request.getParameter("id")));
        person.setActive(false);
        personService.addPerson(person);
        return "redirect:/teachers";
    }

}
