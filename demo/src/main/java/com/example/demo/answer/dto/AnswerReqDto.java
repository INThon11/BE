package com.example.demo.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class AnswerReqDto {
    private Long answerId;
    private String answer;
}
