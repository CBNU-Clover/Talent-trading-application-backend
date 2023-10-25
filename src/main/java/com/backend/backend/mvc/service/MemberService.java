package com.backend.backend.mvc.service;

import com.backend.backend.mvc.controller.member.memberdto.MyProfile;
import com.backend.backend.mvc.controller.ranking.CalculateRanking;
import com.backend.backend.mvc.domain.image.Image;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.controller.member.memberdto.MemberJoinRequest;
import com.backend.backend.mvc.domain.post.values.PostCategory;
import com.backend.backend.mvc.domain.rating.Rating;
import com.backend.backend.mvc.repository.imageRepository.ImageRepository;
import com.backend.backend.mvc.repository.memberRepository.DbMemberRepository;
import com.backend.backend.common.utils.JwtTokenUtil;
import com.backend.backend.mvc.repository.ratingRepository.RatingRepository;
import com.backend.backend.mvc.service.ranking.ratingPolicy.RatingPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final DbMemberRepository dbMemberRepository;
    private final BCryptPasswordEncoder encoder;
    private final ImageRepository imageRepository;
    private final RatingRepository ratingRepository;
    private final RatingPolicy ratingPolicy;

    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs=1000*60*60l; // 한시간
    public String join(MemberJoinRequest memberJoinRequest)
    {
        Image image = new Image(memberJoinRequest.getImage());
        Long imageId=imageRepository.save(image);
        Member member=Member.builder()
                        .nickname(memberJoinRequest.getNickname())
                        .name(memberJoinRequest.getName())
                        .email(memberJoinRequest.getEmail())
                        .phoneNumber(memberJoinRequest.getPhoneNumber())
                        .password(memberJoinRequest.getPassWord())
                        .image(image)
                        .build();
        dbMemberRepository.save(member);
        
        return "Success";
    }

    public String login(String nickname,String passWord)
    {
        //membernickname없음
        Member selectedmember= dbMemberRepository.findMemberByNickname(nickname);
        if(selectedmember==null)
        {
            return "!회원 정보가 없습니다!";
        }
        // exception 필요

        //memberpassWord틀림
        if(!passWord.equals(selectedmember.getPassword().toString()))
        {
            return "!비밀번호가 틀립니다!";
        }
        else
        {
            String token= JwtTokenUtil.createToken(selectedmember.getNickname().toString(),key,expireTimeMs);
            return token;
        }
    }

    public int check_Nickname(String nickname)
    {
        Boolean check;
        check= dbMemberRepository.nicknameDuplicateCheck(nickname);
        if(check)
        {

            return 1;
        }
        else
        {
            return 0;
        }
    }
    public int check_Email(String email)
    {
        Boolean check;
        check= dbMemberRepository.emailDuplicateCheck(email);
        if(check)
        {

            return 1;
        }
        else
        {
            return 0;
        }
    }

    public MyProfile getMyProfile(String nickname)
    {
        String grade;
        Rating rating=ratingRepository.findRatingByNicknameAndCategory(nickname, PostCategory.OTHER);
        CalculateRanking calculateRanking=new CalculateRanking();
        if(rating==null)
        {
            grade=calculateRanking.calculateRanking(0L);
        }
        else {
            grade=calculateRanking.calculateRanking(rating.getScore());
        }
        Member member=dbMemberRepository.findMemberByNickname(nickname);
        MyProfile myProfile=new MyProfile(nickname,grade,member.getImage().getId().toString());
        return myProfile;
    }
}
