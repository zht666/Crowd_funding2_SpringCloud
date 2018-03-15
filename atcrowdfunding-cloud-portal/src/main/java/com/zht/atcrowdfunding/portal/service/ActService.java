package com.zht.atcrowdfunding.portal.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eureka-activiti-service")
public interface ActService {

	@RequestMapping("/startProcessInstance/{loginacct}")
	public String startProcessInstance(@PathVariable("loginacct")String loginacct);

}
