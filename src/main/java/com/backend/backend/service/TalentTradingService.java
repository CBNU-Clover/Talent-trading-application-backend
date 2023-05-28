package com.backend.backend.service;

import com.backend.backend.controller.post.Dto.PostSearchBoard;
import com.backend.backend.controller.trading.tradeDto.TradingHistory;
import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.post.Post;
import com.backend.backend.domain.post.PostStatus;
import com.backend.backend.domain.transactionDetail.TransactionDetail;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.postRepository.PostRepository;
import com.backend.backend.repository.transactionDetailRepository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TalentTradingService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PointService pointService;
    private final TransactionDetailRepository transactionDetailRepository;

    /**
     * postId를 인식해 그 게시물에 대한 거래 진행
     * @param postId
     */
    @Transactional
    public void talentTrading(Long postId, String buyerNickname){
        System.out.println(postId);
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println(buyerNickname);
        System.out.println("+++++++++++++++++++++++++++++++++");
        Member buyer = memberRepository.findMemberByNickname(buyerNickname);
        Post post = postRepository.findPostById(postId);
        post.setPostStatus(PostStatus.CLOSE);

        pointService.remittancePoint(buyerNickname,post.getWriter().getNickname(),post.getPrice());

        TransactionDetail transactionDetail = TransactionDetail.builder()
                .seller(post.getWriter())
                .buyer(buyer)
                .postName(post.getPostName())
                .price(post.getPrice())
                .build();
        transactionDetailRepository.save(transactionDetail);

    }
    /**
     * 로그한 사용자의 거래기록 불러오기
     * @param nickname
     * return 거래기록
     */
    @Transactional
    public List<TransactionDetail> tradinghistory(String nickname)
    {
        
        Member member=memberRepository.findMemberByNickname(nickname);
        List<TransactionDetail>trade_info=transactionDetailRepository.findDetailsByMember(member);

        return trade_info;
    }
}
