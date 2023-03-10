package com.backend.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * new Member()로 선언후 set으로 내부 채워서 사용
 */
@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String passWord;
    private String region;
    private String point;
}
