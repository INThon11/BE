package com.example.demo.family.entity;

import com.example.demo.member.entity.MemberEntity;
import com.example.demo.question.entity.FamilyQuestionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @Column(name = "familyName" ,nullable = false)
    private String familyName;

    @Column(name = "count", nullable = false)
    private Long count;

    @OneToMany(mappedBy = "family", orphanRemoval = true)
    @Builder.Default
    private List<MemberEntity> members = new ArrayList<MemberEntity>();

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<FamilyQuestionEntity> receivedQuestions = new ArrayList<>();

}
