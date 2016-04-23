<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.akmi.jyxt.model.Newsinf"%>
<%@ page language="java" import="com.akmi.jyxt.model.Newstype"%>
<%@ page language="java" import="com.akmi.jyxt.utils.StringUtil"%>
<%@ page language="java" import="com.akmi.jyxt.utils.DateUtil"%>
<%
	String path = request.getContextPath()+"/";
	List<Newsinf> newslist=(List<Newsinf>)request.getAttribute("newslist");
	List<Newstype> newstypelist=(List<Newstype>)request.getAttribute("newstypelist");
	String pagestr=(String)request.getAttribute("pagestr");
	String keyword=(String)request.getAttribute("keyword");
	int newstype=(Integer)request.getAttribute("newstype");
%>
<div id="breadcrumbs" class="breadcrumbs">
          <ul class="breadcrumb">
            <li><span class="">您所在的位置：</span><a >网站文章管理</a></li>
            <li class="active">文章列表</li>
          </ul><!-- .breadcrumb -->
        </div>
        <!-- 单元块 -->
        <div class="unitPt mb20">
        
          <div class="referInn pa10">
            <input type="text"  class="txt1 vm mr5" name="keyword" value="<%=keyword%>" id="keyword"/>
            <select class="form-control vm"  name="newstypeid" id="newstypeid">
            <option value='0'>栏目类型</option>
            <% for(Newstype newstypeinf:newstypelist){%>
              <option 
              <%if(newstype==newstypeinf.getNewstypeid()){ %>
              selected="selected"
              <%} %>
               value="<%=newstypeinf.getNewstypeid()%>"><%=newstypeinf.getNewstypename()%></option>
              <%}%>
            </select>
            <a id="searchnews" class="btn btn-sm btn-danger ml10">查询</a>
          </div>
        </div>
        <!-- 单元块 -->
        <div class="unitPt">
		 <div class="wp100">
            <div class="pa10 bb_ddd">
            
            <a   id="btadd" class="btn btn-sm btn-primary">+ 添加文章</a>
            <a   id="btdel" class="btn btn-sm btn-danger ml10">删除文章</a>
           
            </div>
          </div>
          <div class="wp100">
            <table class="table table-striped table-hover" id="sample-table-1">
              <colgroup>
                <col width="5%">
                <col width="35%">
                <col width="10%">
                <col width="20%">
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
                  <th class="tc">文章标题</th>
                  <th class="tc">所属栏目</th>
                  <th class="tc">发布日期</th>
                  <th class="tc">作者</th>
                  <th class="tc">操作</th>
                </tr>
              </thead>
              <tbody>
			<%
			if(newslist!=null){
			for(Newsinf newsinf:newslist)
			{%>
                <tr>
                  <td class="center"><label>
                  
                  <input  name="id" id="id" value="<%=newsinf.getNewsid()%>" type="checkbox" class="ace"></label></td>
                  <td><a data-toggle="modal" role="button" href="#modal-table"><%=StringUtil.getLimitLengthString(newsinf.getNewstitle(),80)%></a></td>
                  <td class="tc"><%=newsinf.getNewstype()==null?"":newsinf.getNewstype().getNewstypename() %></td>
                  <td class="tc"><%=newsinf.getNewsdate().toString().substring(0,16)%></td>
                  <td class="tc"><%=newsinf.getTeacherinf().getTeachername()%></td>
                  <td class="tc">
                  <div class="btn-group">
                      <a data-newsid="<%=newsinf.getNewsid()%>" class="btn btn-xs btn-info btedit">修改</a>
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
	  var pageid=$(this).data("pageid");
	  var newstypeval=$("#newstypeid").val();
	  var keywordval=$("#keyword").val();
	  var url="<%=path%>manage/newslist/?pageindex="+pageid+"&newstype="+newstypeval+"&keyword="+keywordval;
	  loadPage(url);
	 });
	 /*编辑*/
	 $(".btedit").click(function(){
	  var url="<%=path%>manage/newsedit/"+$(this).data('newsid');
	  loadPage(url) ;
	 });
	 /*添加*/
	  $("#btadd").click(function(){
	  var url="<%=path%>manage/newsadd/";
	  loadPage(url) ;
	 });
	 
	 	/*链接删除*/
	$("#btdel").click(function(){
	 var newstypeval=$("#newstypeid").val();
	 var  keywordval=$("#keyword").val();
	 var delurl="<%=path%>manage/delnewsinf/";
	 var listurl="<%=path%>manage/newslist/?newstype="+newstypeval+"&keyword="+keywordval;
	  delobj("新闻",delurl,listurl);
	});
	 
	 $("#searchnews").click(function(){
	  var newstypeval=$("#newstypeid").val();
	  var keywordval=$("#keyword").val();
	  var url="<%=path%>manage/newslist/?newstype="+newstypeval+"&keyword="+keywordval;
      loadPage(url);
	 });
	 

	
	});
   </script>
	  