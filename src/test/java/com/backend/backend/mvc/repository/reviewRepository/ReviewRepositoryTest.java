package com.backend.backend.mvc.repository.reviewRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.review.Review;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.reviewRepository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static com.backend.backend.mvc.domain.review.QReview.review;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ReviewRepositoryTest extends TestSetting {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    Long memberId;
    Long member2Id;
    Long postId;
    Long post2Id;
    @BeforeEach
    void init(){
        Member member = Fixture.createMember("1");
        memberId = memberRepository.save(member);
        Member member2 = Fixture.createMember("1");
        member2Id = memberRepository.save(member2);

        Post post = Fixture.createPost(memberRepository.findMemberById(memberId), 0L);
        postId = postRepository.save(post);
        Post post2 = Fixture.createPost(memberRepository.findMemberById(memberId), 0L);
        post2Id = postRepository.save(post2);
    }

    @Test
    void save() {
        Review review = Review.builder()
                .writer(memberRepository.findMemberById(memberId))
                .post(postRepository.findPostById(postId))
                .content(".")
                .starRating(3L)
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
                .starRating(1L)
                .build();
        Long reviewId1 = reviewRepository.save(review1);

        Review review2 = Review.builder()
                .writer(memberRepository.findMemberById(memberId))
                .post(postRepository.findPostById(postId))
                .content(".")
                .starRating(4L)
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
                .starRating(3L)
                .build();
        Long reviewId = reviewRepository.save(review);

        reviewRepository.deleteReviewById(reviewId);
        assertThat(reviewRepository.findReviewById(reviewId)).isSameAs(null);
        org.junit.jupiter.api.Assertions.assertThrows(InvalidDataAccessApiUsageException.class, ()->{
            reviewRepository.deleteReviewById(reviewId);
        });
    }

    @Test
    void getReviewStarRatingAvg(){
        Long post1Rate1=3L;
        Long post1Rate2=5L;
        Long post2Rate1=1L;
        Member member = memberRepository.findMemberById(memberId);
        Post post = postRepository.findPostById(postId);
        Member member2 = memberRepository.findMemberById(member2Id);
        Post post2 = postRepository.findPostById(post2Id);
        Review review1 = Fixture.createReview(member,post,post1Rate1);
        reviewRepository.save(review1);
        Review review2 = Fixture.createReview(member2,post,post1Rate2);
        reviewRepository.save(review2);
        Review review3 = Fixture.createReview(member,post2,post2Rate1);
        reviewRepository.save(review3);

        Assertions.assertThat(reviewRepository.getReviewStarRatingAvg(postId))
                .isEqualTo((post1Rate1 + post1Rate2) / 2.0);
        Assertions.assertThat(reviewRepository.getReviewStarRatingAvg(post2Id))
                .isEqualTo((double)post2Rate1);
    }
}