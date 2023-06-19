package com.backend.backend.mvc.domain.member;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;

/**
 * builder로 제작해서 사용
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String passWord;

    @Embedded
    @Column(nullable = false)
    private Point point;




    @Builder
    public Member(String name, String nickname, String email, String phoneNumber, String passWord) {
        Assert.hasText(name, "회원 이름이 없습니다");
        Assert.hasText(nickname, "회원 닉네임이 없습니다");
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.point = new Point();
    }
}
