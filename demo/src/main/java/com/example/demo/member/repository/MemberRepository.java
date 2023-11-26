package com.example.demo.member.repository;

import com.example.demo.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findMemberEntityByMemberId(Long memberId);
}
