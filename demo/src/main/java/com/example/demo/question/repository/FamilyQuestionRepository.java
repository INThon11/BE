package com.example.demo.question.repository;

import com.example.demo.question.entity.FamilyQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FamilyQuestionRepository extends JpaRepository<FamilyQuestionEntity,Long> {
    List<FamilyQuestionEntity> findByFamilyFamilyId(Long familyId);
}
