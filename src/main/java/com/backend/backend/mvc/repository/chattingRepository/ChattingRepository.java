package com.backend.backend.mvc.repository.chattingRepository;

import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.chat.message.ChatMessage;
import com.backend.backend.mvc.domain.member.Member;

import java.util.List;

public interface ChattingRepository {

    /**
     * 채팅방 저장
     * @param chattingRoom 제작된 채팅방
     * @return 채팅방 id
     */
    Long saveRoom(ChattingRoom chattingRoom);

    /**
     * 메시지 저장
     * @param chatMessage 저장할 메시지
     * @return 메시지의 id
     */
    Long saveMessage(ChatMessage chatMessage);

    /**
     * 모든 메시지를 탐색하여 최신 순으로 반환
     * @param roomId 채팅방의 id
     * @return 해당 채팅방에서 진행된 대화 내용을 최신순으로
     */
    List<ChatMessage> findMessagesByRoomId(Long roomId);

    /**
     * 해당 멤버의 채팅방 목록
     * @param member 채팅방을 검색할 멤버
     * @return 채팅방 리스트
     */
    List<ChattingRoom> findChattingRoomByMember(Member member);
}
