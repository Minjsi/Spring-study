package com.example.board.service;

import com.example.board.domain.dao.ConcertDAO;
import com.example.board.domain.vo.ConcertVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service /*여기로 찾아오는듯...*/
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService{ /*인터페이스 사용하는 implements*/
    private final ConcertDAO concertDAO;
    /*boardDAO를 final로 상수취급*/

    @Override/*재정의*/
    public void register(ConcertVO concertVO) {
        concertDAO.register(concertVO);
    }

    @Override
    public ConcertVO read(Long cno) {
        return concertDAO.read(cno);
    }

    @Override
    public boolean modify(ConcertVO concertVO) {
        return concertDAO.modify(concertVO);
    }

    @Override
    public boolean remove(Long cno) {
        return concertDAO.remove(cno);
    }

    @Override
    public List<ConcertVO> getList() {
        return concertDAO.getList();
    }

    @Override
    public int getTotal() {
        return concertDAO.getTotal();
    }
}