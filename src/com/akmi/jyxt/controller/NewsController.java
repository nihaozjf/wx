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
import com.akmi.jyxt.model.Newsinf;
import com.akmi.jyxt.model.Newstype;
import com.akmi.jyxt.model.enu.LMTYPE;
import com.akmi.jyxt.service.NewsService;
import com.akmi.jyxt.service.NewstypeService;
import com.akmi.jyxt.service.TeacherInfService;
import com.akmi.jyxt.utils.DateUtil;
import com.akmi.jyxt.utils.Page;
import com.akmi.jyxt.utils.PageUtil;
import com.akmi.jyxt.utils.StringUtil;

@Controller
public class NewsController extends ManageBaseAction{

	@Autowired
	private NewsService   newsService;
	@Autowired
	private NewstypeService newstypeService;
	@Autowired
	private TeacherInfService teacherinfService;

	/**
	 * 新闻列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/newslist/",method=RequestMethod.GET)
    public ModelAndView newslist(HttpServletRequest request) throws Exception 
    {
		 int pagesize=10;
	     int pageindex=StringUtil.objtoInt(request.getParameter("pageindex"));
		 int newstype=StringUtil.objtoInt(request.getParameter("newstype"));
		 String keyword=request.getParameter("keyword");	 
		 
		 StringBuffer querybuffer=new StringBuffer("from "+Newsinf.class.getSimpleName()+" t1  where 1=1 ");
		
		StringBuffer countbuffer=new StringBuffer(" select count(t1.newsid) from "+Newsinf.class.getSimpleName()+" t1  where 1=1");
		 Map<String, Object> map=new HashMap<String,Object>();
		 List<Object> countparamList=new ArrayList<Object>();

			if(newstype>0)
		    {
			  querybuffer.append(" and t1.newstype=(from  Newstype where newstypeid =:newstype)");
			  countbuffer.append(" and t1.newstype=(from  Newstype where newstypeid =?)");
			  map.put("newstype", newstype);
			  countparamList.add(newstype);
		    }
		    if(StringUtil.stringnotNull(keyword)){
		    	querybuffer.append("  and t1.newstitle like :newstitle");
		    	countbuffer.append("  and t1.newstitle like ?");
				map.put("newstitle", "%"+keyword+"%");
				countparamList.add("%"+keyword+"%");
		    }
		    querybuffer.append(" order by newsid desc");
		    
		 Page<Newsinf> newspage=newsService.findObjectByPageInf(pagesize, pageindex, querybuffer.toString(), countbuffer.toString(),map, countparamList.toArray());
		 Map<String, Object> map1 = new HashMap<String, Object>();
		 map1.put("newslist", newspage.getResult());
		 map1.put("newstypelist",newstypeService.getAllObject());
		 map1.put("keyword",keyword==null?"":keyword);
		 map1.put("newstype", newstype);
		 map1.put("pagestr", PageUtil.getPageStr("", pageindex, newspage.getSumcount(), pagesize));
		 return new ModelAndView("news/newslist",map1);
    }
	
	/**
	 * 新闻编辑
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/newsedit/{newsid}",method=RequestMethod.GET)
    public ModelAndView newsedit(@PathVariable int newsid,HttpServletRequest request) throws Exception 
    {
		 Newsinf newsinf=newsService.getObjectByObjectid(newsid);
		 StringBuilder strbuilder=new StringBuilder();
		 Integer newstypeid=newsinf.getNewstype().getNewstypeid();
		 for(Newstype newstype:newstypeService.getlmlistbylmtypeandparentid(new Object[]{LMTYPE.HAVELM.getIndex(),LMTYPE.MANYART.getIndex()},0))
		 {
		   strbuilder.append("<option value='"+newstype.getNewstypeid()+"'");
		   if(newstypeid.equals(newstype.getNewstypeid()))
			 strbuilder.append("  selected='selected' ");
		   strbuilder.append(">"+newstype.getNewstypename()+"</option>");
		   for(Newstype newstype1:newstypeService.getlmlistbylmtypeandparentid(new Object[]{LMTYPE.HAVELM.getIndex(),LMTYPE.MANYART.getIndex()}, newstype.getNewstypeid().intValue()))
		   {
			   strbuilder.append("<option value='"+newstype1.getNewstypeid()+"'");
			   if(newstypeid.equals(newstype1.getNewstypeid()))
				 strbuilder.append("  selected='selected' ");
			   strbuilder.append(">---"+newstype1.getNewstypename()+"</option>");
		   }
		 }
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("newstypelist",newstypeService.getAllObject());
		 map.put("newsinf", newsinf);
		 map.put("newstypestr", strbuilder.toString());
		 map.put("opertype", "update");
		 return new ModelAndView("news/newsedit",map);
    }
	
	/**
	 * 新闻添加
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/newsadd/",method=RequestMethod.GET)
    public ModelAndView newsadd(HttpServletRequest request) throws Exception 
    {
		 StringBuilder strbuilder=new StringBuilder();
	
		 for(Newstype newstype:newstypeService.getlmlistbylmtypeandparentid(new Object[]{LMTYPE.HAVELM.getIndex(),LMTYPE.MANYART.getIndex()},0))
		 {
		   strbuilder.append("<option value='"+newstype.getNewstypeid()+"'>"+newstype.getNewstypename()+"</option>");
		   for(Newstype newstype1:newstypeService.getlmlistbylmtypeandparentid(new Object[]{LMTYPE.HAVELM.getIndex(),LMTYPE.MANYART.getIndex()}, newstype.getNewstypeid().intValue()))
			   strbuilder.append("<option value='"+newstype1.getNewstypeid()+"'>---"+newstype1.getNewstypename()+"</option>");
		 }
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("newstypelist",newstypeService.getAllObject());
		 map.put("newsinf", new Newsinf());
		 map.put("opertype", "add");
		 map.put("newstypestr", strbuilder.toString());
		 return new ModelAndView("news/newsedit",map);
    }
	
	/**
	 * 新闻保存
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/newsave/",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody    JSONObject newssave(HttpServletRequest request) throws Exception 
    {

		String newstitle=request.getParameter("newstitle");
		String newscontent=request.getParameter("newscontent");
		Timestamp newsdate=DateUtil.getNowTimeStamp();
		String linkurl=request.getParameter("linkurl");
		Integer newstypeid=StringUtil.objtoInt(request.getParameter("newstypeid"));
		String picurl=request.getParameter("picurl");
		String writerid="201222";
		String opertype=request.getParameter("opertype");
		
		
		Newsinf newsinf=null;
		if(opertype.equals("update"))
		{
		   int newsid=StringUtil.objtoInt(request.getParameter("newsid"));
		   newsinf=newsService.getObjectByObjectid(newsid);
		}
		else
			newsinf=new Newsinf();
		newsinf.setNewstitle(newstitle);
        newsinf.setNewscontent(newscontent);
        newsinf.setNewsdate(newsdate);
        newsinf.setLinkurl(linkurl);
        newsinf.setPicurl(picurl);
        newsinf.setTeacherinf(teacherinfService.getObjectByObjectid(writerid));
        newsinf.setNewstype(newstypeService.getObjectByObjectid(newstypeid));
        newsService.saveorupdateObject(newsinf);
        JSONObject jobj = new JSONObject();//new一个JSON 
		jobj.accumulate("msg", 1);
		return jobj;
    }
	
	
	
	/**
	 *删除新闻
	 * @param request
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/delnewsinf",method=RequestMethod.GET)
    public @ResponseBody JSONObject delnewsinf(HttpServletRequest request) throws Exception 
    {   
		 String[] idarr=request.getParameterValues("id");
		 List<Integer> idlist=new ArrayList<Integer>();
		 for(int i=0;i<idarr.length;i++)
			 idlist.add(StringUtil.objtoInt(idarr[i]));
		 
	     Integer delret=0;
		try
		 {
			 Map<String, Object> map=new HashMap<String,Object>();
	    	 map.put("ids",idlist.toArray());
			 newsService.deletebycolname("newsid",map);
		     delret=1;
		 }
		 catch(Exception ex)
		 {}
		 
		 JSONObject jobj = new JSONObject();//new一个JSON 
		 jobj.accumulate("msg", delret);
		 return jobj;
    }
	
	
	
	
	

	

}
