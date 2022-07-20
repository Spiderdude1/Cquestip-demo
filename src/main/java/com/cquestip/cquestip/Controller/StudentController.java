package com.cquestip.cquestip.Controller;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Domain.UserDomain.Student;
import com.cquestip.cquestip.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping()
    public List<Student> getAllStudents()
    {
        return studentService.getStudents();
    }

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") long id) {
        return studentService.getStudent(id);
    }


    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") long id,
                              @RequestParam(required = false) String first,
                              @RequestParam(required = false) String last,
                              @RequestParam(required = false) String middle,
                              @RequestParam(required = false) LocalDate dob,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(id, first, last, middle, dob, email);
    }

    @PostMapping()
    public void addStudent(@RequestBody Student student)
    {
        studentService.addStudent(student);
    }


    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") long id)
    {
        studentService.deleteStudent(id);
    }

}
