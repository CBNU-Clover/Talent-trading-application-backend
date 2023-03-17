package com.backend.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Nonnull
    Member writer;

    @Nonnull
    private String postName;
    private String content;

    @Builder
    public Post(Member writer, String postName, String content) {
        this.writer = writer;
        this.postName = postName;
        this.content = content;
    }
}
