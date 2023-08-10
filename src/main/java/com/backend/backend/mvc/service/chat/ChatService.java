package com.backend.backend.mvc.service.chat;

import com.backend.backend.mvc.controller.chat.chatDTO.ChatHistoryDTO;
import com.backend.backend.mvc.controller.chat.chatDTO.ChattingRoomDTO;
import com.backend.backend.mvc.controller.chat.chatDTO.ChattingRoomListDTO;
import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.chat.message.ChatMessage;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.chattingRepository.ChattingRepository;
import com.backend.backend.mvc.repository.chattingRepository.DbChattingRepository;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {


    private final ChattingRepository chattingRepository;
    private final MemberRepository memberRepository;
   //채팅방 생성
    public Long create_room(Post post , Member seller , Member buyer)
    {

        Long roomId= chattingRepository.makeRoom(post , seller,buyer);
        return roomId; // 성공하면 채팅방 ID 반환
    }

    // 어떤 멤버의 채팅방 리스트 가져오기
    public List<ChattingRoomListDTO> Rooms(String user)
    {
        Member member=memberRepository.findMemberByNickname(user);
        List<ChattingRoom> chattingRooms=new ArrayList<>();
        List<ChattingRoomListDTO> chattingRoomListDTOList =new ArrayList<>();
        chattingRooms=chattingRepository.findChattingRoomByMember(member);

        for(int i=0 ; i<chattingRooms.size();i++)
        {
            chattingRoomListDTOList.add(i,new ChattingRoomListDTO(chattingRooms.get(i).getSeller().getNickname().toString(),chattingRooms.get(i).getId()));
        }
        return  chattingRoomListDTOList;
    }

    //채팅 기록 가져오기
    public List<ChatHistoryDTO> chat_history(Long roomId)
    {
        List<ChatMessage> messages=chattingRepository.findMessagesByRoomId(roomId);
        List<ChatHistoryDTO> chatHistoryDTOS = null;
        for(int i=0;i<messages.size();i++)
        {
            chatHistoryDTOS.add(i,new ChatHistoryDTO(messages.get(i).getId(),messages.get(i).getSender().getNickname().toString(),messages.get(i).getContent().getContent()));
        }
        return chatHistoryDTOS;
    }

    //채팅방 나가기
    public void delete_room(String user , Long roomId)
    {
        Member member=memberRepository.findMemberByNickname(user);
        ChattingRoom chattingRoom=chattingRepository.findChattingRoomById(roomId);
        chattingRepository.removeUserCattingRoom(member,chattingRoom);
    }
}
