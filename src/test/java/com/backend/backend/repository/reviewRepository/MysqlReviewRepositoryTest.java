package com.backend.backend.repository.reviewRepository;

import com.backend.backend.Fixture;
import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.post.Post;
import com.backend.backend.domain.review.Review;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.postRepository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MysqlReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    Long memberId;
    Long postId;
    @BeforeEach
    void init(){
        Member member = Fixture.createMember("1");
        memberId = memberRepository.save(member);

        Post post = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .build();
        postId = postRepository.save(post);
    }

    @Test
    void save() {
        Review review = Review.builder()
                .writer(memberRepository.findMemberById(memberId))
                .post(postRepository.findPostById(postId))
                .content(".")
                .build();
        Long reviewId = reviewRepository.save(review);

        Assertions.assertThat(reviewRepository.findReviewById(reviewId)).isSameAs(review);

    }

    @Test
    void findReviewById() {
        Review review1 = Review.builder()
                .writer(memberRepository.findMemberById(memberId))
                .post(postRepository.findPostById(postId))
                .content(".")
                .build();
        Long reviewId1 = reviewRepository.save(review1);

        Review review2 = Review.builder()
                .writer(memberRepository.findMemberById(memberId))
                .post(postRepository.findPostById(postId))
                .content(".")
                .build();
        Long reviewId2 = reviewRepository.save(review2);

        Assertions.assertThat(reviewRepository.findReviewById(reviewId2)).isSameAs(review2);
        Assertions.assertThat(reviewRepository.findReviewById(reviewId1)).isSameAs(review1);

    }

    @Test
    void deleteReviewById() {
        Review review = Review.builder()
                .writer(memberRepository.findMemberById(memberId))
                .post(postRepository.findPostById(postId))
                .content(".")
                .build();
        Long reviewId = reviewRepository.save(review);

        reviewRepository.deleteReviewById(reviewId);
        assertThat(reviewRepository.findReviewById(reviewId)).isSameAs(null);
        org.junit.jupiter.api.Assertions.assertThrows(InvalidDataAccessApiUsageException.class, ()->{
            reviewRepository.deleteReviewById(reviewId);
        });
    }
}