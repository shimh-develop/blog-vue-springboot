package com.blog.service.impl;

import com.blog.entity.Board;
import com.blog.entity.Comment;
import com.blog.repository.BoardRepository;
import com.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 留言
 *
 * @author Leeqingyi
 * @time 2021/7/11
 */

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    @Transactional
    public Integer saveBoard(Board board) {
         board.setCreatetime(new Date());
        return boardRepository.save(board).getId();
    }

    @Override
    public void deteleBoardById(Integer id) {
      boardRepository.delete(id);
    }
}
