package com.example.board.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardVO {
    private Long boardBno;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardRegisterDate;
    private String boardUpdateDate;

}
/*
*
Lombok 라이브러리에서 제공하는 어노테이션 중에서 자주 사용되는 어노테이션
* 접근자/설정자 자동 생성
제일 먼저 살펴볼 어노테이션은 @Getter와 @Setter 입니다.
* 아마 Lombok에서 가장 많이 사용되는 어노테이션일 텐데요.
* 예를 들어, xxx라는 필드에 선언하면 자동으로 getXxx()(boolean 타입인 경우, isXxx())와
* setXxx() 메소드를 생성해줍니다.
*
* Lombok을 사용하면 생성자도 자동으로 생성할 수 있습니다.
* @NoArgsConstructor 어노테이션은 파라미터가 없는 기본 생성자를 생성해주고,
* @AllArgsConstructor 어노테이션은 모든 필드 값을 파라미터로 받는
* 생성자를 만들어줍니다. 마지막으로 @RequiredArgsConstructor 어노테이션은
* final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줍니다.
*
* 끝판왕! @Data
@Data는 위에서 설명드린
* @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode을
* 한꺼번에 설정해주는 매우 유용한 어노테이션입니다.

사용 방법은 다른 어노테이션들과 대동소이합니다.
* 클래스 레벨에서 @Data 어노테이션을 붙여주면,
* 모든 필드를 대상으로 접근자와 설정자가 자동으로 생성되고,
* final 또는 @NonNull 필드 값을 파라미터로 받는 생성자가 만들어지며,
* toStirng, equals, hashCode 메소드가 자동으로 만들어집니다.

* * */