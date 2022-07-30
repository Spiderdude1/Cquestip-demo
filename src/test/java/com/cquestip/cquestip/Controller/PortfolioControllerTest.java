package com.cquestip.cquestip.Controller;

import com.cquestip.cquestip.Domain.EducationDomain.Experience;
import com.cquestip.cquestip.Service.PortfolioService;
import com.cquestip.cquestip.Service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PortfolioController.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest
class PortfolioControllerTest {
    @Autowired
    private PortfolioController portfolioController;

    @MockBean
    private PortfolioService portfolioService;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link PortfolioController#addEducation(long, List)}
     */
    @Test
    void testAddEducation() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult =
                MockMvcRequestBuilders.post("/{studentId}", 123L)
                        .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(portfolioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PortfolioController#addExperience(long, long, List)}
     */
    @Test
    void testAddExperience() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/{studentId}/education/{educationId}", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult =
                MockMvcBuilders.standaloneSetup(portfolioController)
                        .build()
                        .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PortfolioController#deleteEducation(long, long)}
     */
    @Test
    void testDeleteEducation() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.delete("/{studentId}/education/{educationId}",
                        123L, 123L);
        ResultActions actualPerformResult =
                MockMvcBuilders.standaloneSetup(portfolioController)
                        .build()
                        .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PortfolioController#getAllEducation()}
     */
    @Test
    void testGetAllEducation() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/test");
        ResultActions actualPerformResult =
                MockMvcBuilders.standaloneSetup(portfolioController)
                        .build()
                        .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PortfolioController#getEducation(long)}
     */
    @Test
    void testGetEducation() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{studentId}", 123L);
        ResultActions actualPerformResult =
                MockMvcBuilders.standaloneSetup(portfolioController)
                        .build()
                        .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PortfolioController#updateEducation(long, long, String, Double, LocalDate, String)}
     */
    @Test
    void testUpdateEducation() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{studentId}/education/{educationId}",
                123L, 123L);
        ResultActions actualPerformResult =
                MockMvcBuilders.standaloneSetup(portfolioController)
                        .build()
                        .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PortfolioController#updateExperience(long, long, Experience)}
     */
    @Test
    void testUpdateExperience() throws Exception {
        Experience experience = new Experience();
        experience.setDescription("experience details");
        experience.setExperienceid(1);
        experience.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(experience);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/{studentId}/experience/{educationId}", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(portfolioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

