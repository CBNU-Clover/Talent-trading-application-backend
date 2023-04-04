package com.backend.backend.domain;

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
    private String nickName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String passWord;
    private String region;
    private Long point;

    @OneToMany(mappedBy = "writer",cascade = CascadeType.ALL)
    @OrderBy("date desc")
    private List<Post> posts=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    @OrderBy("date desc")
    private List<Review> reviews = new ArrayList<>();

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
