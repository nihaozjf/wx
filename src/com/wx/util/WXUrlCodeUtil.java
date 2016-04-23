package com.wx.util;

import java.io.UnsupportedEncodingException;

public class WXUrlCodeUtil {
	
	
	public static String urlEncode(String url) throws UnsupportedEncodingException{
		String result=null;
		try{
			result=java.net.URLEncoder.encode(url,"utf-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return result;
	}

}
