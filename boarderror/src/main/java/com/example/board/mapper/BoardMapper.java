package com.example.board.mapper;
import com.example.board.domain.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시글 목록
    public List<BoardVO> getList();

    // 게시글 등록
    public void insert(BoardVO boardVO);

    // 게시글 상세보기
    public BoardVO getDetail(Long bno); // 딱 하나 가져오면 리턴 타입에 BoardVO

    // 게시글 삭제
    public int delete(Long bno); // insert, delete, update는 int로 리턴 해줌

    // 게시글 수정
    public int update(BoardVO boardVO);
//    삭제, 수정 메소드는 리턴타입으로 int를 받아서
    // 실행한 횟수를 알수 있도록 하자!

}
