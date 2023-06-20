package com.backend.backend.mvc.domain.member;

import com.backend.backend.mvc.domain.member.values.*;
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

    @Embedded
    @Column(name = "member_name", nullable = false)
    private Name name;

    @Embedded
    @Column(unique = true, nullable = false)
    private Nickname nickname;

    @Embedded
    @Column(unique = true)
    private Email email;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    @Column(nullable = false)
    private Password password;

    @Embedded
    @Column(nullable = false)
    private Point point;




    @Builder
    public Member(String name, String nickname, String email, String phoneNumber, String password) {
        Assert.hasText(name, "회원 이름이 없습니다");
        Assert.hasText(nickname, "회원 닉네임이 없습니다");
        this.name = Name.from(name);
        this.nickname = Nickname.from(nickname);
        this.email = Email.from(email);
        this.phoneNumber = PhoneNumber.from(phoneNumber);
        this.password = Password.from(password);
        this.point = new Point();
    }



}
