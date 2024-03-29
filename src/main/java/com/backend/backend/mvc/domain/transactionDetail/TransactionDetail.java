package com.backend.backend.mvc.domain.transactionDetail;

import com.backend.backend.mvc.domain.image.Image;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.common.exception.NotExistException;
import com.backend.backend.mvc.domain.post.values.PostName;
import com.backend.backend.mvc.domain.post.values.Price;
import com.backend.backend.mvc.domain.transactionDetail.values.TransactionStatus;
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

    private PostName postName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;

    private Price price;

    @Builder
    public TransactionDetail(Member seller, Member buyer,String postName,Long price,Image image) {
        if(seller==null){
            throw new NotExistException("판매자가 없습니다");
        }
        if(buyer==null){
            throw new NotExistException("구매자가 없습니다");
        }
        this.seller = seller;
        this.buyer = buyer;
        this.status = TransactionStatus.TRADING;
        this.postName = PostName.from(postName);
        this.price = Price.from(price);
        this.image = image;
    }
}
