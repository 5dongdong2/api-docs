package com.study.api_docs.controller;

import com.study.api_docs.dto.MessageBodyDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/api/get/{id}")
    public MessageBodyDTO get(@PathVariable Long id, @RequestParam String queryString) {
        return MessageBodyDTO.builder()
                .id(id)
                .description("id: " + id + " / queryString: " + queryString)
                .build();
    }

    @PutMapping("/api/post/{id}")
    public Long post(@PathVariable Long id, @RequestBody MessageBodyDTO messageBody) {
        return id;
    }
}