package com.onexy.pss;

import java.io.File;
import java.io.FileWriter;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCodeTest {
	private static boolean flag = false; // false表示不覆盖已有的文件
	private static boolean deleteflag = false; // deleteflag表示是否清除自动创建的文件,false表示不删除已有的文件
	private static final String SRC = "src/main/java/";
	private static final String TEST = "src/test/java/";
	private static final String PACKAGE = "com/onexy/pss/";
	private static final String RESOURCES = "src/main/resources/";
	private static final String WEBAPP = "src/main/webapp/";

	String[] domains = { "Role", "Resource", "Menu", "SystemDictionaryType",
			"SystemDictionaryDetail", "ProductType", "Product", "Supplier",
			"PurchaseBill", "PurchaseBillItem", "Depot", "ProductStock", "StockincomeBill", "StockincomeBillItem"};

	String[] templates = { "Service.java", "ServiceImpl.java", "Query.java",
			"Action.java", "hbm.xml", "Context.xml", "ServiceTest.java",
			"domain.js", "list.jsp", "input.jsp" };

	String[] files = { SRC + PACKAGE + "service/",
			SRC + PACKAGE + "service/impl/", SRC + PACKAGE + "page/",
			SRC + PACKAGE + "web/", RESOURCES + PACKAGE + "domain/",
			RESOURCES + "manager/", TEST + PACKAGE + "service/",
			WEBAPP + "js/model/", WEBAPP + "WEB-INF/views/",
			WEBAPP + "WEB-INF/views/" };

	@Test
	public void creatTemplate() throws Exception {

		final Logger logger = LoggerFactory.getLogger(getClass());

		if (templates.length != files.length) {
			throw new RuntimeException("templates,files --> 长度不一致");
		}

		VelocityContext context = new VelocityContext();
		for (int i = 0; i < domains.length; i++) {
			context.put("classEntity", domains[i]);
			String lowerClassEntity = StringUtils.uncapitalize(domains[i]);
			context.put("lowerClassEntity", lowerClassEntity);

			for (int j = 0; j < templates.length; j++) {
				File file = new File(files[j] + domains[i] + templates[j]);
				if ("Service.java".equals(templates[j])) {
					file = new File(files[j] + "I" + domains[i] + templates[j]);
				} else if ("hbm.xml".equals(templates[j])) {
					file = new File(files[j] + domains[i] + "." + templates[j]);
				} else if ("domain.js".equals(templates[j])) {
					file = new File(files[j] + lowerClassEntity + ".js");
				} else if ("list.jsp".equals(templates[j])) {
					file = new File(files[j] + lowerClassEntity + "/"
							+ lowerClassEntity + ".jsp");
				} else if ("input.jsp".equals(templates[j])) {
					file = new File(files[j] + lowerClassEntity + "/"
							+ lowerClassEntity + "-input.jsp");
				}

				if (deleteflag) {
					if (file.exists()) {
						try {
							file.delete();
							logger.info("成功删除文件:" + file.getAbsolutePath());
						} catch (Exception e) {
							logger.error("文件删除失败:" + file.getAbsolutePath()
									+ e.getMessage());
						}
					}
				} else {
					if (!flag && file.exists()) {
						continue;
					}

					FileWriter writer = null;
					try {
						File parentFile = file.getParentFile();
						if (!parentFile.exists()) {
							parentFile.mkdirs();
						}
						Template template = Velocity.getTemplate("template/"
								+ templates[j], "UTF-8");
						writer = new FileWriter(file);
						template.merge(context, writer);
						logger.info("成功创建文件:" + file.getAbsolutePath());
					} catch (Exception e) {
						logger.error("文件创建失败:" + file.getAbsolutePath()
								+ e.getMessage());
					} finally {
						if (writer != null) {
							writer.close();
							logger.info("资源成功关闭");
						}
					}
				}

			}
		}

	}
}
