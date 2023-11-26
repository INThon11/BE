package com.example.demo.answer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnswerDto {

    @JsonProperty
    private String answer;

}

