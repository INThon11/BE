package com.example.demo.family.service;

import com.example.demo.family.dto.FamilyDto;
import com.example.demo.family.entity.FamilyEntity;
import com.example.demo.family.repository.FamilyRepository;
import org.springframework.stereotype.Service;

@Service
public class FamilyService {
    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public FamilyDto saveFamily(FamilyDto family) {

        FamilyEntity familyEntity = FamilyEntity.builder()
                .familyName(family.getFamilyName())
                .count(family.getCount())
                .build();
        familyRepository.save(familyEntity);
        return new FamilyDto(familyEntity.getFamilyName(),familyEntity.getCount());
    }
}
