package com.study.api_docs.controller;

import com.study.api_docs.dto.MessageBodyDTO;
import com.study.api_docs.service.IntegrationTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class IntegrationTestController {

    private final IntegrationTestService integrationTestService;

    @GetMapping("/api/test/get/{id}")
    public MessageBodyDTO get(@PathVariable Long id) {
        return integrationTestService.get(id);
    }

    @PostMapping("/api/test/post")
    public void post(@RequestBody MessageBodyDTO messageBodyDTO) {
        integrationTestService.post(messageBodyDTO.getDescription());
    }

    @PutMapping("/api/test/put/{id}")
    public void put(
            @PathVariable Long id,
            @RequestBody MessageBodyDTO messageBodyDTO) {
        integrationTestService.put(messageBodyDTO);
    }

    @DeleteMapping("/api/test/delete/{id}")
    public void delete(@PathVariable Long id) {
        integrationTestService.delete(id);
    }
}
