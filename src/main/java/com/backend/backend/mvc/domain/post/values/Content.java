package com.backend.backend.mvc.domain.post.values;

import lombok.Setter;

import javax.persistence.Lob;
import java.util.Objects;

public class Content {

    @Lob
    private String content;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected Content() {

    }

    private Content(String content) {
        validateCheck(content);
        this.content = content;
    }

    private void validateCheck(String content) {
        if (content == null) {
            throw new IllegalArgumentException("게시글 제목을 입력해주세요.");
        }
    }

    public static Content from(String content) {
        return new Content(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content1 = (Content) o;
        return Objects.equals(content, content1.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
