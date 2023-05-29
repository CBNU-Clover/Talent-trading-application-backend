package com.backend.backend.controller.point.dto;

import com.backend.backend.domain.pointDetail.PointStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class PointHistory {


    private String  recipient;

    private Long  amount;
    private Long  balance;
    private String date;

    @Enumerated(EnumType.STRING)
    private String status;



    public PointHistory(String recipient,Long amount,Long balance,String status,String date) {
        this.recipient=recipient;
        this.amount=amount;
        this.balance=balance;
        this.status = status;
        this.date=date;
    }

    public PointHistory()
    {

    }


}
