package com.study.api_docs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.api_docs.dto.MessageBodyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTestControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void POST_GET_PUT_DELETE() {
        //POST
        given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(MessageBodyDTO.builder()
                        .description("등록하겠습니다.")
                        .build())
                .post("/api/test/post")
                .then()
                .statusCode(200);

        //GET
        given()
                .port(port)
                .get("/api/test/get/{id}", 1)
                .then()
                .body("id", equalTo(1))
                .body("description", equalTo("등록하겠습니다."));

        //PUT
        given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(MessageBodyDTO.builder()
                        .id(1L)
                        .description("수정하겠습니다.")
                        .build())
                .put("/api/test/put/{id}", 1)
                .then()
                .statusCode(200);

        given()
                .port(port)
                .get("/api/test/get/{id}", 1)
                .then()
                .body("id", equalTo(1))
                .body("description", equalTo("수정하겠습니다."));

        //DELETE
        given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .delete("/api/test/delete/{id}", 1)
                .then()
                .statusCode(200);

        given()
                .port(port)
                .get("/api/test/get/{id}", 1)
                .then()
                .body("id", equalTo(1))
                .body("description", nullValue());
    }

}
//https://github.com/rest-assured/rest-assured/wiki/Usage#default-values