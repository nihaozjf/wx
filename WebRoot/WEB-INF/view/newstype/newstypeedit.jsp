<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.akmi.jyxt.model.Newstype"%>
<%@ page language="java" import="com.akmi.jyxt.model.enu.LMTYPE"%>
<%
String path = request.getContextPath()+"/";
String opertype=(String)request.getAttribute("opertype");
List<Newstype> newstypelist=(List<Newstype>)request.getAttribute("newstypelist");
Newstype newstypeinf=null;
if(opertype.equals("update"))
  newstypeinf=(Newstype)request.getAttribute("newstypeinf");
else
  newstypeinf=new Newstype();
%>
  
<div class="modal-header">
    <a class="close" data-dismiss="modal">×</a>
    <h3 class="fms">栏目编辑</h3>
  </div>
  <div class="modal-body" id="objinf" >
    <form  id="objform">
    <input type="hidden" name="newstypeid" id="newstypeid" value="<%=newstypeinf.getNewstypeid()==null?"":newstypeinf.getNewstypeid()%>">
    <input type="hidden" name="opertype" id="opertype" value="<%=opertype%>">
      <table class="table table-horizontal mt10">
        <colgroup>
          <col width="30%">
          <col width="*">
        </colgroup>
        <tbody>
        <tr>
          <th class="tr"><label >栏目名称</label></th>
          <td>
		  <input  type="text" placeholder="请输入栏目名称" name="lmname" value="<%=newstypeinf.getNewstypename()==null?"":newstypeinf.getNewstypename()%>" id="lmname"  style="width:50%" class="txt1">
		  </td>
        </tr>
        <tr>
          <th class="tr"><label>栏目类型</label></th>
          <td>
		     <select class="form-control vm" name="lmtype" id="lmtype" style="width:50%">
                    <option value="" >栏目类型</option>
                    <%for(LMTYPE lmtype:LMTYPE.values()){ %>
                      <option value="<%=lmtype.getIndex() %>"
					  <%if(newstypeinf.getLmtype()!=null&&lmtype.getIndex()==newstypeinf.getLmtype()){%>
					   selected="selected" 
					   <%}%>
					   ><%=lmtype.getName()%></option>
                    <%} %>
             </select>
		   </td>
          </tr>
		  <tr>
          <th class="tr"><label>上级栏目</label></th>
          <td>
           <select class="form-control vm" name="parentlmid" id="parentlmid" style="width:50%">
               
                    <option value="0" 
                    <%if(newstypeinf.getParentnewstype()==null){%>
                      selected="selected" 
					  <%}%>
                    >一级目录</option>
                    <%for(Newstype newstype:newstypelist){ %>
                      <option value="<%=newstype.getNewstypeid()%>" 
					  <%if(newstypeinf.getParentnewstype()!=null&&newstype.getNewstypeid().equals(newstypeinf.getParentnewstype().getNewstypeid())){ %>
					  selected="selected" 
					  <%}%>
					  ><%=newstype.getNewstypename()%></option>
                    <%} %>
             </select>
          <td>
		  </td>
        </tr>
      </tbody></table>
    </form>
  </div>
  <div class="modal-footer">
    <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
    <a href="#" class="btn btn-primary"  id="btsave">提交</a>
  </div>
  <script type="text/javascript">
	$(document).ready(function() {
	  validatenewstypeForm("<%=path%>/manage/newstypesave/","<%=path%>/manage/newstypelist/");
      $("#btsave").click(function() {
	       $("#objform").submit();
       });
})	
</script>