<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Links</title>
  <link rel="stylesheet" href="jjs/managedivision.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="jjs/managedivision.css">
  <script src="jjs/division.js"></script>
   <script>
  $(document).ready(function () {
	
  });
  </script>
</head>
<body>
	<div style="background-color: #9ECEDE;">
		<table>
			<tr>
				<td>
					<Table>
						<tr>
							<td>
								Manage Links
							</td>
							<td align="right">
								<span id="addShow">Add</span>
							</td>
						</tr>
					</Table>
					
				</td>
			</tr>
			<tr>
				<Td>
					<div id="manageDivLinks" class="mytoggle">
					<table>
						<tr>
							<td class="columnHeaderStyle">
							
								Name
							</td>
							<td class="columnHeaderStyle">
							
								URL
							</td>
							<td class="columnHeaderStyle">
								User Name
							</td>
						</tr>
						<tr>
							<td class="columnHeaderStyle">
							
								<input type="text" name="linkName" id="linkName" >
							</td>
							<td class="columnHeaderStyle">
							
								<input type="text" name="linkURL" id="linkURL" >
							</td>
							<td class="columnHeaderStyle">
								<input type="text" name="userName" id="userName" >
							</td>
						</tr>
						<tr>
							<td class="columnHeaderStyle">
								Password
							</td>
							<td class="columnHeaderStyle">
								Order
							</td>
							<td class="columnHeaderStyle">
								
							</td>
						</tr>
						<tr>
							<td class="columnHeaderStyle">
								<input type="password" name="passwordU" id="passwordU" >
							</td>
							<td class="columnHeaderStyle">
								<input type="text" name="orderNum" id="orderNum" >
							</td>
							<td class="columnHeaderStyle">
								<input type="button" value="Add" onclick="addLink()">
							</td>
						</tr>
					</table>
					</div>
					<div>
						<table>
							<tr>
								<td class="columnHeaderStyleColumns1">Name</td>
								<td class="columnHeaderStyleColumns2">URL</td>
								<td class="columnHeaderStyleColumns1">User Name</td>
								<td class="columnHeaderStyleColumns3">Order</td>
								<td class="columnHeaderStyleColumns1"></td>
							</tr>
						</table>
					</div>
					<div id="insertLink"></div>
				</Td>
			</tr>
		</table>
	</div>
</body>
</html>