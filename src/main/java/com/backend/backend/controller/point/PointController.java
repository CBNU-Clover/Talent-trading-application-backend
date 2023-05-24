package com.backend.backend.controller.point;


import com.backend.backend.DataProcessing.TokenParsing;
import com.backend.backend.controller.point.dto.ChargePoint;
import com.backend.backend.controller.trading.tradeDto.TradePost;
import com.backend.backend.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/vi/Point")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @PostMapping("/charge_point")
    public void charge_point(HttpServletRequest request, @RequestBody @Valid ChargePoint chargePoint){
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        chargePoint.setSender("은행");
        // 클라이언트에서 postID값만 보내서 받는다.
        pointService.chargePoint(result,"은행", chargePoint.getPoint());
    }
}
