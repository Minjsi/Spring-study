package com.example.ex02.controller;

import com.example.ex02.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


@Controller
@RequestMapping("/ex/*") //ex 자리에 user, board 등등 넣으면 됨
@Slf4j


public class ExampleController {

    private UserVO uservo;

    //    value에 경로를 작성하고, method에 호출할 서블릿 메소드를 설정한다.
    @RequestMapping(value = "/example", method = {RequestMethod.GET, RequestMethod.POST})
    public void ex01() {
        log.info("ex01...............................");
    }

    @GetMapping("")
    public void ex02() {
        log.info("ex02////////////////////////////");
    }

    @GetMapping("/ex03")
    public String ex03(ExampleVO exampleVO) {
        log.info("--------------------------------------------------");
        log.info(exampleVO.toString());
        log.info("--------------------------------------------------");

        return "ex03";
    }

    @GetMapping("/ex04")
    public String ex04(TaskVO taskVO) {
        log.info(taskVO.toString());
        return "ex04";
    }

    @GetMapping("/login1")
    public void login1() {
    } // 경로 : void가 붙어서 ex/login 으로 이동. ex 폴더로 들어간다는 뜻.

    @PostMapping("/login")
    public String login(UserVO uservo) {
        // return userVO.getUserId().equals("admin") ? "admin" : "user";
        if (uservo.getId().equals("admin")) {
            return "/ex/admin";
        } else {
            return "/ex/user";
        } // /ex/가 있으니 처음 경로로 돌아가 ex부터 시작한다는 뜻.
    }
// 실습 이름입력, 출근과 퇴근 버튼 클릭
    // 출근시작 09:00 , 퇴근 시간 18시
    // 출근버튼 클릭시 9시 넘으면 지각
    // 18시 전이면 퇴근이 아닌 업무시간으로 처리

    // 문자열을 Date 타입으로 변경시키는 방법
    // - SimpleDateFormat 생성자에 전달받은 날짜 형식을 작성한다.
    // - parse() 메소드에 작성한 형식에맞는 문자열을 전달하면 Date타입으로 변한다.
    // 모든 html은 work경로안에 생성
    // getToWork.html
    // leaveWork.html
    // late.html
    // work.html

    //sdf.format(cal.getTime());
    // sdf.format(cal.

    SimpleDateFormat sdf = new SimpleDateFormat("HH");
    Calendar cal = Calendar.getInstance();


    @GetMapping("/work")
    public void work() {
    } // 경로 : void가 붙어서 ex/work 으로 이동. ex 폴더로 들어간다는 뜻.

    @PostMapping("/start")
    public String work1(WorkVO workVO) {
        int time = Integer.parseInt(sdf.format(cal.getTime()));
        if (time > 9 && time < 18) {
            log.info("--------------------getToWork-------------------");
            log.info("---------------------" + time + "----------------------");
            return "/work/late";
        } else {
            log.info("-------------------late-------------------");
            log.info("---------------------" + time + "----------------------");
            return "/work/getToWork";
        }
    }

    @PostMapping("/end")
    public String work2(WorkVO workVO) {
        int time = Integer.parseInt(sdf.format(cal.getTime()));
        if (time < 9 || time > 18) {
            log.info("--------------------leaveWork--------------------");
            log.info("---------------------" + time + "----------------------");
            return "/work/leaveWork";
        } else {
            log.info("---------------------work--------------------");
            log.info("---------------------" + time + "----------------------");
            return "/work/work";
        }
    }

    // 실습
    // 사용자가 입력한 바코드 번호
//    @getMapping("/ex/check")

    @GetMapping("/market")
    public String goMarket(){
        return"product/market";
    }

    @PostMapping("/check")
    public String product(String barcode, Model model) {
        String name;
        // 스위치문 쓰자! if문 말고
        if(barcode.equals("4383927")) {
            name = "오징어땅콩";
            model.addAttribute("name", name);
        }else if(barcode.equals("0832147")){
            name = "초코 우유";
            model.addAttribute("name", name);
        }else if(barcode.equals("9841631")){
            name = "벌꿀 피자";
            model.addAttribute("name", name);
        }else if(barcode.equals("5587578")){
            name = "샌드위치";
            model.addAttribute("name", name);
        }
        return "product/casher";
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @PostMapping("/user")
    public String user(LoginVO loginVO) {
        String id = loginVO.getId();
        String pw = loginVO.getPw();
        if(id.equals("apple") && pw.equals("banana")) {
            return "login/user";
        }else {
            return "login/id";
        }
    }

    @GetMapping("/sing")
    public String song() {
        return "song/sing";
    }

    @PostMapping("/score")
    public String score(ScoreVO scoreVO, Model model) {
        String ment;
        int score = Integer.parseInt(scoreVO.getScore());
        if (score < 30) {
           ment = "연습 더 하고 오세요";
        } else if(score < 60) {
            ment = "들어줄 만 하군";
        }else if(score < 90) {
            ment = "더불러조";
        } else {
            ment = "가수로 채용! ";
        }
        model.addAttribute("ment", ment);
        return "song/score";
    }
// 강사님 방법
// 적기


//실습 두번째 문제
//
//    @GetMapping("/upgrade")
//    public String upgrade() {
//        return "upgrade/form";
//    }
//
//    @PostMapping("/upgrade")
//    public String upgrade(String choice) { // 입력 값 있어야함
//        log.info("-------------  choice  ------------");
//        log.info("---------------" + choice + "--------------");
//        log.info("------------------------------------");
//        int scroll_percent = Integer.parseInt(choice);
//        Random rand = new Random();
////      offense_power
//        int offense_power = 10; // 일단 아무거나 설정해보자
//
//        // 1차 퍼센트 방 만든다.
//        int[] first_array = new int[10];
//        for (int i = 0; i < scroll_percent; i++) {
//            first_array[i] = 1;
//        }
//        // 2차 퍼센트 방 만든다. 성공률 10퍼
//        int[] second_array = new int[10];
//        second_array[0] = 1;
//
//        // 랜덤으로 인덱스를 뽑는다.
//        int index_first = rand.nextInt(10);
//        int index_second = rand.nextInt(10);
//
//        // 진짜 본론! 게임 시작
//        if (first_array[index_first] == 1) {
//            if (second_array[index_second] == 1) {
//                offense_power = offense_power * 5;
//            } else {
//                offense_power = offense_power + (scroll_percent * 10);
//            }
//        } else {
//        }
////        return offense_power;
//    }
//    public boolean getChance(int rating) {
//
//    }
//

    @GetMapping("/info")
//    @ModelAttribute("KEY") Object obj
//    전달받은 파라미터를 화면쪽으로 보낼 때 쉽고 간편하게 사용할 수 있다.
//    여러 개의 데이터를 보낼 때에는 Model 데이터 전달자를 사용하고,
//    2개 이하의 데이터를 보낼 때에는 @ModelAttribute()를 사용하는 것이 좋다.
    public void getInfo(@ModelAttribute("name") String name, @ModelAttribute("age") Integer age){
    }

    @GetMapping("/datas")
//    동일한 이름의 파라미터가 여러 개 들어올 때에는 배열 또는 List로 매개변수를 설정한다.
//    이 때 동일한 이름으로 받아야 하기 때문에 @RequestParam("KEY")을 사용해서 전달받을 데이터의 KEY값을 지정해준다.
//    KEY 파라미터명이 전달되면 뒤에 있는 매개변수로 들어간다.
    public void getDatas(@RequestParam("data") ArrayList<Integer> datas){
        log.info(String.valueOf(datas.size()));
        datas.stream().map(String::valueOf).forEach(log::info);
    }

    @GetMapping("/different")
//    파라미터 명과 매개변수 명이 다르면 직접 지정해준다.
    public void getData(@RequestParam("data") String name){

    }

}