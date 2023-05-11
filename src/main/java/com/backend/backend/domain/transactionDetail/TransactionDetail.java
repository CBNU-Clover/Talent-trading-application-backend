package com.backend.backend.domain.transactionDetail;

import com.backend.backend.domain.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionDetail {

    @Id
    @GeneratedValue
    @Column(name = "transaction_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member buyer;


    @CreatedDate
    private LocalDateTime startDate;

    @Setter
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Lob
    private String detail;

    @Builder
    public TransactionDetail(Member seller, Member buyer,String detail) {
        this.seller = seller;
        this.buyer = buyer;
        this.status = TransactionStatus.TRADING;
        this.detail = detail;
    }
}
