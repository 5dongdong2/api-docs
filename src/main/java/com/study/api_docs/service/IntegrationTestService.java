package com.study.api_docs.service;

import com.study.api_docs.dto.MessageBodyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class IntegrationTestService {

    private final Map<Long, String> db = new ConcurrentHashMap<>();

    private Long sequence = 1L;

    public MessageBodyDTO get(Long id) {
        log.info("get method id={}", id);
        return MessageBodyDTO.builder()
                .id(id)
                .description(db.get(id))
                .build();
    }

    public void post(String description) {
        log.info("post method description={}", description);
        db.put(sequence++, description);
    }

    public void put(MessageBodyDTO messageBodyDTO) {
        log.info("put method id={}, description={}", messageBodyDTO.getId(), messageBodyDTO.getDescription());
        db.put(messageBodyDTO.getId(), messageBodyDTO.getDescription());
    }

    public void delete(Long id) {
        log.info("delete method id={}", id);
        db.remove(id);
    }
}
