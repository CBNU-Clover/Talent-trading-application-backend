package com.backend.backend.domain.member;

import com.backend.backend.exception.pointException.PointAmountError;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {

    @Column(name = "point_amount")
    private Long amount =0L;

    /**
     * 포인트를 증가시키고 해당 기록을 저장하는 메소드
     * @param amount 증가할 포인트양
     */
    public void addPoint(Long amount){
        if(amount==null){
            amount=0L;
        }
        if(amount<=0){
            throw new PointAmountError("0이하의 값은 증가시킬 수 없습니다");
        }
        this.amount +=amount;
        //TODO:증가시켰다는 기록 추가
    }

    /**
     * 포인트를 감소시키고 해당 기록을 저장하는 메소드
     * @param amount 감소시킬 포인트양
     */
    public void subPoint(Long amount){
        if(amount==null){
            amount=0L;
        }
        if(amount<=0){
            throw new PointAmountError("0이하의 값은 감소시킬 수 없습니다");
        }
        if(this.amount <amount){
            throw new PointAmountError("포인트 보다 더 많은 값을 감소 시킬 수 없습니다");
        }
        this.amount -=amount;
        //TODO: 포인트를 감소시켰다는 기록을 추가
    }
}
