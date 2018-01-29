package com.shimh.json;

import org.apache.shiro.session.mgt.SimpleSession;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shimh.entity.Category;

public class JSONTest{

	
	@Test
	public void setTest() {
		SimpleSession s = new SimpleSession();
		s.setId(123456);
		
		
		int features = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.SkipTransientField, false);
		int features2 = SerializerFeature.config(features, SerializerFeature.WriteClassName, true);
		String ss = JSON.toJSONString(s, features2);
		System.out.println(ss);
		SimpleSession s2 = JSON.parseObject(ss,SimpleSession.class);
		System.out.println(s2.getId());
		
	}
	
	
}
