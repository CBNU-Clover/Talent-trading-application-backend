package com.backend.backend.domain.member;

import com.backend.backend.domain.post.Post;
import com.backend.backend.domain.review.Review;
import com.backend.backend.domain.transactionDetail.TransactionDetail;
import com.backend.backend.exception.pointException.PointAmountError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Rating rating;



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
