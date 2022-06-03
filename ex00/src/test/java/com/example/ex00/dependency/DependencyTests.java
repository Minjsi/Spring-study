package com.example.ex00.dependency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j // 자동 줄바꿈 해주는..
public class DependencyTests { // 단위 테스트로 (이때는 필드주입만)
    // 코딩을 객체화 시켜서 코딩과 컴퓨터에 오류가 있는지 테스트

    @Autowired
    private Coding coding;

    @Test // 어노테이션 붙여주면 저절로 테스트 해줌
    public void dependencyTest() {
        log.info("-------------------------------");
        log.info("-------------------------------");
        log.info("coding : " + coding.toString());
        log.info("computer : " + coding.getComputer()); // 단위 테스트 // 테스트 해봤더니 의존성 주입 중 필드, 생성자 주입 모두 성공!
        log.info("-------------------------------");
    }
}
