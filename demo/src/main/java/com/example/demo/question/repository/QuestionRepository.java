package com.example.demo.question.repository;

import com.example.demo.question.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    Optional<QuestionEntity> findQuestionEntityByQuestionId(Long questionId);
    List<QuestionEntity> findByQuestionContaining(String keyword);

    List<QuestionEntity> findAll();

    QuestionEntity findByQuestionId(Long questionId);

    List<QuestionEntity> findByQuestionIdIn(List<Long> receivedQuestionIds);


}
