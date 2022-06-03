package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
// sql과 자바가 합쳐지는 곳
@Mapper
public interface ReplyMapper {
    // 댓글 추가하기 수연
    public void insertReply(ReplyVO replyVO);

    // 댓글 한 개 조회하기
    public ReplyVO getReplyDetail(Long replyNumber);

    // 댓글 리스트 조회하기
    public List<ReplyVO> getReplyList(@Param("criteria") Criteria criteria, @Param("boardBno")Long boardBno);
    // 강사님은 criteria 추가하셨음
    // 맵에 담아주는 어노테이션을 쓰자. @Param 그럼 키값에 담아준다
    // 원래는 맵에 담아서 해쉬맵에 담아야함

    // 댓글 수정하기
    public int updateReply(ReplyVO replyVO);

    // 댓글 삭제하기
    public int deleteReply(Long replyNumber);

    // 댓글 검색하기
    // 답댓글
}
