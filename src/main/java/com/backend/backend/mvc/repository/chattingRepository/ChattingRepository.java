package com.backend.backend.mvc.repository.chattingRepository;

import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.chat.message.ChatMessage;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;

import java.util.List;

public interface ChattingRepository {

    /**
     * 채팅방을 아이디로 찾음
     * @param id 채팅방 아이디
     * @return 채팅방
     */
    ChattingRoom findChattingRoomById(Long id);

    /**
     * 두 회원간의 해당 게시글에 대한 채팅방 제작
     * @param post 채팅방을 생성할 게시글
     * @param seller 해당 게시글의 작성자 즉 판매자
     * @param buyer 해당 게시글의 구매를 원하는자
     * @return 채팅방 id
     */
    Long makeRoom( Post post, Member seller, Member buyer);

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

    /**
     * 해당 post와 member의 채팅방을 반환
     * @param post 채팅방을 찾을 post
     * @param member 채팅방에 소속되어 있는 member
     * @return 해당 post가 가진 채팅방 중 member가 포함되어 있는 채팅방
     */
     List<ChattingRoom> findChattingRoomsByPost(Post post, Member member);

    /**
     * member가 해당 post에 채팅방을 가지고 있는지 여부를 반환
     * @param post 게시글
     * @param member 회원
     * @return 소유 여부
     */
     Boolean hasChatRoomForMember(Post post, Member member);

    /**
     * 해당 유저의 채팅방 리스트에서 해당 채팅방이 뜨지 않도록 삭제
     * @param member 채팅방을 삭제할 유저
     * @param room 삭제할 채팅방
     */
    void removeUserCattingRoom(Member member, ChattingRoom room);
}
