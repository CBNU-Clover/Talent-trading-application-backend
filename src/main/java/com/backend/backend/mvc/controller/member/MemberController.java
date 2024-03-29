package com.backend.backend.mvc.controller.member;

import com.backend.backend.common.DataProcessing.TokenParsing;
import com.backend.backend.mvc.controller.member.memberdto.*;
import com.backend.backend.mvc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @PostMapping("/check_nickname")
    public ResponseEntity<String>check_nickname(@RequestBody Check_nickname check_nickname)
    {
        int check_flag;
        check_flag=memberService.check_Nickname(check_nickname.getNickname());
        if(check_flag==1)
        {
            return ResponseEntity.ok().body("중복되는 닉네임이 있습니다!!");
        }
        else
        {
            return ResponseEntity.ok().body("중복되는 닉네임이 없습니다!!");
        }
        // 닉네임 똑같은거 있는지 검사

    }
    @PostMapping("/check_email")
    public ResponseEntity<String>check_email(@RequestBody Check_Email check_email)
    {

        int check_flag;
        check_flag=memberService.check_Email(check_email.getEmail());
        if(check_flag==1) // 중복되는 닉네임이 있다면
        {

            return ResponseEntity.ok().body("중복되는 이메일이 있습니다!!");
        }
        else
        {

            return ResponseEntity.ok().body("중복되는 이메일이 없습니다!!");
        }
    }

   @PostMapping("/join")
    public ResponseEntity<String>join(@RequestBody MemberJoinRequest memberJoinRequest)
    {
        memberService.join(memberJoinRequest);
        return ResponseEntity.ok().body("회원가입이 성공했습니다!!");
    }

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody MemberLoginRequest memberLoginRequest)
    {
        String token=memberService.login(memberLoginRequest.getNickname(),memberLoginRequest.getPassWord());
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/getMyProfile")
    public MyProfile getMyProfile(HttpServletRequest request)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);

        return memberService.getMyProfile(result);
    }
}
