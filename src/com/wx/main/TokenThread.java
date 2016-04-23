package com.wx.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wx.model.Config;
import com.wx.pojo.Token;

import com.wx.service.ConfigService;
import com.wx.util.ApplicationContextWrapper;
import com.wx.util.WXCommonUtil;
import com.wx.util.WXTokenUtil;

public class TokenThread implements Runnable {
	
	private static Logger logger=Logger.getLogger(TokenThread.class);
	public static String appid="";
	public static String appsecret="";
	public static Token token = null;

	AnnotationConfigApplicationContext ctx =new AnnotationConfigApplicationContext(ConfigService.class);
	ConfigService configService=ctx.getBean(ConfigService.class);
	
	
	
	public void run(){
		while(true){
			try{
				token=WXCommonUtil.getAccessToken(appid, appsecret);
				if(null!=token){
					logger.info(String.format("获取accessToken 成功  %s ",token.getAccessToken()));
					
					String queryHql="select cfg from "+Config.class.getSimpleName()+" cfg  where name=:token";
					Map<String, Object> map=new HashMap<String,Object>();
					map.put("token", "accessToken");
					Config config=configService.getObjectByquery(queryHql, map);
					if(config!=null){
						logger.info("update accessToken");
						config.setValue(TokenThread.token.getAccessToken());
						configService.updateObject(config);
					}else{
						logger.info("save accessToken");
						Config cfg= new Config();
						cfg.setName("accessToken");
						cfg.setValue(TokenThread.token.getAccessToken());
						configService.saveObject(cfg);
					}
				
					
					Thread.sleep((token.getExpiresIn()-200)*1000);
					
				}else{
					Thread.sleep(60*1000);
				}
			}catch (InterruptedException e) {
				// TODO: handle exception
				try{
					Thread.sleep(60*1000);
				}catch(InterruptedException e1){
					logger.error(e1.toString());
				}
				logger.error(e.toString());
			}
		}
	}

}
