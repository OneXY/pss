package com.onexy.pss.service;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

public class VelocityTest {

	@Test
	public void testMager() throws Exception {
		VelocityContext context = new VelocityContext();
		context.put("text", "中秋快乐!");
		Template template = Velocity
				.getTemplate("template/hello.html", "UTF-8");
		StringWriter out = new StringWriter();
		template.merge(context, out);
		System.out.println(out);
		out.close();
	}

}
