package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardMapperTests {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void getListTest(Criteria criteria){
        boardMapper.getList(criteria).stream().map(BoardVO::toString).forEach(log::info);
    }

    @Test
    public void getTotalTest(){
        log.info("-----------------" + boardMapper.getTotal());
        log.info("총 개수 " + boardMapper.getTotal());
        log.info("----------------- " + boardMapper.getTotal());
    }

    @Test
    public void insertTest(){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardTitle("새로운 게시글 제목2");
        boardVO.setBoardContent("새로운 게시글 내용2");
        boardVO.setBoardWriter("hds1111");

        boardMapper.insert(boardVO);

        log.info("게시글 번호 : " + boardVO.getBoardBno());
    }

    @Test
    public void getDetailTest(){
        Long boardBno = 195L;
        log.info(boardMapper.getDetail(boardBno).toString());
    }

    @Test
    public void deleteTest(){
        Long boardBno = 197L;
        log.info("DELETE COUNT : " + boardMapper.delete(boardBno));
    }

        @Test
        public void modifyTest(){
            Long boardBno = 10L;
            BoardVO boardVO = boardMapper.getDetail(boardBno);
            if(boardVO == null) { log.info("NO BOARD"); return;}

            boardVO.setBoardTitle("수정된 게시글 제목");
            boardVO.setBoardContent("수정된 게시글 내용");
            boardVO.setBoardWriter("한동석");

            log.info("몇 개 수정?? " + boardMapper.update(boardVO));
        }
}













