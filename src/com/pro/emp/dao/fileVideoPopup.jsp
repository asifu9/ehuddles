<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
EmpInfo emp=null;
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
			
			String empId="";
			if(request.getParameter("eed")!=null)
				empId=request.getParameter("eed");
			 String msg="";

			 if(request.getParameter("message")!=null)
			 	msg=request.getParameter("message");
%>
<html>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<style>

.headerLink1 {
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			font-size: 16px;
			font-style: normal;
			display: block;
			color:#696969;
			font-weight: bold;
	}
	
.headerLinkMO1{
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			color:white;
			font-size: 16px;
			font-style: normal;
			font-weight: bold;
			display: block;
			

	}
.headerLinkMOBlock1{
			background-color: #AEC9E2;
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:white;
}
.headerLinkBlock1{
			 background-color: white; 
			 padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:balck;
}
.link1 {
	text-decoration: none;
	font-family: Tahoma;
	font-weight: bold;
	font-size: 16px;

}
.headerGap1 {
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:pink;
	}
	
	.infoHeader{
		border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
			border-top-color: #EEEEEE;
		border-top-style: outset;
		border-top-width: 1px;
		padding-top: 5px;
		padding-bottom: 5px;
	border-right-width: 1px;
			border-right-color: #EEEEEE;
		border-right-style: inset;
		font-weight: bold;
		font-size: 15px;
		font-family: verdane;
		padding-left: 4px;
		
	}
	.infoHeaderh{
	
		padding-left:2px;
		border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
			border-top-color: #EEEEEE;
		border-top-style: outset;
		border-top-width: 1px;
		background-color: #EEEEEE;
		
		padding-top: 5px;
		padding-bottom: 5px;
		
		border-left-color: #EEEEEE;
		border-left-style: outset;
		border-left-width: 1px;
	
	}
	.infoMiddleh{
	border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
		background-color:#EEEEEE;
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.infoMiddle{
	border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
			
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.infoFooter{
		border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
			
		padding-top: 5px;
		padding-bottom: 5px;
	}
.headerGap {
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:pink;
	}
</style>


  <link rel="stylesheet" href="css/mystyle.css">
 <link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
 <link rel="stylesheet" href="css/mystyle.css">
<!-- <link rel="stylesheet" href="css/bootstrap-responsive.min.css"> -->
<link rel="stylesheet" href="css/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="css/jquery.fileupload-ui.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery_.min.js"></script>
<title>Insert title here</title>

 <script type="text/javascript">
 function validationUpload(){
	 var dooo= document.getElementById("files").value;
	 if(dooo==""){
		 alert(" Please select any video to upload");
		 
	 }else{
		 //var progress= document.getElementById("progressBar");
		 $('#progressBar').css('display','block');
		 
		 $("#uploadBtn").attr("disabled", "disabled");
		 $("#addBtn").attr("disabled", "disabled");
		 $("#addBtttn").attr("disabled", "disabled");
		 $("#files").attr("display", "none");
		 $("#newBTN").attr("disabled", "disabled");
		 $("#tdBlock").attr("disabled", "disabled");
		 
		 
		 document.forms['fileupload'].submit();
			
	 }
 }
 </script>
    
    
    
</head>
<body>


	<table width="100%" align="left" cellpadding="0" cellspacing="0" >
		<tr>
			<td class="imageUIHeader">
				Upload Video
			</td>
		</tr>
		<tr>
			<td align="left" style="padding-bottom: 10px;">

			<form id="fileupload" name="fileupload"  action="UploadVideo" method="POST" enctype="multipart/form-data">
		        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
		        <div class="row fileupload-buttonbar"  style="width: 100%; width: 900px; background-color: white; padding-bottom: 10px;padding-top: 10px;">
		            <div class="span7" width="100%">
		                <!-- The fileinput-button span is used to style the file input field as button -->
		               <input type="hidden" name="empId" value="<%=empId%>" >
		               <table>
		               	<tr>
		               		<td style="padding-left: 80px;">
		              		<table>
		              			<tr>
		              				<td id="tdBlock" style="padding-left: 20px;">
		              				<span id="addBtn" class="btn btn-success fileinput-button">
						                    <span id="addBtttn" ><i id="newBTN" class="icon-plus icon-white"></i> Add files...</span>
						                    
						                    <input style="display: block;" type="file" id="files" name="files[]" multiple> 
						                </span>
						                <!-- - name="files[]" multiple -->
		              					 
		              				</td>
		              				<td>
		              					
		           						<button id="uploadBtn" type="button" onclick="validationUpload()" class="btn btn-primary start" >
				                    		<i class="icon-upload icon-white"></i> Start upload
				                		</button>
		              						
		              				</td>
		              			</tr>
		              		</table>
			               
		                <output id="list" class="uploadTextFormat"><%=msg%></output>
		                <div align="center" style="display: none;" id="progressBar" class="hidedisplayProgress"><img src="images/loader.gif" width="50px" height="50px"  /></div>
		                
		                 <!--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                <button type="reset" class="btn btn-warning cancel">
		                    <i class="icon-ban-circle icon-white"></i> Cancel upload
		                </button>
		              <button type="button" class="btn btn-danger delete">
		                    <i class="icon-trash icon-white"></i> Delete
		                </button>
		                <input type="checkbox" class="toggle"> -->
		                </td>
		               	</tr>
		               </table>
		            </div>
		           
		        </div>
		        <!-- The loading indicator is shown during image processing -->
							        
							        
		        <!-- The table listing the files available for upload/download -->
		        <table class="table table-striped"><tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody></table>
		    </form>
		</td>
		</tr>
    </table>
</body>
</html>
<script>
  function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object
	var j=0;
    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

      // Only process image files.
      if (!f.type.match('video.*')) {
        continue;
      }else{
    	  j=j+1;
      }
      
      
    }
    
          // Render thumbnail.
         
          document.getElementById('list').innerHTML=['<span class="uploadTextFormat">Selected '+j+ ' files to upload</span>'].join('');
         // document.getElementById('list').insertBefore(newChild, refChild)
      // Read in the image file as a data URL.
      
    
  }

  document.getElementById('files').addEventListener('change', handleFileSelect, false);
</script>