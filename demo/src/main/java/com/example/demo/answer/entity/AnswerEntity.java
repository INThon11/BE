package com.example.demo.answer.entity;

import com.example.demo.member.entity.MemberEntity;
import com.example.demo.question.entity.QuestionEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long answerId;

    @JsonProperty
    @Column(name = "answer" ,nullable = false)
    private String answer;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private MemberEntity member;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId")
    private QuestionEntity question;
}
