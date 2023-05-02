package com.backend.backend.repository.memberRepository;

import lombok.Builder;
import lombok.Getter;

/**
 * 이 객체에 들어가 있는 정보들에 해당하는 값들을 검색시에 사용
 */
@Getter
@Builder
public class MemberSearch {
    private String nickname;
}
