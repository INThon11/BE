package com.example.demo.question.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class TodayResDto {
    private Long questionId;
    private String question;
}
