package com.backend.backend.mvc.controller.member.memberdto;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class MemberJoinRequest {

    private String nickname;
    private String email;
    private String name;
    private String passWord;
    private String phoneNumber;
    private byte[] image;

    public MemberJoinRequest(String nickname, String email, String name, String passWord, String phoneNumber, byte[] image) {
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.image=image;
    }
    public MemberJoinRequest(String nickname, String email, String name, String passWord, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.image=null;
    }



    public MemberJoinRequest() {

    }
}
