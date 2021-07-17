package com.blog.repository.wrapper;

import com.blog.entity.Article;
import com.blog.vo.ArticleVo;
import com.blog.vo.PageVo;

import java.util.List;

public interface ArticleWrapper {
    List<Article> listArticles(PageVo page);

    List<Article> listArticles(ArticleVo article, PageVo page);

    List<ArticleVo> listArchives();

}
