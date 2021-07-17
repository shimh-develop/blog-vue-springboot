package com.blog.controller;

import com.blog.common.annotation.LogAnnotation;
import com.blog.common.constant.ResultCode;
import com.blog.common.result.Result;
import com.blog.entity.Board;
import com.blog.service.BoardService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 留言
 *
 * @author Leeqingyi
 * @time 2021/7/11
 */

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public Result listBoards(){
        List<Board> boards=boardService.findAll();
        return Result.success(boards);
    }

    @PostMapping("/add")
    public Result saveBoard(@Validated @RequestBody Board board){
        System.out.println(board.getContext());
        Integer boardId=boardService.saveBoard(board);
        Result r=Result.success();
        r.simple().put("boardId",boardId);
        return r;
    }

    @GetMapping("/delete/{id}")
    @RequiresAuthentication
    public Result deleteBoardById(@PathVariable("id") Integer id) {
        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        boardService.deteleBoardById(id);

        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }
}
