package com.example.board.controller;

import com.example.board.domain.vo.ConcertVO;
import com.example.board.service.ConcertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/concert/*")
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService;
//   특정 콘서트 번호의 이름 출력 - 매개변수 한 개 있고, 리턴은 문자열

    @GetMapping("/ticket")
    public String ticket() {
        return "/concert/concert";
    }

}