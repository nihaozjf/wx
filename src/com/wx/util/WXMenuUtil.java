package com.wx.util;



import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.wx.menu.Menu;

public class WXMenuUtil {
    
	public static Logger logger=Logger.getLogger(WXMenuUtil.class);

	private final static  String menu_create_url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	private final static  String menu_get_url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	private final static String menu_delete_url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	/**
	 * 创建菜单
	 * @param menu
	 * @param accessToken
	 * @return true or false
	 */
	
	public static boolean createMenu(Menu menu,String accessToken){
		boolean result = false;
		
		String url=menu_create_url.replace("ACCESS_TOKEN", accessToken);
		String jsonMenu = JSONObject.fromObject(menu).toString();
		JSONObject jsonObject = WXCommonUtil.httpRequest(url, "POST", jsonMenu);
		if(null!=jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMessage=jsonObject.getString("errmsg");
			
			if(0==errorCode){
				result=true;
			}else{
				result=false;
				logger.error(String.format("创建菜单失败 %d %s",errorCode,errorMessage));
			}
			
		}
		return result;
	}
	/**
	 * 查询菜单
	 */
	public static String getMenu(String accessToken){
	
		String result=null;
		String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accessToken);
	
		JSONObject jsonObject =  WXCommonUtil.httpRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			result= jsonObject.toString();
		}
		
		return result;
	
	}
	/**
	 * 删除菜单
	 */
	public static boolean deleteMenu(String accessToken){
		boolean result = false;
		
		String requestUrl = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = WXCommonUtil.httpRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg= jsonObject.getString("errmsg");
			if(0==errorCode){
				result = true;
			}else{
				result=false;
				logger.error(String.format("删除菜单失败%d %s", errorCode,errorMsg));
			}
		}
		return result;
	}

}
