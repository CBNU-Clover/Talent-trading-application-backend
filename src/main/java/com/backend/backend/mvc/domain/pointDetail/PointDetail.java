package com.backend.backend.mvc.domain.pointDetail;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.common.exception.NotExistException;
import com.backend.backend.mvc.domain.pointDetail.values.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EnableJpaAuditing
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


    @Embedded
    @Column(nullable = false)
    private Recipient recipient;

    @Embedded
    @Column(nullable = false)
    private Sender sender;

    @Embedded
    @Column(nullable = false)
    private Amount amount;

    @Embedded
    @Column(nullable = false)
    private Balance balance;

    @CreatedDate
    private LocalDateTime date;


    @Enumerated(EnumType.STRING)
    private PointStatus status;

    @Setter
    private String memo;

    @Builder
    public PointDetail(Member owner, String recipient, String sender,
                       PointStatus status,Long amount, String memo) {
        if(owner==null){
            throw new NotExistException("기록 소유자가 없습니다");
        }
        if(status==null){
            throw new NotExistException("포인트거래가 입력인지 출력인지 알수 없습니다");
        }

        this.owner = owner;
        this.recipient = Recipient.from(recipient);
        this.sender = Sender.from(sender);
        this.status = status;
        this.amount = Amount.from(amount);
        this.memo = memo;
        this.balance = Balance.from(owner.getPoint().getAmount());
    }
}
