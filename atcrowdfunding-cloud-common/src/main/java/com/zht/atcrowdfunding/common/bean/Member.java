package com.zht.atcrowdfunding.common.bean;

import java.io.Serializable;

public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String loginacct;
	private String memberpswd;
	private String membername;
	private String email;
	private String authstatus;
	private String type;
	private String realname;
	private String cardnum;
	private String accttype;
	private String actstatus;
	private String tel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginacct() {
		return loginacct;
	}

	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}

	public String getMemberpswd() {
		return memberpswd;
	}

	public void setMemberpswd(String memberpswd) {
		this.memberpswd = memberpswd;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthstatus() {
		return authstatus;
	}

	public void setAuthstatus(String authstatus) {
		this.authstatus = authstatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getAccttype() {
		return accttype;
	}

	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}

	public String getActstatus() {
		return actstatus;
	}

	public void setActstatus(String actstatus) {
		this.actstatus = actstatus;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
