package com.shimh.service;

import java.util.List;

import com.shimh.entity.Article;
import com.shimh.entity.Tag;
import com.shimh.vo.ArticleVo;
import com.shimh.vo.PageVo;

/**
 * @author shimh
 * <p>
 * 2018年1月25日
 */
public interface ArticleService {

    List<Article> listArticles(PageVo page);

    List<Article> listArticles(ArticleVo article, PageVo page);

    List<Article> findAll();

    Article getArticleById(Integer id);

    Integer publishArticle(Article article);

    Integer saveArticle(Article article);

    Integer updateArticle(Article article);

    void deleteArticleById(Integer id);

    List<Article> listArticlesByTag(Integer id);

    List<Article> listArticlesByCategory(Integer id);

    Article getArticleAndAddViews(Integer id);

    List<Article> listHotArticles(int limit);

    List<Article> listNewArticles(int limit);

    List<ArticleVo> listArchives();

}
