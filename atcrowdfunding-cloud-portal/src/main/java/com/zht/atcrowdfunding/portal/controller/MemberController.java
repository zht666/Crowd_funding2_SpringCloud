package com.zht.atcrowdfunding.portal.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zht.atcrowdfunding.common.BaseController;
import com.zht.atcrowdfunding.common.bean.Cert;
import com.zht.atcrowdfunding.common.bean.Datas;
import com.zht.atcrowdfunding.common.bean.Member;
import com.zht.atcrowdfunding.common.bean.MemberCert;
import com.zht.atcrowdfunding.common.bean.Ticket;
import com.zht.atcrowdfunding.common.constant.AttrConst;
import com.zht.atcrowdfunding.portal.service.ActService;
import com.zht.atcrowdfunding.portal.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ActService actService;
	
	@ResponseBody
	@RequestMapping("/finishApply")
	public Object finishApply(HttpSession session, String authcode) {
		start();
		
		try {
			//获取会员信息
			Member loginMember = (Member)session.getAttribute(AttrConst.SESSION_MEMBER);
			//获取流程审批单
			Ticket t = memberService.queryTicketByMembetid(loginMember.getId());
			
			//获取邮件验证码
			//判断验证码是否正确
			if(authcode.equals(t.getAuthcode())) {
				//更新会员实名认证状态
				loginMember.setAuthstatus("1");
				session.setAttribute(AttrConst.SESSION_MEMBER, loginMember);
				memberService.updateAuthstatus(loginMember);
				success();
			}else {
				fail();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/sendEmail")
	public Object sendEmail(HttpSession session, String email) {
		start();

		try {
			Member loginMember = (Member)session.getAttribute(AttrConst.SESSION_MEMBER);
			loginMember.setEmail(email);
			session.setAttribute(AttrConst.SESSION_MEMBER, loginMember);
			//更新会员邮箱地址
			memberService.updateEmail(loginMember);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/uploadCerts")
	public Object uploadCerts(HttpSession session, Datas ds) {
		start();
		
		try {
			//获取登录会员信息
			Member loginMember = (Member)session.getAttribute(AttrConst.SESSION_MEMBER);
			List<MemberCert> mcs = ds.getMcs();
			for (MemberCert mc : mcs) {
				mc.setMemberid(loginMember.getId());
				
				//保存图片
				MultipartFile file = mc.getFile();
				// xxx.jpg
				String fileName = file.getOriginalFilename();
				// xxxxxxxxx-yyyyyy-xxxxxxx.jpg
				String uuidFileName = UUID.randomUUID().toString();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				File destFile = new File("D:\\Study\\resources\\atcrowdfunding\\img\\cert\\" + uuidFileName + suffix);
				file.transferTo(destFile);//将图片保存到指定文件
				
				mc.setIconpath(uuidFileName + suffix);
				mc.setFile(null);
			}
			
			memberService.insertMemberCerts(mcs);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/updateBasicinfo")
	public Object updateBasicinfo(HttpSession session, Member member) {
		start();
		
		try {
			Member loginMember = (Member)session.getAttribute(AttrConst.SESSION_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setTel(member.getTel());
			loginMember.setCardnum(member.getCardnum());
			memberService.updateBasicinfo(loginMember);
			session.setAttribute(AttrConst.SESSION_MEMBER, loginMember);
			
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/updateAccoutType")
	public Object updateAccoutType(HttpSession session, Member member) {
		start();
		
		try {
			//获取登录会员
			Member loginMember = (Member)session.getAttribute(AttrConst.SESSION_MEMBER);
			loginMember.setAccttype(member.getAccttype());
			//更新会员的账户类型
			memberService.updateAccoutType(loginMember);
			//分布式环境中，由于session数据是保存在缓存服务器中
			//所有更新session数据后，应该显示的调用setAttribute方法
			//作用就是将缓存服务器中的数据也同时更新
			session.setAttribute(AttrConst.SESSION_MEMBER, loginMember);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
	
	@RequestMapping("/apply")
	public String apply(HttpSession session, Model model) {
		
		//获取当前的会员信息
		Member loginMember = (Member)session.getAttribute(AttrConst.SESSION_MEMBER);
		
		//查询会员的流程审批单
		Ticket t = memberService.queryTicketByMembetid(loginMember.getId());
		
		//当第一次申请时，跳转到账户类型选择页面
		if(t == null) {
			//启动流程，获取流程实例的ID
			String piid = actService.startProcessInstance(loginMember.getLoginacct());
			
			t = new Ticket();
			t.setMemberid(loginMember.getId());
			t.setPstep("accttype");
			t.setPiid(piid);
			t.setStatus("0");
			
			//保存当前流程审批单
			memberService.insertTicket(t);
			return "member/apply-accttype-select";//账户类型选择页面
		}else {
			//根据流程步骤跳转页面
			String step = t.getPstep();
			
			//如果是基本信息
			if("basicinfo".equals(step)) {
				return "member/apply-basic-info";//基本信息页面
			//如果是证明文件
			}else if("cert".equals(step)) {
				//查询当前会员需要提交的证明文件列表
				List<Cert> certs = memberService.queryCertsByAccoutType(loginMember.getAccttype());
				model.addAttribute("certs", certs);
				return "member/apply-cert-upload";//证明文件上传页面
			}else if("email".equals(step)) {
				return "member/apply-email";
			}else if("checkcode".equals(step)) {
				return "member/apply-check-code";
			//如果是账户类型
			}else {
				return "member/apply-accttype-select";
			}
		}
		
	}
}
