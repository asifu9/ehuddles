<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
function closeMe(){
	//alert("losng");
	parent.disablePopupGroup();
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<table>
		<tr>
			<td>
				<span>Group photo uploaded successfully</span><br>
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" onclick="closeMe()" value="close">
			</td>
		</tr>
	</table>
	
	
</body>
</html>