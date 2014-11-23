<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/arrowBox.css" rel="stylesheet" type="text/css" media="all">
<title>Insert title here</title>
<%
			EmpInfo emp=null;
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
			}else{
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
		%>
</head>
<body>


</body>
</html>