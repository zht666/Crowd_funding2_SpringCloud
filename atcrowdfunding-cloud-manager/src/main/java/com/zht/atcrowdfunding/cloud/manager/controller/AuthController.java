package com.zht.atcrowdfunding.cloud.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zht.atcrowdfunding.cloud.manager.service.MemberService;
import com.zht.atcrowdfunding.cloud.manager.service.ProcessService;
import com.zht.atcrowdfunding.common.BaseController;
import com.zht.atcrowdfunding.common.bean.MemberCert;
import com.zht.atcrowdfunding.common.bean.Page;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@Autowired
	private ProcessService processService;
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@RequestMapping("/ok")
	public Object ok(String taskid, Integer memberid) {
		start();
		
		try {
			
			//更新会员实名认证状态
			Map<String, Object> varMap = new HashMap<>();
			varMap.put("memberid", memberid);
			varMap.put("taskid", taskid);
			//完成审核
			memberService.finishAuth(varMap);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
	
	@RequestMapping("/detail")
	public String detail(String taskid, Integer memberid, Model model) {
		List<MemberCert> mcs = memberService.queryMemberCertsByMemberid(memberid);
		model.addAttribute("mcs", mcs);
		model.addAttribute("memberid", memberid);
		model.addAttribute("taskid", taskid);
		
		return "auth/detail";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "auth/index";
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(Integer pageno, Integer pagesize) {
		start();
		
		try {
			
			int count = processService.pageQueryTaskCount();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);
			
			List<Map<String, Object>> taskMaps = processService.pageQueryTaskData(paramMap);
			
			Page<Map<String, Object>> taskPage = new Page<>();
			taskPage.setTotalPagesize(count);
			taskPage.setPageno(pageno);
			taskPage.setPagesize(pagesize);
			taskPage.setDatas(taskMaps);
			data(taskPage);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
}
