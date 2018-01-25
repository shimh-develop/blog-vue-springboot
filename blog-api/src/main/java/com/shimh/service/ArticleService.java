package com.shimh.service;

import java.util.List;

import com.shimh.entity.Article;
/**
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
public interface ArticleService {

	List<Article> findAll();

	Article getArticleById(Integer id);

	Integer saveArticle(Article article);

	Integer updateArticle(Article article);

	void deleteArticleById(Integer id);

}
