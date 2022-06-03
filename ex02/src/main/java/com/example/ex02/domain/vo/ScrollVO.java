package com.example.ex02.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class ScrollVO {
    private int offense_power;
    private int scroll100;
    private int scroll60;
    private int scroll10;

    public ScrollVO() {
    }



}
