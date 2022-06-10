package com.example.board.domain.dao;

import com.example.board.domain.vo.FileVO;
import com.example.board.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    public void registerFile(FileVO fileVO) {
        fileMapper.insertFile(fileVO);
    }

    public void removeFile(Long boardBno) {
        fileMapper.deleteFile(boardBno);
    }

    public List<FileVO> findByBoardBno(Long boardBno) {
        return fileMapper.findByBoardBno(boardBno);
    }
}
