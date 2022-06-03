package com.example.board.domain.dao;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository /*dao가 repository라고 알려줘야 함*/
@RequiredArgsConstructor /* 보드 맵퍼 생성자 주입 */ // final붙은거 초기화시켜줌
@Slf4j
public class ReplyDAO { /* 보드맵퍼 주입 받아서 써야함  mapper를 바로 쓰지 않고 dao를 거쳐서 필터링 해주는 작업임  */
    private final ReplyMapper replyMapper;
    // 댓글 추가하기
    public void registerReply(ReplyVO replyVO) {replyMapper.insertReply(replyVO);}

    // 댓글 1개 조회하기

    public ReplyVO readReply(Long replyNumber){
        log.info("remove ====" + replyNumber);
        return replyMapper.getReplyDetail(replyNumber);
    };

    //댓글 목록
    //    public List<ReplyVO> getReplyList(Long boardBno){return replyMapper.getReplyList(criteria, boardBno);}
    public List<ReplyVO> getReplyList(Criteria criteria, Long boardBno){
        log.info("getList........ : " + criteria);
        log.info("getList........ : " + boardBno);
        return replyMapper.getReplyList(criteria, boardBno);
    }


    // 댓글 수정하기
    public boolean modifyReply(ReplyVO replyVO){return replyMapper.updateReply(replyVO) == 1;};

    // 댓글 삭제하기
    public boolean removeReply(Long replyNumber){return replyMapper.deleteReply(replyNumber) == 1;};

// 댓글 검색하기
// 답댓글
}