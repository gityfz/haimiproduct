<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.awt.image.BufferedImage"%>  
<%@page import="javax.imageio.ImageIO"%>  
<%@page import="org.apache.commons.io.IOUtils"%>  
<%@page import="java.io.InputStream"%>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form  id="imageForm" name="imageForm" enctype="multipart/form-data" method="post" >  
                   上传图片:
       <input type="file" id="file" name="file"><br/>  
	 <button type="button" id="button"  onclick="upload();">上传</button>
	 <a href="${pageContext.request.contextPath}/autodev-upload/readImage.do">读图
	 </a>
	 </form>
</body>
</html>
<script type="text/javascript" >
function upload(){
	var file = document.getElementById("file").value.trim();
    if(file==""){
    	alert("错误提示：请正确选择上传图片！");
    	return false;
    }
	alert("xxxx");
	document.imageForm.action="${pageContext.request.contextPath}/autodev-upload/testUploadImage.do";
	document.imageForm.submit();
}
</script>