package com.shimh.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shimh.BlogApiApplicationTests;
import com.shimh.entity.Article;

public class ArticleServiceTest extends BlogApiApplicationTests{

	@Autowired
	private ArticleService articleService;
	
	
	
	@Test
	public void listArticlesByTagTest() {
		int id = 1;
		List<Article> as = articleService.listArticlesByTag(id);
		
		System.out.println(as.size());
		
	}
	
	@Test
	public void listArticlesByCategoryTest() {
		int id = 1;
		
		List<Article> as = articleService.listArticlesByCategory(id);

		System.out.println(as.size());
	}
	
	@Test
	public void listHotArticlesTest() {
		
		List<Article> as = articleService.listHotArticles(4);

		as.stream().forEach( a -> System.out.println(a.getTitle()));
		System.out.println(as.size());
	}
	
	@Test
	public void listNewArticlesTest() {
		
		List<Article> as = articleService.listNewArticles(4);

		as.stream().forEach( a -> System.out.println(a.getTitle()));
		System.out.println(as.size());
	}
}
