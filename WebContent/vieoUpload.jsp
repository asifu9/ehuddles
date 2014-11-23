<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.pro.emp.domain.EmpInfo"%>
    <%@page import="java.util.HashMap"%>
    <%@page import="com.pro.emp.EmployeeInfo"%>
    <%@page import="com.pro.emp.util.Constant"%>
    <%@page import="com.pro.emp.Session_control"%>
    
    <%
    
    EmpInfo emp=null;
	if(Session_control.getSession(request)!=null){
		emp= Session_control.getSession(request);
	}else{
		response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
	}
	
	System.out.println(" emp value in jsp page " + emp);
    
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
HttpSession session = request.getSession(true);
function checkType(){
	var name = document.getElementById("fileImgLogo").value;
	alert(" name " + name);
	alert(name.indexOf(".wmv"));
	var wmvType= name.indexOf(".wmv");
	var mp4Type = name.indexOf(".mp4");
	var mkvType=name.indexOf(".mkv");
	var flvType=name.indexOf(".flv");
	alert("      " + mp4Type + "          " + mkvType + "        " + flvType);
	if(wmvType==-1 && mp4Type==-1 && mkvType==-1 && flvType==-1){
		alert(" not supported ");
		return false;
	}
	return true;
	
	
}
</script>
</head>
<body>
<form action="uploadVideo" onsubmit="return checkType();"  method="post" enctype="multipart/form-data" name=videohotoUpload" id="videohotoUpload">
	
Video name: <INPUT TYPE="text" NAME="name" value="video name"/><br/>
Description: <INPUT TYPE="text" NAME="desc" value="description"/><br/>
File:	<input type="file" name="fileImgLogo" id="fileImgLogo">
<input type="hidden" name="eid" value="<%=emp.getId()%>"/>

	<INPUT TYPE="submit" VALUE="Upload"/>
</FORM>
</body>
</html>