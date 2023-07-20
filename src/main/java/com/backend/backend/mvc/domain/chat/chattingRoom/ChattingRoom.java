package com.backend.backend.mvc.domain.chat.chattingRoom;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRoom {

    @Id
    @Column(name = "room_id")
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id",nullable = false)
    private Member seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id",nullable = false)
    private Member buyer;

    public ChattingRoom(Post post, Member seller, Member buyer) {
        if(post==null){
            throw new IllegalArgumentException("게시글이 입력되지 않았습니다");
        }
        if(seller==null){
            throw new IllegalArgumentException("판매자가 입력되지 않았습니다");
        }
        if(buyer==null){
            throw new IllegalArgumentException("구매자가 입력되지 않았습니다");
        }
        this.post = post;
        this.seller = seller;
        this.buyer = buyer;
    }
}
