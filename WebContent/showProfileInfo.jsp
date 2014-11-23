<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% EmpInfo emp= (EmpInfo)session.getAttribute("userInfo");
HashMap roleMap = EmployeeInfo.getRolesForEmpId(emp.getId());
if((roleMap.get(Constant.CALENDAR_VIEW)==null?false:true)){
	response.sendRedirect(request.getContextPath()+"/profilefeed.jsp");
}

%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<table>
					<tr>
						<td>
							Basic Information
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td>Name:</td>
									<td><%= emp.getEmpName() %>
									<td>Employee Id</td>
									<td><%= emp.getEmpid() %></td>
								</tr>
								<tr>
									<td>Department</td>
									<td><%= emp.getDepartment() %></td>
									<td>Designation</td>
									<td><%= emp.getDesignation() %></td>
								</tr>
								<tr>
									<td>Date of Birth</td>
									<td><%= emp.getDob() %></td>
									<td>Date of Joining</td>
									<td><%= emp.getDoj() %></td>
								</tr>
							</table>
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
		<tr>
			<td>
			</td>
		</tr>
		<tr>
			<td>
			</td>
		</tr>
	
	</table>
</body>
</html>