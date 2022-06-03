package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConcertVO {
    private Long concertNumber;
    private String concertName;
    private String concertDate;
    private Long concertPrice;
    private Long userNumber;
}
