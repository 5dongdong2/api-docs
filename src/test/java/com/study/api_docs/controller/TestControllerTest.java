package com.study.api_docs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.api_docs.dto.MessageBodyDTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTest() throws Exception {
        final Long id = 1L;
        final String queryString = "queryString";
        final MessageBodyDTO expectedResponseBody = MessageBodyDTO.builder()
                .description("id: " + id + " / queryString: " + queryString)
                .build();

        this.mockMvc
                .perform(get(StringUtils.replace("/api/get/{id}", "{id}", id.toString()))
                        .param("queryString", queryString))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponseBody)));
    }

    @Test
    void putTest() throws Exception {
        final Long id = 1L;
        final String queryString = "queryString";
        final MessageBodyDTO requestBody = MessageBodyDTO.builder()
                .id(id)
                .description("id: " + id + " / queryString: " + queryString)
                .build();

        this.mockMvc
                .perform(put(StringUtils.replace("/api/post/{id}", "{id}", id.toString()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(id.toString()));
    }

}

//1. MockMvcRequestBuilders: http method 있는 class
//2. MockMvcResultMatchers: http response