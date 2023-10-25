package com.backend.backend.mvc.controller.member.memberdto;


import lombok.Getter;

@Getter
public class MyProfile {
    private String my_nickname;
    private String my_ranking;
    private String my_image_url;

    public MyProfile(String my_nickname,String my_ranking, String my_image_url) {
        this.my_nickname = my_nickname;
        this.my_ranking=my_ranking;
        this.my_image_url = my_image_url;
    }


    public MyProfile()
    {

    }
}
