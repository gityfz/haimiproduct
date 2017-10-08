<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传入口</title>
</head>
<body>
    <div>
    <img id = "pic" src="javaScript:readPic()">
    </div>
</body>
</html>
<script type="text/javascript">


function readPic(url){
	if(url!=null & url!=""){
		$.ajax({
			url:"${pageContext.request.contextPath}/autodev-upload/readImage.do?id="+id+"&size="+size,
			type:"get",
			cache:false,
	        async:false,
			dataType:"json",
			contentType:"application/json; charset=utf-8",
	        success : function(json){
	        	if(json==-1){
	        		window.location.href="${pageContext.request.contextPath}/redirectToLlogin.jsp";
	        	}else {
	        		if(json==1){
	        			layer.msg("信息提示：合同信息已经成功删除！");
	        			submitPageLimit(1);
	        		}else{
	        			layer.msg("信息提示：系统故障，请稍后重试！");
	        		}
	        	}
	        }
	    });
	}
}
</script>
