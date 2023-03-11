package com.backend.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

/**
 * builder로 제작해서 사용
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickName;
    private String email;
    private String phoneNumber;
    private String passWord;
    private String region;
    private Long point;

    @Builder
    public Member(String name, String nickName, String email, String phoneNumber, String passWord, String region, Long point) {
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(nickName, "nickName must not be empty");
        //Assert.hasText(email, "email must not be empty");
        //Assert.hasText(phoneNumber, "phoneNumber must not be empty");
        Assert.hasText(passWord, "passWord must not be empty");
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.region = region;
        this.point = point;
    }
}
