package com.cquestip.cquestip.Controller;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Domain.EducationDomain.Experience;
import com.cquestip.cquestip.Service.PortfolioService;
import com.cquestip.cquestip.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @PostMapping("{studentId}/education/{educationId}")
    public void addExperience(@PathVariable("studentId") long id, @PathVariable("educationId") long educationId ,@RequestBody List<Experience> Experience) {
        portfolioService.addExperience(id, Experience, educationId);
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
