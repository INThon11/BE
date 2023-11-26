package com.example.demo.answer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class QuestionAnswerDto {
    private String memberName;
    private String answer;
    private Long answerId;
}
