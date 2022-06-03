package com.example.board.controller;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j

public class ReplyControllerTests {
    // 마치 브라우저에서 url을 요청한 것처럼 환경을 만들어준다.
    private MockMvc mockMvc;
    // 서버 환경 및 설정, 요청 등을 처리해주는 WebApplicationCentext를 불러온다.
    @Autowired
    private WebApplicationContext webApplicationContext;

    // 모든 @Test 실행 전 한 번씩 실행된다.
    // @Test 메소드가 2개라면 두 번 실행된다.

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        /*mockmvc가 뭐하는 애여 */
    }

    @Test
    public void getListTest() throws Exception {
        log.info(mockMvc.perform(
               MockMvcRequestBuilders.get("/board/list")
                .param("pageNum", "1")
                .param("amount", "10")
                )
                .andReturn().getModelAndView().getModelMap().toString());
    }

    //    @Test
//    public void getListTest() throws Exception{
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
//                .andReturn().getModelAndView().getModelMap().toString());
//    }

    @Test
    public void registerTest() throws Exception {
        log.info("테스트 게시글 내용 " + mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("boardTitle", "테스트 새 글 제목")
                .param("boardContent", "테스트 내용 내용")
                .param("boardWriter", "글쓴이이이")
        ).andReturn().getFlashMap());
    }

    @Test
    public void readTest() throws Exception {
        log.info(
          "테스트 상세정보 내용" +
                  mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("boardBno", "195")).
                          andReturn().getModelAndView().getModelMap().toString());
    }


    //    @Test
//    public void readTest() throws Exception {
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("boardBno", "3"))
//                .andReturn().getModelAndView().getModelMap().toString());
//    }


    @Test
    public void modifyTest() throws Exception { // 브라우저 상에서 외부로 전송할때는 다양한 오류가 발생할 수 있어서 강제로 try catch를 잡으라고 하는 것
        log.info("테스트 수정된 게시글 내용 " + mockMvc.perform(MockMvcRequestBuilders.post("/board/completeModify")
                .param("boardBno", "4")
                .param("boardTitle", "수정테스트 새 글 제목")
                .param("boardContent", "테스트 내용 내용")
                .param("boardWriter", "글쓴이이이") // 키 값은 해당객체 필드값이랑 똑같이 적어줘야 함
        ).andReturn().getFlashMap().toString());
    } // 그럼 회사 들어가서 테스트 할 때도 이렇게 직접 다 쓰고 테스트 해보면 되는곤가??
    // toString은 log.info 쓸 때는 무조건 써주는건가?

    @Test
    public void removeTest() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/board/remove")
                .param("boardBno", "195")
        ).andReturn().getModelAndView().getViewName();
    }
}