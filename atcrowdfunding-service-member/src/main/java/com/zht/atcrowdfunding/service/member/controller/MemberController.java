package com.zht.atcrowdfunding.service.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zht.atcrowdfunding.common.bean.Cert;
import com.zht.atcrowdfunding.common.bean.Member;
import com.zht.atcrowdfunding.common.bean.MemberCert;
import com.zht.atcrowdfunding.common.bean.Ticket;
import com.zht.atcrowdfunding.service.member.service.ActService;
import com.zht.atcrowdfunding.service.member.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private ActService actService;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/finishAuth")
	public void finishAuth(@RequestBody Map<String, Object> varMap) {
		//更新会员的实名认证状态
		Integer memberid = (Integer) varMap.get("memberid");
		Member member = memberService.queryMemberById(memberid);
		member.setAuthstatus("2");
		memberService.updateAuthstatus(member);
		
		//让流程继续执行
		String taskid = (String) varMap.get("taskid");
		actService.endProcess(taskid);
		
		//更新流程审批单状态
		Ticket t = memberService.queryTicketByMembetid(memberid);
		t.setStatus("1");
		memberService.updateTicketStatus(t);
	}
	
	@RequestMapping("/queryMemberCertsByMemberid/{memberid}")
	public List<MemberCert> queryMemberCertsByMemberid(@PathVariable("memberid") Integer memberid){
		return memberService.queryMemberCertsByMemberid(memberid);
	}
	
	@RequestMapping("/queryMemberByPiid/{piid}")
	public Member queryMemberByPiid(@PathVariable("piid") String piid) {
		return memberService.queryMemberByPiid(piid);
	}
	
	@RequestMapping("/updateAuthstatus")
	public void updateAuthstatus(@RequestBody Member loginMember) {
		//更新会员实名认证状态
		memberService.updateAuthstatus(loginMember);
		//让流程继续执行
		actService.process(loginMember.getLoginacct());
	}
	
	@RequestMapping("/updateEmail")
	public void updateEmail(@RequestBody Member loginMember) {
		//更新邮箱地址
		memberService.updateEmail(loginMember);
		//让流程继续执行
		String authcode = actService.sendEmail(loginMember);
		//更新流程步骤
		Ticket t = memberService.queryTicketByMembetid(loginMember.getId());
		t.setPstep("checkcode");
		t.setAuthcode(authcode);
		memberService.updateAuthcodeAndStep(t);
	}
	
	@RequestMapping("/insertMemberCerts")
	public void insertMemberCerts(@RequestBody List<MemberCert> mcs) {
		//增加会员证明文件数据
		memberService.insertMemberCerts(mcs);
		
		Integer memberid = mcs.get(0).getMemberid();
		Member loginMember = memberService.queryMemberById(memberid);
		//更新流程步骤
		Ticket t = memberService.queryTicketByMembetid(memberid);
		t.setPstep("email");
		memberService.updateStep(t);
		//让流程继续执行
		actService.nextProcess(loginMember.getLoginacct());
	}
	
	@RequestMapping("/queryCertsByAccoutType/{accttype}")
	public List<Cert> queryCertsByAccoutType(@PathVariable("accttype")String accttype){
		return memberService.queryCertsByAccoutType(accttype);
	}
	
	@RequestMapping("/updateBasicinfo")
	public void updateBasicinfo(@RequestBody Member loginMember) {
		//更新会员基本信息
		memberService.updateBasicinfo(loginMember);
		//更新流程步骤
		Ticket t = memberService.queryTicketByMembetid(loginMember.getId());
		t.setPstep("cert");
		memberService.updateStep(t);
		//让流程继续执行
		actService.nextProcess(loginMember.getLoginacct());
	}
	
	@RequestMapping("/updateAccoutType")
	//传对象需要添加@RequestBody 注解
	public void updateAccoutType(@RequestBody Member loginMember) {
		//更新会员账户类型
		memberService.updateAccoutType(loginMember);
		//更新流程步骤
		Ticket t = memberService.queryTicketByMembetid(loginMember.getId());
		t.setPstep("basicinfo");
		memberService.updateStep(t);
		//让流程继续执行
		actService.process(loginMember.getLoginacct());
	}
	
	@RequestMapping("/insertTicket")
	public void insertTicket(@RequestBody Ticket t) {
		memberService.insertTicket(t);
	}
	
	@RequestMapping("/queryTicketByMembetid/{id}")
	public Ticket queryTicketByMembetid(@PathVariable("id")Integer id) {
		Ticket t = memberService.queryTicketByMembetid(id);
		return t;
	}
	
	@RequestMapping("/login/{loginacct}")
	public Object login(@PathVariable("loginacct")String loginacct) {
		Member member = memberService.queryMemberByLoginacct(loginacct);
		return member;
	}
}
