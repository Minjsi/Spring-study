package com.example.board.damain.dao;

import com.example.board.domain.dao.FileDAO;
import com.example.board.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileDaoTests {
    @Autowired
    private FileDAO fileDao;

    @Test
    public void registerFileTest() {
        FileVO fileVO = new FileVO();
        fileVO.setFileName("day01.txt");
        fileVO.setUuid("몰라1");
        fileVO.setUploadPath("2022/06/10");
        fileVO.setImage(false);
        fileVO.setBoardBno(190L);
        fileDao.registerFile(fileVO);
    }

    @Test
    public void removeFileTest() {
        fileDao.removeFile("몰라1");
    }
    @Test
    public void findFileTest() {
        FileVO fileVO = new FileVO();
        fileVO.setFileName("day01.txt");
        fileVO.setUuid("몰라");
        fileVO.setUploadPath("2022/06/10");
        fileVO.setImage(false);
        fileVO.setBoardBno(190L);
        fileDao.findByBoardBno(190L).stream().map(FileVO::toString).forEach(log::info);
    }
}
