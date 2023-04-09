package com.backend.backend.controller;

import com.backend.backend.domain.Member;
import com.backend.backend.dto.memberdto.MemberJoinRequest;
import com.backend.backend.dto.memberdto.MemberLoginRequest;
import com.backend.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;


   @PostMapping("/join")
    public ResponseEntity<String>join(@RequestBody MemberJoinRequest memberJoinRequest)
    {
        memberService.join(memberJoinRequest);
        return ResponseEntity.ok().body("회원가입이 성공했습니다!!");
    }

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody MemberLoginRequest memberLoginRequest)
    {
        String token=memberService.login(memberLoginRequest.getNickName(),memberLoginRequest.getPassWord());
        return ResponseEntity.ok().body(token);
    }

}
