<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>上传文件图片案列</title>
</head>
<body>
    用户名称:${requestScope.usename }<br /> 
    图片名称:${requestScope.file1 }<br /> 
    文件名称:${requestScope.file2 }<br />
    <!-- 把上传的图片显示出来 -->
    <img alt="go" src="upload1/<%=(String) request.getAttribute("file1")%> " />
</body>
</html>