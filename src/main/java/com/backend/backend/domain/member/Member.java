package com.backend.backend.domain.member;

import com.backend.backend.domain.post.Post;
import com.backend.backend.domain.review.Review;
import com.backend.backend.domain.transactionDetail.TransactionDetail;
import com.backend.backend.exception.pointException.PointAmountError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    private Long point;

    @Embedded
    @Column(nullable = false)
    private Rating rating=new Rating();

    @OneToMany(mappedBy = "writer",cascade = CascadeType.ALL)
    @OrderBy("date desc")
    private List<Post> posts=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    @OrderBy("date desc")
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @OrderBy("startDate desc")
    private List<TransactionDetail> sales = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    @OrderBy("startDate desc")
    private List<TransactionDetail> purchases = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, String email, String phoneNumber, String passWord) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.point = 0L;
    }

    /**
     * 포인트를 더해주고 해당 기록을 저장하는 메소드
     * @param amount 추가할 포인트양
     */
    public void addPoint(Long amount){
        point+=amount;
        //TODO:더했다는 기록 추가
    }

    /**
     * 포인트를 감소시키고 해당 기록을 저장하는 메소드
     * @param amount 감소시킬 포인트양
     */
    public void subPoint(Long amount){
        if(amount<=0){
            throw new PointAmountError("0이하의 값은 감소시킬 수 없습니다");
        }
        if(this.point<amount){
            throw new PointAmountError("포인트 보다 더 많은 값을 감소 시킬 수 없습니다");
        }
        point-=amount;
        //TODO: 포인트를 감소시켰다는 기록을 추가
    }
}
