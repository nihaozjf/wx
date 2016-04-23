
function validateroomForm(saveurl,listurl)
{
   $("#objform").validate({
          onfocusout:true,
		  rules: {
		   roomname: "required",
		   roomdes: "required"
		  },
		   messages: {
		   roomname: "房间名称不能为空",
		   roomdes: "房间描述不能为空"
		  },
		    errorPlacement : function(error, element) {
				element.parent().append("<em  class='red ml10 '></em>");
				error.insertAfter(element);
            },
          submitHandler : function(form) {
               updateObj(saveurl,listurl);
          }
    });

}



function  validatereservercheckForm(saveurl,listurl)
{


 $("#objform").validate({
           onfocusout:true,
		  rules: {
		   checkresult: "required"
		  },
		   messages: {
		   checkresult: "请选择审核结果"
		  },
		    errorPlacement : function(error, element) {
				
				element.parent().append("<em  class='red ml10 '></em>");
				error.insertAfter(element);
            },
          submitHandler : function(form) {
              updateObj(saveurl,listurl);
          }
    });

}
	
function validatelinkForm(saveurl,listurl)
{
      $("#objform").validate({
           onfocusout:true,
		  rules: {
		   linkname: "required",
		   linkurl: {
		   required: true,
           url: true
		    }
		  },
		   messages: {
		   linkname: "链接名称不为空",
		   linkurl: "请输入正确的链接地址"
		  },
		    errorPlacement : function(error, element) {
				
				element.parent().append("<em  class='red ml10 '></em>");
				element.next("em").html(error);
            },
          submitHandler : function(form) {
              updateObj(saveurl,listurl);
          }
    });
}
function validatenewstypeForm(saveurl,listurl)
{
      $("#objform").validate({
           onfocusout:true,
		  rules: {
		   lmname: "required",
		   parentlmid: "required",
		   lmtype: {
		   required: true,
		    }
		  },
		   messages: {
		   lmname: "标题名称不能为空",
		   parentlmid: "请选择上级栏目",
		   lmtype: "栏目类型不能为空"
		  },
		    errorPlacement : function(error, element) {
				element.parent().append("<em  class='red ml10 '></em>");
				element.next("em").html(error);
            },
          submitHandler : function(form) {
               updateObj(saveurl,listurl);
          }
    });
}


function validatereserverForm(saveurl,listurl)
{
       $("#objform").validate({
           onfocusout:true,
		  rules: {
		   startdate:"required",
		   enddate:"required",
		   reservertype: "required",
		   reserverdes: "required"
		  },
		   messages: {
		    startdate:"开始日期不能为空",
		   enddate:"结束日期不能为空",
		   reservertype: "类型不能为空",
		   reserverdes: "描述内容不能为空"
		  },
		    errorPlacement : function(error, element) {
				element.parent().append("<em  class='red ml10 '></em>");
				error.insertAfter(element);
            },
          submitHandler : function(form) {
               updateObj(saveurl,listurl);
          }
    });

}
 function updateObj(objsaveurl,listurl)
 {
       jQuery.ajaxSetup ({ cache: false });
		$.ajax({
            type:"POST",
            url:objsaveurl,
            data:$("#objform").serialize(),
            datatype: "json",
            beforeSend:function(){
               jQuery('#modal_edit').showLoading();
            },
            success:function(data){
             jQuery('#modal_edit').hideLoading();  
            },
            complete: function(XMLHttpRequest, textStatus){
               alert("提交成功");
			   $("#modal_edit").modal("toggle");
               loadPage(listurl);
			   jQuery('#modal_edit').hideLoading();
            },
            error: function(){
               jQuery('#modal_edit').hideLoading();
            }        
         });
 }
 
   /*
删除对象
   */
  	function delobj(objname,delobjurl,listurl)
	{
	   var icount=0;
	   var str="?1";
	   $('input[name="id"]:checked').each(function(){
                str+="&id="+$(this).val();
                icount++;
       });
		if(icount>0)
		{
		 if(confirm('确认删除这'+icount+'条'+objname+'?'))
		  {
		 
		    $.ajax({
			type: "GET",
			url: delobjurl+str,
			success: function(msg){
				
				 loadPage(listurl);
			}
		   });
		 }
		}
		else
		{
		  alert("请选择要删除的信息!");
		}
	}
	
	
	function ajaxgetoper(operurl,listurl)
	{

		   
		    $.ajax({
			type: "GET",
			url: operurl,
			
			 beforeSend:function(){
               jQuery('#modal_edit').showLoading();
            },
            success:function(data){
             
            },
            complete: function(XMLHttpRequest, textStatus){
               alert("操作成功");
			   $("#modal_edit").modal("toggle");
               loadPage(listurl);
			   jQuery('#modal_edit').hideLoading();
            },
            error: function(){
               
            }        
            
            
			
		   });
	}
	
	
function loadPage(url)
{
  if(url.indexOf("?")==-1)
   pageurl=url+"?randid="+(new Date()).valueOf();
  else
   pageurl=url+"&randid="+(new Date()).valueOf();
  $("#container-right").addClass("loading"); 
  $("#container-right").html("<img src='../../images/main/loading.gif' ><p class='mt10'>正在加载中</p>");
  $.ajaxSetup ({ cache: false });
  
     $.ajax({
			type: "GET",
			url: pageurl,
			success: function(content){
		//	alert(content);
			   $("#container-right").removeClass("loading"); 
			   $("#container-right").html(content);
			},
			error: function(data){
            },
             complete: function(XMLHttpRequest, textStatus){
            },
            //调用出错执行的函数
            error: function(){ 
            }        
		   });
 }
 
    function loadmodalpage(modalurl)
      {
	  if(modalurl.indexOf("?")==-1)
		modalurl+="?randid="+(new Date()).valueOf();
	  else
	   modalurl+="&randid="+(new Date()).valueOf();
	  
	   $("#modal_edit").modal("show");
       $("#modal_edit").addClass("loading"); 
       $("#modal_edit").html("<img src='../../images/main/loading.gif' ><p class='mt10'>正在加载中</p>");
       $.ajax({
			type: "GET",
			url: modalurl,
			success: function(content){
			
			   $("#modal_edit").removeClass("loading"); 
			   $("#modal_edit").html(content);
			},
			error: function(data){
             
            },
             complete: function(XMLHttpRequest, textStatus){
              
            },
            error: function(){
              
            }        
		   });
		   
		   
	 }
 