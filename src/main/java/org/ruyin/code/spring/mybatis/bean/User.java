package org.ruyin.code.spring.mybatis.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表,序列化方便反序列化,可能缓存数据不一定只存在于内存,可能存储在硬盘或者远端的服务器上
 */
public class User implements Serializable{

	private static final long serialVersionUID = -8351035021004308290L;
	
	private int id;
	private String username;
	private String sex;
	private String address;
	private Date birthday;

	private List<Orders> orders;

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", address=" + address + ", birthday="
				+ birthday + "]";
	}

}
