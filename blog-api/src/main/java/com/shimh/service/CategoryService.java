package com.shimh.service;

import java.util.List;

import com.shimh.entity.Category;
/**
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
public interface CategoryService {

	List<Category> findAll();

	Category getCategoryById(Integer id);

	Integer saveCategory(Category category);

	Integer updateCategory(Category category);

	void deleteCategoryById(Integer id);

}
