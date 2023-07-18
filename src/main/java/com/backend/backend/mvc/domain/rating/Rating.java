package com.backend.backend.mvc.domain.rating;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.values.PostCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostCategory category;

    private Long score= 0L;

    public Rating(Member member, PostCategory category) {
        this.member = member;
        this.category = category;
    }

    public void addScore(Long point) {
        this.score += point;
    }

}
