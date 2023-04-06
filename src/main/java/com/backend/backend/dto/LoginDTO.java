package com.backend.backend.dto;

import lombok.Getter;


public class LoginDTO {
    private String nickName;
    private String passWord;

    public String getNickName() {
        return nickName;
    }

    public String getPassWord() {
        return passWord;
    }
}
