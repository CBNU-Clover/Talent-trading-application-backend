package com.backend.backend.domain.post;

import com.backend.backend.domain.review.Review;
import com.backend.backend.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    Member writer;

    @Column(nullable = false)
    @Setter
    private String postName;
    @Setter
    @Lob
    private String content;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime date;


    @Builder
    public Post(Member writer, String postName, String content) {
        this.writer = writer;
        this.postName = postName;
        this.content = content;
    }
}
