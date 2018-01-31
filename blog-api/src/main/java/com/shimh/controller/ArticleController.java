package com.shimh.controller;

import java.util.List;

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
import com.shimh.entity.ArticleBody;
import com.shimh.entity.Tag;
import com.shimh.entity.User;
import com.shimh.service.ArticleService;
import com.shimh.service.TagService;
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
	
	@Autowired
	private TagService tagService;
	
	@GetMapping
	@FastJsonView(
		exclude = {
				@FastJsonFilter(clazz = Article.class, props = {"body","category","comments"}),
				@FastJsonFilter(clazz = Tag.class, props = {"id","avatar"})},
		include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
	public Result listArticles(){
		List<Article> articles = articleService.findAll();
		
		return Result.success(articles);
	}
	
	@GetMapping("/hot")
	@FastJsonView( include = {@FastJsonFilter(clazz = Article.class, props = {"id", "title"})})
	public Result listHotArticles(){
		int limit = 6;
		List<Article> articles = articleService.listHotArticles(limit);
		
		return Result.success(articles);
	}
	
	@GetMapping("/new")
	@FastJsonView( include = {@FastJsonFilter(clazz = Article.class, props = {"id", "title"})})
	public Result listNewArticles(){
		int limit = 6;
		List<Article> articles = articleService.listNewArticles(limit);
		
		return Result.success(articles);
	}
	
	
	
	@GetMapping("/{id}")
	@FastJsonView(
			exclude = {
					@FastJsonFilter(clazz = Article.class, props = {"category","comments"}),
					@FastJsonFilter(clazz = ArticleBody.class, props = {"contentHtml"}),
					@FastJsonFilter(clazz = Tag.class, props = {"avatar"})},
			include = {@FastJsonFilter(clazz = User.class, props = {"nickname","avatar"})})
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
	
	@GetMapping("/view/{id}")
	@FastJsonView(
			exclude = {
					@FastJsonFilter(clazz = Article.class, props = {"category","comments"}),
					@FastJsonFilter(clazz = ArticleBody.class, props = {"contentHtml"}),
					@FastJsonFilter(clazz = Tag.class, props = {"avatar"})},
			include = {@FastJsonFilter(clazz = User.class, props = {"nickname","avatar"})})
	public Result getArticleAndAddViews(@PathVariable("id") Integer id){
		
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		Article article = articleService.getArticleAndAddViews(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.setData(article);
		return r;
	}
	
	@GetMapping("/tag/{id}")
	@FastJsonView(
		exclude = {
				@FastJsonFilter(clazz = Article.class, props = {"body","category","comments"}),
				@FastJsonFilter(clazz = Tag.class, props = {"id","avatar"})},
		include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
	public Result listArticlesByTag(@PathVariable Integer id){
		List<Article> articles = articleService.listArticlesByTag(id);
		
		return Result.success(articles);
	}
	
	
	@GetMapping("/category/{id}")
	@FastJsonView(
		exclude = {
				@FastJsonFilter(clazz = Article.class, props = {"body","category","comments"}),
				@FastJsonFilter(clazz = Tag.class, props = {"id","avatar"})},
		include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
	public Result listArticlesByCategory(@PathVariable Integer id){
		List<Article> articles = articleService.listArticlesByCategory(id);
		
		return Result.success(articles);
	}
	
	@PostMapping("/create")
	@RequiresAuthentication
	public Result saveArticle( @Validated @RequestBody Article article){
		
		Integer articleId = articleService.saveArticle(article);
		
		Result r = Result.success();
		r.simple().put("articleId", articleId);
		return r;
	}
	
	@PostMapping("/update")
	@RequiresRoles(Base.ROLE_ADMIN)
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
	@RequiresRoles(Base.ROLE_ADMIN)
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
