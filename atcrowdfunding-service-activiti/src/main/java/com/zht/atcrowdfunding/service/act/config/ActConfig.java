package com.zht.atcrowdfunding.service.act.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

//接口注入
@Configuration
public class ActConfig implements ProcessEngineConfigurationConfigurer {

	@Override
	public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
		processEngineConfiguration.setActivityFontName("宋体");
		processEngineConfiguration.setLabelFontName("宋体");
		processEngineConfiguration.setMailServerPort(25);
	}

}
