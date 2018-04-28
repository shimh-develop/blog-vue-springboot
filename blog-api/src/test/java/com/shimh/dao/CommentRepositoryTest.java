package com.shimh.dao;

import com.shimh.BlogApiApplicationTests;
import com.shimh.entity.Comment;
import com.shimh.repository.CommentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CommentRepositoryTest extends BlogApiApplicationTests {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void add() {

        Comment parent = commentRepository.findOne(1);

        Comment sub1 = new Comment();
        sub1.setContent("sub1");
        sub1.setParent(parent);

        Comment sub2 = new Comment();
        sub2.setContent("sub2");
        sub2.setParent(parent);

        commentRepository.save(sub1);
        commentRepository.save(sub2);

    }

    @Test
    @Transactional
    public void get() {

        Comment parent = commentRepository.findOne(1);

        System.out.println(parent.getChildrens().size());

    }
}
