package org.ruyin.code.spring.mybatis.timer;

import java.util.TimerTask;

import org.ruyin.code.spring.mybatis.bean.User;
import org.ruyin.code.spring.mybatis.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalculateTask extends TimerTask{

	int i =0;
	
	@Override
	public void run() {
		System.out.println("excute start..."+(i++));
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}
	
}
