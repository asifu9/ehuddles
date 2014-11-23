<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" >
history.go(1); /* undo user navigation (ex: IE Back Button) */

</script>
<meta http-Equiv="Cache-Control" Content="no-cache">
<meta http-Equiv="Pragma" Content="no-cache">
<meta http-Equiv="Expires" Content="0">
</head>
<body>
<%@page import="java.util.*" %>

<%session.invalidate();%>
You have logged out. Click here to 
<a href="login.jsp"><b>Login</b></a>
</body>
</html>