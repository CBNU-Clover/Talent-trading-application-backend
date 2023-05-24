package com.backend.backend.controller.point;


import com.backend.backend.DataProcessing.TokenParsing;
import com.backend.backend.controller.point.dto.ChargePoint;
import com.backend.backend.controller.post.Dto.PostGetAllBoard;
import com.backend.backend.controller.trading.tradeDto.TradePost;
import com.backend.backend.domain.post.Post;
import com.backend.backend.service.MemberService;
import com.backend.backend.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vi/Point")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final MemberService memberService;

    @PostMapping("/charge_point")
    public void charge_point(HttpServletRequest request, @RequestBody @Valid ChargePoint chargePoint){
        System.out.println(chargePoint.getPoint());
        System.out.println("+++++++++++++++++++++++++++");
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        // 클라이언트에서 postID값만 보내서 받는다.
        pointService.chargePoint(result,"은행", chargePoint.getPoint());
    }

    @GetMapping("/show_point")
    public Long show_point(HttpServletRequest request)
    {
        Long point;
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        point=pointService.showPoint(result);
        return point;
    }
}
