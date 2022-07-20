package com.cquestip.cquestip.Config;

import com.cquestip.cquestip.Domain.UserDomain.Student;
import com.cquestip.cquestip.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student Zaki = new Student("Zaki", "A", "Shaik", "zakiahmed1422@live.com", LocalDate.of(2002, Month.APRIL, 22));


            studentRepository.save(Zaki);
        };


    };
}
