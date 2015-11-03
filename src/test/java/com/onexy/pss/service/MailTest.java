package com.onexy.pss.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MailTest {
	@Autowired
	MailSender mailSender;
	
	@Test
	public void testMailSender() throws Exception {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject("标题");
		mailMessage.setText("text");
		mailMessage.setFrom("yi.pingdong@163.com");
		mailMessage.setTo("504887284@qq.com");
		mailMessage.setSentDate(new Date());
		
		mailSender.send(mailMessage);
	}
}
