package com.blog.repository;

import com.blog.common.entity.BaseEntity;
import com.blog.entity.Board;
import com.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * 留言板
 *
 * @author Leeqingyi
 * @time 2021/7/11
 */

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
