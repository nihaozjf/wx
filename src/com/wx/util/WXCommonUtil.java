package com.wx.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.wx.pojo.Token;



public class WXCommonUtil {
	
	public static Logger logger=Logger.getLogger(WXCommonUtil.class);
	
	
	private  static String TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?"+
								"grant_type=client_credential"+
								"&appid=APPID"+
								"&secret=APPSECRET";
	
	/**
	 * 发起https请求并获取结果
	 * @param  requestURL 请求地址
	 * @param requestMethod 请求方法(GET,POST)
	 * @param outputStr 提交的数据
	 * @return JSONObject
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod,String outputStr){
		
		JSONObject jsonObject=null;
		StringBuffer buffer = new StringBuffer();
		try{
			//创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm= {new MyX509TrustManager()};
			SSLContext sslContext=SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			SSLSocketFactory ssf= sslContext.getSocketFactory();
			
			URL url=new URL(requestUrl);
			HttpsURLConnection httpUrlConn=(HttpsURLConnection)url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			
			httpUrlConn.setRequestMethod(requestMethod);
			
			if("GET".equals(requestMethod)){
				httpUrlConn.connect();
			}
			if(null != outputStr){
				OutputStream outputStream=httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream=httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str=null;
			
			while((str=bufferedReader.readLine())!=null){
				buffer.append(str);
			}
			
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream=null;
			httpUrlConn.disconnect();
			
			jsonObject=JSONObject.fromObject(buffer.toString());
			
		}catch(ConnectException ce){
			logger.error("Weixin server connection timed out");
		}catch(Exception e){
			logger.error("https request error");
		}
		
		return jsonObject;
	}
	
	/**
	 * 获取 access_token
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static Token getAccessToken(String appid,String appsecret){
		
		
		Token token=null;
		
		String requestUrl = TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		//System.out.println(requestUrl);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			try{
				token= new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			}catch(JSONException e){
				token=null;
				logger.error("获取token失败");
			}
		}
		return token;	
		
	}
	
//	public static void main(String[] args){
//		
//		String appid="wx6751c7de064d0f2d";
//		String appsecret="9e66a496e71cc0b497bfef5ac17898e8 ";
//		
//		Token token= getAccessToken(appid, appsecret);
//		
//		System.out.println("Token:\t"+token.getAccessToken());
//		System.out.println("Expire:\t"+token.getExpiresIn());
//		
//	}

}
