package com.backend.backend.mvc.repository.chattingRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class ChattingRepositoryTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ChattingRepository chattingRepository;

    Member seller;
    Member buyer;
    Post post;
    @BeforeEach
    void setup(){
        seller = Fixture.createMember("1");
        memberRepository.save(seller);

        buyer = Fixture.createMember("1");
        memberRepository.save(buyer);

        post = Fixture.createPost(seller, 0L);
        postRepository.save(post);
    }

    @Test
    void findChattingRoomByMember() {
        ChattingRoom chattingRoom = new ChattingRoom(post, seller, buyer);
        chattingRepository.saveRoom(chattingRoom);

        List<ChattingRoom> sellerChat = chattingRepository.findChattingRoomByMember(seller);
        List<ChattingRoom> buyerChat = chattingRepository.findChattingRoomByMember(buyer);

        Assertions.assertThat(sellerChat).contains(chattingRoom);
        Assertions.assertThat(buyerChat).contains(chattingRoom);
    }

    @Test
    void findMessagesByRoomId() {

    }
}