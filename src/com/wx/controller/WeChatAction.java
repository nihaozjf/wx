package com.wx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import net.sf.json.JSONObject;

import org.hibernate.hql.ast.tree.FromClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;








import com.wx.service.ConfigService;
import com.wx.service.WxUserInfoService;
import com.wx.main.Lottery;
import com.wx.main.TokenThread;
import com.wx.model.Config;
import com.wx.model.WxUserInfo;
import com.wx.pojo.SNSUserInfo;
import com.wx.pojo.WXOauth2Token;
import com.wx.util.WXAdvancedUtil;
import com.wx.util.WXTokenUtil;
import com.zhuanpan.model.Magic_Config;
import com.zhuanpan.model.Magic_Useraddnumber;
import com.zhuanpan.model.Magic_Useraddorder;
import com.zhuanpan.service.MagicConfigService;
import com.zhuanpan.service.MagicUserAddNumberService;
import com.zhuanpan.service.MagicUserAddOrderService;




/**
 * @author Administrator
 *
 */
@Controller
public class WeChatAction{
	
	@Autowired
	private WxUserInfoService wxUserInfoService;
	@Autowired
	private MagicConfigService magicConfigService;
	@Autowired
	private MagicUserAddNumberService magicUserAddNumberService;
	@Autowired
	private MagicUserAddOrderService magicUserAddOrderService;
	
	AnnotationConfigApplicationContext ctx =new AnnotationConfigApplicationContext(ConfigService.class);
	ConfigService configService=ctx.getBean(ConfigService.class);
	

	@RequestMapping(value="/weixin",method=RequestMethod.GET)
    public void wxget(HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
 	  MyWeChat myWechat=new MyWeChat(request);
 	  String result=myWechat.execute();
 	  response.getOutputStream().write(result.getBytes());
    }
	
	@RequestMapping(value="/weixin",method=RequestMethod.POST)
    public void wxpost(HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
 	  MyWeChat myWechat=new MyWeChat(request);
 	  String result=myWechat.execute();
 	  response.getOutputStream().write(result.getBytes());
    }
	@RequestMapping(value="/Oauth",method=RequestMethod.GET)
    public ModelAndView OauthGet(HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
		String appID="wx88344b554e23fa74";
		String appsecret="7e5dd6de0b30c2d42c926bca96e00d7b";
		//用户同意后能获取code
		String code = request.getParameter("code");
		//用户同意授权
		if(!"authdeny".equals(code)){
			//获取网页授权access_TOken
			WXOauth2Token wxOauth2Token =WXAdvancedUtil.getOauth2Token(appID, appsecret, code);
			//网页授权接口访问凭证
			String accessToken= wxOauth2Token.getAccessToken();
			
			//用户标识
			String openId=wxOauth2Token.getOpenId();
			//获取用户标识
			SNSUserInfo snsUserInfo = WXAdvancedUtil.getSNSUserInfo(accessToken, openId);
			
			WxUserInfo userInfo=new WxUserInfo();
		    
			userInfo.setOpenId(snsUserInfo.getOpenId());
			userInfo.setNickName(snsUserInfo.getNickName());
			
			wxUserInfoService.saveObject(userInfo);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("userinfo", snsUserInfo);
			
			return new ModelAndView("wx/zhuanpan",map);
		}else{
			return null;
		}
    }
	@RequestMapping(value="/token",method=RequestMethod.GET)
    public void token(HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
		String result =null;
 	 // MyWeChat myWechat=new MyWeChat(request);
 	 // String result=TokenThread.token.getAccessToken();

		String queryHql="select cfg from "+Config.class.getSimpleName()+" cfg  where name=:token";
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("token", "accessToken");
		Config config=configService.getObjectByquery(queryHql, map);
		if(config!=null){
			result=config.getValue();
			System.out.println(result);
			
		}

		else{
			result=null;
		}
 	  response.getOutputStream().write(result.getBytes());
    }
	@RequestMapping(value="/zhuanpan",method=RequestMethod.POST)
    public @ResponseBody    JSONObject ZhuanPanGet(HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
		List<Magic_Config> magic_Configs=magicConfigService.getAllObject();
		
		String userNumberHql="select uan from "+Magic_Useraddnumber.class.getSimpleName()+" uan  where aid=:id";
		Map<String, Object> numbermap=new HashMap<String,Object>();
		numbermap.put("id", 2);
		Magic_Useraddnumber magic_Useraddnumber=magicUserAddNumberService.getObjectByquery(userNumberHql, numbermap);
		
		
		

		
		int times=magic_Useraddnumber.getNumber();
		Lottery lottery = new Lottery();
		Map<String, Object> resultmap  =lottery.choujiang(magic_Configs,times);
		
//		System.out.println(resultmap.get("angle"));
//		System.out.println(resultmap.get("praisename"));
//		System.out.println(resultmap.get("praisecontent"));
		//用户抽奖次数减1
		
		Integer num=(Integer) resultmap.get("num");
		Integer prizeid=(Integer)resultmap.get("prizeid");
		if(num!=0){
			magic_Useraddnumber.setNumber(num);
			magicUserAddNumberService.updateObject(magic_Useraddnumber);
			Magic_Useraddorder magic_Useraddorder= new Magic_Useraddorder();
			magic_Useraddorder.setAid(2);
			magic_Useraddorder.setPrizeid(prizeid);
			magicUserAddOrderService.saveObject(magic_Useraddorder);
			
		}
	    
		
		JSONObject jobj = new JSONObject();
		jobj.accumulate("angle",resultmap.get("angle"));
		jobj.accumulate("praisename",resultmap.get("praisename"));
		jobj.accumulate("num",resultmap.get("num"));
		
		
		return jobj;
		
    }
	@RequestMapping(value="/choujiang",method=RequestMethod.GET)
    public ModelAndView ChouJiangGet(HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
	
		return new ModelAndView("wx/zhuanpan");
		
    }
	
	
}
