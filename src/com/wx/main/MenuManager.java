package com.wx.main;


import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.wx.util.WXUrlCodeUtil;
import com.wx.util.WXCommonUtil;
import com.wx.util.WXMenuUtil;
import com.wx.menu.Button;
import com.wx.menu.CommonButton;
import com.wx.menu.ComplexButton;
import com.wx.menu.Menu;
import com.wx.menu.ViewButton;
import com.wx.pojo.Token;

public class MenuManager {
	public static Logger logger=Logger.getLogger(MenuManager.class);
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		String appId="wx88344b554e23fa74";
		String appSecret="7e5dd6de0b30c2d42c926bca96e00d7b ";
		
		Token at = WXCommonUtil.getAccessToken(appId, appSecret);
		
		System.out.println(at);
		if(null!=at){
			boolean result = WXMenuUtil.createMenu(getMenu(), at.getAccessToken());
			
			if(result){
				logger.info("创建菜单成功");
			}else{
				logger.info(String.format("创建菜单失败%d", result));
			}
		}
		
	}
	
	private static Menu getMenu() throws UnsupportedEncodingException{
		
		Menu menu= new Menu();
		
		String redirect_uri=WXUrlCodeUtil.urlEncode("http://peterzhang2012.oicp.net/wx/Oauth");
		
		System.out.println(redirect_uri);
		
		ViewButton btn11 = new ViewButton();
		btn11.setName("大转盘");
		btn11.setType("view");
		btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?"+
					 "appid="+"wx88344b554e23fa74"+
					 "&redirect_uri="+redirect_uri+
					 "&response_type="+"code"+
					 "&scope="+"snsapi_userinfo"+
					 "&state=STATE#wechat_redirect");
		
		
		CommonButton btn21 = new CommonButton();
		btn21.setName("通知信息");
		btn21.setType("click");
		btn21.setKey("21");

		
		menu.setButton(new Button[]{btn11,btn21});
		
		return menu;
	}

}
