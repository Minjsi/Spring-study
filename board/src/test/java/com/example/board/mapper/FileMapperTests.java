package com.example.board.mapper;

import com.example.board.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileMapperTests {
    @Autowired
    private FileMapper fileMapper;

    @Test
    public void insertFileTest() {
        FileVO fileVO = new FileVO();
        fileVO.setFileName("day01.txt");
        fileVO.setUuid("몰라1");
        fileVO.setUploadPath("2022/06/10");
        fileVO.setImage(false);
        fileVO.setBoardBno(190L);
        fileMapper.insertFile(fileVO);
    }

    @Test
    public void deleteFileTest() {
        fileMapper.deleteFile("몰라1");
    }
    @Test
    public void findFileTest() {
        FileVO fileVO = new FileVO();
        fileVO.setFileName("day01.txt");
        fileVO.setUuid("몰라");
        fileVO.setUploadPath("2022/06/10");
        fileVO.setImage(false);
        fileVO.setBoardBno(190L);
        fileMapper.findByBoardBno(190L).stream().map(FileVO::toString).forEach(log::info);
    }
}
