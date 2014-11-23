<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intercom - Manage Division</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="jjs/managedivision.css">
  <script src="jjs/division.js"></script>
  <script>
  $(function() {
    $( "#accordion" ).accordion();
  });
  </script>
  <script>
  $(document).ready(function () {
	  showDivision();
  });
  </script>
</head>
<body>

	<div id="accordion">
		<h3>Department</h3>
		<div>
			
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td>
									Department
								</td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											
											<td class="columnHeaderStyle">
												Dep Name
											</td>
											<td class="columnHeaderStyle">
												Order
											</td>
											<td class="columnHeaderStyle">
												Delete
											</td>
										</tr>
										<tr>
											
											<td>
												<input type="text" name="depName" id="depName">
											</td>
											<td>
												<input type="text" name="depOrder" id="depOrder">
											</td>
											<td>
												<input type="button" value="Add Dep" onclick="addDepartment()">
											</td>
											
										</tr>
									</table>
									<div id="insertDepDiv" ></div> 
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
			
			
			
		</div>
		<h3>Designation</h3>
		<div>
			
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td>
									Designation
								</td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											
											<td class="columnHeaderStyle">
												Designation Name
											</td>
											<td class="columnHeaderStyle">
												Order
											</td>
											<td class="columnHeaderStyle">
												Delete
											</td>
										</tr>
										<tr>
											
											<td>
												<input type="text" name="desName" id="desName">
											</td>
											<td>
												<input type="text" name="desOrder" id="desOrder">
											</td>
											<td>
												<input type="button" value="Add Des" onclick="addDesignation()">
											</td>
											
										</tr>
									</table>
									<div id="insertDesDiv" ></div> 
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
			
			
			
		</div>
		<h3>Section 3</h3>
		<div>
			<p>Nam enim risus, molestie et, porta ac, aliquam ac, risus.
				Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in
				pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac
				felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam
				nisi, eu iaculis leo purus venenatis dui.</p>
			<ul>
				<li>List item one</li>
				<li>List item two</li>
				<li>List item three</li>
			</ul>
		</div>
		<h3>Section 4</h3>
		<div>
			<p>Cras dictum. Pellentesque habitant morbi tristique senectus et
				netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum
				primis in faucibus orci luctus et ultrices posuere cubilia Curae;
				Aenean lacinia mauris vel est.</p>
			<p>Suspendisse eu nisl. Nullam ut libero. Integer dignissim
				consequat lectus. Class aptent taciti sociosqu ad litora torquent
				per conubia nostra, per inceptos himenaeos.</p>
		</div>
	</div>
</body>
</html>