package com.cquestip.cquestip.Domain.UserDomain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DemographicTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Demographic#Demographic(int, String, String, boolean)}
     *   <li>{@link Demographic#getDisability()}
     * </ul>
     */
    @Test
    void testConstructorAndGetDisability() {
        assertTrue((new Demographic(
                1,
                "Gender",
                "Race",
                true)).getDisability());
    }
}

