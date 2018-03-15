package com.zht.atcrowdfunding.service.act.service;

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

	@RequestMapping("/queryMemberByPiid/{piid}")
	Member queryMemberByPiid(@PathVariable("piid") String piid);

}
