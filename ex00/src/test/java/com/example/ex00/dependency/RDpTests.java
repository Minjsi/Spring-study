package com.example.ex00.dependency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RDpTests {

    @Autowired
    private Chef chef;

    @Test
    public void rdptest(){
        log.info("-------------------------------");
        log.info("-------------------------------");
        log.info("chef : " + chef.toString());
        log.info("restaurant : " + chef.getRestaurant()); // 단위 테스트 // 테스트 해봤더니 의존성 주입 중 필드, 생성자 주입 모두 성공!
        log.info("-------------------------------");
    }

}
