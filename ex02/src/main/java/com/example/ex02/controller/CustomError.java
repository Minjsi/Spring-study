package com.example.ex02.controller;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

// 오류가 떴을 때 처리하는 컨트롤러
@Controller
public class CustomError {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAuthType(RequestDispatcher.ERROR_STATUS_CODE);
        if(status!=null) {
            int statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            }
        }
        return "error/505";
    }

}
