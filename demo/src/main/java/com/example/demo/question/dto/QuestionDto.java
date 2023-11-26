package com.example.demo.question.dto;

import com.example.demo.answer.dto.AnswerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class QuestionDto {
        private Long questionId;
        private String question;
        private List<AnswerDto> answers;
}
