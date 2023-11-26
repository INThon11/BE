package com.example.demo.family.controller;

import com.example.demo.family.dto.FamilyDto;
import com.example.demo.family.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/family")
@RestController
public class FamilyController {
    private final FamilyService familyService;
    @PostMapping()
    public ResponseEntity<FamilyDto> createAnswer(@RequestBody FamilyDto family) {
        return new ResponseEntity<>(familyService.saveFamily(family), HttpStatus.CREATED);
    }
}
