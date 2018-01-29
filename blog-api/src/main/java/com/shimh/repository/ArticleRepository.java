package com.shimh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

	@Query(value = "select * from me_article order by views desc limit :limit", nativeQuery = true)
	List<Article> findOrderByViewsAndLimit( @Param("limit") int limit);

	@Query(value = "select * from me_article order by create_date desc limit :limit", nativeQuery = true)
	List<Article> findOrderByCreateDateAndLimit( @Param("limit") int limit);
}
