package com.backend.backend.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {

    @Id
    @GeneratedValue
    @Column(name = "rating_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;
    private RatingCategory category;

    private Long score= 0L;

    public Rating(Member member,RatingCategory category) {
        this.member = member;
        this.category = category;
    }

    public void addScore(Long point) {
        this.score += point;
    }

}
