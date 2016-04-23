/** 

* @Title: HelloWorldInterceptor.java

* @Package com.wskj.controller

* @Description: TODO(用一句话描述该文件做什么)

* @author A18ccms A18ccms_gmail_com 

* @date 2014-2-22 下午07:31:53

* @version V1.0 

*/ 
package com.akmi.jyxt.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



/**

 * @ClassName: HelloWorldInterceptor

 * @Description: TODO

 * @author hongwei
 * @date 2014-2-22 下午07:31:53

 * @editor hongwei
 * @editdate 2014-2-22 下午07:31:53


 */

	 public  class Interceptor implements HandlerInterceptor  {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	//	System.out.println("After completion handle2");
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("Post-handle");   

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		/*
		if(request.getSession().getAttribute("userid")!=null&&request.getSession().getAttribute("token")!=null)
		{
			
			return true;
		}
		else
		{
		 response.sendRedirect(request.getContextPath()+"/error?errorid=1");
		 return false;
		}*/
		String userid="slry1";
		 request.getSession().setAttribute("userid", userid);
		
		 return true;
  }
		}
	

