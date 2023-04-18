package com.study.api_docs.controller;

import com.study.api_docs.dto.MessageBodyDTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTestControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void POST_GET_PUT_DELETE() {
        Long id = 1L;
        MessageBodyDTO requestBody = MessageBodyDTO.builder()
                .description("등록합니다.")
                .build();

        //POST
        restTemplate.postForObject("http://localhost:" + port + "/api/test/post", requestBody, Void.class);

        //GET
        ResponseEntity<MessageBodyDTO> responseAfterCreate = restTemplate.getForEntity(
                StringUtils.replace("http://localhost:" + port + "/api/test/get/{id}", "{id}", id.toString()), MessageBodyDTO.class);

        assertThat(responseAfterCreate.getBody().getId()).isEqualTo(id);
        assertThat(responseAfterCreate.getBody().getDescription()).isEqualTo("등록합니다.");

        //PUT
        MessageBodyDTO requestBodyAfterModifying = MessageBodyDTO.builder()
                .id(id)
                .description("수정합니다.")
                .build();
        restTemplate.put(StringUtils.replace("http://localhost:" + port + "/api/test/put/{id}", "{id}", id.toString()), requestBodyAfterModifying);

        ResponseEntity<MessageBodyDTO> responseAfterModifying = restTemplate.getForEntity(
                StringUtils.replace("http://localhost:" + port + "/api/test/get/{id}", "{id}", id.toString()), MessageBodyDTO.class);

        assertThat(responseAfterModifying.getBody().getId()).isEqualTo(id);
        assertThat(responseAfterModifying.getBody().getDescription()).isEqualTo("수정합니다.");

        //DELETE
        restTemplate.delete(StringUtils.replace("http://localhost:" + port + "/api/test/delete/{id}", "{id}", id.toString()));

        ResponseEntity<MessageBodyDTO> responseAfterDelete = restTemplate.getForEntity(
                StringUtils.replace("http://localhost:" + port + "/api/test/get/{id}", "{id}", id.toString()), MessageBodyDTO.class);

        assertThat(responseAfterDelete.getBody().getDescription()).isNull();
    }
}
//https://github.com/spring-projects/spring-restdocs/issues/426
//spring rest docs랑 같이 쓰고싶은데.. 개고생하기 싫으면 rest-assured 써라
//https://yjksw.github.io/spring-boot-testresttemplate/