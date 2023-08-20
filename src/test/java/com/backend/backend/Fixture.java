package com.backend.backend;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.review.Review;

public class Fixture {

    public static Member createMember(String postFix){
        return Member.builder()
                .name("가나다")
                .nickname("TestNickName"+postFix)
                .password("5456")
                .email("566511561sd1"+postFix+"@test.com")
                .phoneNumber("01012345678")
                .build();
    }
    public static Member createMember2(){
        return Member.builder()
                .name("홀란드")
                .nickname("striker")
                .password("7777")
                .email("holand7777@naver.com")
                .phoneNumber("01077777777")
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

    public static Review createReview(Member member,Post post,Long rate){
        return Review.builder()
                .writer(member)
                .post(post)
                .content(".")
                .starRating(rate)
                .build();
    }
}
