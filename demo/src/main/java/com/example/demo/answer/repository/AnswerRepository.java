package com.example.demo.answer.repository;

import com.example.demo.answer.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {
    Optional<AnswerEntity> findByQuestionQuestionIdAndMemberMemberId(Long questionId, Long memberId);
    List<AnswerEntity> findAll();
    List<AnswerEntity> findAnswersByQuestionQuestionId(Long questionId);
}
