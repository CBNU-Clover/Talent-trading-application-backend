package com.backend.backend.dto.memberdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginRequest {
    private String nickName;
    private String passWord;
}
