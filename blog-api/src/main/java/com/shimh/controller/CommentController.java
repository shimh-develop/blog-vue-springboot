package com.shimh.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.Article;
import com.shimh.entity.Comment;
import com.shimh.entity.Tag;
import com.shimh.entity.User;
import com.shimh.service.CommentService;
/**
 * 评论api
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
@RestController
@RequestMapping(value="/comments")
public class CommentController {

	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public Result listComments(){
		List<Comment> comments = commentService.findAll();
		
		return Result.success(comments);
	}
	
	@GetMapping("/{id}")
	public Result getCommentById(@PathVariable("id") Integer id){
		
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		Comment comment = commentService.getCommentById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.setData(comment);
		return r;
	}
	
	@GetMapping("/article/{id}")
	@FastJsonView(
			exclude = {
					@FastJsonFilter(clazz = Comment.class, props = {"article"})},
			include = {@FastJsonFilter(clazz = User.class, props = {"nickname","avatar"})})
	public Result listCommentsByArticle(@PathVariable("id") Integer id){
		
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		List<Comment> comments = commentService.listCommentsByArticle(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.setData(comments);
		return r;
	}
	
	
	@PostMapping("/create")
	@RequiresAuthentication
	public Result saveComment(@Validated @RequestBody Comment comment){
		
		Integer commentId = commentService.saveComment(comment);
		
		Result r = Result.success();
		r.simple().put("commentId", commentId);
		return r;
	}
	
	
	@GetMapping("/delete/{id}")
	@RequiresAuthentication
	public Result deleteCommentById(@PathVariable("id")Integer id){
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		commentService.deleteCommentById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		return r;
	}
	
	@PostMapping("/create/change")
	@FastJsonView(
			exclude = {
					@FastJsonFilter(clazz = Comment.class, props = {"article"})},
			include = {@FastJsonFilter(clazz = User.class, props = {"nickname","avatar"})})
	@RequiresAuthentication
	public Result saveCommentAndChangeCounts(@RequestBody Comment comment){
		
		Comment savedComment = commentService.saveCommentAndChangeCounts(comment);
		
		Result r = Result.success(savedComment);
		return r;
	}
	
	
	@GetMapping("/delete/change/{id}")
	@RequiresAuthentication
	public Result deleteCommentByIdAndChangeCounts(@PathVariable("id")Integer id){
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		commentService.deleteCommentByIdAndChangeCounts(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		return r;
	}
	
}
