package com.shimh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.entity.Tag;

@RestController
@RequestMapping(value="/test")
public class TestController {

	@RequestMapping("/json")
	@FastJsonView(exclude = {@FastJsonFilter(clazz = Tag.class,props = {"avatar"})})
	public Tag testJson(){
		Tag t = new Tag();
		t.setAvatar("1");
		t.setTagname("2");
		return t;
	}
}
