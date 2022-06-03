package com.example.board.domain.dao;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository /*dao가 repository라고 알려줘야 함*/
@RequiredArgsConstructor /* 보드 맵퍼 생성자 주입 */
public class BoardDAO { /* 보드맵퍼 주입 받아서 써야함  mapper를 바로 쓰지 않고 dao를 거쳐서 필터링 해주는 작업임  */
    private final BoardMapper boardMapper;

    public List<BoardVO> getList(Criteria criteria){
        return boardMapper.getList(criteria);
    };

    public void register(BoardVO boardVO){
        boardMapper.insert(boardVO);
    };

    public BoardVO read(Long bno){
        return boardMapper.getDetail(bno);
    };

    public boolean remove(Long bno){return boardMapper.delete(bno) != 0;};

    public boolean modify(BoardVO boardVO){ /* dao에서는 boolean 을 리턴하자*/
        return boardMapper.update(boardVO) != 0;
    }

    public int getTotal(){ /* dao에서는 boolean 을 리턴하자*/
        return boardMapper.getTotal();
    }
}
