package com.example.board.mapper;

import com.example.board.domain.vo.ConcertVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ConcertMapperTests {
    @Autowired
    private ConcertMapper concertMapper;

    @Test
    public void getListTest(){
        concertMapper.getList().stream().map(ConcertVO::toString).forEach(log::info);
    }

    @Test
    public void getTotalTest(){
        log.info("-----------------" + concertMapper.getTotal());
        log.info("총 개수 " + concertMapper.getTotal());
        log.info("----------------- " + concertMapper.getTotal());
    }

    @Test
    public void insertTest(){
        ConcertVO concertVO = new ConcertVO();
        concertVO.setConcertName("한동석 팬미팅");
        concertVO.setConcertDate("20220606");
        concertVO.setConcertPrice(300000L);
        concertVO.setUserNumber(1L);

        concertMapper.insert(concertVO);

        log.info("콘서트 번호 : " + concertVO.getConcertNumber());
    };

    @Test
    public void getDetailTest(){
        Long cno = 40L;
        log.info(concertMapper.detail(cno).toString());
    }

    @Test
    public void deleteTest(){
        Long cno = 39L;
        log.info("DELETE COUNT : " + concertMapper.delete(cno));
    }

        @Test
        public void modifyTest(){
            Long cno = 38L;
            ConcertVO concertVO = concertMapper.detail(cno);
            if(concertVO == null) { log.info("NO concert"); return;}

            concertVO.setConcertName("한동석 클럽 투어");
            concertVO.setConcertDate("20220808");
            concertVO.setConcertPrice(50000L);
            concertVO.setUserNumber(1L);

            log.info("몇 개 수정?? " + concertMapper.update(concertVO));
        }
}













