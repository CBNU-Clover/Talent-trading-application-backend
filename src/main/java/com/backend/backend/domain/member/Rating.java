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
    private Long ratingId;

    @JsonIgnore
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating")
    private Member member;
    private Long category1=0L;
    private Long category2=0L;
    private Long category3=0L;
    private Long category4=0L;

    public Rating(Member member) {
        this.member = member;
    }

    public void addCategory1(Long point) {
        this.category1 += point;
    }

    public void addCategory2(Long point) {
        this.category2 += point;
    }

    public void addCategory3(Long point) {
        this.category3 += point;
    }

    public void addCategory4(Long point) {
        this.category4 += point;
    }

}
