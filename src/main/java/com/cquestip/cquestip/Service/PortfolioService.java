package com.cquestip.cquestip.Service;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Domain.EducationDomain.Experience;
import com.cquestip.cquestip.Domain.UserDomain.Student;
import com.cquestip.cquestip.Repository.EducationRepository;
import com.cquestip.cquestip.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class PortfolioService {

    private StudentRepository studentRepository;

    private EducationRepository educationRepository;

    @Autowired
    public PortfolioService(StudentRepository studentRepository, EducationRepository educationRepository){
        this.studentRepository = studentRepository;
        this.educationRepository = educationRepository;
    }
    public List<Education> getEducation(long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if(!studentOptional.isPresent())
        {
            throw new IllegalStateException("Student does not exist");
        }

        Student student = studentOptional.get();

        return student.getEducations();
    }

    public void addEducation(long id, List<Education> education) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if(!studentOptional.isPresent())
        {
            throw new IllegalStateException("Student does not exist");
        }

        Student student = studentOptional.get();
        ListIterator<Education> educationListIterator = education.listIterator();

        while(educationListIterator.hasNext())
        {
            student.getEducations().add(educationListIterator.next());
        }


        studentRepository.save(student);


    }

    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    public void updateEducation(long studentId, long educationId, String instituteName, Double gpa,
                                LocalDate date, String instituteLevel) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if(!studentOptional.isPresent()){
            throw new IllegalStateException("Student does not exist");
        }

        List<Education> educations = studentOptional.get().getEducations();
        Education target = null;
        for (Education education : educations) {
            if(education.getEducationid() == educationId) {

                target = education;
                break;
            }
        }


        if(target != null && instituteName != null){

            target.setInstitutename(instituteName);
        }

        if(target != null && gpa != null)
        {
            target.setGpa(gpa);
        }

        if(target != null && date != null)
        {
            target.setDatejoined(date);
        }

        if(target != null && instituteLevel != null)
        {
            target.setInstitutelevel(instituteLevel);
        }

        studentRepository.save(studentOptional.get());





    }

    public void deleteEducation(long studentid, long educationId) {
        Optional<Student> studentOptional = studentRepository.findById(studentid);

        if(!studentOptional.isPresent()){
            throw new IllegalStateException("Student does not exist");
        }

        List<Education> educations = studentOptional.get().getEducations();
        Education target = null;
        for (Education education : educations) {
            if(education.getEducationid() == educationId) {

                target = education;
                break;
            }
        }

        if (target != null) {
            educations.remove(target);
            studentRepository.save(studentOptional.get());

        }


    }

    public void addExperience(long id, List<Experience> experience, long educationid) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if(!studentOptional.isPresent()){
            throw new IllegalStateException("Student does not exist");
        }

        List<Education> educations = studentOptional.get().getEducations();
        Education target = null;
        for (Education education : educations) {
            if(education.getEducationid() == educationid) {
                target = education;
                break;
            }
        }



        if(target != null) {
            for (Experience ex : experience) {
                target.getExperiences().add(ex);
            }
        }



        studentRepository.save(studentOptional.get());

    }

    public void updateExperience(long id, long educationId, Experience experience) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        System.out.println(experience.getExperienceid());

        if(!studentOptional.isPresent()){
            throw new IllegalStateException("Student does not exist");
        }

        List<Education> educations = studentOptional.get().getEducations();
        Education target = null;
        for (Education education : educations) {
            if(education.getEducationid() == educationId) {
                target = education;
                break;
            }
        }

        if (target == null) {
            throw new IllegalStateException("The specified Education does not exist");
        }

        List<Experience> experiences = target.getExperiences();
        //System.out.println(experiences.size());
//        System.out.println(experiences.get(0).getExperienceid());

        Experience experienceTargeted = null;
        for(Experience experienceTarget : experiences) {
            if (experienceTarget.getExperienceid() == experience.getExperienceid()) {

                experienceTargeted = experienceTarget;
                break;
            }
            System.out.println(experienceTarget.getExperienceid());

        }

//        if (experienceTargeted == null) {
//            throw new IllegalStateException("The specified Experience does not exist");
//        }

        experienceTargeted.setName(experience.getName());
        experienceTargeted.setDescription(experience.getDescription());

        studentRepository.save(studentOptional.get());


    }
}
