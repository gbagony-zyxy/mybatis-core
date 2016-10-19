package org.ruyin.code.spring.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.ruyin.code.spring.mybatis.bean.Orders;
import org.ruyin.code.spring.mybatis.bean.OrdersCustom;
import org.ruyin.code.spring.mybatis.bean.User;
import org.ruyin.code.spring.mybatis.mapper.OrdersMapperCustom;

public class OrdersMapperTest {

	SqlSessionFactory factory;

	@Before
	public void init() throws IOException {
		String resource = "config/SqlMapConfig1.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindOrderUser() throws Exception {
		SqlSession sqlSession = factory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

		List<OrdersCustom> list =ordersMapperCustom.findOrderUser();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindOrderUserResultMap() throws Exception {
		SqlSession sqlSession = factory.openSession();
		
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findOrderUserResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindOrderAndOrderdetailUserResultMap() throws Exception {
		SqlSession sqlSession = factory.openSession();
		
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findOrderAndOrderdetailUserResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession sqlSession = factory.openSession();
		
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<User> list = ordersMapperCustom.findUserAndItemsResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindUserOrderLazyLoading() throws Exception {
		SqlSession sqlSession = factory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> orders = ordersMapperCustom.findUserOrderLazyLoading();
		System.out.println(orders);
		
		sqlSession.close();
	}
}
