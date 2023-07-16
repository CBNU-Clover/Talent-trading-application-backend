package com.backend.backend.mvc.domain.post;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.common.exception.NotExistException;
import com.backend.backend.mvc.domain.post.values.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostCategory category;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime date;

    @Embedded
    private ViewCount viewCount;


    @Builder
    public Post(Member writer, String postName, String content,
                LocalDateTime transactionDate,Long price,
                PostCategory category) {
        if(writer==null){
            throw new NotExistException("작성자가 없습니다");
        }
        if(category==null){
            category = PostCategory.OTHER;
        }

        this.writer = writer;
        this.postName = PostName.from(postName);
        this.content = Content.from(content);
        this.postStatus = PostStatus.OPEN;
        this.transactionDate = transactionDate;
        this.price = Price.from(price);
        this.viewCount = ViewCount.from();
        this.category = category;
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

    public void addViewCount(){
        viewCount.addCount(1L);
    }
    public void addViewCount(Long num){
        viewCount.addCount(num);
    }
}
