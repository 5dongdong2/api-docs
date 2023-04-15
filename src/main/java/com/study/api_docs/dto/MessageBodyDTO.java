package com.study.api_docs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class MessageBodyDTO {
    private Long id;
    private String description;
}
