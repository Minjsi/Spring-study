package com.example.board.service;

import com.example.board.domain.dao.ReplyDAO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service /*여기로 찾아오는듯...*/
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{ /*인터페이스 사용하는 implements*/
    private final ReplyDAO replyDAO;

    @Override
    public List<ReplyVO> getReplyList(Criteria criteria, Long boardBno) {
        return replyDAO.getReplyList(criteria, boardBno);
    }

    @Override/*재정의*/
    public ReplyVO readReply(Long replyNumber) {
        return replyDAO.readReply(replyNumber);
    }

    @Override/*재정의*/
    public void registerReply(ReplyVO replyVO) {
        replyDAO.registerReply(replyVO);
    }

    @Override
    public boolean modifyReply(ReplyVO replyVO) {
        return replyDAO.modifyReply(replyVO);
    }

    @Override
    public boolean removeReply(Long boardBno) {
        return replyDAO.removeReply(boardBno);
    }
}