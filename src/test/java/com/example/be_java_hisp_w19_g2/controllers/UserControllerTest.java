package com.example.be_java_hisp_w19_g2.controllers;

import com.example.be_java_hisp_w19_g2.dtos.FollowerCountDTO;
import com.example.be_java_hisp_w19_g2.dtos.FollowersDTO;
import com.example.be_java_hisp_w19_g2.dtos.UserDTO;
import com.example.be_java_hisp_w19_g2.repositories.UserRepository;
import com.example.be_java_hisp_w19_g2.services.IUserService;
import com.example.be_java_hisp_w19_g2.services.UserService;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import utils.ObjectUtil;
import utils.UserDTOFactory;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getCountFollowers() throws Exception {
        ObjectWriter writer = ObjectUtil.writerUtil();

        // Definimos la respuesta que esperamos por el controller
        FollowerCountDTO expectedFollowers = UserDTOFactory.createFollowersCountWithDataDTO();
        // Pasamos el objeto de respuesta a texto plano
        String expectedBody = writer.writeValueAsString(expectedFollowers);

        // Expected
        ResultMatcher expectedStatus = MockMvcResultMatchers
                .status().isOk();
        ResultMatcher expectedJson = MockMvcResultMatchers
                .content().json(expectedBody);
        ResultMatcher expectedContentType = MockMvcResultMatchers
                .content().contentType(MediaType.APPLICATION_JSON);

        // Request
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                // Definimos el endpoint con sus argumentos o queries
                .get("/users/{userId}/followers/count", 6);

        // act & assert
        mockMvc
                .perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        expectedStatus,
                        expectedJson,
                        expectedContentType
                );
    }

    @Test
    void getFollowedList() throws Exception {
        ObjectWriter writer = ObjectUtil.writerUtil();

        // Definimos la respuesta que esperamos por el controller
        FollowersDTO expectedFollowers = UserDTOFactory.createFollowerDTO3();
        // Pasamos el objeto de respuesta a texto plano
        String expectedBody = writer.writeValueAsString(expectedFollowers);

        // Expected
        ResultMatcher expectedStatus = MockMvcResultMatchers
                .status().isOk();
        ResultMatcher expectedJson = MockMvcResultMatchers
                .content().json(expectedBody);
        ResultMatcher expectedContentType = MockMvcResultMatchers
                .content().contentType(MediaType.APPLICATION_JSON);

        // Request
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                // Definimos el endpoint con sus argumentos o queries
                .get("/users/{userId}/followed/list", 6);

        // act & assert
        mockMvc
                .perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        expectedStatus,
                        expectedJson,
                        expectedContentType
                );
    }

    @Test
    void getAscendingFollowedListByQuerieParam() throws Exception {
        ObjectWriter writer = ObjectUtil.writerUtil();

        // Definimos la respuesta que esperamos por el controller
        FollowersDTO expectedFollowers = UserDTOFactory.createFollowerDTO3();
        // Pasamos el objeto de respuesta a texto plano
        String expectedBody = writer.writeValueAsString(expectedFollowers);


        // Expected
        ResultMatcher expectedStatus = MockMvcResultMatchers
                .status().isOk();
        ResultMatcher expectedJson = MockMvcResultMatchers
                .content().json(expectedBody);
        ResultMatcher expectedContentType = MockMvcResultMatchers
                .content().contentType(MediaType.APPLICATION_JSON);

        // Request
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                // Definimos el endpoint con sus argumentos o queries
                .get("/users/{userId}/followed/list", 6)
                .param("order", "name_asc");

        // act & assert
        mockMvc
                .perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        expectedStatus,
                        expectedJson,
                        expectedContentType
                );
    }

    @Test
    void getFollowersList() throws Exception {
        ObjectWriter writer = ObjectUtil.writerUtil();

        // Definimos la respuesta que esperamos por el controller
        FollowersDTO expectedFollowers = UserDTOFactory.createFollowerDTO3();
        // Pasamos el objeto de respuesta a texto plano
        String expectedBody = writer.writeValueAsString(expectedFollowers);

        // Expected
        ResultMatcher expectedStatus = MockMvcResultMatchers
                .status().isOk();
        ResultMatcher expectedJson = MockMvcResultMatchers
                .content().json(expectedBody);
        ResultMatcher expectedContentType = MockMvcResultMatchers
                .content().contentType(MediaType.APPLICATION_JSON);

        // Request
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                // Definimos el endpoint con sus argumentos o queries
                .get("/users/{userId}/followed/list", 6);

        // act & assert
        mockMvc
                .perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        expectedStatus,
                        expectedJson,
                        expectedContentType
                );
    }

    @Test
    void followSeller() throws Exception {
        ObjectWriter writer = ObjectUtil.writerUtil();

        // Definimos la respuesta que esperamos por el controller
        UserDTO expectedFollowers = UserDTOFactory.createUserResponseFollowsSeller();
        // Pasamos el objeto de respuesta a texto plano
        String expectedBody = writer.writeValueAsString(expectedFollowers);

        // Expected
        ResultMatcher expectedStatus = MockMvcResultMatchers
                .status().isOk();
        ResultMatcher expectedJson = MockMvcResultMatchers
                .content().json(expectedBody);
        ResultMatcher expectedContentType = MockMvcResultMatchers
                .content().contentType(MediaType.APPLICATION_JSON);

        // Request
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                // Definimos el endpoint con sus argumentos o queries
                .post("/users/{userId}/follow/{userIdToFollow}", 1,10);

        // act & assert
        mockMvc
                .perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        expectedStatus,
                        expectedJson,
                        expectedContentType
                );
    }

    @Test
    void unfollow() throws Exception {
    }
}