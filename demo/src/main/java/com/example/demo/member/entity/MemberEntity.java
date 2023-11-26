package com.example.demo.member.entity;

import com.example.demo.family.entity.FamilyEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "memberName", nullable = false)
    private String memberName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familyId")
    private FamilyEntity family;
}
