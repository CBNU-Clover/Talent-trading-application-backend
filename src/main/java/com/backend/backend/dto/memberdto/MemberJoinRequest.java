package com.backend.backend.dto.memberdto;

import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberJoinRequest {

    private String nickName;
    private String email;
    private String name;
    private String passWord;
    private String phoneNumber;

}
