package com.example.demo.question.controller;

import com.example.demo.answer.service.AnswerService;
import com.example.demo.question.dto.KeywordResDto;
import com.example.demo.question.dto.QuestionResDto;
import com.example.demo.question.dto.TodayResDto;
import com.example.demo.question.entity.QuestionEntity;
import com.example.demo.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/question")
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    // Create a new question
    @PostMapping
    public ResponseEntity<QuestionResDto> createQuestion(@RequestBody QuestionResDto question) {
        QuestionResDto createdQuestion = questionService.saveQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    // Get all questions
    @GetMapping("/{familyId}")
    public ResponseEntity<List<TodayResDto>> getAllQuestions(@PathVariable Long familyId) {
        List<TodayResDto> questions = questionService.getAllQuestions(familyId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{familyId}/{questionId}")
    public ResponseEntity<TodayResDto> getQuestionById(@PathVariable Long familyId, @PathVariable Long questionId) {
        TodayResDto questions = questionService.getQuestionById(familyId, questionId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Get a specific question by ID
    @GetMapping("/find")
    public ResponseEntity<List<KeywordResDto>> getQuestionByKeyword(@RequestParam Long familyId, @RequestParam String keyword) {
        List<KeywordResDto> questions = questionService.getQuestionByKeyword(familyId, keyword);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }



    // Update a question by ID
    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionEntity> updateQuestion(@PathVariable Long questionId,
                                                         @RequestBody QuestionEntity updatedQuestion) {
        try {
            QuestionEntity result = questionService.updateQuestion(questionId, updatedQuestion);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Delete a question by ID
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {

        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/today")
    public ResponseEntity<TodayResDto> getTodayQuestion(@RequestParam Long familyId) {

        TodayResDto todayQuestion = questionService.getRandomQuestion(familyId);
        return new ResponseEntity<>(todayQuestion, HttpStatus.OK);

    }


}
