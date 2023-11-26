package com.example.demo.answer.service;

import com.example.demo.answer.dto.*;
import com.example.demo.answer.entity.AnswerEntity;
import com.example.demo.answer.repository.AnswerRepository;
import com.example.demo.family.entity.FamilyEntity;
import com.example.demo.member.entity.MemberEntity;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.question.entity.QuestionEntity;
import com.example.demo.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, MemberRepository memberRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
    }
    // CREATE
    public AnswerResDto saveAnswer(Long questionId, Long memberId, AnswerDto answer) {

        Optional<QuestionEntity> questionOptional = questionRepository.findQuestionEntityByQuestionId(questionId);
        QuestionEntity questionEntity = questionOptional.get();

        Optional<MemberEntity> memberOptional = memberRepository.findMemberEntityByMemberId(memberId);
        MemberEntity memberEntity = memberOptional.get();

        AnswerEntity answerEntity = AnswerEntity.builder()
                .answer(answer.getAnswer())
                .question(questionEntity)
                .member(memberEntity)
                .build();
        answerRepository.save(answerEntity);

        return new AnswerResDto(answerEntity.getAnswerId(),answerEntity.getAnswer());
    }

    // READ
    public List<AnswerResDto> getAllAnswers() {
        List<AnswerEntity> answers = answerRepository.findAll();

        return answers.stream().map(answer -> {
            return new AnswerResDto(
                    answer.getAnswerId(),
                    answer.getAnswer()
            );
        }).collect(Collectors.toList());
    }

    public Optional<AnswerEntity> getAnswerById(Long answerId) {
        return answerRepository.findById(answerId);
    }

    public List<QuestionAnswerDto> getAnswersByQuestionId(Long familyId, Long questionId) {
        List<AnswerEntity> result = answerRepository.findAnswersByQuestionQuestionId(questionId);

        return result.stream()
                .filter(answerEntity -> isMemberInFamily(answerEntity, familyId))
                .map(this::mapToQuestionAnswerDto)
                .collect(Collectors.toList());
    }

    private boolean isMemberInFamily(AnswerEntity answerEntity, Long familyId) {
        // Assuming AnswerEntity has a relationship with MemberEntity
        MemberEntity member = answerEntity.getMember();

        // Assuming MemberEntity has a relationship with FamilyEntity
        FamilyEntity family = member.getFamily();

        // Check if the familyId of the member matches the given familyId
        return family != null && family.getFamilyId().equals(familyId);
    }

    private QuestionAnswerDto mapToQuestionAnswerDto(AnswerEntity answerEntity) {
        String memberName = answerEntity.getMember().getMemberName();
        String answer = answerEntity.getAnswer();
        Long answerId = answerEntity.getAnswerId();

        return new QuestionAnswerDto(memberName, answer, answerId);
    }

    public List<AnswerDto> getAnswerByQuestionAndMember(Long questionId, Long memberId){
        Optional<AnswerEntity> optionalAnswerEntity= answerRepository.findByQuestionQuestionIdAndMemberMemberId(questionId, memberId);

        List<AnswerDto> answerList = optionalAnswerEntity
                .map(answerEntity -> Collections.singletonList(new AnswerDto(answerEntity.getAnswer())))
                .orElse(Collections.emptyList());

        return answerList;
    }

    // UPDATE
    public AnswerUpdateResDto updateAnswer(Long answerId, AnswerUpdateReqDto updatedAnswer) {
        Optional<AnswerEntity> existingAnswerOptional = answerRepository.findById(answerId);
        if (existingAnswerOptional.isPresent()) {
            AnswerEntity existingAnswer = existingAnswerOptional.get();
            existingAnswer.setAnswer(updatedAnswer.getNewAnswer());
            // You can update other fields as needed
            answerRepository.save(existingAnswer);

            return new AnswerUpdateResDto(existingAnswer.getAnswerId(), existingAnswer.getAnswer());
        } else {
            throw new RuntimeException("Answer not found with id: " + answerId);
        }
    }

    // DELETE
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}
