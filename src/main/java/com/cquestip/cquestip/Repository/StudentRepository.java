package com.cquestip.cquestip.Repository;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Domain.UserDomain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
@Transactional(readOnly = true)
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> getStudentByEmail(String email);

    Optional<Student> findByEmail(String email);
}
