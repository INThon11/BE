package com.example.demo.answer.controller;

import com.example.demo.answer.dto.*;
import com.example.demo.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/answer")
@RestController
public class AnswerController {

    private final AnswerService answerService;

    // Create a new answer
    @PostMapping("/{questionId}/{memberId}")
    public ResponseEntity<AnswerResDto> createAnswer(@PathVariable Long questionId, @PathVariable Long memberId, @RequestBody AnswerDto answer) {
        return new ResponseEntity<>(answerService.saveAnswer(questionId, memberId, answer), HttpStatus.CREATED);
    }



    // Get all answers
//    @GetMapping
//    public ResponseEntity<List<AnswerEntity>> getAllAnswers() {
//        List<AnswerEntity> answers = answerService.getAllAnswers();
//        return new ResponseEntity<>(answers, HttpStatus.OK);
//    }

    @GetMapping("/{questionId}/{memberId}")
    public ResponseEntity<List<AnswerDto>> getAnswerByQuestionAndMember(@PathVariable Long questionId, @PathVariable Long memberId) {
        List<AnswerDto> createdAnswer = answerService.getAnswerByQuestionAndMember(questionId, memberId);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }

    // Get a specific answer by ID
//    @GetMapping("/{answerId}")
//    public ResponseEntity<AnswerEntity> getAnswerById(@PathVariable Long answerId) {
//        Optional<AnswerEntity> answer = answerService.getAnswerById(answerId);
//        return answer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @GetMapping("/all")
    public ResponseEntity<List<AnswerResDto>> getAllAnswers(){
        List<AnswerResDto> answers = answerService.getAllAnswers();
        if (!answers.isEmpty()) {
            return new ResponseEntity<>(answers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<QuestionAnswerDto>> getAnswerByQuestionId(@RequestParam Long familyId, @RequestParam Long questionId) {

        List<QuestionAnswerDto> answers = answerService.getAnswersByQuestionId(familyId, questionId);

        if (!answers.isEmpty()) {
            return new ResponseEntity<>(answers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Update an answer by ID
    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerUpdateResDto> updateAnswer(@PathVariable Long answerId,
                                                           @RequestBody AnswerUpdateReqDto updatedAnswer) {
        try {
            AnswerUpdateResDto result = answerService.updateAnswer(answerId, updatedAnswer);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Delete an answer by ID
    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
