package com.shimh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shimh.common.entity.BaseEntity;

@Entity
@Table(name = "me_tag")
public class Tag extends BaseEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5025313969040054182L;

	
	private String tagname;
	
	
	private String avatar;
	
	
	
	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	
	
	
}
