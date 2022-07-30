package com.cquestip.cquestip.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Domain.EducationDomain.Experience;
import com.cquestip.cquestip.Domain.UserDomain.Student;
import com.cquestip.cquestip.Repository.EducationRepository;
import com.cquestip.cquestip.Repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PortfolioService.class})
@ExtendWith(SpringExtension.class)
class PortfolioServiceTest {
    @MockBean
    private EducationRepository educationRepository;
    @Autowired
    private PortfolioService portfolioService;
    @MockBean
    private StudentRepository studentRepository;

    /**
     * Method under test: {@link PortfolioService#getEducation(long)}
     * testing to get the student's education information
     */
    @Test
    void testGetEducation() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        ArrayList<Education> educationList = new ArrayList<>();
        student.setEducations(educationList);
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        List<Education> actualEducation = portfolioService.getEducation(123L);
        assertSame(educationList, actualEducation);
        assertTrue(actualEducation.isEmpty());
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#getEducation(long)}
     * tests what happens when the student does not exist
     */
    @Test
    void testGetEducationThrowsIllegalStateException() {
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class,
                () -> portfolioService.getEducation(123L));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#getEducation(long)}
     * checking if the expected error is thrown when
     * a particular student does not exist
     */
    @Test
    void testGetEducationStudentDoesNotExist() {
        when(studentRepository.findById(any()))
                .thenThrow(new IllegalStateException("Student does not exist"));
        assertThrows(IllegalStateException.class,
                () -> portfolioService.getEducation(123L));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#addEducation(long, List)}
     * testing to add empty education
     */
    @Test
    void testAddEducationEmpty() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setDemographics(new ArrayList<>());
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setEducations(new ArrayList<>());
        student1.setEmail("jane.doe@example.org");
        student1.setFirst("First");
        student1.setLast("Last");
        student1.setMiddle("Middle");
        student1.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student1);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        //empty education
        portfolioService.addEducation(123L, new ArrayList<>());
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
        assertTrue(portfolioService.getAllEducation().isEmpty());
    }

    /**
     * Method under test: {@link PortfolioService#addEducation(long, List)}
     */
    @Test
    void testAddEducationStudentDoesNotExist() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.save(any())).thenThrow(new IllegalStateException("student does not exist"));
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        assertThrows(IllegalStateException.class, () -> portfolioService.addEducation(123L, new ArrayList<>()));
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#addEducation(long, List)}
     */
    @Test
    void testAddEducationCheckIllegalStateException() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> portfolioService.addEducation(123L, new ArrayList<>()));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#addEducation(long, List)}
     * student.getEducations().add(educationListIterator.next());
     */
    @Test
    void testAddEducationEducationInfoInstitute() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setDemographics(new ArrayList<>());
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setEducations(new ArrayList<>());
        student1.setEmail("jane.doe@example.org");
        student1.setFirst("First");
        student1.setLast("Last");
        student1.setMiddle("Middle");
        student1.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student1);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);

        Education education = new Education();
        education.setDatejoined(LocalDate.ofEpochDay(1L));
        education.setEducationid(1L);
        education.setExperiences(new ArrayList<>());
        education.setGpa(4.0);
        education.setInstitutelevel("CSSE");
        education.setInstitutename("UWB");

        ArrayList<Education> educationList = new ArrayList<>();
        educationList.add(education);
        portfolioService.addEducation(123L, educationList);
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#getAllEducation()}
     */
    @Test
    void testGetAllEducation() {
        ArrayList<Education> educationList = new ArrayList<>();
        when(educationRepository.findAll()).thenReturn(educationList);
        List<Education> actualAllEducation = portfolioService.getAllEducation();
        assertSame(educationList, actualAllEducation);
        assertTrue(actualAllEducation.isEmpty());
        verify(educationRepository).findAll();
    }

    /**
     * Method under test: {@link PortfolioService#getAllEducation()}
     */
    @Test
    void testGetAllEducationCheckForIllegalStateException() {
        when(educationRepository.findAll()).thenThrow(new IllegalStateException("none found"));
        assertThrows(IllegalStateException.class, () -> portfolioService.getAllEducation());
        verify(educationRepository).findAll();
    }

    /**
     * Method under test: {@link PortfolioService#updateEducation(long, long, String, Double, LocalDate, String)}
     */
    @Test
    void testUpdateEducation() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setDemographics(new ArrayList<>());
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setEducations(new ArrayList<>());
        student1.setEmail("jane.doe@example.org");
        student1.setFirst("First");
        student1.setLast("Last");
        student1.setMiddle("Middle");
        student1.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student1);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        portfolioService.updateEducation(123L, 123L, "Institute Name", 10.0d, LocalDate.ofEpochDay(1L), "Institute Level");
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
        assertTrue(portfolioService.getAllEducation().isEmpty());
    }

    /**
     * Method under test: {@link PortfolioService#updateEducation(long, long, String, Double, LocalDate, String)}
     * if(target != null && instituteName != null)
     */
    @Test
    void testUpdateEducationEducationInfoNotNull() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.save(any())).thenThrow(new IllegalStateException("foo"));
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        assertThrows(IllegalStateException.class,
                () -> portfolioService.updateEducation(
                        123L,
                        123L,
                        "Institute Name",
                        10.0d,
                        LocalDate.ofEpochDay(1L),
                        "Institute Level"));
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#updateEducation(long, long, String, Double, LocalDate, String)}
     */
    @Test
    void testUpdateEducationCreateEducationsAndUpdate() {
        //constructing new student education
        Education education = new Education();
        education.setDatejoined(LocalDate.ofEpochDay(1L));
        education.setEducationid(1L);
        education.setExperiences(new ArrayList<>());
        education.setGpa(4.0);
        education.setInstitutelevel("Institutelevel");
        education.setInstitutename("Institutename");

        ArrayList<Education> educationList = new ArrayList<>();
        //save the education info
        educationList.add(education);

        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        //add the student education info
        student.setEducations(educationList);
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        //optional of student with education info
        Optional<Student> optionalStudent = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setDemographics(new ArrayList<>());
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setEducations(new ArrayList<>());
        student1.setEmail("jane.doe@example.org");
        student1.setFirst("First");
        student1.setLast("Last");
        student1.setMiddle("Middle");
        student1.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student1);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        //update the student education
        portfolioService.updateEducation(
                123L,
                123L,
                "Institute Name",
                10.0d,
                LocalDate.ofEpochDay(1L),
                "Institute Level");
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
        assertTrue(portfolioService.getAllEducation().isEmpty());
    }

    /**
     * Method under test: {@link PortfolioService#updateEducation(long, long, String, Double, LocalDate, String)}
     */
    @Test
    @Disabled("TODO: Fix NullPointerException " +
            "Cannot invoke java.util.Optional.isPresent() " +
            "because studentOptional is null" +
            "at PortfolioService.updateEducation" +
            "(PortfolioService.java:73)")
    void testUpdateEducationFixNullPointer() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any())).thenReturn(null);
        portfolioService.updateEducation(
                123L,
                123L,
                "Institute Name",
                4.0, LocalDate.ofEpochDay(1L),
                "Institute Level");
    }

    /**
     * Method under test: {@link PortfolioService#updateEducation(long, long, String, Double, LocalDate, String)}
     */
    @Test
    void testUpdateEducationCheckIllegalStateException() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class,
                () -> portfolioService.updateEducation(
                        123L,
                        123L,
                        "Institute Name",
                        4.0,
                        LocalDate.ofEpochDay(1L),
                        "Institute Level"));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#deleteEducation(long, long)}
     * testing to delete student education
     */
    @Test
    void testDeleteEducationOptionalFindByID() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        portfolioService.deleteEducation(1L, 123L);
        verify(studentRepository).findById(any());
        assertTrue(portfolioService.getAllEducation().isEmpty());
    }

    /**
     * Method under test: {@link PortfolioService#deleteEducation(long, long)}
     * testing to delete student education
     */
    @Test
    void testDeleteEducationProvidedStudentIDEducationID() {
        Education education = new Education();
        education.setDatejoined(LocalDate.ofEpochDay(1L));
        education.setEducationid(1L);
        education.setExperiences(new ArrayList<>());
        education.setGpa(4.0);
        education.setInstitutelevel("Institutelevel");
        education.setInstitutename("Institutename");

        ArrayList<Education> educationList = new ArrayList<>();
        educationList.add(education);

        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(educationList);
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        portfolioService.deleteEducation(1L, 123L);
        verify(studentRepository).findById(any());
        assertTrue(portfolioService.getAllEducation().isEmpty());
    }

    /**
     * Method under test: {@link PortfolioService#deleteEducation(long, long)}
     */
    @Test
    @Disabled("TODO: Fix NullPointerException")
    void testDeleteEducationWhenNull() {
        when(studentRepository.findById(any())).thenReturn(null);
        portfolioService.deleteEducation(1L, 123L);
    }

    /**
     * Method under test: {@link PortfolioService#deleteEducation(long, long)}
     * tests deleting the student education when student is not present
     */
    @Test
    void testDeleteEducationStudentNotPresent() {
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class,
                () -> portfolioService.deleteEducation(1L, 123L));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#deleteEducation(long, long)}
     * the student does not exist, throws IllegalStateException
     */
    @Test
    void testDeleteEducationThrowIllegalStateException() {
        when(studentRepository.findById(any())).thenThrow(new IllegalStateException("Student does not exist"));
        assertThrows(IllegalStateException.class, () -> portfolioService.deleteEducation(1L, 123L));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#deleteEducation(long, long)}
     * testing to delete student education by educationID.
     */
    @Test
    void testDeleteEducationGrabEducationToDelete() {
        Education education = new Education();
        education.setDatejoined(LocalDate.ofEpochDay(1L));
        education.setEducationid(1L);
        education.setExperiences(new ArrayList<>());
        education.setGpa(4.0);
        education.setInstitutelevel("Institutelevel");
        education.setInstitutename("Institutename");

        ArrayList<Education> educationList = new ArrayList<>();
        educationList.add(education);

        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(educationList);
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setDemographics(new ArrayList<>());
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setEducations(new ArrayList<>());
        student1.setEmail("jane.doe@example.org");
        student1.setFirst("First");
        student1.setLast("Last");
        student1.setMiddle("Middle");
        student1.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student1);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        //delete the education
        portfolioService.deleteEducation(1L, 1L);
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#addExperience(long, List, long)}
     * testing to add empty experience
     */
    @Test
    void testAddExperienceEmptyExperience() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.save(any())).thenThrow(new IllegalStateException("foo"));
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        assertThrows(IllegalStateException.class,
                () -> portfolioService.addExperience(
                        123L,
                        new ArrayList<>(),
                        1L));
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#addExperience(long, List, long)}
     * testing to add student experience.
     * if(education.getEducationid() == educationid)
     */
    @Test
    void testAddExperienceByEducationID() {
        Education education = new Education();
        education.setDatejoined(LocalDate.ofEpochDay(1L));
        education.setEducationid(1L);
        education.setExperiences(new ArrayList<>());
        education.setGpa(4.0);
        education.setInstitutelevel("Institutelevel");
        education.setInstitutename("Institutename");

        ArrayList<Education> educationList = new ArrayList<>();
        educationList.add(education);

        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(educationList);
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setDemographics(new ArrayList<>());
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setEducations(new ArrayList<>());
        student1.setEmail("jane.doe@example.org");
        student1.setFirst("First");
        student1.setLast("Last");
        student1.setMiddle("Middle");
        student1.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student1);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        portfolioService.addExperience(123L, new ArrayList<>(), 1L);
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
        assertTrue(portfolioService.getAllEducation().isEmpty());
    }

    /**
     * Method under test: {@link PortfolioService#addExperience(long, List, long)}
     * testing to add experience when student is not present. throws
     * IllegalStateException
     */
    @Test
    void testAddExperienceStudentNotPresentStateException() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class,
                () -> portfolioService.addExperience(
                        123L,
                        new ArrayList<>(),
                        1L));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#updateExperience(long, long, Experience)}
     */
    @Test
    void testUpdateExperience() {
        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        assertThrows(IllegalStateException.class, () -> portfolioService.updateExperience(123L, 123L,
                new Experience(
                        1,
                        "Name",
                        "description of experience")));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#updateExperience(long, long, Experience)}
     */
    @Test
    void testUpdateExperienceDoesNotExist() {
        Education education = new Education();
        education.setDatejoined(LocalDate.ofEpochDay(1L));
        education.setEducationid(1L);
        education.setExperiences(new ArrayList<>());
        education.setGpa(4.0);
        education.setInstitutelevel("");
        education.setInstitutename("");

        ArrayList<Education> educationList = new ArrayList<>();
        educationList.add(education);

        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(educationList);
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        assertThrows(IllegalStateException.class, () -> portfolioService.updateExperience(123L, 123L,
                new Experience(1, "Name", "description")));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#updateExperience(long, long, Experience)}
     */
    @Test
    @Disabled("TODO: Fix NullPointerException")
    void testUpdateExperienceIsNull() {
        when(studentRepository.findById(any())).thenReturn(null);
        portfolioService.updateExperience(123L, 123L,
                new Experience(
                        1,
                        "Name",
                        "experience details"));
    }

    /**
     * Method under test: {@link PortfolioService#updateExperience(long, long, Experience)}
     * testing update experience but student is not present
     */
    @Test
    void testUpdateExperienceThrowsIllegalStateException() {
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> portfolioService.updateExperience(123L, 123L,
                new Experience(
                        1,
                        "Name",
                        "experience details")));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#updateExperience(long, long, Experience)}
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    @Disabled("TODO: fix NullPointerException")
    void testUpdateExperienceWithNullOptionalStudent() {

        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        portfolioService.updateExperience(123L, 123L, null);
    }

    /**
     * Method under test: {@link PortfolioService#updateExperience(long, long, Experience)}
     */
    @Test
    void testUpdateExperienceStudentDoesNotExist() {
        when(studentRepository.findById(any())).thenThrow(new IllegalStateException("Student does not exist"));
        assertThrows(IllegalStateException.class, () -> portfolioService.updateExperience(123L, 123L,
                new Experience(1, "Name", "description")));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link PortfolioService#updateExperience(long, long, Experience)}
     */
    @Test
    @Disabled("TODO: fix .NullPointerException: ")
    void testUpdateExperienceNewExperience() {

        Education education = new Education();
        education.setDatejoined(LocalDate.ofEpochDay(1L));
        education.setEducationid(1L);
        education.setExperiences(new ArrayList<>());
        education.setGpa(4.0);
        education.setInstitutelevel("The specified Education does not exist");
        education.setInstitutename("The specified Education does not exist");

        ArrayList<Education> educationList = new ArrayList<>();
        educationList.add(education);

        Student student = new Student();
        student.setAge(1);
        student.setDemographics(new ArrayList<>());
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(educationList);
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        portfolioService.updateExperience(123L, 1L,
                new Experience(1, "Name", "description"));
    }
}