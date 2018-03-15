package com.zht.atcrowdfunding.cloud.manager.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
//@WebFilter
//@WebServlet
public class ServerStartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String path = application.getContextPath();
		application.setAttribute("APP_PATH", path);

	}

}
