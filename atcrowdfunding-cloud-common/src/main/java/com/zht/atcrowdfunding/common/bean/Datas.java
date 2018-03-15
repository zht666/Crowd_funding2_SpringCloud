/**
 * 
 */
package com.zht.atcrowdfunding.common.bean;

import java.util.List;

/**
 * 数据包装类，不能用泛型
 * @author : ZHT
 * @date : 2018年1月25日
 */
public class Datas {

	private List<User> users;
	private List<Role> roles;
	private List<Integer> ids;
	private List<MemberCert> mcs;
	
	public List<MemberCert> getMcs() {
		return mcs;
	}

	public void setMcs(List<MemberCert> mcs) {
		this.mcs = mcs;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	
	
}
