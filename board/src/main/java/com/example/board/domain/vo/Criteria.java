package com.example.board.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

// 검색의 기준
// 페이징!!!! 클래스에 담는다! 클래스!!!!!
@Component
@Data
@AllArgsConstructor // 기본생성자를 직접 만들어서 default를 정해주자 왜? 페이지 기본값을 정해줘야하기 때문
public class Criteria {
    private int pageNum;
    private int amount;
    private String type;
    private String keyword;


    /*단축키 뭐 누르셨지...*/ /* 기본생성자를 직접 만들어서 default를 정해준다. */

    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String getListLink() { /* 글에서 목록 갔을 때 그 페이징 기억할 수 있도록 하는 메서드 */
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.amount)
                .queryParam("type", this.type)
                .queryParam("keyword", this.keyword);
        return builder.toUriString();
    }
    public String[] getTypes() {
        return type == null ? new String[] {}:type.split("");
    }
}
