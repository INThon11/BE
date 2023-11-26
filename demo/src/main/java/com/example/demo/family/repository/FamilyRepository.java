package com.example.demo.family.repository;

import com.example.demo.family.entity.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FamilyRepository extends JpaRepository<FamilyEntity,Long> {
    FamilyEntity findFamilyEntityByFamilyId(Long familyId);
}
