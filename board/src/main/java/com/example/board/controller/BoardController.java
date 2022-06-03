package com.example.board.controller;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor

/*
Task           URL        Method          Parameter         Form         URL 이동
전체목록 /board/List        GET
등록처리 /board/register    POST           모든 항목            필요        /board/List
조회  /board/read          GET             bno
                        (상관 없음(
삭제처리 /board/remove  GET                 bno                         /board/List
수정처리 /board/modify POST                 모든 항목           필요      /board/detail
                       (수정할 내용이 많으면 post)


*/


public class BoardController {
    /* 서비스 객체가 필요해서 주입 받아야함 */
    private final BoardService boardService;

    @GetMapping("/list")
    public String getList(Criteria criteria, Model model){
        log.info("나 리스트에 왓삼 /List");
        model.addAttribute("boardList", boardService.getList(criteria));
        // getList 실행! model.addAttribute에 boardList라는 이름으로 scope이 들어간다~ 이말이야
        model.addAttribute("pageDTO", new PageDTO(criteria, boardService.getTotal()));
        // getTotal 실행! mel.addAttribute에 pageDTO란 이름으로 ...메서드가 scope 형태로 들어간다~
        return "/board/list";
    }

    @PostMapping("/register")
    public RedirectView register(BoardVO boardVO, RedirectAttributes rttr) {
        boardService.register(boardVO);
        rttr.addAttribute("boardBno", boardVO.getBoardBno());
        /*뒤로가기 했을 때 쓴 내용 계속 남아있지 않도록 redirect 쓰기
        redirect 쓰면 context path 사라짐
        * scope이 초기화 됨
        * 전달해야하는데 뒤로가기 해서 사라지는 거 싫다고 없앴다가 진짜
        * 사라지면 안 되니까 어디다가 저장했다가 보내자
        redirectattributes의 종류
        * 1. 쿼리 스트링
        *   url 뒤에 key와 value를 쿼리스트링으로 연결해준다.
        * rttr.addFlashAttribute("boardBno", boardVO.getBoardBno());

        * 2. Flash 영역 사용하기
        *   세션 플래시에 담아 두는 방식. redirect로 쏴도 세션 플래시에 담아두기 때문에 안 날아감
        *   훨씬 편함
        *   단위 테스트에서도 flashmap을 이용해야됨
        *
        *   세션에 파라미터를 저장하고 request 객체가 초기화 된 후 다시 저장해준다.
        * 두개의 방법중 2번 방법 사용하기

        * */

        rttr.addFlashAttribute("boardBno", boardVO.getBoardBno());
//        redirect 쓰면 context path 사라짐
        return new RedirectView("/board/list");
    }

    @GetMapping("/register")
    public void register(){}

    @GetMapping({"/read" , "/modify"}) // 수정하러 가는 페이지도 대괄호로 써버리장
    public void read(Long boardBno, Criteria criteria, HttpServletRequest request, Model model) {
        log.info("------ uri -------");
        log.info("------ uri -------");
        String requestURL = request.getRequestURI(); // uri를 출력해서 확인해보자
        log.info(requestURL.substring(requestURL.lastIndexOf("/")));
        log.info("------ uri -------");
        log.info(criteria.toString());
        log.info("------ uri -------");
        model.addAttribute("board", boardService.read(boardBno));
        /* boardService.read(boardBno) 를 부르고 board에  */
    }

    //    수정
//    Redirect 방식으로 전송
//    Flash로 데이터 전달 - 수정 성공 시에만 "success" 전달
//    @PostMapping("/readModify")
//    public String modify(Long boardBno, Model model) {
//        boardService.read(boardBno);
//        model.addAttribute("board", boardService.read(boardBno));
//        return "board/modifyForm";
//    }

    @PostMapping("/modify")
    public RedirectView modify(BoardVO boardVO, Criteria criteria, RedirectAttributes rttr){
        if(boardService.modify(boardVO)){
//            rttr.addFlashAttribute("boardBno", boardVO.getBoardBno() );
//            컨트롤러에서 다른 컨트롤러의 매개변수로 파라미터를 전달할 때에는
//            addAttribute(), 쿼리스트링 방식으로 전달해야 받을 수 있다.
//            Flash방식은 최종 응답 화면에서 사용될 파라미터를 전달할 때에만 사용하도록 한다.
            rttr.addAttribute("boardBno", boardVO.getBoardBno());
            rttr.addAttribute("pageNum", criteria.getPageNum());
            rttr.addAttribute("amount", criteria.getAmount());
        }
            /*
            * redirectattribute : redirect시 데이터 전달 방법
	- addflashattribute :
	- 문자열이 아닌 object를 전달하기 위해 사용 (문자열은 그냥 addattribute)
	- 값을 setting해서 다른 method로 parameter를 전달해준다.
parameter를 받는 쪽에서는 별다른 mapping 작업이나 addAttribute setting없이 바로 사용할 수 있다
* ( 세션을 이용하기 때문. 단 1회용이므로 redirect 되면 사라진다.)*/

        return new RedirectView("/board/read"); /*쿼리스트링 방식*/
    }

    @PostMapping("/remove")
    public String remove(Long boardBno, Criteria criteria, Model model){
        // 왜 forward로 가지? model은 왜쓰지 ㅇ아아ㅏㄱ악
        boardService.remove(boardBno);
//        다른 컨트롤러로 이동하고자 할 때 해당 메소드를 직접 실행한다.
//        만약 필요한 파라미터가 있다면 최초 요청 처리 메소드를 통해 파라미터를 전달해준다.
//        model.addAttribute("hi","하이루ㅜ루");
        return getList(criteria, model);
        // model을 넣어서 지금 쓰고 있는걸 전달해줘야 한다. 메서드만 부르면 mapping()으로 링크를 쏘는게
        // 아니기때문에 매개변수?리퀘스트 그게 비어있어서 현재 쓰고 있는 model을 보내주는거임
        // forward 방식은 살아있ㄱㅣ땜에 페이지만 쏴줘도 댐
    }
}