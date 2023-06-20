package com.backend.backend.mvc.domain.pointDetail.values;

public class Sender {
    private String name;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected Sender() {

    }

    private Sender(String name) {
        validateCheck(name);
        this.name = name;
    }

    private void validateCheck(String name) {
        if (name == null) {
            throw new IllegalArgumentException("발신처를 입력해주세요.");
        }
    }

    public static Sender from(String name) {
        return new Sender(name);
    }
}
