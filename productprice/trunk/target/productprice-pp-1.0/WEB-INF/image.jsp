<?xml version="1.0" encoding="UTF-8" ?>  
<%@page import="java.awt.image.BufferedImage"%>  
<%@page import="javax.imageio.ImageIO"%>  
<%@page import="org.apache.commons.io.IOUtils"%>  
<%@page import="java.io.InputStream"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
<title>读取服务器上的文件</title>  
</head>  
<body>  
<div style="border: solid red ;" >
<%  
    response.reset();//清除buffer  
    response.setContentType("image/gif");  
    out.clear();  
    out = pageContext.pushBody();  
%> 
</div>

</body>  
</html>   