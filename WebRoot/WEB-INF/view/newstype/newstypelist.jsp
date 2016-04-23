<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.akmi.jyxt.model.Newsinf"%>
<%@ page language="java" import="com.akmi.jyxt.model.Newstype"%>
<%@ page language="java" import="com.akmi.jyxt.utils.StringUtil"%>
<%@ page language="java" import="com.akmi.jyxt.utils.DateUtil"%>
<%@ page language="java" import="com.akmi.jyxt.model.enu.LMTYPE"%>
<%
	String path = request.getContextPath()+"/";
	List<Newstype> newstypelist=(List<Newstype>)request.getAttribute("newstypelist");
	String pagestr=(String)request.getAttribute("pagestr");

%>
<div id="breadcrumbs" class="breadcrumbs">
          <ul class="breadcrumb">
            <li><span class="">您所在的位置：</span><a >网站内容管理</a></li>
            <li class="active">栏目列表</li>
          </ul><!-- .breadcrumb -->
        </div>
        <!-- 单元块 -->
        <!-- 单元块 -->
        <div class="unitPt">
		 <div class="wp100">
            <div class="pa10 bb_ddd">
            
            <a   id="btadd" class="btn btn-sm btn-primary">+ 添加栏目</a>
            <a   id="btdel" class="btn btn-sm btn-danger ml10">删除栏目</a>
           
            </div>
          </div>
          <div class="wp100">
            <table class="table table-striped table-hover" id="sample-table-1">
              <colgroup>
                <col width="5%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                 <col width="10%">
                <col width="10%">
                <col width="*">
              </colgroup>
              <thead>
                <tr>
                  <th class="center">
                    <label>
                      <input type="checkbox">
                      <span class="lbl"></span>
                    </label>
                  </th>
                 
                  <th class="tc">栏目名称</th>
                  <th class="tc">栏目类型</th>
                  <th class="tc">上级栏目</th>
                  <th class="tc">发布日期</th>
                  <th class="tc">作者</th>
                  <th class="tc">操作</th>
                </tr>
              </thead>
              <tbody>
			<%
			if(newstypelist!=null){
			for(Newstype newstype:newstypelist)
			{%>
                <tr>
                  <td class="center"><label>
                  
                  <input  name="id" id="id" value="<%=newstype.getNewstypeid()%>" type="checkbox" class="ace"></label></td>
                   <td class="tc"><%=newstype.getNewstypename() %></td>
                    <td class="tc">
                   <%=LMTYPE.getLmTypeName(newstype.getLmtype())%>
                   </td>
                    <td class="tc"><%=newstype.getParentnewstype()==null?"一级栏目":newstype.getParentnewstype().getNewstypename() %></td>
                 
                  <td class="tc"><%=newstype.getCreatedate()==null?"":newstype.getCreatedate().toString().substring(2,16)%></td>
                  <td class="tc"><%=newstype.getTeacherinf()==null?"":newstype.getTeacherinf().getTeachername() %></td>
                  <td class="tc">
                  <div class="btn-group">
                      <a data-newstypeid="<%=newstype.getNewstypeid()%>"  class="btn btn-xs btn-info btedit">修改</a>
                  </div>
                  </td>
                </tr>
               <%}}%>  
                
              </tbody>
            </table>
          <%=pagestr%>
      </div>
	<script language="javascript">   
	 
	 $(document).ready(function() {
	 $(".pagination li").click(function(){
	  pageid=$(this).data("pageid");
	  newstypeval=$("#newstypeid").val();
	  var url="<%=path%>manage/newslist/?pageindex="+pageid+"&newstype="+newstypeval+"&keyword="+keywordval;
	  loadPage(url);
	 });
	
	 /*栏目添加*/
	  $("#btadd").click(function(){
		  var url="<%=path%>manage/newstypeadd/";
	      loadmodalpage(url);
	 });
	  
	  /*栏目编辑*/
		$(".btedit").click(function(){
		  var url="<%=path%>manage/newstypeedit/"+$(this).data("newstypeid");
	      loadmodalpage(url);
		});
	 
	 	/*栏目删除*/
		$("#btdel").click(function(){
			  pageid=$(this).data("pageid");
		  delobj("栏目","<%=path%>manage/delnewstypeinf/","<%=path%>manage/newstypelist/?pageindex="+pageid);
		});
	 
	 
	
	 

	
	});
   </script>
	  