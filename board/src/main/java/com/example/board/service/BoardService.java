package com.example.board.service;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;

import java.util.List;

public interface BoardService {
    public void register(BoardVO boardVO);
    public BoardVO read(Long bno);
    public boolean modify(BoardVO boardVO);
    public boolean remove(Long bno);
    public int getTotal();
    public List<BoardVO> getList(Criteria criteria);
    /*앞에 Long 처럼 클래스 타입을 쓰자. 그냥 자료형 쓰자 뭐 암튼 클래스타입이 좋대 */
}
