package com.example.board.controller;

import com.example.board.domain.vo.ConcertVO;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.service.ConcertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/info/*")
@RequiredArgsConstructor
public class ConcertRestController {
    private final ConcertService concertService;
//   특정 콘서트 번호의 이름 출력 - 매개변수 한 개 있고, 리턴은 문자열
    @PostMapping("/name/{cno}")
    public String concertName(@PathVariable("cno") Long concertNumber) {
        return (concertService.read(concertNumber)).getConcertName();
    }

//   요즘 핫한 콘서트 하나 뽑기 - 매개변수 없고, json object 리턴
    @GetMapping("/detail")
    public ConcertVO detail() {
        return( concertService.read(40L));
    }

//  콘서트 정보 수정하기 - 매개변수 여러개, 리턴은 json object
    @PostMapping("/modify/{cno}")
    public ConcertVO modify(@PathVariable("cno") Long concertNumber, @RequestBody ConcertVO concertVO) {
        concertVO.setConcertNumber(concertNumber);
        concertService.modify(concertVO);
        return concertService.read(concertNumber);
    }


//  콘서트 전체 가져오기 - 매개변수 여러개, 리턴은 json array
    @PostMapping("/getlist/{ticket}")
    public List<ConcertVO> getList(@PathVariable("ticket") String ticket, ConcertVO concertVO) {
        concertService.register(concertVO);
        return concertService.getList();
    }
}