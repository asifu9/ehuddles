<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jsValidation.js" ></script>
 <script src="js/jquery4.1.min.js" type="text/javascript"></script>
    <script src="js/jquery.Jcrop.js" type="text/javascript"></script>
    <link href="js/jquery.Jcrop.css" rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="icon/browserIcon.png">
    <script src="js/myOwnPopup.js" type="text/javascript"></script>
    <script type="text/javascript" language="javascript">


// This function allows to select and resize the cropbox
//        $(function() {                    
//            $('#ImFullImage').Jcrop({
//                //onSelect: updateCoords,
//            //onChange: updateCoords
//              
//            });
//        });
        function updateCoords(c) {
            $('#hfX').val(c.x);
            $('#hfY').val(c.y);
            $('#hfHeight').val(c.h);
            $('#hfWidth').val(c.w);
        }
       
        $(function() {   
            $('#ImFullImage').Jcrop({
            onChange: updateCoords,
            setSelect:   [ 0, 0, 100, 100 ],
            animateTo:   [ 0, 0, 100, 100 ],
            allowSelect: false,
            allowResize: false   

           
        });
    });





    </script>
<script>
function uploadPhoto(){
var url = "ManageCalSession?empid="+document.myForm.empid.value;
try {
req = new ActiveXObject("Microsoft.XMLHTTP");
req.open("POST", url, true);				
req.send();

}catch(e){
	 req= new window.XMLHttpRequest();
	 req.open("POST", url, false);				
	  req.send(null);

}
}

function submit_check(o) {
	var title = o.title;
	var file = o.fileImgLogo;
	if(isEmpty(file.value)){
		alert(" Please select a image to upload");
		return false;
	}
	if((file.value.length > 0)) {
	if(file.value.substring(file.value.length-3) == 'gif' 
		|| file.value.substring(file.value.length-3) == 'GIF'
			|| file.value.substring(file.value.length-3) == 'jpg'
				|| file.value.substring(file.value.length-3) == 'JPG'
			|| file.value.substring(file.value.length-3) == 'png'
				|| file.value.substring(file.value.length-3) == 'PNG'
			|| file.value.substring(file.value.length-3) == 'bmp'
				|| file.value.substring(file.value.length-3) == 'BMP'
			|| file.value.substring(file.value.length-4) == 'jpeg'
				|| file.value.substring(file.value.length-4) == 'JPEG') {
	return true;
	}
	else { //Wrong filetype!
		alert("Please upload an image type file. (.gif/.jpg/.png/.jpef./bmp)");
	return false;
	}
	
	}
}

<% 
	String msg=null;
	String flow=null;
	if(request.getParameter("msg")!=null){
		msg= request.getParameter("msg");
	}
	if(request.getParameter("flow")!=null){
		flow= request.getParameter("flow");
	}
	System.out.println(" flow is " + flow);
%>
</script>
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all">
</head>
<body marginheight="0px" marginwidth="0px" style="margin-top: 0px; margin-left: 0px;margin-right: 0px;">

	<form action="photoUpload" onsubmit="return submit_check(this)"; method="post" enctype="multipart/form-data" name="photoUpload" id="photoUpload">
	
	<input type="hidden" id="hfx">
	<input type="hidden" id="hfY">
	<input type="hidden" id="hfHeight">
	<input type="hidden" id="hfWidth">
	

	  <table width="100%" cellpadding="0" cellspacing="0">
    	<tr>
    		<td class="listTableHeader" width="100%" style="height: 25px;">
    			Cover page photo Upload
    		</td>
    	</tr>
    	<tr>
    		<td width="100%">
    			<Table width="100%" cellpadding="0" cellspacing="0">
    				<tr>
			    		<td align="left" class="valueColumn">
			    		 	<input type="hidden" name="flow" value="editFlow">
			    		 	<input type="file" name="fileImgLogo" id="fileImgLogo">
			    		 	<input type="hidden" name="flowpage" id="flowpage" value="cover">
			    		 	<input type="Submit"  value="Upload File"> &nbsp; <a href="ImageCropCover.jsp" class="myStyleCropp">Crop</a> <br>
			    		</td>
	    			</tr>
	    		</Table>
	    		<%if(msg!=null){ %>
	    		
	    		<%=msg %>
	    		<%} %>
    		</td>
    	</tr>
    </table>
    <div>
    	<% EmpInfo emp=null;
    	if(Session_control.getSession(request)!=null){
    		emp= Session_control.getSession(request);
    	}
    	else {
    		response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
    	}
    	 %>
    	 <%System.out.println(" in jsp : " +emp.getCoverPath()); %>
    	<img id="ImFullImage" src="<%=emp.getCoverPath() %>" />
    	
    	
    </div>
    </form>
</body>
</html>