<%@page import="com.pro.emp.util.Constant"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.domain.EmployeeDomain"%>
<%@page import="com.pro.emp.domain.EmpIdproofInfo"%>
<%@page import="com.pro.emp.domain.EmpAdditionalInfo"%>
<%@page import="com.pro.emp.domain.EmpActivityInfo"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <% 
  String id = request.getParameter("empid").toString().trim();
  EmployeeDomain empInfo = EmployeeInfo.getCompleteEmpInfo(id);
  Set<EmpActivityInfo> activityInfo = empInfo.getEmpActInfo();
  EmpAdditionalInfo additionalInfo= empInfo.getEmpAddInfo();
  Set<EmpIdproofInfo> idProofSet = empInfo.getEmpIdProofs();
  EmpInfo employeeInfo = empInfo.getEmpInfo();
 
 
 HashMap roleMap = EmployeeInfo.getRolesForEmpId(employeeInfo.getId());
	if((roleMap.get(Constant.PROFILE_VIEW)==null?false:true)){
		response.sendRedirect(request.getContextPath()+"/profilefeed.jsp");
	}
	
  %>
  
  
  <style>
  .columnHeader {
  		font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
  		font-size: 12px;
  		font-weight: bold;
  		width: 150px;
  		background-color: #E8F7F4;
  		padding: 2px;
  		height: 22px;
  }
  .valueColumn {
  		font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
  		font-size: 12px;
  		font-weight: normal;
  		width: 150px;
  		 background-color: #F5FCFB;
  		 padding: 2px;
  }
  
  
  </style>
     </head>
 <style type="text/css">
 .titleSection {
 			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 15px;
 			font-weight: bold;
 			background-color: #6F5B7B;
 			color:#FFFFFF;
 			padding-left: 5px;
 }
 .borderForTable {
 
 			border-bottom-color: #6F5B7B;
 			border-bottom-style: solid;
 			border-bottom-width: 1px;
 			
 			border-right-color: #6F5B7B;
 			border-right-style: solid;
 			border-right-width: 1px;
 			
 			border-left-color: #6F5B7B;
 			border-left-style: solid;
 			border-left-width: 1px;
 			
 			padding-bottom: 10px;
 }
 .Button {
 			background-color: #52435A;
 			border-color: #6F5B7B;
 			border-style: solid;
 			border-width: 1px;
 			color:#FFFFFF;
 			 font-size:15px;
         font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
         font-weight: bold;
 }
.head
       {   
          font-size:20px;
          font-weight: bolder;
          font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
          color: #544E4F;
       }

    .textStyle
       {   
         font-size:15px;
         font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
         
       }
  </style>
  <body class="textStyle">
  <div style="width: 100%;" align="center">
     <form action="ProfileServlet" method="post">

	<table  border="0" width="100%">
		<tr>
			<td valign="top" width="100%" style="padding-bottom: 10px;">
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td class="titleSection" height="25px">Basic Employee information</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table border="0" style="background-color:white;" width="100%">
								<tr>
									<td width="180px" style="padding: 2px;">
										<% if(employeeInfo.getImagePath()==null || employeeInfo.getImagePath().trim().equalsIgnoreCase("")) {%>
										<img src="images/person.jpg" width="100%" height="160px">
										<%}else{ %>
										<img src="<%= employeeInfo.getImagePath()%>" width="100%" height="200px">
										<%} %>
									</td>
								
									<td valign="top">
										<table width="100%">
											<tr>
												<td class="columnHeader">Employee Id </td>
												<td class="valueColumn"><%= employeeInfo.getEmpid() %></td>
												<td class="columnHeader">Date of Birth</td>
			          							<td class="valueColumn"><%= employeeInfo.getDob() %></td>
											</tr>
											<tr>
												<td class="columnHeader">Employee Name</td>
			          							<td class="valueColumn"><%= employeeInfo.getEmpName() %></td>
												<td class="columnHeader">Date of Joining</td>
			          							<td class="valueColumn"><%= employeeInfo.getDoj() %></td>
			          							
											</tr>
											<tr>
												<td class="columnHeader">Designation</td>
			          							<td class="valueColumn"><%= employeeInfo.getDesignation() %></td>
			          							<td class="columnHeader">Department</td>
			         							<td class="valueColumn"><%= employeeInfo.getDepartment() %></td>
											</tr>
											<tr>
												<td class="columnHeader">Email ID</td>
			          							<td class="valueColumn"><%=employeeInfo.getEmailid() %></td>
			          							<td class="columnHeader">Login name</td>
			          							<td class="valueColumn"><%=employeeInfo.getLoginName() %></td>
											</tr>
										</table>
										
									</td>
							
								</tr>
							</table>
							
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="800px" style="padding-bottom: 10px;">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="titleSection" height="25px">
							Additional Information
						</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table width="100%">
								<tr>
									<td class="columnHeader">Address</td>
									<td class="valueColumn"><% if (additionalInfo !=null && additionalInfo.getAddress()!=null) { %> <%=additionalInfo.getAddress()%><%} %></td>
									<td class="columnHeader">City</td>
									<td class="valueColumn"><% if (additionalInfo !=null && additionalInfo.getCity()!=null) {%> <%= additionalInfo.getCity() %><%} %></td>
								</tr>
								<tr>
									<td class="columnHeader">State</td>
									<td class="valueColumn"><%if(additionalInfo !=null && additionalInfo.getState()!=null){ %><%= additionalInfo.getState() %><%} %></td>
									<td class="columnHeader">Country	</td>
									<td class="valueColumn"><%if(additionalInfo !=null && additionalInfo.getCountry()!=null) { %><%= additionalInfo.getCountry() %><%} %></td>									
								</tr>
								<tr>
									<td  class="columnHeader">Pincode	</td>
									<td class="valueColumn"><%if(additionalInfo !=null && additionalInfo.getPincode()!=null) { %><%= additionalInfo.getPincode() %><%} %></td>
									<td class="columnHeader">Telephone No</td>
									<td class="valueColumn"><%if(additionalInfo !=null && additionalInfo.getTelephone()!=null) { %><%= additionalInfo.getTelephone() %><%} %></td>
								</tr>
								<tr>
									<td class="columnHeader">Mobile No</td>								
									<td class="valueColumn"><%if(additionalInfo !=null && additionalInfo.getMobile()!=null) { %><%= additionalInfo.getMobile() %><%} %></td>
									<td class="columnHeader">Reference Contact Address</td>
									<td class="valueColumn"><%if(additionalInfo !=null && additionalInfo.getRefContact()!=null) { %><%= additionalInfo.getRefContact() %><%} %></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="top" width="800px" style="padding-bottom: 10px;">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="titleSection" height="25px">
							Extra Activity
						</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table width="100%">
								<tr>
									<td class="columnHeader">Type of Activity</td>
									<td class="columnHeader">Comments</td>
								</tr>
								<% for(EmpActivityInfo actInfo:activityInfo){ %>
								 <tr>
									<td class="valueColumn"><%= actInfo.getActivityType() %></td>
									<td class="valueColumn"><%= actInfo.getComments() %></td>
								</tr>
								<%} %>
							</table>
						</td>
					</tr>
				</table>
				
			</td>
		</tr>
		<tr>
			<td style="padding-bottom: 10px;">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="titleSection" height="25px">
							ID Proof
						</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table width="100%">
								<tr>
									<td class="columnHeader">Attachment</td>
									<td class="columnHeader">Type of Document</td>
									<td class="columnHeader">Description</td>
								</tr>

								<%for(EmpIdproofInfo idProof:idProofSet){%>
									<tr>
										<td class="valueColumn"><%= idProof.getAttachment() %></td>
										<td class="valueColumn"><%= idProof.getType() %></td>
										<td class="valueColumn"><%= idProof.getDescription() %></td>	
									</tr>
								<%} %>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

  <!--   <table width="800px" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    		<input class="Button" style="height: 30px;"  type="submit" value="  Save  " />
    			&nbsp;&nbsp;
    		<input class="Button" style="height: 30px;"  type="button" value="  Cancel  " />
    		</td>
    		
    	</tr>
    	
    </table> -->
       

        </form>
      </div>
     </body>
</html>
      