package com.zht.atcrowdfunding.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@ServletComponentScan
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
//@EnableHystrix
public class AtcrowdfundingCloudPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcrowdfundingCloudPortalApplication.class, args);
	}
}
