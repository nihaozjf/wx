package com.wx.menu;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;




import com.wx.pojo.Token;
import com.wx.util.WXUrlCodeUtil;
import com.wx.util.WXCommonUtil;
import com.wx.util.WXMenuUtil;

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
				logger.info(String.format("创建菜单失败，错误码是:%d", result));
			}
		}
		
	}
	
	private static Menu getMenu() throws UnsupportedEncodingException{
		Menu menu= new Menu();
		
		ViewButton btn11 = new ViewButton();
		String redirect_uri=WXUrlCodeUtil.urlEncode("http:");
		btn11.setName("买电影票");
		btn11.setType("view");
		btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?"+
					 "appid="+"wx88344b554e23fa74"+
					 "&redirect_uri="+"http%3A%2F%2Fershoupiao.sinaapp.com%2FOAuthServlet"+
					 "&response_type="+"code"+
					 "&scope="+"snsapi_userinfo"+
					 "&state=STATE#wechat_redirect");
		
		CommonButton btn12 = new CommonButton();
		btn12.setName("话剧票");
		btn12.setType("click");
		btn12.setKey("12");
		
		CommonButton btn21 = new CommonButton();
		btn21.setName("卖电影票");
		btn21.setType("click");
		btn21.setKey("21");
		
	//	CommonButton btn22 = new CommonButton();
	//	btn22.setName("话剧票");
	//	btn22.setType("click");
	//	btn22.setKey("22");
		
	//	ComplexButton mainBtn1 = new ComplexButton();
	//	mainBtn1.setName("我要买票");
	//	mainBtn1.setSub_button(new CommonButton[]{btn11,btn12});
		
	//	ComplexButton mainBtn2 = new ComplexButton();
	//	mainBtn2.setName("我要卖票");
	//	mainBtn2.setSub_button(new CommonButton[]{btn21,btn22});
		
		menu.setButton(new Button[]{btn11,btn21});
		
		return menu;
	}

}
