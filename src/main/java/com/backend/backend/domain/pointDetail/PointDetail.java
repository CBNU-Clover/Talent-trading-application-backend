package com.backend.backend.domain.pointDetail;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.transactionDetail.TransactionStatus;
import com.backend.backend.exception.NotExistException;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointDetail {

    @Id
    @GeneratedValue
    @Column(name = "point_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member owner;


    @Column(nullable = false)
    private String  recipient;

    @Column(nullable = false)
    private String  sender;

    @CreatedDate
    private LocalDateTime date;


    @Enumerated(EnumType.STRING)
    private PointStatus status;

    private String detail;

    @Builder
    public PointDetail(Member owner, String recipient, String sender,
                       PointStatus status, String detail) {
        Assert.hasText(recipient, "수신처가 없습니다");
        Assert.hasText(sender, "발신처가 없습니다");
        if(owner==null){
            throw new NotExistException("기록 소유자가 없습니다");
        }
        if(status==null){
            throw new NotExistException("포인트거래가 입력인지 출력인지 알수 없습니다");
        }

        this.owner = owner;
        this.recipient = recipient;
        this.sender = sender;
        this.status = status;
        this.detail = detail;
    }
}
