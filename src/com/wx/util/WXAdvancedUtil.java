package com.wx.util;

import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.wx.pojo.SNSUserInfo;
import com.wx.pojo.WXOauth2Token;
import com.wx.pojo.WeixinUserList;

public class WXAdvancedUtil {
	
	public static Logger logger=Logger.getLogger(WXAdvancedUtil.class);
	/**
	 *
	 * @param appId;
	 * @param appSecret;
	 * @param code
	 * @return WXOauth2Token;
	 */
	public static WXOauth2Token getOauth2Token(String appId,String appSecret,String code){
		
		String requestUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";	
		WXOauth2Token wat=null;
		
		requestUrl =requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		
		JSONObject jsonObject = WXCommonUtil.httpRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			try{
				wat = new WXOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
				
			}catch(Exception e){
				wat=null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				
				logger.error(String.format("获取OAuth认证失败 %d %s", errorCode,errorMsg));
				
			}
		}
		return wat;
	}
	/**
	 * 刷新OAuth token
	 * @param appId;
	 * @param refreshToken;
	 * @return WXOauth2Token
	 */
	public static WXOauth2Token refreshOauth2Token(String appId,String refreshToken){
		
		WXOauth2Token wat=null;
		String requestUrl ="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		
		requestUrl = requestUrl.replace("APPID",appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		
		JSONObject jsonObject = WXCommonUtil.httpRequest(requestUrl, "GET", null);
		
		if(null!= jsonObject){
			try{
				wat = new WXOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
				
			}catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				
				logger.error(String.format("ˢ����ҳ��Ȩƾ֤ʧ��%d %s", errorCode,errorMsg));
				// TODO: handle exception
			}
		}
		return wat;
	}
	/**
	 * 获取用户信息
	 * @param access_token
	 * @param openId
	 * @return SNSUserInfo
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken,String openId){
		SNSUserInfo snsUserInfo = null;
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		
		JSONObject jsonObject = WXCommonUtil.httpRequest(requestUrl, "GET", null);
		if(null!=jsonObject){
			
			try{
				snsUserInfo  = new SNSUserInfo();
				
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setNickName(jsonObject.getString("nickname"));
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				snsUserInfo.setCountry(jsonObject.getString("country"));
				snsUserInfo.setProvince(jsonObject.getString("province"));
				snsUserInfo.setCity(jsonObject.getString("city"));
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege")));
				
			}catch (Exception e) {
				snsUserInfo=null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				
				logger.error(String.format("获取用户信息失败%d %s", errorCode,errorMsg));
			}
			
			
		}
		
		return snsUserInfo;
	}
	
	@SuppressWarnings("deprecation")
	public static WeixinUserList getUserList(String accessToken,String nextOpenId){
		
		WeixinUserList weixinUserList = null;
		if(null==nextOpenId){
			nextOpenId="";
		}
		String requestUrl="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("NEXT_OPENID", nextOpenId);
		
		
		JSONObject jsonObject = WXCommonUtil.httpRequest(requestUrl, "GET", null);
		
		if(null!= jsonObject){
			try{
				weixinUserList = new WeixinUserList();
				
				weixinUserList.setTotal(jsonObject.getInt("total"));
				weixinUserList.setCount(jsonObject.getInt("count"));
				weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
				
				JSONObject dataObject = (JSONObject)jsonObject.get("data");
				weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"),List.class));
				
			}catch(JSONException e){
				weixinUserList=null;
				int errorcode =jsonObject.getInt("errcode");
				String errorMsg=jsonObject.getString("errmsg");
				
				logger.error(String.format("获取关注者列表失败%d %s", errorcode,errorMsg));
			}
		}
		return weixinUserList;
	}

}
