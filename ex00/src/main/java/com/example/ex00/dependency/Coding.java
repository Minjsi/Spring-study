package com.example.ex00.dependency;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data // data로 인해 tostring 재정의 되어 있음
@RequiredArgsConstructor
// ㄴ final 또는 @NunNull이 붙은 필드만 초기화 생성자로 선언
// ㄴ 진짜 주입 받아야할 생성자만 주입 받음! 전체 필드를 다 초기화하는 생성자가 아니라 final이 붙어있거나 none null이 붙어있을 때만 초기화를 해주는 어노테이션
public class Coding {
    // 필드 주입
    // autowired가 필드 위에 있어서 필드 주입!

    // 장점
    // 굉장히 편하다.
    // 순환 참조 시 오류가 발생하지 않기 때문에 StackOverFlow가 발생한다.

    // 단점
    // final을 붙일 수 없기 때문에 다른곳에서 변형이 가능하다.
    // final을 쓰지 않으면 어디서나 수정이 가능해서 객체 변동성 때문에 단점이 있음
//    @Autowired
//    private Computer computer; // 스프링이 computer를 알고 있어서 이렇게만 선언해도 됨.


    // 생성자 주입

    // 순환 참조 시 컴파일러 인지 기능, 오류 발생시킴, 아~ 필드 주입의 단점이 보완되는구나
    // 메모리에 할당하며 초기값으로 주입되므로 final 키워드를 사용 가능, 다른 곳에서 변형이 불가능하다. 안전.
    // 의존성 주입이 되지 않으면 객체가 생성되지 않으므로 NullPointerException(NPE) 방어할 수 있다.
    // 고로 생성자 주입을 사용하는 것을 추천!
//    private final Computer computer;
    // 생성자를 사용하는 것은 스프링, 우리 요청에 맞는 객체로 주입을 해줌

//    @Autowired
//    public Coding(Computer computer) {
//        this.computer = computer;
//    }


    // 1. final 꼭 붙여주기 2. @RequiredArgusConstructor을 꼭 넣어주기
    @NonNull
    private final Computer computer;

}
