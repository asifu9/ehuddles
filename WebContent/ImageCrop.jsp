<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="com.pro.emp.Util"%>
<html>
	<head>
	<link rel="icon" type="image/png" href="icon/browserIcon.png">
	<%
	String status="";
	if(request.getAttribute("status")!=null){
		status=request.getAttribute("status").toString();
	}
	
	 EmpInfo empInfo= Session_control.getSession(request);
	String ext ="";
		String imagePath=empInfo.getImagePath();//EmployeeInfo.getImagePath("",request);
		boolean isImagePathExistis=false;
		if(imagePath!=null && !imagePath.equalsIgnoreCase("") && imagePath.length()>4){
			ext = imagePath.substring(imagePath.length()-3,imagePath.length());
			isImagePathExistis=true;
		}
	
	%>
		<script src="js/jquery4.1.min.js"></script>
		<script src="js/jquery.Jcrop.js"></script>
		   <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all">
		<link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
		<link rel="stylesheet" href="css/demos.css" type="text/css" />

		<script language="Javascript">
			var ssss = '<%=status%>';
			if(ssss=='done'){
				//alert("done");
				document.window.reload();
			}
		
			$(function(){

				$('#cropbox').Jcrop({
					aspectRatio: 1,
					onSelect: updateCoords,
					 setSelect:   [ 0, 0, 200, 200 ],
			            animateTo:   [ 0, 0, 200, 200 ],
					 allowSelect: true,
			            allowResize: true 
				});

			});

			function updateCoords(c)
			{
				$('#x').val(c.x);
				$('#y').val(c.y);
				$('#w').val(c.w);
				$('#h').val(c.h);
			};

			function checkCoords()
			{
				if (parseInt($('#w').val())) return true;
				alert('Please select a crop region then press submit.');
				return false;
			};

		</script>

	</head>

	<body marginheight="0" marginwidth="0">
 <table width="100%" cellpadding="0" cellspacing="0">
    	<tr>
    		<td class="listTableHeader" width="100%" style="height: 25px;">
    			Photo Upload
    		</td>
    	</tr>
    	</table>
	<div id="outer">
	<div class="jcExample">
	<div class="article">
<form action="ImageCrop" method="get"  onsubmit="return checkCoords();">
<input type="submit" value=" Crop Image " class="Button"  style="z-index: 20;"/>
<br><br>
		<% if(isImagePathExistis){ %>
		<!-- This is the image we're attaching Jcrop to -->
		<img src="<%=imagePath %>" id="cropbox" />

		<!-- This is the form that our event handler fills -->
		
		
			<input type="hidden" id="x" name="l" />
			<input type="hidden" id="y" name="t" />
			<input type="hidden" id="w" name="w" />
			<input type="hidden" id="h" name="h" />
			<input type="hidden" id="imageName" name="imageName" value="<%=imagePath %>">
			<input type="hidden" size="4" id="f" name="f" value="<%=ext %>" />
            <input type="hidden" size="4" id="i" name="i" value="<%=imagePath %>"/>
            <div align="left" style="vertical-align: bottom;padding-bottom: 100px;"> 
			
			</div>
		</form>
		<%}else{ %>
			<span style="font-weight: bold;color:red;">This user has not uploaded any image</span>
		<%} %>
		</div>
	</div>
	</div>

	</body>

</html>
