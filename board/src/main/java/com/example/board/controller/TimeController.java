package com.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@Slf4j

public class TimeController {
//
//    @GetMapping("/time")
//    @ResponseBody
//    public String getReplyDate(String replyDate) throws ParseException {
//        String time = null; /* null로 초기화 한다는 의미는 아래에서 연결할 거 아니라는 뜻 */
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date rDate = sdf.parse(replyDate);
//        Date today = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(rDate); // 수정 날짜로 세팅됨
//
//        long gap = today.getTime() - rDate.getTime();
//
//        if (gap < 1000 * 60 * 60 * 24) {
//            int h = calendar.get(Calendar.HOUR_OF_DAY);
//            int mm = calendar.get(Calendar.MINUTE);
//            int s = calendar.get(Calendar.SECOND);
//
//            time = (h < 10 ? "0" : "") + h + ":" + (mm < 10 ? "0" : "") + mm + (s < 10 ? "0" : "") + s;
//        } else {
//            int y = calendar.get(Calendar.YEAR);
//            int m = calendar.get(Calendar.MONTH) + 1;
//            int d = calendar.get(Calendar.DATE);
//
//            time = y + "-" + (m < 10 ? "0" : "") + m + "-" + (d < 10 ? "0" : "") + d;
//
//        }
//        return time;
//    }


    @GetMapping("/time")
    @ResponseBody
    public String getReplyDate(String replyDate) throws ParseException {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date rDate = sdf.parse(replyDate);
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rDate); // 수정된 날짜로 세팅됨

        long gap = today.getTime() - rDate.getTime();

        if(gap < 1000 * 60 * 60 * 24){
            int h = calendar.get(Calendar.HOUR_OF_DAY);
            int mm = calendar.get(Calendar.MINUTE);
            int s = calendar.get(Calendar.SECOND);

            time = (h < 10 ? "0" : "") + h + ":" + (mm < 10 ? "0" : "") + mm + ":" + (s < 10 ? "0" : "") + s;
        }else{
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);

            time = y + "-" + (m < 10 ? "0" : "") + m + "-" + (d < 10 ? "0" : "") + d;
        }
        return time;
    }
}