package com.wx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import com.wx.model.Config;
import com.wx.service.ConfigService;

public class WXTokenUtil {
	 ApplicationContext factory = new FileSystemXmlApplicationContext("classpath:/resource/applicationContext-servlet.xml");
	 ConfigService configService = (ConfigService)factory.getBean("ConfigService");  

	public  String getToken(){
		String token=null;
		String hql="from "+ConfigService.class.getName();
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("name", "accessToken");
		
		List<Config> configList =configService.getObjectListByHql(hql, map);
		Config config=configList.get(0);
		token=config.getValue();
		return token;
	}
	public  void saveToken(String token){
		String hql="from "+ConfigService.class.getName();
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("name", "accessToken");
		
		List<Config> configList =configService.getObjectListByHql(hql, map);
		Config config=configList.get(0);
		
		config.setValue(token);
		configService.updateObject(config);
		
	}
	

}
