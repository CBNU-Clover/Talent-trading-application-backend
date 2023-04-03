package com.backend.backend.controller;

import com.backend.backend.domain.Member;
import com.backend.backend.dto.LoginDTO;
import com.backend.backend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/members/save")
    public void registerMember(@RequestBody Member member)
    {

        memberService.registerMember(member);
    }
}
