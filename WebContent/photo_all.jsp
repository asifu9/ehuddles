<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.PhotoAlbum"%>
<%@page import="com.pro.emp.domain.PhotoAlbumInfo"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.domain.PhotoInfo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all">
 <link href="css/styles.css" rel="stylesheet" media="screen"  />
 <link href="css/style.css" rel="stylesheet" media="screen" />
 	
<script type="text/javascript" src="js/jquery_.min.js" ></script>

<script type="text/javascript">
function ResizeThem()
{

  var maxheight = 200;
  var maxwidth = 200;
  var imgs = document.getElementsByTagName("img");
  for ( var p = 0; p < imgs.length; p++ )
  {
    if ( imgs[p].getAttribute("alt") == "photo" )
    {
      var w = parseInt( imgs[p].width );
      var h = parseInt( imgs[p].height );



      if ( w > maxwidth )
      {
        imgs[p].style.cursor = "pointer";
        imgs[p].onclick = function( )
        {
          var iw = window.open ( this.src, 'ImageViewer','resizable=1' );
          iw.focus();
        };
        h = ( maxwidth / w ) * h;
        w = maxwidth;

        imgs[p].height = h;
        imgs[p].width = w;
      }
      if ( h > maxheight )
      {
        imgs[p].style.cursor="pointer";
        imgs[p].onclick = function( )
        { 
          var iw = window.open ( this.src, 'ImageViewer','resizable=1' );
          iw.focus( );
        };
        imgs[p].width = ( maxheight / h ) * w;

        imgs[p].height = maxheight;
      }
    }
  }
}
</script>
	
<!-- code from herer -->

		
		
<style>
#backgroundPopup{
display:none;
position:fixed;
_position:absolute; /* hack for internet explorer 6*/
height:100%;
width:100%;
top:0;
left:0;
background:#000000;

z-index:1;
}
#popupContact{
display:none;
position:fixed;
_position:absolute; /* hack for internet explorer 6*/
background:#FFFFFF;

z-index:2;
padding:0px;
font-size:13px;
}
#popupContact h1{
text-align:left;
color:#6FA5FD;
font-size:22px;
font-weight:700;
border-bottom:1px dotted #D3D3D3;
padding-bottom:2px;
margin-bottom:20px;
}
#popupContactClose{
font-size:16px;
line-height:14px;
right:6px;
top:4px;
position:absolute;
color:red;
font-weight:700;
display:block;
}
#button{
text-align:center;
margin:100px;
}
</style>
<script>

//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0;

//loading popup with jQuery magic!
function loadPopup(albumId,ownerId,path,width,height){
	//loads popup only if it is disabled
	if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.8"
			
		});
		//alert(" here data " + albumId + " : " + ownerId);
		
		document.getElementById("myIFrame").src="";
		var windowWidth2 = document.documentElement.clientWidth;
		var windowHeight2 = document.documentElement.clientHeight;
		var urrl='photoList1.jsp?albumId='+albumId+'&ownerId='+ownerId+'&path='+path+'&windowWidth2='+windowWidth2+'&windowHeight2='+windowHeight2+'&acutalWidth='+width+'&actualHeight='+height;
		var urrl = encodeURI(urrl);
		//alert(" url i s " + urrl);
		document.getElementById("myIFrame").src=urrl; 
	
		//document.getElementById("myIFrame").contentDocument.location.reload(true);
		$("#backgroundPopup").fadeIn("slow");
		$("#popupContact").fadeIn("slow");
		popupStatus = 1;
	}
}

//disabling popup with jQuery magic!
function disablePopup(){
	//disables popup only if it is enabled
	if(popupStatus==1){
		$("#backgroundPopup").fadeOut("slow");
		$("#popupContact").fadeOut("slow");
		popupStatus = 0;
	}
}

//centering popup
function centerPopup(){
	//request data for centering

	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContact").height()+20;
	var popupWidth = $("#popupContact").width();
	if(popupHeight<500){
		popupHeight=800;
	}
	//centering
	$("#popupContact").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopup").css({
		"height": windowHeight
	});
	
}

$(document).ready(function(){

	//LOADING POPUP
	//Click the button event!
	$(".SendStyle").click(function(){

	});
				
	//CLOSING POPUP
	//Click the x event!
	$("#popupContactClose").click(function(){
		disablePopup();
		//location.reload();
	});
	//Click out event!
	$("#backgroundPopup").click(function(){
		disablePopup();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			disablePopup();
		}
	});

});

function updateHiddenBox (albumId,ownerId,path,pcount,width,height) {

	/*document.myForm.calenderEditEmpId.value = value;

	  // This AJAX call will save the Navigator's state to session.
	  // We don't need a callback function because nothing happens
	  // once said state is saved.
	  var url = "ManageCalSession?empid="+document.myForm.calenderEditEmpId.value;
	  try {
	  req = new ActiveXObject("Microsoft.XMLHTTP");
	  req.open("POST", url, true);				
	  req.send();

	  }catch(e){
		 req= new window.XMLHttpRequest();
		 req.open("POST", url, false);				
		  req.send(null);

	  }*/
		//alert(" hi " + pcount );
		//centering with css
		if(pcount==0){
			
		}else{
		centerPopup();
		//load popup
		loadPopup(albumId,ownerId,path,width,height);
		}
	}
	
</script>
<!--  code till hrere -->
<script  type="text/javascript" language="javascript">
function AjaxToSubmit(){
	var is_chrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
	var txtName = document.popForm.txtName.value;
	var userId = document.popForm.txtUserId.value;
	
	var privacy ="";

	if(is_chrome){
		privacy= document.popForm.txtPrivacy.value;
		
	}else {
		var getSelectedIndex1 =document.popForm.txtPrivacy.selectedIndex;
		privacy = document.popForm.txtPrivacy[getSelectedIndex1].text;
		
	}
	

	var queryString=txtName+"~~~#"+privacy+"~~~#"+userId;
	
	$.post("createAlbum", {inputQueryString : queryString}, function(datas) { // Do an AJAX call
		var divId= document.getElementById("updateDiv");
		divId.innerHTML=datas;
	});
}

$(document).ready(function(){
	$(".QTPopup").css('display','none')
	$(".lnchPopop").click(function(){
		$(".QTPopup").animate({width: 'show'}, 'slow');})
		$(".closeBtn").click(function(){			
			$(".QTPopup").css('display', 'none');
			window.location.reload();
		});
});


</script>
 <style>
 




 </style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
EmpInfo emp=null;
if(Session_control.getSession(request)!=null){
	emp= Session_control.getSession(request);
}
else {
	response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
}
String albumId="";

if(session.getAttribute("albumId")!=null){
	albumId = (String)session.getAttribute("albumId");
}
System.out.println(" album id " + albumId);
List<PhotoInfo>  p = Util.getAllPhotoByAlbumId(albumId);


	PhotoAlbumInfo paInfo= Util.getPhotoAlbumById(albumId);
%>

<title><%=paInfo.getName() %> : <%=paInfo.getUserName() %>'s Photo album</title>
<script type="text/javascript" src="js/jquery4.1.min.js" ></script>
<script type="text/javascript" src="js/locale.js" ></script>
<script type="text/javascript">
$(document).ready(function() {
	$('a img').hover_caption();
	});
	
$('a img').hover_caption({
	caption_font_size: '10px',
	caption_color: 'white',
	caption_bold: false,
	caption_default: ""
	});

</script>
<style type="text/css">
.hover_caption {
text-align:left;
background-image: url(hover_caption_bg.png);
/* NOTE: if you're img elements have paddings
or margins you'll need to match them here
to get things lined up properly. */
}
</style>
<script type="text/javascript">


function callme(iiim){
	//alert(" size " + iiim.width);
	
	//if(iiim.width>100){

		iiim.setAttribute("float","left");
		iiim.setAttribute("position","absolute");
		iiim.setAttribute("top",-50);
		iiim.setAttribute("left",-100);
	//	iiim.setAttribute("margin-left",-200);
	//	alert(" done");
	//}
	/* if($iiim.width()>500){
		$iiim.css({
		
			"margin-top": 200,
			"margin-left": 200 
		});
	} */
}

</script>
</head>
<body topmargin="0px">
<div align="center" id="header" style="margin-top: 0px;">
 <div style="background-color: #00688B;height: 60px;width: 100%;">
 			<div style="height: 20px;"></div>
	  		<div style="padding-left: 100px;vertical-align: baseline;" align="left">
	  		
	  			<span class="NewHeaderStyle"><%=AppConstants.APP_NAME %></span>
	  			
	  		</div>
	  </div>
	  
<div style="width:80%;">
<table width="100%" border="0" cellpadding="0" cellspacing="0"  >
			<tr>
				<td style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;">
					<span class="headerLink" style="padding-left: 5px;"><%=emp.getEmpName() %></span>
				</td>
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;">
					<table  cellpadding="0" cellspacing="0" align="right">
						<tr>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="feeds.jsp" >Home</a>
							</td>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="profilefeed.jsp">Profile</a>
							</td>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="photoall.jsp?userId=<%=emp.getId() %>" >Photo</a>
							</td>
							
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="logout.jsp" >Log out</a>
							</td>
							<!-- <td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="services.jsp" target="contents">Services</a>
							</td>
							<td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="aboutus.jsp" target="contents">About Us</a>
							</td>
							<td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="contactus.jsp" target="contents">Contact Us</a>
							</td>
							 -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
	<div align="left" style="padding-top: 0px;">
		<div style="display: table-cell; vertical-align: top;width: 100px; border-right-color: balck;border-right-style: inset;border-right-width: 1px;padding-right: 10px;" >
			<table>
				<tr >
					<td width="200px">
						<table>
							<tr >
								<td style="padding-top: 20px">
									<% if(emp.getImage()==null ) {%>
										<img src="images/person.jpg" width="150px" height="100px">
										<%}else{ %>
										<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%= emp.getImage().getPhotoPath().replaceFirst("a_","f_")%>" width="160px" >
										<%} %>
									<%-- <img src="<%=(emp.getImagePath().trim()%>" width="200px" height="200px"> --%>
								</td>
							</tr>
							<tr>
    					<td style="padding-top: 5px;height: 22px;" >
    					
    						<table cellpadding="0" cellspacing="0" width="100%" height="100%"> 
    							<tr>
    								<td class="AlbumHeader">
    									<a href="photoall.jsp?userId=<%=emp.getId()%>" class="AlbumHeaderStyle">All Albums</a>
    								</td>
    							</tr>
    						</table>
    						
    					</td>
    				</tr>
					<% List<PhotoAlbum> lisst= Util.getAllPhotoAlbulmByUser(emp.getId()); %>
    				<% for(PhotoAlbum a:lisst){ %>
    				<tr> 
    					<%
    					if(albumId.equalsIgnoreCase(a.getIdPhotoAlbum())) {%>
	    					<td class="headerGap1" style="background-color: #AEC9E2;" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							
								<a href="fileUploader.jsp?albumId=<%=a.getIdPhotoAlbum()%>&eed=<%=emp.getId()%>" class="leftLinkHeader1"><%=a.getName() %></a>
							</td>
    					<%}else{ %>
	    					<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							
								<a href="photo_all.jsp?aid=<%=a.getIdPhotoAlbum()%>&eed=<%=emp.getId()%>" class="leftLinkHeader"><%=a.getName() %></a>
							</td>
    					<%} %>
						
					</tr>
					<%} %>
						</table>
					</td>
				</tr>
			</table>
			
		</div>
		<div style="display: table-cell;" >
			<table >
				<tr>
					<td style="padding-bottom: 5px; padding-top: 5px; padding-left: 5px; border-bottom-color: #EEEEEE;
	border-bottom-style: inset;
	border-bottom-width: thin;" colspan="4">
						<table width="100%"  border="0">
							<tr>
								<td valign="top">
									<span class="photoHeadingstyle"><%=paInfo.getName() %></span>
									<br>
									<span class="albumnDescStyle"><%=(paInfo.getDesc()==null?"":paInfo.getDesc()) %></span>
								</td>
								 <td align="right"  valign="top" style="padding-top:5px;">
									<span >
										<!-- <a href="#" class="lnchPopop"  style="font-size:16px;font-weight:bold;border:0px solid #dadada; background:#fff; line-height:5px; padding:0px; color:gray; text-decoration:none; ">
											Create New Album
										</a>  -->
									</span>
								</td> 
							</tr>
						</table>
						
					</td>
				</tr>
			<tr>
				<% int counter=0;
				
				for(PhotoInfo pp:p){
					System.out.println(" path in original  " + pp.getPhotoPath());
					counter =counter+1;
					if(counter>=4){
					%>
					
					<td  class="photoAlbumSaperator" style="padding-left: 0px;" >
					<% } else { %>
					
					<td  class="photoAlbumSaperator" style="padding-left: 0px;"  >
						<%} %>
						
							<table cellpadding="0" cellspacing="0"  border="0" align="center" width="50px">
								<tr>
									<%if(pp.getHeight()<200 && pp.getWidth()<200){ %>
										<td class="photoAlbumSaperatorBlck"  style="padding-left: 2px;" align="center" width="100%">
										 <a onclick="updateHiddenBox('<%=pp.getIdPhotoAlbum()%>','<%=paInfo.getUserId()%>','<%=pp.getPhotoPath()%>',<%=paInfo.getCount()%>,<%=pp.getWidth()%>,<%=pp.getHeight() %>)" href="#" > 
											<%if(pp.getPhotoPath()==null || pp.getPhotoPath().trim().equals("")) {%>
												<img class="fixedCap" src="images/camera.jpg" width="190px" height="170px" alt="<%=paInfo.getCount() %> Photos" title="">
											<%}else{ %>
												<img class="fixedCap" src="<%=PropertyReader.getValue("photoAlbumPath",request)+""+pp.getPhotoPath().replaceFirst("a_","f_") %>"    title="<%=pp.getDescription() %>" >
											<%} %>
											</a>
										</td>
									<%}else{ %>
										<td class="photoAlbumSaperatorBlck"  style="padding-left: 2px;" align="center" width="100%">
										 <a onclick="updateHiddenBox('<%=pp.getIdPhotoAlbum()%>','<%=paInfo.getUserId()%>','<%=pp.getPhotoPath()%>',<%=paInfo.getCount()%>,<%=pp.getWidth()%>,<%=pp.getHeight() %>)" href="#" > 
											<%if(pp.getPhotoPath()==null || pp.getPhotoPath().trim().equals("")) {%>
												<img class="fixedCap" src="images/camera.jpg" width="190px" height="170px" alt="<%=paInfo.getCount() %> Photos" title="">
											<%}else{ %>
												<img class="fixedCap" width="200px" height="200px" src="<%=PropertyReader.getValue("photoAlbumPath",request)+""+pp.getPhotoPath().replaceFirst("a_","f_") %>"    title="<%=pp.getDescription() %>" >
											<%} %>
											</a>
										</td>
									<%} %>
									
								</tr>
								
							</table>
					<%if(counter>=4){
						counter=0;
					%>
					</td>
					</tr>
					<tr>
					<% } else { %>
					</td>
					
						<%} %>
						
						
				<% 
				} %>
				
			</table>
		</div>
		</div>
		</div>
	</div> 
	
	<!--  popup code -->
<%-- <form name="popForm" action="">
<input type="hidden" value="<%=emp.getId() %>" name="txtUserId">
<div class="QTPopup">
	<div class="popupGrayBg"></div>
	<div class="QTPopupCntnr">
		<div class="gpBdrLeftTop"></div>
		<div class="gpBdrRightTop"></div>
		<div class="gpBdrTop"></div>
		<div class="gpBdrLeft">
			<div class="gpBdrRight">
				<div class="caption">
					Create new Album
				</div>
				<a href="#" class="closeBtn" title="Close"></a>
				
				<div class="content">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>Album name</td>
							<td width="260">
								<div class="titlebarLeftc">
								<div class="titlebarRightc">
								<div class="titlebar" style="width:250px;">
								<input type="text" value="" name="txtName"  style="border:0px;  background:none; margin-top:5px; width:245px;"/>
								</div>
								</div>
								</div> 
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td>Privacy</td>
							<td width="260">
								<div class="titlebarLeftc">
								<div class="titlebarRightc">
								<div class="titlebar" style="width:250px;">
							
								<select name="txtPrivacy" style="border:0px;  background:none; margin-top:5px; width:245px;">
									<option id="1" value="1">Public</option>
									<option id="2" value="2">Private</option>
								</select>
								</div>
								</div>
								</div> 
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td></td>
							<td width="200px" >
								
							<input type="button" value="Create" onclick="AjaxToSubmit()" style="  margin-top:5px; width:100px;">
								<div id="updateDiv">
							</div>
							
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td></td>
							<td width="200px" >
								
							
								
							
							</td>
						</tr>
					</table>
					<br />
					
				</div>
			</div>
		</div>
		<div class="gpBdrLeftBottom"></div>
		<div class="gpBdrRightBottom"></div>
		<div class="gpBdrBottom"></div>
</div>
</div>
</form> --%>
<!--  end of popup code -->
  <!-- The file upload form used as target for the file upload widget -->
    <div id="popupContact" style="width: 50%;height: 50%;" align="center">
		<a id="popupContactClose">x</a>
		<!-- photoList.jsp?albumId=63&ownerId=62 -->
		<iframe src="#"  width="100%" scrolling="no" height="100%" id="myIFrame" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 100%;"></div> 
	
	<script type="text/javascript">
	$("img").each(function()
			{
			  // alert($(this).width());
			   // console.log(this.height());
			});
	
	//$(".fixedCap").each(function() {
		//alert(" hi");
	//  var  $imageWidth = $(".photoAlbumSaperatorBlck img");
	//alert(" hi" + $(this).path());
	    /* if($imageWidth.width()>10){
	    	alert("done 11");
	    	$imageWidth.css("float","left");
	   $imageWidth.css("left","-100");
	   $imageWidth.css("position","absolute"); */
	 //   }
	    
	  //});

</script>
</body>
</html>