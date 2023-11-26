package com.example.demo.question.service;

import com.example.demo.family.entity.FamilyEntity;
import com.example.demo.family.repository.FamilyRepository;
import com.example.demo.question.dto.KeywordResDto;
import com.example.demo.question.dto.QuestionResDto;
import com.example.demo.question.dto.TodayResDto;
import com.example.demo.question.entity.FamilyQuestionEntity;
import com.example.demo.question.entity.QuestionEntity;
import com.example.demo.question.repository.FamilyQuestionRepository;
import com.example.demo.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final FamilyQuestionRepository familyQuestionRepository;
    private final FamilyRepository familyRepository;
    public QuestionService(QuestionRepository questionRepository, FamilyQuestionRepository familyQuestionRepository, FamilyRepository familyRepository) {
        this.questionRepository = questionRepository;
        this.familyQuestionRepository = familyQuestionRepository;
        this.familyRepository = familyRepository;
    }

    // CREATE
    public QuestionResDto saveQuestion(QuestionResDto question) {
        QuestionEntity questionEntity = QuestionEntity.builder()
                .question(question.getQuestion())
                .build();
        questionRepository.save(questionEntity);

        return new QuestionResDto(question.getQuestion());

    }

    // READ
    public List<TodayResDto> getAllQuestions(Long familyId) {

        List<FamilyQuestionEntity> receivedQuestions = familyQuestionRepository.findByFamilyFamilyId(familyId);
        System.out.println(receivedQuestions);

        List<Long> receivedQuestionIds = receivedQuestions.stream()
                .map(familyQuestionEntity -> familyQuestionEntity.getQuestion().getQuestionId())
                .collect(Collectors.toList());
        System.out.println(receivedQuestionIds);

        List<QuestionEntity> foundQuestions = questionRepository.findByQuestionIdIn(receivedQuestionIds);
        return foundQuestions.stream()
                .map(question -> new TodayResDto(question.getQuestionId(), question.getQuestion()))
                .collect(Collectors.toList());
    }

    public TodayResDto getQuestionById(Long familyId, Long questionId) {
        QuestionEntity questionEntity = questionRepository.findByQuestionId(questionId);

        return new TodayResDto(questionEntity.getQuestionId(), questionEntity.getQuestion());
    }

    public List<KeywordResDto> getQuestionByKeyword(Long familyId, String keyword) {
        List<FamilyQuestionEntity> receivedQuestions = familyQuestionRepository.findByFamilyFamilyId(familyId);
        System.out.println(receivedQuestions);

        List<Long> receivedQuestionIds = receivedQuestions.stream()
                .map(familyQuestionEntity -> familyQuestionEntity.getQuestion().getQuestionId())
                .collect(Collectors.toList());
        System.out.println(receivedQuestionIds);

        List<QuestionEntity> foundQuestions = questionRepository.findByQuestionContaining(keyword);

        List<QuestionEntity> filteredQuestions = foundQuestions.stream()
                .filter(question -> receivedQuestionIds.contains(question.getQuestionId()))
                .collect(Collectors.toList());

        // 선택된 질문들 중에서 KeywordResDto로 변환하여 리스트로 반환
        return filteredQuestions.stream()
                .map(question -> new KeywordResDto(question.getQuestionId(), question.getQuestion()))
                .collect(Collectors.toList());
    }

    // UPDATE
    public QuestionEntity updateQuestion(Long questionId, QuestionEntity updatedQuestion) {
        Optional<QuestionEntity> existingQuestionOptional = questionRepository.findById(questionId);
        if (existingQuestionOptional.isPresent()) {
            QuestionEntity existingQuestion = existingQuestionOptional.get();
            existingQuestion.setQuestion(updatedQuestion.getQuestion());
            // You can update other fields as needed
            return questionRepository.save(existingQuestion);
        } else {
            throw new RuntimeException("Question not found with id: " + questionId);
        }
    }

    // DELETE
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public TodayResDto getRandomQuestion(Long familyId) {

        FamilyEntity familyEntity = familyRepository.findFamilyEntityByFamilyId(familyId);

        List<FamilyQuestionEntity> receivedQuestions = familyQuestionRepository.findByFamilyFamilyId(familyId);
        System.out.println(receivedQuestions);

        List<Long> receivedQuestionIds = receivedQuestions.stream()
                .map(familyQuestionEntity -> familyQuestionEntity.getQuestion().getQuestionId())
                .collect(Collectors.toList());
        System.out.println(receivedQuestionIds);


        List<QuestionEntity> allQuestions = questionRepository.findAll();

//        List<QuestionEntity> availableQuestions = allQuestions.stream().filter(i ->
//                !receivedQuestionIds.contains(i)).collect(Collectors.toList());
//
        List<QuestionEntity> availableQuestions = allQuestions.stream()
                .filter(questionEntity -> !receivedQuestionIds.contains(questionEntity.getQuestionId()))
                .collect(Collectors.toList());


        if (availableQuestions.isEmpty()) {
            // 예외 처리: 모든 질문을 다 받았을 경우
            throw new RuntimeException("No more questions available.");
        }

        // 무작위로 질문 선택
        Random random = new Random();
        int randomIndex = random.nextInt(availableQuestions.size());
        QuestionEntity selectedQuestion = availableQuestions.get(randomIndex);

        // 선택된 질문을 사용자가 받은 목록에 추가
        FamilyQuestionEntity familyQuestionEntity = FamilyQuestionEntity.builder()
                .family(familyEntity)
                .question(selectedQuestion)
                .build();
        familyQuestionRepository.save(familyQuestionEntity);

//        familyQuestion.setFamily(familyEntity);
//        familyQuestion.setQuestion(selectedQuestion);

        return new TodayResDto(selectedQuestion.getQuestionId(),selectedQuestion.getQuestion());
    }
}
