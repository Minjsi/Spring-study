package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FruitVO {
    private String fruitName;
    private String fruitSpecies;
    private String fruitCount;

    public FruitVO() {this("사과", "나무열매", "10개");

    }

    public FruitVO(String fruitName, String fruitSpecies, String fruitCount) {
        this.fruitName = fruitName;
        this.fruitSpecies = fruitSpecies;
        this.fruitCount = fruitCount;
    }
}
