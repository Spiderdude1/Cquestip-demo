package com.cquestip.cquestip.Domain.UserDomain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class StudentTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   constructor
     *   <li>{@link Student#Student(String, String, String, String, LocalDate)}
     *   <li>{@link Student#toString()}
     *   <li>{@link Student#getEducations()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Student actualStudent = new Student(
                "First",
                "Middle",
                "Last",
                "jane.doe@example.org",
                LocalDate.ofEpochDay(1L));
        String actualToStringResult = actualStudent.toString();
        //no education provided
        assertNull(actualStudent.getEducations());

        assertEquals("Student{UserID=null, First='First', Middle='Middle', Last='Last', email='jane.doe@example.org',"
                + " dob=1970-01-02, age=null}", actualToStringResult);
    }

    /**
     * Method under test: {@link Student#getAge()}
     */
    @Test
    @Disabled("TODO: fix NullPointerException ")
    void testGetAge() {

        (new Student()).getAge();
    }

    /**
     * Method under test: {@link Student#getAge()}
     */
    @Test
    @Disabled("TODO: Complete test")
    void testGetAge2() {
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
        student.getAge();
    }
}

