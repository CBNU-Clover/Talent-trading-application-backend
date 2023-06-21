package com.backend.backend.mvc.domain.post.values;

import com.backend.backend.mvc.domain.rating.values.Score;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.swing.text.ViewFactory;
import java.util.Objects;

@Embeddable
@Getter
public class ViewCount {
    @Column(name = "view_count")
    private Long count=0L;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected ViewCount() {

    }


    private void validateCheck(Long amount) {
        if (amount == null) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("값이 0보다 커야 합니다");
        }
    }

    public static ViewCount from() {
        return new ViewCount();
    }

    public void addCount(Long count) {
        validateCheck(count);
        this.count += count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewCount viewCount = (ViewCount) o;
        return Objects.equals(count, viewCount.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return count.toString();
    }
}
