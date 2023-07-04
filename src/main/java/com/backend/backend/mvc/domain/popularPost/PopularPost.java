package com.backend.backend.mvc.domain.popularPost;

import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.post.values.PostName;
import com.backend.backend.mvc.domain.post.values.Price;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopularPost {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Post post;

    /**
     * 인기게시글 순서를 위한
     */
    private Long viewCount;

    public PopularPost(Post post,Long viewCount) {
        if(post==null){
            throw new IllegalArgumentException("post가 null이면 안됩니다");
        }
        if(viewCount==null){
            throw new IllegalArgumentException("viewCount가 null이면 안됩니다");
        }

        this.post = post;
        this.viewCount = viewCount;
    }
}
