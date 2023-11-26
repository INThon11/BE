package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.dto.MemberResDto;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto member) {
        return new ResponseEntity<>(memberService.saveMember(member), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemberResDto>> getAllMembers() {
        List<MemberResDto> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
