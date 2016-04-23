package com.akmi.jyxt.controller;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.akmi.jyxt.model.Newstype;
import com.akmi.jyxt.model.enu.LMTYPE;
import com.akmi.jyxt.service.NewstypeService;
import com.akmi.jyxt.service.TeacherInfService;

import com.akmi.jyxt.utils.DateUtil;
import com.akmi.jyxt.utils.Page;
import com.akmi.jyxt.utils.PageUtil;
import com.akmi.jyxt.utils.StringUtil;

@Controller
public class NewstypeController extends ManageBaseAction{

	@Autowired
	private NewstypeService newstypeService;
	@Autowired
	private TeacherInfService teacherinfService;


	/**
	 * 新闻栏目列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/newstypelist/",method=RequestMethod.GET)
    public ModelAndView newslist(HttpServletRequest request) throws Exception 
    {
         int pagesize=10;
	     int pageindex=StringUtil.objtoInt(request.getParameter("pageindex"));
		 Page<Newstype> newspage=newstypeService.findObjectByPageInf(pagesize, pageindex,"order by  newstypeid desc");
		 Map<String, Object> map1 = new HashMap<String, Object>();
		 map1.put("newstypelist", newspage.getResult());
		 map1.put("pagestr", PageUtil.getPageStr("", pageindex, newspage.getSumcount(), pagesize));
		 return new ModelAndView("newstype/newstypelist",map1);
		 
    }
	
	
	/**
	 *删除超链接
	 * @param request
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/delnewstypeinf",method=RequestMethod.GET)
    public @ResponseBody JSONObject dellinkinf(HttpServletRequest request) throws Exception 
    {   
		 String[] linkidstr=request.getParameterValues("id");
		 List<Integer> linkidlist=new ArrayList<Integer>();
		 for(int i=0;i<linkidstr.length;i++)
		 {
			 linkidlist.add(StringUtil.objtoInt(linkidstr[i]));
		 }
		 
	     Integer delret =0;
		 try
		 {
			 Map<String, Object> map=new HashMap<String,Object>();
	    	 map.put("ids",linkidlist.toArray());
			 newstypeService.deletebycolname("newstypeid",map);
		     delret=1;
		 }
		 catch(Exception ex)
		 {}
		 JSONObject jobj = new JSONObject();//new一个JSON 
		 jobj.accumulate("msg", delret);
		 return jobj;
    }
	
	
	/**
	 *新建栏目
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/newstypeadd/",method=RequestMethod.GET)
    public ModelAndView newstypeadd(HttpServletRequest request) throws Exception 
    {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("opertype", "add");
		 map.put("newstypelist",newstypeService.getlmlistbylmtypeandparentid(new Object[]{LMTYPE.HAVELM.getIndex()},0));
		 map.put("newstypeinf", new Newstype());
		 return new ModelAndView("newstype/newstypeedit",map);
    }
	
	/**
	 *新建栏目
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/newstypeedit/{newstypeid}",method=RequestMethod.GET)
    public ModelAndView newstypeedit(@PathVariable int newstypeid,HttpServletRequest request) throws Exception 
    {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("opertype", "update");
		 map.put("newstypeinf", newstypeService.getObjectByObjectid(newstypeid));
		 map.put("newstypelist",newstypeService.getlmlistbylmtypeandparentid(new Object[]{LMTYPE.HAVELM.getIndex()},0));
		 return new ModelAndView("newstype/newstypeedit",map);
    }
	
	/**
	 * 保存栏目保存
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/newstypesave/",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody    JSONObject newstypesave(HttpServletRequest request) throws Exception 
    {
        String opertype=request.getParameter("opertype");
		String lmname=request.getParameter("lmname");
		int lmtype=StringUtil.objtoInt(request.getParameter("lmtype"));
		Timestamp createdate=DateUtil.getNowTimeStamp();
		int parentlmid=StringUtil.objtoInt(request.getParameter("parentlmid"));
		
		String writerid="201222";
		   
		if(opertype.equals("add"))
		{
		   Newstype newstypeobj=new Newstype();
		   newstypeobj.setCreatedate(createdate);
		   newstypeobj.setNewstypename(lmname);
		   newstypeobj.setLmtype(lmtype);
		   if(parentlmid!=0)
		    newstypeobj.setParentnewstype(newstypeService.getObjectByObjectid(parentlmid));
		   newstypeobj.setTeacherinf(teacherinfService.getObjectByObjectid(writerid));
		
		   newstypeService.saveObject(newstypeobj);
		}
		else if(opertype.equals("update"))
		{
			int newstypeid=StringUtil.objtoInt(request.getParameter("newstypeid"));
		    Newstype newstype=newstypeService.getObjectByObjectid(newstypeid);
			newstype.setLmtype(lmtype);
		    newstype.setNewstypename(lmname);
		    newstype.setParentnewstype(newstypeService.getObjectByObjectid(parentlmid));
		    newstypeService.updateObject(newstype);
		}
		JSONObject jobj = new JSONObject();//new一个JSON 
		jobj.accumulate("msg", 1);
		return jobj;
    }

	

}
