package com.example.board.service;

import com.example.board.domain.vo.ConcertVO;

import java.util.List;

public interface ConcertService {
    public void register(ConcertVO concertVO);
    public ConcertVO read(Long cno);
    public boolean modify(ConcertVO concertVO);
    public boolean remove(Long cno);
    public int getTotal();
    public List<ConcertVO> getList();
    /*앞에 Long 처럼 클래스 타입을 쓰자. 그냥 자료형 쓰자 뭐 암튼 클래스타입이 좋대 */
}
