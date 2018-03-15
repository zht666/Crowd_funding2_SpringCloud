package com.zht.atcrowdfunding.portal.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zht.atcrowdfunding.common.bean.Cert;
import com.zht.atcrowdfunding.common.bean.Member;
import com.zht.atcrowdfunding.common.bean.MemberCert;
import com.zht.atcrowdfunding.common.bean.Ticket;

@FeignClient("eureka-member-service")
public interface MemberService {

	@RequestMapping("/login/{loginacct}")
	public Member login(@PathVariable("loginacct")String loginacct);

	@RequestMapping("/queryTicketByMembetid/{id}")
	public Ticket queryTicketByMembetid(@PathVariable("id")Integer id);

	@RequestMapping("/insertTicket")
	public void insertTicket(@RequestBody Ticket t);

	@RequestMapping("/updateAccoutType")
	//传对象需要添加@RequestBody 注解
	public void updateAccoutType(@RequestBody Member loginMember);

	@RequestMapping("/updateBasicinfo")
	public void updateBasicinfo(@RequestBody Member loginMember);

	@RequestMapping("/queryCertsByAccoutType/{accttype}")
	public List<Cert> queryCertsByAccoutType(@PathVariable("accttype")String accttype);

	@RequestMapping("/insertMemberCerts")
	public void insertMemberCerts(@RequestBody List<MemberCert> mcs);

	@RequestMapping("/updateEmail")
	public void updateEmail(@RequestBody Member loginMember);

	@RequestMapping("/updateAuthstatus")
	public void updateAuthstatus(@RequestBody Member loginMember);
}
