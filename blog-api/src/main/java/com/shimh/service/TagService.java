package com.shimh.service;

import java.util.List;

import com.shimh.entity.Tag;
/**
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
public interface TagService {

	List<Tag> findAll();

	Tag getTagById(Integer id);

	Integer saveTag(Tag tag);

	Integer updateTag(Tag tag);

	void deleteTagById(Integer id);

}
