package org.ruyin.code.spring.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.ruyin.code.spring.mybatis.bean.User;
import org.ruyin.code.spring.mybatis.mapper.UserMapper;

public class CacheTest {

	SqlSessionFactory factory;

	@Before
	public void init() throws IOException {
		String resource = "config/SqlMapConfig1.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * SqlSession级别的缓存
	 * sqlSession中的数据销毁时机(更新,删除,关闭,插入)
	 */
	@Test
	public void testCache1() throws Exception {
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		//userMapper.updateUser(user1);
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		sqlSession.close();
	}
	
	/**
	 * 二级缓存是跨sqlsession的
	 * 执行关闭操作,将SqlSession数据写入到缓存区域
	 */
	@SuppressWarnings("unused")
	@Test
	public void testCache2() throws Exception {
		SqlSession sqlSession1 = factory.openSession();
		SqlSession sqlSession2 = factory.openSession();
		SqlSession sqlSession3 = factory.openSession();
		
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		sqlSession1.close();
		
		/*UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		User user = userMapper3.findUserById(1);
		user.setUsername("qianzhui");
		userMapper3.updateUser(user);
		//清空二级缓存
		sqlSession3.commit();
		sqlSession3.close();*/
		
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);
		sqlSession2.close();
	}
}
