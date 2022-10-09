package com.deli.project.domain.service;

import com.deli.project.domain.entity.Board;
import com.deli.project.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board){
        boardRepository.save(board);
        board.setLocalDateTime(LocalDateTime.now());
        return board.getId();
    }

    public Board findOne(Long id){
        return boardRepository.findOne(id);
    }

    @Transactional
    public void update(Long id,String title,String content){
        Board board = findOne(id);
        board.setTitle(title);
        board.setContent(content);
    }


    public List<Board> findAll(BoardSearchDto boardSearchDto){
       return boardRepository.findAll(boardSearchDto);
    }

}
