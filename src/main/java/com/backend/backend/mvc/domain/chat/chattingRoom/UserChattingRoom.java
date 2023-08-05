package com.backend.backend.mvc.domain.chat.chattingRoom;

import com.backend.backend.mvc.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 해당 유저가 가지고 있는 채팅방
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChattingRoom {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    ChattingRoom room;

    public UserChattingRoom(Member member, ChattingRoom room) {
        if(member==null){
            throw new IllegalArgumentException("회원이 입력되지 않았습니다");
        }
        if(room==null){
            throw new IllegalArgumentException("채팅방이 입력되지 않았습니다");
        }
        this.member = member;
        this.room = room;
    }
}
