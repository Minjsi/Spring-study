package com.example.board.damain.dao;

import com.example.board.domain.dao.ReplyDAO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.mapper.ReplyMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyDaoTests {
    /*보드 다오 주입해서 테스트*/
    @Autowired
    private ReplyDAO replyDAO;

//    @Test
//    public void getReplyListTest(){
//        replyDAO.getReplyList(197L).stream().map(ReplyVO::toString).forEach(log::info);
//    }

    @Test
    public void getReplyListTest(){
        replyDAO.getReplyList(new Criteria(2,10), 2l)
                .stream().map(ReplyVO::toString).forEach(log::info);
    }

    @Test
    public void registerReplyTest(){
        ReplyVO replyVO = new ReplyVO();

        replyVO.setBoardBno(197L);
        replyVO.setReplyContent("방가워요 저는 김수연연");
        replyVO.setReplyWriter("김수연");

        replyDAO.registerReply(replyVO);

        log.info("댓글 번호" + replyVO.getReplyNumber()); // insert 하고 나서 잘 들어갔는지 Bno 확인해보기
    }

    @Test
    public void readReplyTest() {
        Long replyNumber = 3L;
        log.info(replyDAO.readReply(replyNumber).toString());
    }

    @Test
    public void modifyReplyTest(){
        Long replyNumber = 4L;
        ReplyVO replyVO = replyDAO.readReply(replyNumber);

        if(replyVO == null) { log.info("NO Reply"); return;}

        replyVO.setReplyContent("수정된 댓글댓글22");
        replyVO.setReplyWriter("김수정아니고 김수연22");
        replyVO.setReplyNumber(2L);

        if(replyDAO.modifyReply(replyVO)){
            log.info("UPDATE SUCCESS");
        }else{
            log.info("UPDATE FAIL");
        }
    }


    @Test
    public void removeReplyTest() {
        Long replyNumber = 4L;
        if(replyDAO.removeReply(replyNumber)) {
            log.info("삭제 성ㅅ공ㄱ오");

        }
    }
}
