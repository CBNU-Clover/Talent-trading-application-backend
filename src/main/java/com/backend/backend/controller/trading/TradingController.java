package com.backend.backend.controller.trading;

import com.backend.backend.DataProcessing.TokenParsing;
import com.backend.backend.controller.trading.tradeDto.TradePost;
import com.backend.backend.controller.trading.tradeDto.TradingHistory;
import com.backend.backend.domain.transactionDetail.TransactionDetail;
import com.backend.backend.service.TalentTradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vi/Trading_Talent")
@RequiredArgsConstructor
public class TradingController {

    private final TalentTradingService talentTradingService;


    /**
     * Post에 가격에 맞게 거래함
     * @param tradePost
     * @return
     */
    @PostMapping("/trade")
    public void trade(HttpServletRequest request, @RequestBody @Valid TradePost tradePost){
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        // 클라이언트에서 postID값만 보내서 받는다.
        talentTradingService.talentTrading(tradePost.getTradePost_id(),result);
    }

    @GetMapping("/trading_history")
    public List<TradingHistory> trade(HttpServletRequest request){


        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        List<TransactionDetail>trade_info=talentTradingService.tradinghistory(result);
        List<TradingHistory> tradingHistories=new ArrayList<>();
        for(int num=0 ; num<trade_info.size();num++)
        {
            tradingHistories.add(num,new TradingHistory(trade_info.get(num).getPostName(),trade_info.get(num).getPrice(),trade_info.get(num).getBuyer().getNickname(),trade_info.get(num).getSeller().getNickname(),trade_info.get(num).getStartDate().toString().replace("T"," ")));
        }

        return tradingHistories;
        // 클라이언트에서 postID값만 보내서 받는다.
    }

}
