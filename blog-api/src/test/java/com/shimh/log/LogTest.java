package com.shimh.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	
	private Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	@Test
	public void logTest(){
		Exception e = new Exception("123456");
		
		logger.error("参数校验错误 , uri: {} , caused by: ", "https:localhost",e);
		
		logger.error(e.getMessage(),e);
	}
}
