package com.shimh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.Tag;
import com.shimh.service.TagService;
/**
 * 标签api
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
@RestController
@RequestMapping(value="/tags")
public class TagController {

	
	@Autowired
	private TagService tagService;
	
	@GetMapping
	public Result listTags(){
		List<Tag> tags = tagService.findAll();
		
		return Result.success(tags);
	}
	
	@GetMapping("/{id}")
	public Result getTagById(@PathVariable("id") Integer id){
		
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		Tag tag = tagService.getTagById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.setData(tag);
		return r;
	}
	
	@PostMapping("/create")
	public Result saveTag( @Validated @RequestBody Tag tag){
		
		Integer tagId = tagService.saveTag(tag);
		
		Result r = Result.success();
		r.simple().put("tagId", tagId);
		return r;
	}
	
	@PostMapping("/update")
	public Result updateTag(@RequestBody Tag tag){
		Result r = new Result();
		
		if(null == tag.getId()){
			r.setResultCode(ResultCode.USER_NOT_EXIST);
			return r;
		}
		
		Integer tagId = tagService.updateTag(tag);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.simple().put("tagId", tagId);
		return r;
	}
	
	@GetMapping("/delete/{id}")
	public Result deleteTagById(@PathVariable("id")Integer id){
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		tagService.deleteTagById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		return r;
	}
	
	
	
}
