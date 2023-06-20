package com.backend.backend.mvc.domain.post.values;

import com.backend.backend.mvc.domain.pointDetail.values.Recipient;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class PostName {
    private String postName;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected PostName() {

    }

    private PostName(String PostName) {
        validateCheck(PostName);
        this.postName = PostName;
    }

    private void validateCheck(String postName) {
        if (postName == null) {
            throw new IllegalArgumentException("게시글 제목을 입력해주세요.");
        }
    }

    public static PostName from(String postName) {
        return new PostName(postName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostName postName1 = (PostName) o;
        return Objects.equals(postName, postName1.postName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postName);
    }

    @Override
    public String toString() {
        return postName;
    }
}
