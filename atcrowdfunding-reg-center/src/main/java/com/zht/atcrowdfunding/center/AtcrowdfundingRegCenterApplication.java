package com.zht.atcrowdfunding.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AtcrowdfundingRegCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcrowdfundingRegCenterApplication.class, args);
	}
}
