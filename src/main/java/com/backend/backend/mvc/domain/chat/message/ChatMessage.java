package com.backend.backend.mvc.domain.chat.message;

import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.chat.message.values.MessageContent;
import com.backend.backend.mvc.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @Column(name = "message_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "chatting_room",nullable = false)
    private ChattingRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id",nullable = false)
    private Member sender;


    @Embedded
    private MessageContent content;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime date;

    public ChatMessage(ChattingRoom room, Member sender, String content) {
        this.room = room;
        this.sender = sender;
        this.content = MessageContent.from(content);
    }
}
