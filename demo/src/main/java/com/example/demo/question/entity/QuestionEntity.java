package com.example.demo.question.entity;

import com.example.demo.answer.entity.AnswerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(name = "question" ,nullable = false)
    private String question;

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    @Builder.Default
    private List<AnswerEntity> answers = new ArrayList<AnswerEntity>();

}
