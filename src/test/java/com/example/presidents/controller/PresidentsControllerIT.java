package com.example.presidents.controller;

import com.example.presidents.model.dto.PresidentDto;
import com.example.presidents.util.TestUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.Instant;

import static com.example.presidents.util.TestUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PresidentsControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestForPresidentSave_thanCorrectResponse() throws Exception {
        //given
        var president = PresidentDto.builder().name("TestPresident").surname("SurnamePresidentTest")
                .politicalParty("Partytest")
                .termFrom(Timestamp.from(Instant.ofEpochMilli(150000000)))
                .termTo(Timestamp.from(Instant.ofEpochMilli(160000000))).build( );
        //when && then
        mockMvc.perform(post("/presidents/save").content(toJson(president))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("TestPresident")))
                .andExpect(jsonPath("$.surname", Matchers.equalTo("SurnamePresidentTest")));

    }

    @Test
    public void whenPostPresidentAndIncorrectName_thenIncorectResponse() throws Exception {
        //given
        var president = PresidentDto.builder().name("").build( );
        //when && then
        mockMvc.perform(post("/presidents/save").content(toJson(president))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsInAnyOrder("Name must be between 1 and 255 characters")));

    }
}
