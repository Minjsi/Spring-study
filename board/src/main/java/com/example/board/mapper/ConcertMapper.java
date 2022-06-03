package com.example.board.mapper;
import com.example.board.domain.vo.ConcertVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ConcertMapper {
    // 콘서트 목록
    public List<ConcertVO> getList();

    // 콘서트 개수
    public int getTotal();

    // 콘서트 등록
    public void insert(ConcertVO concertVO);

    // 콘서트 정보
    public ConcertVO detail(Long cno);

    // 콘서트 삭제
    public int delete(Long cno);

    // 콘서트 수정
    public int update(ConcertVO concertVO);
}
