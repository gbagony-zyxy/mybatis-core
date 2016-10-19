package org.ruyin.code.spring.mybatis.mapper;

import java.util.List;

import org.ruyin.code.spring.mybatis.bean.Orders;
import org.ruyin.code.spring.mybatis.bean.OrdersCustom;
import org.ruyin.code.spring.mybatis.bean.User;

public interface OrdersMapperCustom{
	//一对一
	public List<OrdersCustom> findOrderUser()throws Exception;
	//一对多
	public List<Orders> findOrderUserResultMap()throws Exception;
	//多对一
	public List<Orders> findOrderAndOrderdetailUserResultMap()throws Exception;
	//多对多  查询用户购买商品的信息
	public List<User> findUserAndItemsResultMap()throws Exception;
	//延迟加载用户信息
	public List<Orders> findUserOrderLazyLoading()throws Exception;
}