
package com.akmi.jyxt.utils;
public class PageUtil {

	public static String getPageStr(String pageurl,int pageindex,int totalrow,int pagesize)
	{
		StringBuilder newbuilder=new StringBuilder();
		int pagecount = (int) (totalrow % pagesize == 0 ? totalrow / pagesize : totalrow / pagesize + 1);
		if(pageindex<1)
			pageindex=1;
		
		if(pageindex>pagecount)
			pageindex=pagecount;
		int pagestart=pageindex-3;
		
		if(pagestart<1)
			pagestart=1;
		int pageend=pageindex+3;
		if(pageend>pagecount)
			 pageend=pagecount;
		
		int rowindex=((pageindex-1)*pagesize+1);
		int rowend=pageindex*pagesize>totalrow?totalrow:pageindex*pagesize;
		newbuilder.append("<div class='wp100 clear dataTables_paginate pt5'>");
		newbuilder.append("<div class='fl ml10 mt10 lh16'>共<span class='red'>"+totalrow+"</span> 条");
		if(totalrow>0)
		{
			newbuilder.append("，当前显示 "+rowindex+" 到 "+rowend+" 条");
		}
		newbuilder.append("</div>");
		newbuilder.append("<div class='fr mr10 mt5 clear'><input type='hidden' id='pageindex' value='"+pageindex+"'/>");
		newbuilder.append("<ul class='pagination clear fl'>");
		
		
		if(pageindex>1)
	    {
	     newbuilder.append("<li class='next' data-pageid='"+(pageindex-1)+"'><a href='#'>&lt;</a></li>");
		
	    }
	    for(int i=pagestart;i<=pageend;i++)
	    {
	    	if(i==pageindex)
	    		newbuilder.append("<li data-pageid='"+i+"' class='active'><a href='#'>"+i+"</a></li>");
	    	else
	    		newbuilder.append(" <li data-pageid='"+i+"'><a href='#'>"+i+"</a></li>");
	    }
	    if(pageindex<pagecount)
	    {
	    	newbuilder.append(" <li data-pageid='"+(pageindex+1)+"' class='next'><a href='#'>&gt;</a></li>");
	    }
	   newbuilder.append("</ul>");
	   newbuilder.append(" <p class='fl pageGo'><input type='text' class='fl txt1'><a href='#'>GO</a></p>");
	
	   newbuilder.append("</div></div></div>");
	   return newbuilder.toString();
	}
}
