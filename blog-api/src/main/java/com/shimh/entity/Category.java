package com.shimh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shimh.common.entity.BaseEntity;

@Entity
@Table(name = "me_category")
public class Category extends BaseEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5025313969040054182L;

	
	private String categoryname;
	
	private String description;
	
	private String avatar;

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	
}
