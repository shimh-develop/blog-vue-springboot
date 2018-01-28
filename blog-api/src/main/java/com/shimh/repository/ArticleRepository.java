package com.shimh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimh.entity.Article;
import com.shimh.entity.Category;
import com.shimh.entity.Tag;
/**
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
public interface ArticleRepository extends JpaRepository <Article, Integer>{

	List<Article> findByTags(Tag tag);

	List<Article> findByCategory(Category category);

}
