package com.backend.backend.mvc.controller.member.memberdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginRequest {
    private String nickname;
    private String passWord;
}
