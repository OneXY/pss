package com.onexy.pss.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTest {
	Logger logger = LoggerFactory.getLogger(getClass());

	// slf4j simple logging Facade for java 面向接口编程
	@Test
	public void testname() throws Exception {
		logger.debug("sterteters");
		logger.info("sss");
		logger.warn("ssss");
		logger.error("sssss");
	}
}
