package com.backend.backend.mvc.domain.review;

import com.backend.backend.mvc.domain.image.Image;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.common.exception.NotExistException;
import com.backend.backend.mvc.domain.review.values.StarRating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member writer;
    private String content;

    @Embedded
    private StarRating starRating;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    public Review(Post post, Member writer, String content, Long starRating, Image image) {
        if(post==null){
            throw new NotExistException("해당되는 게시글이 없습니다");
        }
        if(writer==null){
            throw new NotExistException("작성자가 없습니다");
        }
        this.post = post;
        this.writer = writer;
        this.content = content;
        this.starRating = StarRating.from(starRating);
        this.image = image;
    }
}
