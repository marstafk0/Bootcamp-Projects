/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.perscholas.marstafk.sms.jpa.mainrunner;

import com.perscholas.marstafk.sms.jpa.entitymodels.Course;
import com.perscholas.marstafk.sms.jpa.entitymodels.Student;
import com.perscholas.marstafk.sms.jpa.service.CourseService;
import com.perscholas.marstafk.sms.jpa.service.StudentService;
import static java.lang.System.out;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author boss_
 */
public class SMSRunner {
    
    //Went over this project with Kevin, and error pops up while running but doesn't affect the performance of the program or any data.

    private Scanner sin;
    private StringBuilder sb;
    private CourseService courseService = new CourseService();
    private StudentService studentService = new StudentService();
    private Student currentStudent;

    public SMSRunner() {
        sin = new Scanner(System.in);
        sb = new StringBuilder();
        courseService = new CourseService();
        studentService = new StudentService();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        SMSRunner sms = new SMSRunner();
        sms.run();
        
    }
    //run program
    private void run() {
        // Login or quit
        switch (menu1()) {
            case 1 -> {
                if (studentLogin()) {
                    registerMenu();
                }
            }
            case 2 ->
                out.println("Goodbye!");

            default -> {
            }

        }
        studentService.closeSesh();
        courseService.closeSesh();
    }

    //Main Menu
    private int menu1() {
        sb.append("\n1. Student Login\n2. Quit Application\nPlease Enter Selection: ");
        out.print(sb.toString());
        sb.delete(0, sb.length());
        int selection = 0;
        try {
            selection = sin.nextInt();
        } catch (Exception e) {
            System.out.println("Incorrect input. Goodbye");
        }
        return selection;
    }

    //Login page
    private boolean studentLogin() {
        out.print("Enter your email address: ");
        String email = sin.next();
        out.print("Enter your password: ");
        String password = sin.next();

        if (studentService.validateStudent(email, password)) {
            currentStudent = studentService.getStudentByEmail(email);

            List<Course> courses = studentService.getStudentCourses(email);
            out.println("\nMy Classes");
            for (Course course : courses) {
                out.println(course.getcId() + " " + course.getcName() + " " + course.getcInstructorName());
            }
            return true;
        }
        System.out.println("Could not validate student. Goodbye.");
        return false;
    }

    //Register for a course
    private void registerMenu() {
        sb.append("\n1. Register a class\n2. Logout\nPlease Enter Selection:\n ");
        out.print(sb.toString());
        sb.delete(0, sb.length());
        int select = 0;
        try {
            select = sin.nextInt();
        } catch (Exception e) {
            System.out.println("Incorrect input. Goodbye");
        }

        switch (select) {
            case 1:
                List<Course> allCourses = courseService.getAllCourses();
                List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
                allCourses.removeAll(studentCourses);
                out.printf("%-5s %-30S %-30s\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    out.printf("%-5s %-30s %-30s\n", course.getcId(), course.getcName(), course.getcInstructorName());
                }
                out.println();
                out.print("Enter Course Number: ");
                int number = 0;

                try {
                    number = sin.nextInt();
                } catch (Exception e) {
                    System.out.println("Incorrect input. Goodbye");
                }

                Course newCourse = courseService.getCourseById(number);

                if (newCourse != null) {
                    studentService.registerStudentToCourse(currentStudent.getsEmail(), newCourse.getcId());

                    studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());

                    out.println("\nMy Classes");
                    for (Course course : studentCourses) {
                        out.println(course.getcId() + " " + course.getcName() + " " + course.getcInstructorName());
                    }
                }
                break;
            case 2:
            default:
                out.println("Goodbye!");
        }
    }
}
