package com.backend.backend;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;

public class Fixture {

    public static Member createMember(String postFix){
        return Member.builder()
                .name("Test"+postFix)
                .nickname("TestNickName"+postFix)
                .passWord("5456")
                .email("566511561sd1"+postFix+"@test.com")
                .build();
    }

    public static Post createPost(Member seller,Long price){
        return Post.builder()
                .writer(seller)
                .postName("testTest")
                .content(",")
                .price(price)
                .build();
    }
}
