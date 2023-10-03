package com.backend.backend.mvc.controller.chat;

import com.backend.backend.common.DataProcessing.TokenParsing;
import com.backend.backend.mvc.controller.chat.chatDTO.ChatHistoryDTO;
import com.backend.backend.mvc.controller.chat.chatDTO.ChattingRoomDTO;
import com.backend.backend.mvc.controller.chat.chatDTO.ChattingRoomListDTO;
import com.backend.backend.mvc.controller.chat.chatDTO.ChattingRoomRemoveDTO;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private final PostRepository postRepository;

    private final MemberRepository memberRepository;


    //채팅방 만들기
    @PostMapping("/create_room")
    @ResponseBody
    public Long createRoom(HttpServletRequest request,@RequestBody @Valid ChattingRoomDTO chattingRoomDTO)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        Post post=postRepository.findPostById(chattingRoomDTO.getPostId());
        Member seller=memberRepository.findMemberByNickname(chattingRoomDTO.getSeller());
        Member buyer=memberRepository.findMemberByNickname(result);
        Long roomId=chatService.create_room(post , seller , buyer);
        return roomId;
    }

    //채팅방 리스트 가져오기
    @GetMapping("/rooms")
    public List<ChattingRoomListDTO> Rooms(HttpServletRequest request)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        return chatService.Rooms(result);
    }
    

    //채팅 기록 가져오기
    @GetMapping("/chat_history/{roomId}")
    public List<ChatHistoryDTO> chat_history(@PathVariable Long roomId)
    {

        return chatService.chat_history(roomId);
    }

    //채팅방 나가기
    @PostMapping("/delete_chat")
    @ResponseBody
    public void delete_chat(HttpServletRequest request, @RequestBody @Valid ChattingRoomRemoveDTO chattingRoomRemoveDTO)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        chatService.delete_room(result, chattingRoomRemoveDTO.getRoomId());
    }

    //채팅방 있는지 확인
    @GetMapping("/confirm_chattingroom/{postId}")
    public int confirmChattingRoom(HttpServletRequest request,@PathVariable Long postId)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        return chatService.confirmChattingRoom(result,postId);
    }
}
