package org.ruyin.code.spring.mybatis.mapper;

import java.util.List;

import org.ruyin.code.spring.mybatis.bean.User;
import org.ruyin.code.spring.mybatis.bean.UserCustom;
import org.ruyin.code.spring.mybatis.bean.UserQueryVo;

public interface UserMapper {

	//根据id查询用户信息
	public User findUserById(int id)throws Exception;
	
	//根据用户名查询用户列表
	public List<User> findUserByName(String name)throws Exception;

	//添加用户信息
	public void insertUser(User user)throws Exception;

	//删除用户信息
	public void deleteUser(int id)throws Exception;
	
	//用户信息综合查询,pojo包装类
	public List<UserCustom> findUserList(UserQueryVo userQueryVo)throws Exception;
	
	public void updateUser(User user)throws Exception;
}
