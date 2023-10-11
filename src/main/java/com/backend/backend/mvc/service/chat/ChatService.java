package com.backend.backend.mvc.service.chat;

import com.backend.backend.mvc.controller.chat.chatDTO.ChatHistoryDTO;
import com.backend.backend.mvc.controller.chat.chatDTO.ChatMessageDTO;
import com.backend.backend.mvc.controller.chat.chatDTO.ChattingRoomListDTO;
import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.chat.message.ChatMessage;
import com.backend.backend.mvc.domain.chat.message.values.ChatMessageType;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.chattingRepository.ChattingRepository;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {


    private final ChattingRepository chattingRepository;
    private final PostRepository postRepository;
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
            chattingRoomListDTOList.add(i
                    ,new ChattingRoomListDTO(chattingRooms.get(i).getSeller().getNickname().toString()
                    ,chattingRooms.get(i).getId()
                    ,chattingRooms.get(i).getPost().getPostName().toString()
                    ,chattingRooms.get(i).getBuyer().getNickname().toString()
                    ,chattingRooms.get(i).getPost().getPrice().toString()
                    ,chattingRooms.get(i).getPost().getId()));
        }
        return  chattingRoomListDTOList;
    }

    //채팅 기록 가져오기
    public List<ChatHistoryDTO> chat_history(Long roomId)
    {
        String date;
        List<ChatMessage> messages=chattingRepository.findMessagesByRoomId(roomId);
        List<ChatHistoryDTO> chatHistoryDTOS = new ArrayList<>();
        for(int i=0;i<messages.size();i++)
        {
            String dateTimeString = messages.get(i).getDate().toString().replace("T", " ");
            String[] dateTimeParts = dateTimeString.split(" ");
            String[] timeParts = dateTimeParts[1].split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            String period;
            if (hour < 12) {
                period = "오전";
            } else {
                period = "오후";
                if (hour > 12) {
                    hour -= 12; // 오후 시간을 12시간 형식으로 변경
                }
            }
            if(minute<10)
            {
                if(hour==0)
                {
                    date=period + " 0" + hour+":0"+minute;
                }
                else {
                    date=period + " " + hour+":0"+minute;
                }
            }
            else
            {
                if(hour==0)
                {
                    date=period + " 0" + hour+":0"+minute;
                }
                else {
                    date=period + " " + hour+":"+minute;
                }
            }

            chatHistoryDTOS.add(i,new ChatHistoryDTO(
                    messages.get(i).getId(),
                    messages.get(i).getSender().getNickname().toString(),
                    messages.get(i).getContent().getContent(),
                    date));

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

    //채팅 저장
    public void save_chat(ChatMessageDTO message)
    {
        ChatMessage chatMessage=new ChatMessage(
                chattingRepository.findChattingRoomById(message.getRoomId()),
                memberRepository.findMemberByNickname(message.getSender()),
                message.getContent(),
                ChatMessageType.MESSAGE);
        chattingRepository.saveMessage(chatMessage);
    }

    //게시물당 한개의 채팅방을 가질수 있도록 확인하기
    public int confirmChattingRoom(String nickname,Long postId)
    {
        Post post=postRepository.findPostById(postId);
        Member member=memberRepository.findMemberByNickname(nickname);
        if(chattingRepository.hasChatRoomForMember(post,member)==true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
