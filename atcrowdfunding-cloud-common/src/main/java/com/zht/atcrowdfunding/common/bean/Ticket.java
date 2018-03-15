package com.zht.atcrowdfunding.common.bean;

import java.io.Serializable;
/**
 * 流程审批单
 * @author : ZHT
 * @date : 2018年2月25日
 */
public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer memberid;//会员ID
	private String piid;//审批流程实例id
	private String status;//状态
	private String authcode;//验证码
	private String pstep;//流程步骤
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getPstep() {
		return pstep;
	}
	public void setPstep(String pstep) {
		this.pstep = pstep;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
