package org.ruyin.code.spring.mybatis.mapper;

import java.util.List;

import org.ruyin.code.spring.mybatis.bean.Orders;
import org.ruyin.code.spring.mybatis.bean.OrdersCustom;

public interface OrdersMapperCustom{
	
	public List<OrdersCustom> findOrderUser()throws Exception;
	
	public List<Orders> findOrderUserResultMap()throws Exception;

	public List<Orders> findOrderAndOrderdetailUserResultMap()throws Exception;
}