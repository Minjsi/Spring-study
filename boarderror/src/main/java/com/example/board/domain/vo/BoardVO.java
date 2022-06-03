package com.example.board.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class BoardVO {
    private Long boardBno;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardResisterDate;
    private String boardUpdateDate;

    public BoardVO() {

    }
}
