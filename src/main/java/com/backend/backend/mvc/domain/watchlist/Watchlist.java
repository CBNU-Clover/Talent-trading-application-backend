package com.backend.backend.mvc.domain.watchlist;

import com.backend.backend.common.exception.NotExistException;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Watchlist {
    @Id
    @GeneratedValue
    @Column(name = "watchlist_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Watchlist(Member member, Post post) {
        if(member==null){
            throw new NotExistException("회원이 입력되지 않았습니다");
        }
        if(member==null){
            throw new NotExistException("게시글이 입력되지 않았습니다");
        }
        this.member = member;
        this.post = post;
    }
}
