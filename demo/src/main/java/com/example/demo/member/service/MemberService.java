package com.example.demo.member.service;

import com.example.demo.family.repository.FamilyRepository;
import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.dto.MemberResDto;
import com.example.demo.member.entity.MemberEntity;
import com.example.demo.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final FamilyRepository familyRepository;
    public MemberService(MemberRepository memberRepository, FamilyRepository familyRepository) {
        this.memberRepository = memberRepository;
        this.familyRepository = familyRepository;
    }


    public MemberDto saveMember(MemberDto member) {
        MemberEntity memberEntity = MemberEntity.builder()
                .memberName(member.getMemberName())
                .family(familyRepository.findFamilyEntityByFamilyId(Long.valueOf(1)))
                .build();
        memberRepository.save(memberEntity);
        return new MemberDto(memberEntity.getMemberName());
    }

    public List<MemberResDto> getAllMembers() {
        List<MemberEntity> members = memberRepository.findAll();

        return members.stream().map(member -> {
            return new MemberResDto(member.getMemberId(), member.getMemberName());
        }).collect(Collectors.toList());
    }


}
