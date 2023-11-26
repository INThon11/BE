package com.example.demo.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class AnswerUpdateResDto {
    private Long answerId;
    private String newAnswer;
}
