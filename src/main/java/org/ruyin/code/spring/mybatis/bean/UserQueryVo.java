package org.ruyin.code.spring.mybatis.bean;

import java.util.List;

/**
 * pojo的包装类
 */
public class UserQueryVo {

	private List<Integer> ids;
	private UserCustom userCustom;

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	public List<Integer> getIds() {
		return ids;
	}
	
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
}
