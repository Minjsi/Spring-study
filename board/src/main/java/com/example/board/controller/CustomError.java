package com.example.board.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

// 오류가 떴을 때 처리하는 컨트롤러
@Controller
public class CustomError implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        return "error/error";
    }

}
