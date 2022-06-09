package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Autowired
    private ReplyMapper replyMapper;
//    강사님정답
    private Long[] arBno = {5L, 6L, 7L, 8L, 9L};
    @Test
    public void replyMapperTest(){
        log.info(replyMapper + "");
    }

//    내.... 코드 ㅎ_ㅎ
     @Test
    public void insertReplyTest(){
        ReplyVO replyVO = new ReplyVO();
        List<Long> replyArray = new ArrayList<>();
        for(Long i = 5L ; i < 10L ; i++ ){
            replyVO.setBoardBno(i);
            for(Long j = 0L ; j < 30L ; j++ ) {
                replyVO.setReplyContent("댓글댓글내용" + j);
                replyVO.setReplyWriter("댓글댓글작성자" + j);
                replyMapper.insertReply(replyVO);
                log.info("게시글 번호 : " + replyVO.getReplyNumber());
            }
        }
    }
//  강사님 코드
    @Test
     public void replyInsert(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO replyVO = new ReplyVO();
            replyVO.setBoardBno(arBno[ i % 5 ]);
            replyVO.setReplyContent("댓글댓글내용5" + i);
            replyVO.setReplyWriter("댓글댓글작성자5" + i);
            replyMapper.insertReply(replyVO);
            log.info("게시글 번호 : " + replyVO.getReplyNumber());

        });
    }

//    이재원님
//    @Test
//    public void replyInsert(){
//        log.info("========================");
//        Criteria criteria = new Criteria();
//        criteria.setPageNum(1);
//        criteria.setAmount(5);
//        List<BoardVO> list = boardMapper.getList(criteria);
//        int i = 1;
//        for(BoardVO boardVO : list){
//            Long boardBon = boardVO.getBoardBno();
//            ReplyVO replyVO = new ReplyVO();
//            for(int j=0; j<2; j++){
//                replyVO.setBoardBno( boardBon );
//                replyVO.setReplyContent("뎃글 테스트2차"+i);
//                replyVO.setReplyWriter("runeMaster");
//                i+= replyMapper.insertReply(replyVO);
//            }
//        }
//        log.info("========================");
//    }


    @Test
    public void getReplyListTest(){
        replyMapper.getReplyList(new Criteria(2,10), 8l)
                .stream().map(ReplyVO::toString).forEach(log::info);
    }

    @Test
    public void getReplyDetailTest(){
        Long replyNumber = 3L;
        log.info(replyMapper.getReplyDetail(replyNumber).toString());
    }

    @Test
    public void deleteReplyTest(){
        Long replyNumber = 5L;
        log.info("DELETE COUNT : " + replyMapper.deleteReply(replyNumber));
    }

    @Test
    public void modifyTest(){
        Long replyNumber = 4L;
        ReplyVO replyVO = replyMapper.getReplyDetail(replyNumber);
        if(replyVO == null) { log.info("NO Reply"); return;}

        replyVO.setReplyContent("수정된 댓글댓글");
        replyVO.setReplyWriter("김수정아니고 김수연");
        replyVO.setReplyNumber(2L);

        log.info("몇 개 수정?? " + replyMapper.updateReply(replyVO));
    }

    @Test
    public void getReplyTotal(){
        log.info("댓글 개수" + replyMapper.getReplyTotal(9L));
    }
}
