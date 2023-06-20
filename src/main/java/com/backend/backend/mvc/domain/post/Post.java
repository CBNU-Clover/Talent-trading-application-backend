package com.backend.backend.mvc.domain.post;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.common.exception.NotExistException;
import com.backend.backend.mvc.domain.post.values.Content;
import com.backend.backend.mvc.domain.post.values.PostName;
import com.backend.backend.mvc.domain.post.values.PostStatus;
import com.backend.backend.mvc.domain.post.values.Price;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Embedded
    private PostName postName;

    @Embedded
    private Content content;

    @Embedded
    private Price price;

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

        this.writer = writer;
        this.postName = PostName.from(postName);
        this.content = Content.from(content);
        this.postStatus = PostStatus.OPEN;
        this.transactionDate = transactionDate;
        this.price = Price.from(price);
    }

    public void setPostName(String  postName) {
        this.postName = PostName.from(postName);
    }

    public void setContent(String content) {
        this.content = Content.from(content);
    }

    public void setPrice(Long price) {
        this.price = Price.from(price);
    }
}
