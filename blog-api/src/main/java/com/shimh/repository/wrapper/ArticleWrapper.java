package com.shimh.repository.wrapper;

import com.shimh.entity.Article;
import com.shimh.vo.ArticleVo;
import com.shimh.vo.PageVo;

import java.util.List;

public interface ArticleWrapper {
    List<Article> listArticles(PageVo page);

    List<Article> listArticles(ArticleVo article, PageVo page);

    List<ArticleVo> listArchives();

}
