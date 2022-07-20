package com.cquestip.cquestip.Service;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Domain.UserDomain.Student;
import com.cquestip.cquestip.Repository.EducationRepository;
import com.cquestip.cquestip.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private StudentRepository studentRepository;

    private EducationRepository educationRepository;






    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.getStudentByEmail(student.getEmail());

        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("Student does not exist");
        }

        studentRepository.save(student);

    }

    @Transactional
    public void updateStudent(long id, String first, String last, String middle, LocalDate dob, String email) {
       Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException
                ("Student with the stated ID does not exist"));

       if (first != null)
       {
           student.setFirst(first);
       }

       if(last != null) {
           student.setLast(last);
       }
        if(middle != null) {
            student.setLast(middle);
        }
        if(dob != null) {
            student.setDob(dob);
        }
        if(email != null) {
            student.setEmail(email);
        }


    }

    public Student getStudent(long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if(!studentOptional.isPresent())
        {
            throw new IllegalStateException("Student does not exist");
        }

        return studentOptional.get();

    }


    public void deleteStudent(long id) {
        boolean existsById = studentRepository.existsById(id);

        if(!existsById)
        {
            throw new IllegalStateException("The student does not exist");
        }

        Optional<Student> byId = studentRepository.findById(id);


        studentRepository.delete(byId.get());

    }





}
