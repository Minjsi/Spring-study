package com.example.board.domain.dao;

import com.example.board.domain.vo.ConcertVO;
import com.example.board.mapper.ConcertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository /*dao가 repository라고 알려줘야 함*/
@RequiredArgsConstructor /* 보드 맵퍼 생성자 주입 */
public class ConcertDAO { /* 보드맵퍼 주입 받아서 써야함  mapper를 바로 쓰지 않고 dao를 거쳐서 필터링 해주는 작업임  */
    private final ConcertMapper concertMapper;

    public List<ConcertVO> getList(){
        return concertMapper.getList();
    };

    public void register(ConcertVO concertVO){
        concertMapper.insert(concertVO);
    };

    public ConcertVO read(Long cno){
        return concertMapper.detail(cno);
    };

    public boolean remove(Long cno){return concertMapper.delete(cno) != 0;};

    public boolean modify(ConcertVO concertVO){ /* dao에서는 boolean 을 리턴하자*/
        return concertMapper.update(concertVO) != 0;
    }

    public int getTotal(){ /* dao에서는 boolean 을 리턴하자*/
        return concertMapper.getTotal();
    }
}
