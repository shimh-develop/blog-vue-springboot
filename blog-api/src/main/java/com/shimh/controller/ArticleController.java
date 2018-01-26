package com.shimh.controller;

import java.util.List;

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
import com.shimh.entity.Tag;
import com.shimh.entity.User;
import com.shimh.service.ArticleService;
/**
 * 文章api
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
@RestController
@RequestMapping(value="/articles")
public class ArticleController {

	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping
	@FastJsonView(
		exclude = {
				@FastJsonFilter(clazz = Article.class, props = {"body","category"}),
				@FastJsonFilter(clazz = Tag.class, props = {"id","avatar"})},
		include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
	public Result listArticles(){
		List<Article> articles = articleService.findAll();
		
		return Result.success(articles);
	}
	
	@GetMapping("/{id}")
	public Result getArticleById(@PathVariable("id") Integer id){
		
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		Article article = articleService.getArticleById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.setData(article);
		return r;
	}
	
	@PostMapping("/create")
	public Result saveArticle( @Validated @RequestBody Article article){
		
		Integer articleId = articleService.saveArticle(article);
		
		Result r = Result.success();
		r.simple().put("articleId", articleId);
		return r;
	}
	
	@PostMapping("/update")
	public Result updateArticle(@RequestBody Article article){
		Result r = new Result();
		
		if(null == article.getId()){
			r.setResultCode(ResultCode.USER_NOT_EXIST);
			return r;
		}
		
		Integer articleId = articleService.updateArticle(article);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.simple().put("articleId", articleId);
		return r;
	}
	
	@GetMapping("/delete/{id}")
	public Result deleteArticleById(@PathVariable("id")Integer id){
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		articleService.deleteArticleById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		return r;
	}
	
	
	
}
