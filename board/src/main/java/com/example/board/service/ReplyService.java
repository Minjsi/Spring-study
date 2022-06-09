package com.example.board.service;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;

import java.util.List;

public interface ReplyService {
    public List<ReplyVO> getReplyList(Criteria criteria, Long boardBno);
    public ReplyVO readReply(Long replyNumber);
    public void registerReply(ReplyVO replyVO);
    public boolean modifyReply(ReplyVO replyVO);
    public boolean removeReply(Long replyNumber);
    /*앞에 Long 처럼 클래스 타입을 쓰자. 그냥 자료형 쓰자 뭐 암튼 클래스타입이 좋대 */
    public int getReplyTotal(Long boardBno);
}
