package com.blog.service;

import com.blog.entity.Board;

import java.util.List;

/**
 * 留言
 *
 * @author Leeqingyi
 * @time 2021/7/11
 */

public interface BoardService {
    List<Board> findAll();
    Integer saveBoard(Board board);
    void deteleBoardById(Integer id);
}
