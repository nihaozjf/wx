<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.akmi.jyxt.model.Link"%>
<%@ page language="java" import="com.akmi.jyxt.utils.StringUtil"%>
<%@ page language="java" import="com.akmi.jyxt.utils.DateUtil"%>
<%
String path = request.getContextPath()+"/";
List<Link> linklist=(List<Link>)request.getAttribute("linklist");
String pagestr=(String)request.getAttribute("pagestr");
%>
<div  class="breadcrumbs" id="linklist">
          <ul class="breadcrumb">
            <li><span class="">您所在的位置：</span><a >网站内容管理</a></li>
            <li class="active">网站链接</li>
          </ul><!-- .breadcrumb -->
        </div>
        <!-- 单元块 -->
        <!-- 单元块 -->
        <div class="unitPt">
		 <div class="wp100">
            <div class="pa10 bb_ddd">
              <a  id="btaddlink"  class="btn btn-sm btn-primary">+ 添加链接</a>
              <a  id="btdellink"  class="btn btn-sm btn-danger ml10">删除链接</a>
             </div>
          </div>
          <div class="wp100" >
            <table class="table table-striped table-hover" id="sample-table-1">
              <colgroup>
                <col width="5%">
                <col width="25%">
                <col width="25%">
                <col width="15%">
                <col width="*">
              </colgroup>
              <thead>
                <tr>
                  <th class="tc">
                    <label>
                      <input type="checkbox">
                      <span class="lbl"></span>
                    </label>
                  </th>
                  <th class="tc">链接名称</th>
                  <th class="tc">链接地址</th>
                  <th class="tc">创建时间</th>
                  <th class="tc">操作</th>
                </tr>
              </thead>
              <tbody>
              
			<%
			if(linklist!=null){
			for(Link linkinf:linklist)
			{%>
                <tr>
                  <td class="tc"><label><input type="checkbox" class="ace"
                  value="<%=linkinf.getLinkinfid()%>" name="id" id="id"></label></td>
                  <td class="tc"><%=StringUtil.getLimitLengthString(linkinf.getLinkname(),80)%></td>
                  <td class="tc"><a href="<%=linkinf.getLinkurl()%>" target="_blank"><%=linkinf.getLinkurl()%></a></td>
                   <td class="tc"><%=linkinf.getCreatedate().toString().substring(0,16)%></td>
                  <td class="tc">
                  <div class="btn-group">                       
                  <a  class="btn btn-xs btn-info btedit"  data-linkid="<%=linkinf.getLinkinfid()%>">修改</a>
                 </div>
                  </td>
                </tr>
               <%}}%>  
              </tbody>
            </table>
           <%=pagestr %>
      </div>
	  
	  

 <script language="javascript">   
	$(document).ready(function() {
	
	/*链接编辑*/
	
	
	$(".btedit").click(function(){
	  var url="<%=path%>manage/linkedit/"+$(this).data("linkid");
      loadmodalpage(url);
	});
	
	/*链接添加*/
	
	$("#btaddlink").click(function(){
	  var url="<%=path%>manage/linkadd/";
      loadmodalpage(url);
	});
	
	/*链接删除*/
	$("#btdellink").click(function(){
	  pageid=$(this).data("pageid");
	  delobj("链接","<%=path%>manage/dellinkinf/","<%=path%>manage/linklist/?pageindex="+pageid);
	});
	 
	 /*链接翻页*/
	 $(".pagination li").click(function(){
	   pageid=$(this).data("pageid");
	   loadPage("<%=path%>manage/linklist/?pageindex="+pageid);
	 });
	 
	});
	
	

   </script>
	  