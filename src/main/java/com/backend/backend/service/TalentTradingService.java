package com.backend.backend.service;

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

@Service
@RequiredArgsConstructor
public class TalentTradingService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PointService pointService;
    private final TransactionDetailRepository transactionDetailRepository;

    @Transactional
    public void talentTrading(Long postId, String buyerNickname){
        Member buyer = memberRepository.findMemberByNickname(buyerNickname);
        Post post = postRepository.findPostById(postId);
        post.setPostStatus(PostStatus.CLOSE);

        pointService.remittancePoint(buyerNickname,post.getWriter().getNickname(),post.getPrice());

        TransactionDetail transactionDetail = TransactionDetail.builder()
                .seller(post.getWriter())
                .buyer(buyer)
                .build();
        transactionDetailRepository.save(transactionDetail);

    }

}
