package org.ruyin.code.spring.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.ruyin.code.spring.mybatis.bean.User;
import org.ruyin.code.spring.mybatis.bean.UserCustom;
import org.ruyin.code.spring.mybatis.bean.UserQueryVo;
import org.ruyin.code.spring.mybatis.mapper.UserMapper;

public class UserMapperTest {
	SqlSessionFactory factory;

	@Before
	public void init() throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() throws Exception {
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(2);
		System.out.println(user);
		sqlSession.close();
	}
	
	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		userQueryVo.setIds(list);;
		//userCustom.setSex("bb");
		//userCustom.setUsername("nes");
		//userQueryVo.setUsername("nes");
		userQueryVo.setUserCustom(userCustom);
		
		List<UserCustom> listInfo = userMapper.findUserList(userQueryVo);
		System.out.println(listInfo);
		sqlSession.close();
	}
}
