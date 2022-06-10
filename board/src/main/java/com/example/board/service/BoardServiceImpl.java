package com.example.board.service;

import com.example.board.domain.dao.BoardDAO;
import com.example.board.domain.dao.FileDAO;
import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service /*여기로 찾아오는듯...*/
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{ /*인터페이스 사용하는 implements*/
    private final BoardDAO boardDAO;
    /*boardDAO를 final로 상수취급*/
    private final FileVO fileVO;
    private final FileDAO fileDAO;

    @Override
    public List<FileVO> getList(Long boardBno) {
        return fileDAO.findByBoardBno(boardBno);
    }

    @Override/*재정의*/
    // 하나의 트랜젝션에 여러 개의 DML 이 있을 경우 한 개라도 오류 시 전체 ROLLBACK
    @Transactional(rollbackFor = Exception.class)
    public void register(BoardVO boardVO) {
        // 게시글 추가
        boardDAO.register(boardVO);
        // 게시글에 업로드된 첨부파일 정보 중 게시글 번호를 따로 추가
        boardVO.getFileList().forEach(FileVO -> {
            fileVO.setBoardBno(boardVO.getBoardBno());
            fileDAO.registerFile(fileVO);
        });
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