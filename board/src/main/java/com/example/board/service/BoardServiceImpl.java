package com.example.board.service;

import com.example.board.domain.dao.BoardDAO;
import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service /*여기로 찾아오는듯...*/
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{ /*인터페이스 사용하는 implements*/
    private final BoardDAO boardDAO;
    /*boardDAO를 final로 상수취급*/

    @Override/*재정의*/
    public void register(BoardVO boardVO) {
        boardDAO.register(boardVO);
    }

    @Override
    public BoardVO read(Long bno) {
        return boardDAO.read(bno);
    }

    @Override
    public boolean modify(BoardVO boardVO) {
        return boardDAO.modify(boardVO);
    }

    @Override
    public boolean remove(Long bno) {
        return boardDAO.remove(bno);
    }

    @Override
    public List<BoardVO> getList(Criteria criteria) {
        return boardDAO.getList(criteria);
    }

    @Override
    public int getTotal() {
        return boardDAO.getTotal();
    }
}