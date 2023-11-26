package com.example.demo.question.entity;

import com.example.demo.family.entity.FamilyEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FamilyQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyQuestionId;

    @ManyToOne
    @JoinColumn(name = "familyId")
    private FamilyEntity family;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private QuestionEntity question;
}
