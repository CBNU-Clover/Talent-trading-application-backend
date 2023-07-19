package com.backend.backend.mvc.domain.chat.message.values;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class MessageContent {
    private String content;

    public MessageContent() {
    }

    public MessageContent(String content) {
        validateCheck(content);
        this.content = content;
    }

    private void validateCheck(String content) {
        if (content==null) {
            throw new IllegalArgumentException("메시지 내용은 null이 될 수 없습니다");
        }

        if (content.isBlank()) {
            throw new IllegalArgumentException("메시지 내용이 비어있습니다");
        }

    }

    public static MessageContent from(String content) {
        return new MessageContent(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageContent content1 = (MessageContent) o;
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
