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

}
