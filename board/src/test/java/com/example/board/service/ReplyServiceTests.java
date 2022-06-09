package com.example.board.service;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;
//
//    @Test
//    public void getReplyListTest(){
//        replyService.getReplyList(new Criteria()).stream().map(BoardVO::toString).forEach(log::info);
//    }

    @Test
    public void getReplyListTest(){
        replyService.getReplyList(new Criteria(1,10), 8l)
                .stream().map(ReplyVO::toString).forEach(log::info);
    }

    @Test
    public void registerReplyTest(){
        ReplyVO replyVO = new ReplyVO();
        replyVO.setBoardBno(197L);
        replyVO.setReplyContent("헤헹 서비스 테스트");
        replyVO.setReplyWriter("헤헹 서비스 김수연");

        replyService.registerReply(replyVO);

        log.info("게시글 번호 : " + replyVO.getReplyNumber());
    }

    @Test
    public void readReplyTest(){
        Long replyNumber = 3L;
        log.info(replyService.readReply(replyNumber).toString());
    }


    @Test
    public void removeReplyTest(){
        Long replyNumber = 3L;
        if(replyService.removeReply(replyNumber)){
            log.info("DELETE SUCCESS");
            return;
        }
        log.info("DELETE FAIL");
    }

    @Test
    public void modifyReplyTest(){
        Long replyNumber = 3L;
        ReplyVO replyVO = replyService.readReply(replyNumber);
        if(replyVO == null) { log.info("NO Reply"); return;}

        replyVO.setReplyContent("수정된 2323댓글댓글22");
        replyVO.setReplyWriter("김수정아니고 2323김수연22");
        replyVO.setReplyNumber(3L);

        if(replyService.modifyReply(replyVO)){
            log.info("UPDATE SUCCESS");
        }else{
            log.info("UPDATE FAIL");
        }
    }

    @Test
    public void getReplyTotalTest() {
        log.info("댓글 개수는 : " + replyService.getReplyTotal(9L));
    }
}
