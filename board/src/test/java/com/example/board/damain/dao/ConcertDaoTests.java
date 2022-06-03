package com.example.board.damain.dao;

import com.example.board.domain.dao.ConcertDAO;
import com.example.board.domain.vo.ConcertVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ConcertDaoTests {
    /*보드 다오 주입해서 테스트*/
    @Autowired
    private ConcertDAO concertDAO;

    @Test
    public void getListTest(){
        concertDAO.getList().stream().map(ConcertVO::toString).forEach(log::info);
    }


    @Test
    public void registerTest(){
        ConcertVO concertVO = new ConcertVO();
        concertVO.setConcertName("한동석 팬미팅");
        concertVO.setConcertDate("20220606");
        concertVO.setConcertPrice(300000L);
        concertVO.setUserNumber(1L);

        concertDAO.register(concertVO);

        log.info("콘서트 번호 : " + concertVO.getConcertNumber());
    }

    @Test
    public void modifyTest(){
        Long cno = 38L;
        ConcertVO concertVO = concertDAO.read(cno);

        if(concertVO == null) { log.info("NO concert"); return;}

        concertVO.setConcertName("한동석 클럽 투어");
        concertVO.setConcertDate("20220808");
        concertVO.setConcertPrice(50000L);
        concertVO.setUserNumber(1L);
        
        if(concertDAO.modify(concertVO)){
            log.info("UPDATE SUCCESS");
        }else{
            log.info("UPDATE FAIL");
        }
    }

    @Test
    public void readTest() {
        Long cno = 3L;
        log.info(concertDAO.read(cno).toString());
    }

    @Test
    public void removeTest() {
        Long cno = 36L;
        if(concertDAO.remove(cno)) {
            log.info("삭제 성ㅅ공ㄱ오");

        }
    }
}
