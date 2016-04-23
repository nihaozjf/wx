package com.akmi.jyxt.controller;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.akmi.jyxt.model.Teacherinf;
import com.akmi.jyxt.service.TeacherInfService;



@Controller
public class TeacherController extends ManageBaseAction{

	@Autowired
	private TeacherInfService   teacherService;

	/**
	 *链接列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sysuer/userlogindex/",method=RequestMethod.GET)
    public ModelAndView userloginindex(HttpServletRequest request) throws Exception 
    {
		return new ModelAndView("login");
		 
    }

	
	@RequestMapping(value="/sysuser/userlogin/",method=RequestMethod.POST,consumes="application/json;charset=UTF-8",produces="application/json;charset=UTF-8")
    public @ResponseBody    JSONObject userlogin(@RequestBody JSONObject jsonObj,HttpServletRequest request) throws Exception 
    {
		String userid=jsonObj.getString("userid");
		String userpwd=jsonObj.getString("userpwd");
		Teacherinf teacherinf=teacherService.getObjectByObjectid(userid);
		int respCode=0;
		String respDes="";
		if(teacherinf==null)
		{
			respCode=0;
			respDes="用户名错误!";
		}
		else
		{
		  if(teacherinf.getTeacherpwd().equals(userpwd))
		  {
			  respCode=1;
			  respDes="登录成功!";
			  request.getSession().setAttribute("teacherinf", teacherinf);
		  }
		  else 
		  {
			  respCode=0;
			  respDes="密码错误!";
		  }
		 }
		JSONObject jobj = new JSONObject();
		jobj.accumulate("respCode",respCode);
		jobj.accumulate("respDes",respDes);
		return jobj;

	
	}

}
