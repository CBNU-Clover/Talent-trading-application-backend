package com.backend.backend.mvc.controller.point;


import com.backend.backend.common.DataProcessing.TokenParsing;
import com.backend.backend.mvc.controller.point.dto.ChargePoint;
import com.backend.backend.mvc.controller.point.dto.PointHistory;
import com.backend.backend.mvc.controller.point.dto.ShowPointDTO;
import com.backend.backend.mvc.domain.pointDetail.PointDetail;
import com.backend.backend.mvc.domain.pointDetail.PointStatus;
import com.backend.backend.mvc.service.MemberService;
import com.backend.backend.mvc.service.PointService;
import lombok.RequiredArgsConstructor;
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
    public ShowPointDTO show_point(HttpServletRequest request)
    {
        Long point;
        ShowPointDTO showPointDTO=new ShowPointDTO();
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        point=pointService.showPoint(result);
        showPointDTO.setPoint(point);
        showPointDTO.setNickname(result);
        return showPointDTO;
    }

    @GetMapping("/point_history")
    public List<PointHistory> point_history(HttpServletRequest request)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        List<PointDetail>point_history_info=pointService.point_history(result);
        List<PointHistory> pointHistories=new ArrayList<>();
        for(int num=0 ; num<point_history_info.size();num++)
        {
            if(point_history_info.get(num).getStatus().equals(PointStatus.DEPOSIT))
            {
                pointHistories.add(num,new PointHistory(point_history_info.get(num).getRecipient(),point_history_info.get(num).getAmount(),point_history_info.get(num).getBalance(),"입금",point_history_info.get(num).getDate().toString().replace("T"," ")));
            }
            else if(point_history_info.get(num).getStatus().equals(PointStatus.TRANSFER)){
                pointHistories.add(num,new PointHistory(point_history_info.get(num).getRecipient(),point_history_info.get(num).getAmount(),point_history_info.get(num).getBalance(),"출금",point_history_info.get(num).getDate().toString().replace("T"," ")));
            }
           
        }

        return pointHistories;
    }

}
