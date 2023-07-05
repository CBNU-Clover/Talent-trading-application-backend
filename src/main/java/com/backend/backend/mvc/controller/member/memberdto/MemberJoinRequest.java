package com.backend.backend.mvc.controller.member.memberdto;

import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberJoinRequest {

    private String nickname;
    private String email;
    private String name;
    private String passWord;
    private String phoneNumber;

    public MemberJoinRequest() {
    }
}
