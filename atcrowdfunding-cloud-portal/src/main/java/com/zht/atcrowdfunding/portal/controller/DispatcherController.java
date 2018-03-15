package com.zht.atcrowdfunding.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zht.atcrowdfunding.common.BaseController;
import com.zht.atcrowdfunding.common.bean.MD5Util;
import com.zht.atcrowdfunding.common.bean.Member;
import com.zht.atcrowdfunding.portal.service.MemberService;

@Controller
public class DispatcherController extends BaseController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
//	@HystrixCommand(fallbackMethod="checkLoginError")
	@ResponseBody
	@RequestMapping("/checkLogin")
	public Object checkLogin(Member member, HttpSession session) {
		start();
		
		try {
			//查询会员信息，通过账号查询
			Member dbMember = memberService.login(member.getLoginacct());
			if(dbMember == null) {//如果会员信息不存在
				fail();
			}else {//如果会员信息存在
				//如果输入密码和会员信息表中的密码匹配，登录成功，否则登录失败
				if(dbMember.getMemberpswd().equals(MD5Util.digest(member.getMemberpswd()))) {
					//把登录的会员信息放到session域中
					session.setAttribute("loginMember", dbMember);
					success();
				}else {
					fail();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
//	public Object checkLoginError(Member member) {
//		System.out.println("error....");
//		start();
//		fail();
//		return end();
//	}
}
