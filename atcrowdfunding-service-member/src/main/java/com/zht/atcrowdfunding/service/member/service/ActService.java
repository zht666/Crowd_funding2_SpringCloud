package com.zht.atcrowdfunding.service.member.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zht.atcrowdfunding.common.bean.Member;

@FeignClient("eureka-activiti-service")
public interface ActService {

	@RequestMapping("/process/{loginacct}")
	public void process(@PathVariable("loginacct") String loginacct);

	@RequestMapping("/nextProcess/{loginacct}")
	public void nextProcess(@PathVariable("loginacct") String loginacct);

	@RequestMapping("/sendEmail")
	public String sendEmail(@RequestBody Member loginMember);
	
	@RequestMapping("/endProcess/{taskid}")
	public void endProcess(@PathVariable("taskid") String taskid);
}
