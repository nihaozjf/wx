<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.akmi.jyxt.model.Link"%>
<%
String path = request.getContextPath()+"/";
String opertype=(String)request.getAttribute("opertype");
Link linkinf=(Link)request.getAttribute("linkinf");
%>
  
<div class="modal-header">
    <a class="close" data-dismiss="modal">×</a>
    <h3 class="fms">系统链接编辑</h3>
  </div>
  <div class="modal-body" >
    <form action="" id="objform">
    <input type="hidden" name="linkid" id="linkid" value="<%=linkinf.getLinkinfid()==null?"":linkinf.getLinkinfid()%>">
    <input type="hidden" name="opertype" id="opertype" value="<%=opertype%>">
      <table class="table table-horizontal mt10">
        <colgroup>
          <col width="65">
          <col width="*">
        </colgroup>
        <tbody>
        <tr>
          <th class="tr"><label for="inputEmail">链接名称</label></th>
          <td>
		  <input value="<%=linkinf.getLinkname()==null?"":linkinf.getLinkname()%>" type="text" placeholder="请输入链接名称" name="linkname" id="linkname"  style="width:50%" class="txt1">
		  
		  </td>
        </tr>
        <tr>
          <th class="tr"><label for="inputEmail">链接地址</label></th>
          <td>
		  <input type="text" style="width:50%" value="<%=linkinf.getLinkurl()==null?"":linkinf.getLinkurl()%>" placeholder="请输入链接地址" name="linkurl"  id="linkurl" class="txt1">
         
          </td>
          
        </tr>
        <tr>
        <td colspan="2" class="tc"><img name="linkimg1" id="linkimg1" src="<%=linkinf.getLinkimg()==null?"":linkinf.getLinkimg()%>" style="width:200px;height:150px"/></td>
        </tr>
        <tr>  
          <th class="tr"><label for="inputEmail">链接图标</label></th>
          <td>
          <input type="hidden" name="linkimg" id="linkimg" value="<%=linkinf.getLinkimg()==null?"":linkinf.getLinkimg()%>">
          <input id="custom_file_upload" type="file" name="Filedata" /></div>
           <div id="custom-queue"></div>
          </td>
        </tr>

      </tbody></table>
    </form>
  </div>
  <div class="modal-footer">
    <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
    <a href="#" class="btn btn-primary"  id="savelink">提交</a>
  </div>
  <script type="text/javascript">
	$(document).ready(function() {
	inituploadfile();
	validatenewstypeForm("<%=path%>/manage/linksave/","<%=path%>/manage/linklist/");
    $("#savelink").click(function() {
	       $("#objform").submit();
       });
})	

function inituploadfile()
{
	$('#custom_file_upload').uploadify({
	  'swf'       : '<%=path%>thirdparty/uploadify/uploadify.swf',
	  'uploader'         : '<%=path%>Upload;jsessionid=<%=request.getSession().getId()%>',
	  'method' : "post",
	  'cancelImage' : '<%=path%>thirdparty/uploadify/uploadify-cancel.png',//设置取消的图片
      'buttonText'  : '浏览',                         
	  'buttonImg'      : '<%=path%>thirdparty/uploadify/sel.png',
	  'queueID'        : 'custom-queue',
	  'fileTypeExts':'*.jpg;*.gif;*.png',
	  'fileTypeDesc':'图片文件',
	  'removeCompleted': true,
	  'onSelectOnce'   : function(event,data) {
		  $('#custom-queue').text('文件上传中!');
		},
	'onUploadSuccess'  : function(file, data, response) {
		  $('#custom-queue').text('文件上传成功!');
		  $('#linkimg').attr("value",data);
		  $('#linkimg1').attr("src",data);
		}
	});
}
</script>