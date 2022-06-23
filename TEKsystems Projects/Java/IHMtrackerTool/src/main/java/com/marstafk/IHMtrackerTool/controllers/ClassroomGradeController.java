package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.ClassGrade;
import com.marstafk.IHMtrackerTool.models.Classroom;
import com.marstafk.IHMtrackerTool.models.Grade;
import com.marstafk.IHMtrackerTool.models.Person;
import com.marstafk.IHMtrackerTool.service.ClassroomService;
import com.marstafk.IHMtrackerTool.service.GradeService;
import com.marstafk.IHMtrackerTool.service.PersonService;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ClassroomGradeController {

    @Autowired
    GradeService gradeService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    PersonService personService;

    private Set<ConstraintViolation<Classroom>> violations = new HashSet<>();

    @GetMapping("classrooms")
    public String displayClassrooms(Model model) throws ObjectNotFoundException {
        List<Grade> grades = new ArrayList<>();
        for (Grade g : gradeService.getAllGrades()) {
            if (classroomService.getClassroomByGradeId(g.getId()) == null) {
                grades.add(g);
            }
        }
        model.addAttribute("classes", bindGradeClassroom(classroomService.getAllClassrooms(true)));
        model.addAttribute("grades", grades);
        model.addAttribute("errors", violations);
        return "classrooms";
    }

    @GetMapping("getStudentsInGrade")
    @ResponseBody
    public List<Person> getStudentsInGrade(@RequestParam("id") long id) {
        return personService.getAllPersonsByClassroomId(id);
    }

    @GetMapping("getClassroom")
    @ResponseBody
    public List<List<Object>> getClassroom(@RequestParam("id") long id) throws ObjectNotFoundException {
        List<Object> classrooms = new ArrayList<>();
        List<Object> grades = new ArrayList<>();
        List<Object> availGrades = new ArrayList<>();
        // Get selected grades
        for (Grade g : gradeService.getAllGradesByClassroomId(id)) {
            grades.add(g);
        }
        List<List<Object>> gradeClassrooms = new ArrayList<>();
        // Get classroom
        classrooms.add(classroomService.getClassroomById(id));
        // Get available grades
        for (Grade g : gradeService.getAllGrades()) {
            if (classroomService.getClassroomByGradeId(g.getId()) == null) {
                availGrades.add(g);
            }
        }
        for (Object g : grades) {
            availGrades.add(g);
        }
        gradeClassrooms.add(classrooms);
        gradeClassrooms.add(grades);
        gradeClassrooms.add(availGrades);
        return gradeClassrooms;
    }

    @PostMapping("addClassroom")
    public String addClassroom(HttpServletRequest request) {
        violations.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Classroom classroom = new Classroom();
        classroom.setClassName(request.getParameter("className"));
        classroom.setActive(true);
        String[] ids;
        List<Grade> grades = new ArrayList<>();
        try {
            ids = request.getParameterValues("gradeId");
            for (String s : ids) {
                grades.add(gradeService.getGradeById(Long.parseLong(s)));
            }
        } catch (ObjectNotFoundException e) {
        }
        classroom.setGrades(grades);

        violations = validator.validate(classroom);

        if (violations.isEmpty()) {
            classroomService.saveClassroom(classroom);
        }
        return "redirect:/classrooms";
    }

    @PostMapping("editClassroom")
    public String editClassroom(Classroom classroom, HttpServletRequest request) {
        violations.clear();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        String[] ids;
        List<Grade> grades = new ArrayList<>();
        try {
            ids = request.getParameterValues("gradeId");
            for (String s : ids) {
                grades.add(gradeService.getGradeById(Long.parseLong(s)));
            }
        } catch (ObjectNotFoundException e) {
        }
        classroom.setGrades(grades);

        violations = validator.validate(classroom);

        if (!violations.isEmpty()) {
            return "redirect:/classrooms";
        }
        classroomService.saveClassroom(classroom);
        return "redirect:/classrooms";
    }

    @PostMapping("confirmDeactivateClassroom")
    public String confirmDeactivateClassroom(HttpServletRequest request) throws ObjectNotFoundException {
        Classroom classroom = classroomService.getClassroomById(Long.parseLong(request.getParameter("id")));
        List<Grade> empty = new ArrayList<>();
        classroom.setGrades(empty);
        classroomService.saveClassroom(classroom);
        classroomService.deleteClassroom(classroom.getId());
        return "redirect:/classrooms";
    }

    private List<ClassGrade> bindGradeClassroom(List<Classroom> classrooms) {
        List<ClassGrade> classGrades = new ArrayList<>();
        for (Classroom c : classrooms) {
            String grades = "";
            for (Grade g : gradeService.getAllGradesByClassroomId(c.getId())) {
                grades += g.getGradeName() + " ";
            }
            classGrades.add(new ClassGrade(c.getId(), grades, c.getClassName()));
        }
        return classGrades;
    }

}
