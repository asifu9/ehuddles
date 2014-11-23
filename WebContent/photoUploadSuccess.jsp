<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<% String msg="";

if(request.getParameter("message")!=null)
	msg=request.getParameter("message");

%>
</head>
<body>
<table width="100%" align="left" cellpadding="0" cellspacing="0" >
		<tr>
			<td class="imageUIHeader">
				Upload Images
			</td>
		</tr>
		<tr>
		<td>
	<%= msg %>
	</td>
	</tr>
	</table>
</body>
</html>