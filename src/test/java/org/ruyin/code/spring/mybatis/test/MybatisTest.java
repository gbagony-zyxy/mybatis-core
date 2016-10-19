package org.ruyin.code.spring.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.ruyin.code.spring.mybatis.bean.User;
import org.ruyin.code.spring.mybatis.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisTest {

	@Test
	public void findUserByIdTest() throws IOException {
		String resource = "config/SqlMapConfig1.xml";
		InputStream config = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(config);
		// 通过工厂获取SqlSession
		SqlSession session = sessionFactory.openSession();
		// 通过SqlSession操作数据库
		// statement参数指定映射文件中"namespace+id"
		// parameter参数指定parameterType类型的参数
		User user = session.selectOne("test.findUserById", 1);
		List<User> users = session.selectList("test.findUserByName", "qq");
		System.out.println(user);
		System.out.println(users);

		session.close();
	}

	@Test
	public void insertUserTest() throws IOException {
		String resource = "config/SqlMapConfig1.xml";
		InputStream config = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(config);
		// 通过工厂获取SqlSession
		SqlSession session = sessionFactory.openSession();
		// 通过SqlSession操作数据库
		// statement参数指定映射文件中"namespace+id"
		// parameter参数指定parameterType类型的参数
		User user = new User();
		user.setAddress("11");
		user.setBirthday(new Date());
		user.setSex("bb");
		user.setUsername("大中华");
		int result = session.insert("test.insertUser", user);
		System.out.println(result);

		session.commit();

		session.close();
	}

	@Test
	public void deleteUserTest() throws IOException {
		String resource = "config/SqlMapConfig1.xml";
		InputStream config = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(config);
		// 通过工厂获取SqlSession
		SqlSession session = sessionFactory.openSession();
		// 通过SqlSession操作数据库
		// statement参数指定映射文件中"namespace+id"
		// parameter参数指定parameterType类型的参数
		session.delete("test.deleteUser",10);

		session.commit();

		session.close();
	}

	@Test
	public void updateUserTest() throws IOException {
		String resource = "config/SqlMapConfig1.xml";
		InputStream config = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(config);
		// 通过工厂获取SqlSession
		SqlSession session = sessionFactory.openSession();
		// 通过SqlSession操作数据库
		// statement参数指定映射文件中"namespace+id"
		// parameter参数指定parameterType类型的参数
		User user = new User();
		//user.setId(9);
		user.setAddress("23");
		user.setBirthday(new Date());
		user.setSex("Male");
		user.setUsername("大千世界");
		int result = session.insert("test.updateUser", user);
		System.out.println(result);

		session.commit();

		session.close();
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSpringWithMybatis() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}
}
