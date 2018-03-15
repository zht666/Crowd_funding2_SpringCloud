package com.zht.atcrowdfunding.service.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.zht.**.dao")
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AtcrowdfundingServiceMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcrowdfundingServiceMemberApplication.class, args);
	}
}
