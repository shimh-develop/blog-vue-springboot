package com.shimh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimh.entity.Article;
/**
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
public interface ArticleRepository extends JpaRepository <Article, Integer>{

}
