<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.akmi.jyxt.model.Newsinf"%>
<%@ page language="java" import="com.akmi.jyxt.model.Newstype"%>
<%@ page language="java" import="com.akmi.jyxt.utils.DateUtil"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>      
<%
String path = request.getContextPath()+"/";
Newsinf newsinf=(Newsinf)request.getAttribute("newsinf");
String opertype=(String)request.getAttribute("opertype");
String newstypestr=(String)request.getAttribute("newstypestr");
List<Newstype> newstypelist=(List<Newstype>)request.getAttribute("newstypelist");
%>
<style>
<!--
div.daterangepicker{width:300px;}
-->
</style>
 <div id="breadcrumbs" class="breadcrumbs">     
          <ul class="breadcrumb">
            <li><span class="">您所在的位置：</span><a href="#">文章管理</a></li>
            <li class="active">添加文章</li>
          </ul>
        </div>
        <!-- 单元块 -->
     
<div class="mt10">
    <a href="javascript:void(0)" id="btnewssub"  class="btn btn-sm btn-primary">提交</a>
    <a href="javascript:void(0)" id="btnewsret"  class="btn btn-sm btn-primary">返回</a>
    <a href="javascript:void(0)" id="btnewsreset" class="btn btn-sm btn-primary">重置</a>
</div>
      <br/>
        <!-- 单元块 -->
        <div class="unitPt">
        
          <div class="wp100" id="newsinf">
            <form action="" name="form1" id="form1">
            <input type="hidden" value="<%=opertype%>" name="opertype" id="opertype">
            <input type="hidden" value="<%=newsinf.getNewsid()%>" name="newsid" id="newsid">
            <table class="table table-horizontal">
                <colgroup>
                  <col width="10%">
                  <col width="40%">
                  <col width="10%">
                  <col width="40%">
                </colgroup>
                <tr>
                  <th class="tr"><label for="inputEmail">文章标题</label></th>
                  <td colspan="3"><input name="newstitle" id="newstitle" type="text" class="txt1" style="width:70%;" value="<%=newsinf.getNewstitle()==null?"":newsinf.getNewstitle()%>" ></td>
                </tr>
				 <tr>
                  <th class="tr"><label for="inputEmail">所属栏目</label></th>
                  <td >
                    <select class="form-control vm" style="width:30%;" id="newstype" id="newstype">
                    <option value="" >所属栏目</option>
                   <%=newstypestr%>
                    </select>
                  </td>
                   <th class="tr"><label for="inputEmail">发布日期</label></th>
                  <td >
                 <input readonly="readonly"  type="text"  name="subtime" value="<%=DateUtil.getNowTime("yyyy-MM-dd HH:mm")%>"  id="subtime" style="width:130px;" class="txt1">
                  </td>
                </tr>
                <tr>
                  <th class="tr"><label for="inputEmail">外部链接</label></th>
                  <td ><input type="text" class="txt1" style="width:90%;" name="linkurl" value="<%=newsinf.getLinkurl()==null?"":newsinf.getLinkurl() %>" id="linkurl"  placeholder="外部链接">
                    <input id="custom_file_upload" type="file" name="Filedata" /></div>
                   <div id="custom-queue"></div>
                   填写该栏目后将直接文章将直接打开该链接！
                  </td>
                   <th class="tr"><label for="inputEmail">标题图</label></th>
                  <td ><input type="text" class="txt1" style="width:90%;" name="picurl" value="<%=newsinf.getPicurl()==null?"":newsinf.getPicurl()%>" id="picurl"  placeholder="标题图">
                    <input id="custom_file_upload1" type="file" name="Filedata" /></div>
                   <div id="custom-queue1"></div>
                   填写该栏目后将在首页大图中显示！
                  </td>
                </tr>
				<tr>
                  <th class="tr"><label for="inputEmail">内容</label></th>
                  <td colspan="3">
                   <s:textarea id="newscontent" name="newscontent" ><%=newsinf.getNewscontent()==null?"":newsinf.getNewscontent()%></s:textarea>
                   <ckf:setupCKEditor  basePath="/jyxt/ckfinder/"  editor="newscontent"/>  
                   <ck:replace replace="newscontent" basePath="/jyxt/ckeditor"> </ck:replace>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </div>
  <script type="text/javascript">
	$(document).ready(function() {
	inituploadfile();
	inituploadimge();
        $("#btnewsret").click(function() {
	      loadPage('<%=path%>manage/newslist/');
        });
       $("#btnewssub").click(function() {
	     newssumit();
       });
       $("#btnewsreset").click(function() {
	     $("#form1")[0].reset();
       });
        /*
         *时间选择
         */
       	$('#subtime').daterangepicker({ 
			drops:'down',
			singleDatePicker: true, 
			timePicker: true,
            timePickerIncrement: 1,
            format: 'YYYY-MM-DD HH:mm'
		}, function(start, end, label) {
	  });
  })
  
     function newssumit(){
      var newstitle=$("#newstitle").val();
      var newsneirong=CKEDITOR.instances.newscontent.getData();
      var newsidval=$("#newsid").val();
      var newstypeval=$("#newstype").val();
      var opertypeval=$("#opertype").val();
      var linkurlval=$("#linkurl").val();
      var picurlval=$("#picurl").val();
   $.ajax({
            //提交数据的类型 POST
            type:"POST",
            //提交的网址
            url:"<%=path%>manage/newsave/",
            //提交的数据
            data:{newstitle:newstitle,newscontent:newsneirong,newstypeid:newstypeval,opertype:opertypeval,newsid:newsidval,linkurl:linkurlval,picurl:picurlval},
            //返回数据的格式
            datatype: "json",
            //在请求之前调用的函数
            beforeSend:function(){
               jQuery('#newsinf').showLoading();
            },
                 
            success:function(data){
               jQuery('#newsinf').hideLoading();
               alert("提交成功");
               loadPage('<%=path%>manage/newslist/');
            }   ,
            //调用执行后调用的函数
            complete: function(XMLHttpRequest, textStatus){
              jQuery('#newsinf').hideLoading();
            },
            //调用出错执行的函数
            error: function(){
            }        
         });
  }
  function inituploadfile()
{
	$('#custom_file_upload').uploadify({
	  'swf'       : '<%=path%>thirdparty/uploadify/uploadify.swf',
	  'uploader'         : '<%=path%>Upload;jsessionid=<%=request.getSession().getId()%>',
	  'method' : "post",
	  'cancelImage' : '<%=path%>thirdparty/uploadify/uploadify-cancel.png',
      'buttonText'  : '浏览',                         
	  'buttonImg'      : '<%=path%>thirdparty/uploadify/sel.png',
	  'queueID'        : 'custom-queue',
	  'fileTypeExts':'*.jpg;*.gif;*.png;*.doc;*.docx;*.pdf;*.rar;*.xls;*.xlsx;*.pdf;*.ppt;*.pptx;*.zip',
	  'fileTypeDesc':'图片文件',
	  'removeCompleted': true,
	  'onSelectOnce'   : function(event,data) {
		  $('#custom-queue').text('文件上传中!');
		},
	'onUploadSuccess'  : function(file, data, response) {
		  $('#custom-queue').text('');
		  $('#linkurl').attr("value",data);
		}
	});
}
 function inituploadimge()
{
	$('#custom_file_upload1').uploadify({
	  'swf'       : '<%=path%>thirdparty/uploadify/uploadify.swf',
	  'uploader'         : '<%=path%>Upload;jsessionid=<%=request.getSession().getId()%>',
	  'method' : "post",
	  'cancelImage' : '<%=path%>thirdparty/uploadify/uploadify-cancel.png',
      'buttonText'  : '浏览',                         
	  'buttonImg'      : '<%=path%>thirdparty/uploadify/sel.png',
	  'queueID'        : 'custom-queue1',
	  'fileTypeExts':'*.jpg;*.gif;*.png;',
	  'fileTypeDesc':'图片文件',
	  'removeCompleted': true,
	  'onSelectOnce'   : function(event,data) {
		  $('#custom-queue1').text('文件上传中!');
		},
	'onUploadSuccess'  : function(file, data, response) {
		  $('#custom-queue1').text('');
		  $('#picurl').attr("value",data);
		}
	});
}
</script>
