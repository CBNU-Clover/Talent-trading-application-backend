package com.backend.backend.domain.pointDetail;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.transactionDetail.TransactionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    public PointDetail(Member owner, String recipient, String sender,
                       PointStatus status, String detail) {
        this.owner = owner;
        this.recipient = recipient;
        this.sender = sender;
        this.status = status;
        this.detail = detail;
    }
}
