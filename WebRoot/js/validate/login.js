

function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}


$(document).ready(function() {

$("#btlogin").bind("click", function() {
		$('#userlogin').submit();
	});
	$('#userlogin').validate({
		  onfocusout:true,
		  rules: {
		   userid: "required",
		   userpwd: {
		   required: true
		    }
		  },
		   messages: {
		   userid: "<em  class='red ml10 '>用户名不能为空</em>",
		   userpwd: "<em  class='red ml10 '>密码不能为空</em>"
		  },
		    errorPlacement : function(error, element) {
			//element.append(error);
			error.insertAfter(element);
            },
          submitHandler : function(form) {
            var data={
					userid : $('#userid').val(),
					userpwd : $('#userpwd').val()
				};
            jQuery.ajax( {     
                  type : 'POST',     
                  contentType : 'application/json',     
                  url : getContextPath() + "/sysuser/userlogin/",
				  data : JSON.stringify(data),   
                  dataType : 'json',     
                  success : function(data) {     
							 alert(data.respCode);                         
                  },     
                  error : function(data) { 
                    alert(JSON.stringify(data));    
                    alert("error");  
                  }       
              });  

          }
		
	});
});
	