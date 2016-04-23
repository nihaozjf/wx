package com.wx.main;

import com.wx.service.ConfigService;
import com.wx.util.ApplicationContextWrapper;
import com.wx.util.WXTokenUtil;

public class GetUserList {
	
	
	public static void main(String[]args){
		//ConfigService configService =(ConfigService)ApplicationContextWrapper.getBean("ConfigService");
	   String aString="abcd";
	   String bString="a,b";
	   String cString="a,b,c,d";
		System.out.println(aString.split(",").length);
		System.out.println(bString.split(",").length);
		System.out.println(cString.split(",").length);
	}

	
	
	

}
