package com.backend.backend;

import com.backend.backend.domain.member.Member;

public class Fixture {

    public static Member createMember(String postFix){
        return Member.builder()
                .name("Test"+postFix)
                .nickname("TestNickName"+postFix)
                .passWord("5456")
                .email("566511561sd1"+postFix+"@test.com")
                .build();
    }
}
