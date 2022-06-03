package com.example.board.service;

import com.example.board.domain.vo.ConcertVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ConcertServiceTests {
    @Autowired
    private ConcertService concertService;

    @Test
    public void getListTest(){
        concertService.getList().stream().map(ConcertVO::toString).forEach(log::info);
    }


    @Test
    public void registerTest(){
        ConcertVO concertVO = new ConcertVO();
        concertVO.setConcertName("한동석 팬미팅");
        concertVO.setConcertDate("20220606");
        concertVO.setConcertPrice(300000L);
        concertVO.setUserNumber(1L);

        concertService.register(concertVO);
        log.info("콘서트 번호 : " + concertVO.getConcertNumber());
    }

    @Test
    public void readTest(){
        Long cno = 3L;
        log.info(concertService.read(cno).toString());
    }

    @Test
    public void removeTest(){
        Long cno = 21L;
        if(concertService.remove(cno)){
            log.info("DELETE SUCCESS");
            return;
        }

        log.info("DELETE FAIL");
    }

    @Test
    public void modifyTest(){
        Long cno = 37L;
        ConcertVO concertVO = concertService.read(cno);
        if(concertVO == null) { log.info("NO BOARD"); return;}
        concertVO.setConcertName("한동석 썸머 투어");
        concertVO.setConcertDate("20220808");
        concertVO.setConcertPrice(50000L);
        concertVO.setUserNumber(1L);
        if(concertService.modify(concertVO)){
            log.info("UPDATE SUCCESS");
        }else{
            log.info("UPDATE FAIL");
        }
    }

    @Test
    public void getTotalTest(){
        log.info("총 개수 : " + concertService.getTotal());
    }
}
