package com.cquestip.cquestip.Domain.EducationDomain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EducationTest {
    /**
     * Method under test: {@link Education#Education(String, Double, LocalDate, String)}
     */
    @Test
    void testConstructor() {
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        Education actualEducation = new Education(
                "Institutename",
                4.0,
                ofEpochDayResult,
                "Institute Level");

        LocalDate dateJoined = actualEducation.getDatejoined();
        assertSame(ofEpochDayResult, dateJoined);
        assertEquals("1970-01-02", dateJoined.toString());
        assertEquals("Institutename", actualEducation.getInstitutename());
        assertEquals("Institute Level", actualEducation.getInstitutelevel());
        assertEquals(4.0, actualEducation.getGpa().doubleValue());
        assertTrue(actualEducation.getExperiences().isEmpty());
        assertEquals(0L, actualEducation.getEducationid());
    }
}

