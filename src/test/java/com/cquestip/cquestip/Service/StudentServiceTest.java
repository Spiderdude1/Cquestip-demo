package com.cquestip.cquestip.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cquestip.cquestip.Domain.UserDomain.Demographic;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//now
@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    //mocking dependency injections
    @MockBean
    private EducationRepository educationRepository;
    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;


    /**
     * Method under test: {@link StudentService#addStudent(Student)}
     * method tests if we can add a student into the database.
     */
    @Test
    void testIfCanAddStudent() {
        //instantiate Student object
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

        Optional<Student> optionalStudent = Optional.of(student1);
        /*
         * when the student is saved into the database,
         * return the instantiated student
         */
        when(studentRepository.save(any())).thenReturn(student);
        /*
         * if we find the optional student by email in the database,
         * then we can return it
         * */
        when(studentRepository.getStudentByEmail(any()))
                .thenReturn(optionalStudent);

        Student student2 = new Student();
        student2.setAge(1);
        student2.setDemographics(new ArrayList<>());
        student2.setDob(LocalDate.ofEpochDay(1L));
        student2.setEducations(new ArrayList<>());
        student2.setEmail("jane.doe@example.org");
        student2.setFirst("First");
        student2.setLast("Last");
        student2.setMiddle("Middle");
        student2.setUserID(1L);

        assertThrows(IllegalStateException.class,
                () -> studentService.addStudent(student2));
        //making sure that we can verify the added student by email
        verify(studentRepository).getStudentByEmail(any());
    }

    /**
     * Method under test: {@link StudentService#addStudent(Student)}
     * method tests if the added student's properties
     * (like first name, last name...) matches the mocked student
     */
    @Test
    void testAddStudentAndVerifyStudentProperties() {
        Student student = new Student();
        student.setAge(1);
        ArrayList<Demographic> demographicList = new ArrayList<>();
        student.setDemographics(demographicList);
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setEducations(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setFirst("First");
        student.setLast("Last");
        student.setMiddle("Middle");
        student.setUserID(1L);
        //save the student into the database and return it
        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.getStudentByEmail(any()))
                .thenReturn(Optional.empty());

        Student student1 = new Student();
        student1.setAge(1);
        ArrayList<Demographic> demographicList1 = new ArrayList<>();
        student1.setDemographics(demographicList1);
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setEducations(new ArrayList<>());
        student1.setEmail("jane.doe@example.org");
        student1.setFirst("First");
        student1.setLast("Last");
        student1.setMiddle("Middle");
        student1.setUserID(1L);
        studentService.addStudent(student1);
        verify(studentRepository).save(any());
        verify(studentRepository).getStudentByEmail(any());
        //testing the student information
        assertEquals(1L, student1.getUserID().longValue());
        assertEquals("Middle", student1.getMiddle());
        assertEquals("Last", student1.getLast());
        assertEquals("First", student1.getFirst());
        assertEquals("jane.doe@example.org", student1.getEmail());
        assertEquals(demographicList1, student1.getEducations());
        assertEquals("1970-01-02", student1.getDob().toString());
        assertEquals(demographicList, student1.getDemographics());
        assertTrue(studentService.getStudents().isEmpty());
    }

    /**
     * Method under test: {@link StudentService#addStudent(Student)}
     * method tests what happens when the student is not present
     */
    @Test
    void testAddStudentThrowsIllegalStateException() {
        when(studentRepository.save(any()))
                .thenThrow(new IllegalStateException("no student available"));
        when(studentRepository.getStudentByEmail(any()))
                .thenThrow(new IllegalStateException("no email available"));
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
        assertThrows(IllegalStateException.class, () -> studentService.addStudent(student));
        verify(studentRepository).getStudentByEmail(any());
    }

    /**
     * Method under test: {@link StudentService#addDemographic(long, Demographic)}
     * method tests if the student's demographics can be added
     */
    @Test
    void testIfCanAddStudentDemographics() {
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
        //expected behavior when adding student
        when(studentRepository.save(any())).thenReturn(student1);
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        //add the student demographics
        studentService.addDemographic(
                123L,
                new Demographic(
                        1,
                        "Gender",
                        "Race",
                        true));
        verify(studentRepository).save(any());
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link StudentService#addDemographic(long, Demographic)}
     */
    @Test
    void testAddDemographicAlreadyExists() {
        ArrayList<Demographic> demographicList = new ArrayList<>();
        demographicList.add(new Demographic(
                1,
                "Gender",
                "Race",
                true));

        Student student = new Student();
        student.setAge(1);
        student.setDemographics(demographicList);
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
        //demographics already exists
        assertThrows(IllegalStateException.class,
                () -> studentService.addDemographic(
                        123L,
                        new Demographic(
                                1,
                                "Gender",
                                "Race",
                                true)));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link StudentService#addDemographic(long, Demographic)}
     * testing when the student demographics does not exist
     */
    @Test
    void testAddDemographicIsEmptyThrowsException() {
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
        //student does not exist
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class,
                () -> studentService.addDemographic(
                        123L,
                        new Demographic(
                                1,
                                "Gender",
                                "Race",
                                true)));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link StudentService#getStudents()}
     */
    @Test
    void testGetStudentsAllStudents() {
        ArrayList<Student> studentList = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> actualStudents = studentService.getStudents();
        assertSame(studentList, actualStudents);
        assertTrue(actualStudents.isEmpty());
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentService#getStudent(long)}
     */
    @Test
    void testGetStudentById() {
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
        //student retrieved
        assertSame(student, studentService.getStudent(123L));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link StudentService#getStudent(long)}
     * //test when there is no student found
     */
    @Test
    void testGetStudentNotFound() {
        //student not found
        when(studentRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> studentService.getStudent(123L));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link StudentService#getStudent(long)}
     * displays message when student not found
     */
    @Test
    void testGetStudentNotFoundDisplayMessage() {
        when(studentRepository.findById(any())).thenThrow(new IllegalStateException("Student does not exist"));
        assertThrows(IllegalStateException.class, () -> studentService.getStudent(123L));
        verify(studentRepository).findById(any());
    }


    /**
     * Method under test: {@link StudentService#updateStudent(long, String, String, String, LocalDate, String, List)}
     */
    @Test
    void testUpdateStudentIllegalStateExceptionEmpty() {
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        LocalDate dob = LocalDate.ofEpochDay(1L);
        assertThrows(IllegalStateException.class, () -> studentService.updateStudent(
                123L,
                "First",
                "Last",
                "Middle",
                dob,
                "jane.doe@example.org", new ArrayList<>()));
        verify(studentRepository).findById(any());
    }


    /**
     * Method under test: {@link StudentService#updateStudent(long, String, String, String, LocalDate, String, List)}
     */
    @Test
    void testUpdateStudentThrowsIllegalStateException() {
        when(studentRepository.findById(any()))
                .thenThrow(new IllegalStateException("Student with the stated ID does not exist"));
        LocalDate dob = LocalDate.ofEpochDay(1L);
        assertThrows(IllegalStateException.class,
                () -> studentService.updateStudent(
                        123L,
                        "First",
                        "Last",
                        "Middle",
                        dob,
                        "jane.doe@example.org", new ArrayList<>()));
        verify(studentRepository).findById(any());
    }

    /**
     * Method under test: {@link StudentService#updateStudent(long, String, String, String, LocalDate, String, List)}
     * testing updating the student information...their demographics
     */
    @Test
    @Disabled("TODO: Fix IndexOutOfBounds")
    void testIfCanUpdateStudentByDemographics() {
        //initial student
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
        LocalDate dob = LocalDate.ofEpochDay(1L);

        ArrayList<Demographic> demographicList = new ArrayList<>();
        demographicList.add(new Demographic(
                1,
                "Gender",
                "Race",
                true));
        //update student with new demographics'
        //error here
        studentService.updateStudent(
                123L,
                "First",
                "Last",
                "Middle",
                dob,
                "jane.doe@example.org",
                demographicList);
    }

    /**
     * Method under test: {@link StudentService#deleteStudent(long)}
     */
    @Test
    void testDeleteStudent() {
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
        doNothing().when(studentRepository).delete(any());
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        when(studentRepository.existsById(any())).thenReturn(true);
        studentService.deleteStudent(123L);
        verify(studentRepository).existsById(any());
        verify(studentRepository).findById(any());
        verify(studentRepository).delete(any());
        assertTrue(studentService.getStudents().isEmpty());
    }

    /**
     * Method under test: {@link StudentService#deleteStudent(long)}
     * test to delete student if present in the database
     */
    @Test

    void testDeleteStudentIfPresent() {
        doNothing().when(studentRepository).delete(any());
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        when(studentRepository.existsById(any())).thenReturn(true);
        //delete student if present
        studentService.deleteStudent(123L);
    }

    /**
     * Method under test: {@link StudentService#deleteStudent(long)}
     */
    @Test
    void testDeleteStudentCheckIllegalStateException() {
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
        doNothing().when(studentRepository).delete(any());
        when(studentRepository.findById(any())).thenReturn(optionalStudent);
        when(studentRepository.existsById(any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> studentService.deleteStudent(123L));
        verify(studentRepository).existsById(any());
    }

}