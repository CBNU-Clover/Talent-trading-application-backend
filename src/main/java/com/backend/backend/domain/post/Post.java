package com.backend.backend.domain.post;

import com.backend.backend.domain.review.Review;
import com.backend.backend.domain.member.Member;
import com.backend.backend.exception.NotExistException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    Member writer;

    @Column(nullable = false)
    @Setter
    private String postName;
    @Setter
    @Lob
    private String content;

    private Long price;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private PostStatus postStatus;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime date;


    @Builder
    public Post(Member writer, String postName, String content, LocalDateTime transactionDate,Long price) {
        if(writer==null){
            throw new NotExistException("작성자가 없습니다");
        }
        Assert.hasText(postName, "게시글 이름이 없습니다");

        if(price==null){
            price=0L;
        }

        this.writer = writer;
        this.postName = postName;
        this.content = content;
        this.postStatus = PostStatus.OPEN;
        this.transactionDate = transactionDate;
        this.price = price;
    }
}
