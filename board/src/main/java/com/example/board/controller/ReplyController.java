package com.example.board.controller;


import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.FruitVO;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.domain.vo.TestVO;
import com.example.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/reply/*") /* reply 요청하면 일루 들어오게끔 ㅋ */
@RequiredArgsConstructor
/* REST (Representational State Transfer) 데이터 전송 대표 상태
1. 서버가 달라도 데이터를 전송하고 받을 수 있어야함
2. 개발할 때 다양한 언어를 쓴다. 다른 언어에서 다른 언어로 객체를 보낼 수 없기땜에 json을 쓴다. json 형식으로 데이터를 전송
    서버가 달라지고 언어도 달라지니 데이터 전송을 위해서는 json이 필수적이다. 그ㅡㅡㅡㅡㅡㅡㅡㅡㅡ냥 메서드 처럼 불러다가 데이터
    쓰고 싶어.~~~~~
결론 : 서버도 다르고 언어도 다르지만 데이터를 주고 받아 웹을 만들기 위해 json ajax 쓰는 것이고 이런걸 REST 라고 한다~

하나의 uri는 하나의 고유한 리소스(데이터)를 대표하도록 설계된다.
URL이 하나의 정보를 의미한다.
*/
public class ReplyController {
    /* 서비스 객체가 필요해서 주입 받아야함 */
    private final ReplyService replyService;

//    댓글 등록 /* 추가 후 페이지 이동 없어야 함 */
//    브라우저에서 json 타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열을 결과를 리턴한다.
    /*
    consumes : 전달받은 데이터의 타입
    produces : 콜백함수로 결과를 전달할 때의 타입
    ResponseEntity : 서버의 상태코드, 응답 메세지등을 담을 수 있는 타입
    @RequestBody : 전달받은 타입이 자바가 아닌 json 타입이라서 json으로 받은 값을 자동으로 객체 형태로 바꿔주는 어노테이션
                    그래서 json 받아서 굳이 setter를 할 필요가 없삼 하지만 json이므로 이 어노테이션을 써조야함
                    (전달받은 데이터를 알맞는 매개변수로 주입할 때 사용)

      * */
   // 댓글 입력
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8") // 정해져 있는 형식
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {
        // ResponseEntity<String> 은 예전 방식 이다 ~
        // rest 되고 전달받은 데이터가 위 create requestbody 의 replyVO로 들어감
        log.info("replyVO" + replyVO); // 제대로 전달 받았는지 확인해보장장
//        댓글에 대한 전체 정보
        replyService.registerReply(replyVO); // try catch로 잡아주쟝
        return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"), HttpStatus.OK);
        // OK는 성공했다는 의미이므로 ok를 써야 ajax에 success 콜백 함수가 실행된다.
        // 콜백함수에 전달할 응답 데이터를 전달해주자 단 String 타입으로 우리가 썼으니 new String 으로 댓글등록 성공 보내주자
        // getBytes로 전체를 바이트로 바꿔주는 대신 utf-8로 한글 안 깨지게 해주자
        // 성공하면 콜백함수 f("댓글 등록 성공") 이렇게 들어가는 원리
    }

//   게시글 댓글 1개 조회
    /*최신 버전 예시*/
    @GetMapping("/{rno}") // 중괄호안에 받을 값의 이름을 적어줘야함 /reply/27 ..28.. 29 번 댓글 이런식으로
    public ReplyVO readReply(@PathVariable("rno") Long replyNumber) {
        log.info("read.......................");
        log.info("read......................." + replyNumber);
        log.info("read.......................");
        return replyService.readReply(replyNumber);
        // restcontroller 는 뷰리졸버가 관여하지 않음 따라서 리턴타입에 replyVO
    }

//   게시글 전체 댓글 조회 - 내 코드 : 오늘만 개발하는 타입 ^ ^
    @GetMapping("/list/{brno}")
//    public List<ReplyVO> getReplyList(Criteria criteria)
    public List<ReplyVO> getReplyList(Criteria criteria, @PathVariable("brno") Long boardBno) {
        log.info("getReplyList.......................");
        log.info("getReplyList......................." + boardBno);
        log.info("getReplyList.......................");
        return replyService.getReplyList(new Criteria(1, 5), boardBno);
//        return replyService.getReplyList(criteria, boardBno);
    }

//    게시글 전체 댓글 조회 - 강사님 코드
    @GetMapping("/list/{bno}/{page}")
    public List<ReplyVO> getReplyList(@PathVariable("page") int pageNum, @PathVariable("bno") Long boardBno) {
       return replyService.getReplyList(new Criteria(pageNum, 10), boardBno);
    }

    //   게시글 댓글 삭제 - 내 코드
    @PostMapping("/remove/{rno}")
    public boolean removeReply(@PathVariable("rno") Long replyNumber, Criteria criteria) {
        log.info("removeReply.......................");
        log.info("removeReply......................." + replyNumber);
        log.info("removeReply.......................");
        return replyService.removeReply(replyNumber);
    }

    //   게시글 댓글 삭제 - 강사님 코드
    @DeleteMapping("/{rno}") // 위에 rno 경로가 이미 있으니 post나 get 둘중하나 쓰셔야한다 // 근데 성공 후에 새로고침 해야되니까 json 쓰면 안되겠쥬?
    public String removeReply(@PathVariable("rno") Long replyNumber){
        replyService.removeReply(replyNumber);
        return "댓글 삭제 성공";
    }

    //   게시글 댓글 수정 - 내 코드
    @PostMapping("/modify1/{rno}")
    public boolean modifyReply(@PathVariable("rno") Long replyNumber, Criteria criteria, ReplyVO replyVO) {
        log.info("modifyReply.......................");
        log.info("modifyReply......................." + replyNumber);
        log.info("modifyReply.......................");
        return replyService.modifyReply(replyVO);
    }
    @PostMapping("/modify2")
    public boolean modifyReply2(Long replyNumber, Criteria criteria, ReplyVO replyVO) {
        log.info("modifyReply.......................");
        log.info("modifyReply......................." + replyNumber);
        log.info("modifyReply.......................");
        return replyService.modifyReply(replyVO);
    }
    @PostMapping(value = "/modify3", consumes = "application/json", produces = "text/plain; charset=utf-8") // 정해져 있는 형식
    public ResponseEntity<String> modifyReply3(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {
        log.info("replyVO" + replyVO); // 제대로 전달 받았는지 확인해보장장
        replyService.modifyReply(replyVO); // try catch로 잡아주쟝
        return new ResponseEntity<>(new String("댓글 수정 성공".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    //   게시글 댓글 수정 - 강사님 코드
    /* 두가지 방식이 있삼 put 방식 patch 방식 (전체 수정, 부분 수정) */
    /*
    put : 자원의 전체 수정, 자원 내 모든 필드를 전달해야 함, 일부만 전달할 경우 오류
    patch : 자원의 일부 수정, 수정할 필드만 전송 (자동 주입이 아닌 부분만 수정하는 쿼리문에서 사용)
    put , patch도 get이나 post처럼 뭘 써도 상관은 없는데... null이면 이걸 바꾸줘 ? 엥 이게 안됨 ? 아무튼 그래

    근데 이거 할바엔 그냥 컨트롤러 공부 하셈
    * */
    @PatchMapping(value = {"/{rno}/{writer}","/{rno}"}, consumes = "application/json")
// consumes = "application/json" 는 헤더를 통해서 body를 받겠다는 뜻
//    patch는 전달받지 않은 null을 디폴트로 해주나보다 ~ 라고 인식함
    public String modifyReply(@PathVariable("rno") Long replyNumber, @PathVariable(value = "writer", required = false) String replyWriter, @RequestBody ReplyVO replyVO) {
        log.info("modify ---------------------");
        log.info("modify ---------------------" + replyVO);
        log.info("modify ---------------------" + replyNumber);

        if(replyVO.getReplyWriter() == null) {
            replyVO.setReplyWriter(Optional.ofNullable(replyWriter).orElse("anonymous")); // 디폴트값 정해주는 것
        } // patch가 null을 알아서 안해주니까 일케 if문 해줘야댐
        // 사실 여기서 json 검증을 통해서 ... 어............암튼 null 일때 필요한 부분을 셀렉트 할 수 있음 ... 근데 코드 길어짐 ....
        replyVO.setReplyNumber(replyNumber); // rno를 따로 받았기 때문에 언ㄴㄴㄴ능 replyvo에 넣어줘자자자
        replyService.modifyReply(replyVO);
        return "댓글 수정 성공공";
    }

//    5개
    // 1번 매개변수 없고 리턴은 문자열
    @GetMapping("/success")
    public String one() {
        return "연결 성공 ^^ ";
    }

//    매개변수 1개 있고 리턴은 문자열
    @PostMapping("/two/{note}")
    public String one(@PathVariable String note) {
        return note;
    }

//    매개변수 없고 리턴은 json object
    @GetMapping("/three")
    public FruitVO three() {
        FruitVO fruitVO = new FruitVO();
        return fruitVO;
    }


//    매개변수 여러 개 있고 리턴은 json object
    @PostMapping(value = "/fruit/{fno}/{fname}/{fspecies}", consumes = "application/json")
    public FruitVO four(@PathVariable("fno") String fno, @PathVariable("fname") String fname, @PathVariable("fspecies") String fspecies) {
        return new FruitVO(fname, fspecies, fno);
    }


//    매개 변수 여러 개 있고 리턴은 json array
    @PostMapping("/five")
    public List<TestVO> five(@RequestBody List<TestVO> fiveList) {
        return fiveList;
    }
}