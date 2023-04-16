package com.study.api_docs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.api_docs.dto.MessageBodyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TestController.class)
@AutoConfigureRestDocs
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint());
    }

    private OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
                modifyUris()
                        .scheme("https")
                        .host("docs.api.com")
                        .removePort(),
                prettyPrint());
    }

    @Test
    void 조회() throws Exception {
        final Long id = 1L;
        final String queryString = "queryString";
        final MessageBodyDTO expectedResponseBody = MessageBodyDTO.builder()
                .id(id)
                .description("id: " + id + " / queryString: " + queryString)
                .build();

        this.mockMvc
                .perform(get("/api/get/{id}", id.toString())
                        .param("queryString", queryString))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponseBody)))
                .andDo(document("조회", getDocumentRequest(), getDocumentResponse(),
                        pathParameters(parameterWithName("id").description("아이디")),
                        queryParameters(parameterWithName("queryString").description("queryString")),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("id + description")
                        )
                ));
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
                .perform(put("/api/put/{id}", id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().string(id.toString()))
                .andDo(document("put", getDocumentRequest(), getDocumentResponse(),
                        pathParameters(parameterWithName("id").description("아이디")),
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("id + description")
                        )
                ));
    }

}

//1-1. MockMvcRequestBuilders: http method 있는 class
//1-2. RestDocumentationRequestBuilders: spring rest docs 사용 시 http method 있는 class(PathVariable 사용 시)
//2. MockMvcResultMatchers: http response