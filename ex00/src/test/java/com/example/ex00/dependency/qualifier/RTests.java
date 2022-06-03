package com.example.ex00.dependency.qualifier;

import com.example.ex00.dependency.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RTests {
    @Autowired
    private Restaurant restaurant;

    @Test
    public void rtest(){

    }
}
