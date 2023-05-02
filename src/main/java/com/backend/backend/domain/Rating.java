package com.backend.backend.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {
    private Long category1=0L;
    private Long category2=0L;
    private Long category3=0L;
    private Long category4=0L;

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
