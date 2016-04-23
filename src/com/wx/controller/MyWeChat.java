package com.wx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.sword.wechat4j.WechatSupport;
import org.sword.wechat4j.response.ArticleResponse;



public class MyWeChat extends WechatSupport {


	HttpServletRequest request = null;

	public MyWeChat(HttpServletRequest request) {
		super(request);
		this.request=request;
	
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void click() {
		// TODO Auto-generated method stub
		// responseText("Hello World click!");
		 
		 List<ArticleResponse> items=new ArrayList<ArticleResponse>();
         
		 ArticleResponse item=new ArticleResponse();
		 item.setDescription("1守住一份寂寞，留住一份亲情，做我所做，爱我所爱！");
		 item.setPicUrl("http://www.caipingzhushou.com/hqservice/Image/test.jpg");
		 item.setTitle("守住一份寂寞，留住一份亲情，做我所做，爱我所爱");
		 item.setUrl("http://www.baidu.com");
		 items.add(item);
		 
		 item=new ArticleResponse();
		 item.setDescription("2守住一份寂寞，留住一份亲情，做我所做，爱我所爱！");
		 item.setPicUrl("http://www.caipingzhushou.com/hqservice/Image/test.jpg");
		 item.setTitle("2守住一份寂寞，留住一份亲情，做我所做，爱我所爱");
		 item.setUrl("http://www.baidu.com");
		 items.add(item);
		 
		 
		 this.responseNews(items);
		

	}

	@Override
	protected void location() {
		// TODO Auto-generated method stub
		 responseText("Hello World location!");

	}

	@Override
	protected void locationSelect() {
		// TODO Auto-generated method stub
		responseText("Hello World locationSelect!");

	}

	@Override
	protected void onImage() {
		// TODO Auto-generated method stub
		responseText("Hello World onImage!");

	}

	@Override
	protected void onLink() {
		// TODO Auto-generated method stub
		responseText("Hello World onLink!");

	}

	@Override
	protected void onLocation() {
		// TODO Auto-generated method stub
		responseText("Hello World onLocation!");

	}

	@Override
	protected void onText() {
		// TODO Auto-generated method stub
      responseText("Hello World onText!");
  
	}

	@Override
	protected void onUnknown() {
		// TODO Auto-generated method stub
		responseText("Hello World onUnknown !");

	}

	@Override
	protected void onVideo() {
		// TODO Auto-generated method stub
		responseText("Hello World onVideo !");
	}

	@Override
	protected void onVoice() {
		// TODO Auto-generated method stub
		responseText("Hello World onVoice !");

	}

	@Override
	protected void picPhotoOrAlbum() {
		// TODO Auto-generated method stub
		responseText("Hello World picPhotoOrAlbum !");

	}

	@Override
	protected void picSysPhoto() {
		// TODO Auto-generated method stub
		responseText("Hello World picSysPhoto !");

	}

	@Override
	protected void picWeixin() {
		// TODO Auto-generated method stub
		responseText("Hello World picWeixin !");


	}

	@Override
	protected void scan() {
		// TODO Auto-generated method stub
		responseText("Hello World scan !");

	}

	@Override
	protected void scanCodePush() {
		// TODO Auto-generated method stub
		responseText("Hello World scanCodePush !");

	}

	@Override
	protected void scanCodeWaitMsg() {
		// TODO Auto-generated method stub
		responseText("Hello World scanCodeWaitMsg !");

	}

	@Override
	protected void subscribe() {
		// TODO Auto-generated method stub
		 
		 List<ArticleResponse> items=new ArrayList<ArticleResponse>();
		 ArticleResponse item=new ArticleResponse();
		 item.setDescription("1守住一份寂寞，留住一份亲情，做我所做，爱我所爱！");
		 item.setPicUrl("http://www.caipingzhushou.com/hqservice/Image/test.jpg");
		 item.setTitle("守住一份寂寞，留住一份亲情，做我所做，爱我所爱");
		 item.setUrl("http://www.baidu.com");
		 items.add(item);
		 
		 item=new ArticleResponse();
		 item.setDescription("2守住一份寂寞，留住一份亲情，做我所做，爱我所爱！");
		 item.setPicUrl("http://www.caipingzhushou.com/hqservice/Image/test.jpg");
		 item.setTitle("2守住一份寂寞，留住一份亲情，做我所做，爱我所爱");
		 item.setUrl("http://www.baidu.com");
		 items.add(item);
		 this.responseNews(items);

	}

	@Override
	protected void templateMsgCallback() {
		// TODO Auto-generated method stub
		responseText("Hello World templateMsgCallback !");


	}

	@Override
	protected void unSubscribe() {
		// TODO Auto-generated method stub
		responseText("Hello World unSubscribe !");

	}

	@Override
	protected void view() {
		// TODO Auto-generated method stub
		responseText("Hello World view !");

	}

}
