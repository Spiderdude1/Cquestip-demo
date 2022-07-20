package com.cquestip.cquestip.Controller;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Service.PortfolioService;
import com.cquestip.cquestip.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student/portfolio")
public class PortfolioController {

    private StudentService studentService;
    private PortfolioService portfolioService;


    @Autowired
    public PortfolioController(StudentService studentService, PortfolioService portfolioService) {
        this.studentService = studentService;
        this.portfolioService = portfolioService;
    }

    @GetMapping("{studentId}")
    public List<Education> getEducation(@PathVariable long studentId) {
        return portfolioService.getEducation(studentId);
    }

    @GetMapping("/test")
    public List<Education> getAllEducation() {
        return portfolioService.getAllEducation();
    }


    @PostMapping("{studentId}")
    public void addEducation(@PathVariable("studentId") long id, @RequestBody List<Education> education) {
        portfolioService.addEducation(id, education);
    }

    @DeleteMapping(path = "{studentId}/education/{educationId}")
    public void deleteEducation(@PathVariable("studentId") long studentid,
                                @PathVariable("educationId") long educationId) {
        portfolioService.deleteEducation(studentid, educationId);
    }

    @PutMapping(path = "{studentId}/education/{educationId}")
    public void updateEducation(
            @PathVariable("studentId") long studentId,
            @PathVariable("educationId") long educationId,
            @RequestParam(required = false) String instituteName,
            @RequestParam(required = false) Double gpa,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) String instituteLevel) {

        portfolioService.updateEducation(studentId, educationId, instituteName, gpa, date, instituteLevel);
    }
}
