package com.cquestip.cquestip.Repository;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Domain.UserDomain.Demographic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EducationRepository extends JpaRepository<Education, Long> {


}
