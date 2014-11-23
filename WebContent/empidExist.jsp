<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.highlight {
	font-size: 12px;
	font-weight: bold;
	color:red;

}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Duplicate EMP ID</title>

<%	String empid="";
	String message="";
	if(request.getParameter("empid")!=null)
		empid= request.getParameter("empid").toString();
	
	if(request.getParameter("message")!=null)
		message= request.getParameter("message").toString();
       %>
</head>
<body>

	Sorry, <%=message %>, <a href="createProfile.jsp">click here</a>to try once again...
</body>
</html>