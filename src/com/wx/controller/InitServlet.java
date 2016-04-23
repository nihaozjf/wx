package com.wx.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wx.main.TokenThread;

import com.wx.util.ApplicationContextWrapper;
import com.wx.util.WXCommonUtil;

public class InitServlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	private static Logger logger=Logger.getLogger(WXCommonUtil.class);
	
	public void init()throws ServletException{
		TokenThread.appid=getInitParameter("appid");
		TokenThread.appsecret=getInitParameter("appsecret");
		
		    
        
		logger.info(String.format("appid: %s",TokenThread.appid));
		logger.info(String.format("appsecret: %s",TokenThread.appsecret));
		
		if("".equals(TokenThread.appid)||"".equals(TokenThread.appsecret)){
			logger.info(String.format("appid and appsecret config error"));
		}else{
			new Thread(new TokenThread()).start();
			
		}
	}

}
