package com.wx.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextWrapper implements ApplicationContextAware{
	
	      
	private static ApplicationContext applicationContext;
	 
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	ApplicationContextWrapper.applicationContext = applicationContext;
    }
 
    public static ApplicationContext getApplicationContext() {
    	return applicationContext;
    }
 
    public static Object getBean(String name){
    	return getApplicationContext().getBean(name);
    }

}
