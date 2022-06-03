package com.example.board.damain.dao;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.dao.BoardDAO;
import com.example.board.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardDaoTests {
    /*보드 다오 주입해서 테스트*/
    @Autowired
    private BoardDAO boardDAO;



    @Test
    public void getListTest(){
        boardDAO.getList(new Criteria()).stream().map(BoardVO::toString).forEach(log::info);
    }


    @Test
    public void registerTest(){
        BoardVO boardVO = new BoardVO();

        boardVO.setBoardTitle("하이하이");
        boardVO.setBoardContent("방가워요 저는 김수연연");
        boardVO.setBoardWriter("김수연");

        boardDAO.register(boardVO);

        log.info("게시글 번호" + boardVO.getBoardBno()); // insert 하고 나서 잘 들어갔는지 Bno 확인해보기
    }

    @Test
    public void modifyTest(){
        /*boardVO.setBoardBno((long) 2); 내가 한 방법 */

        Long boardBno = 195L;
        BoardVO boardVO = boardDAO.read(boardBno);/*강사님 방법*/

        if(boardVO == null ) { log.info("NO BOARD"); return;}; // 없을 때 테스트

        boardVO.setBoardTitle("아아로 수정");
        boardVO.setBoardContent("콘텐트는 아아아");
        boardVO.setBoardWriter("수연김김김");

        if(boardDAO.modify(boardVO)){
            log.info("UPDATE SUCCESS");
        }else{
            log.info("UPDATE FAIL");
        }
    }

    @Test
    public void readTest() {
        Long boardBno = 3L;
        log.info(boardDAO.read(boardBno).toString());
    }

    @Test
    public void removeTest() {
        Long boardBno = 2L;
        if(boardDAO.remove(boardBno)) {
            log.info("삭제 성ㅅ공ㄱ오");

        }
    }
}
