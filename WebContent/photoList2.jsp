<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="com.pro.emp.domain.PhotoInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
EmpInfo emp=null;
if(Session_control.getSession(request)!=null){
	emp= Session_control.getSession(request);
}
else {
	response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
}
//System.out.println(" here is data");
String albumId="";
String ownerId="";
String path="";
String acutalWidth="";
String actualHeight="";
String winHie="0";
String winWid="0";

if(request.getParameter("albumId")!=null){
	albumId=request.getParameter("albumId");
}
if(request.getParameter("ownerId")!=null){
	ownerId=request.getParameter("ownerId");
}
if(request.getParameter("path")!=null){
	path=request.getParameter("path");
}
if(request.getParameter("windowWidth2")!=null){
	winWid=request.getParameter("windowWidth2");
}
if(request.getParameter("windowHeight2")!=null){
	winHie=request.getParameter("windowHeight2");
}
if(request.getParameter("acutalWidth")!=null){
	acutalWidth=request.getParameter("acutalWidth");
}
if(request.getParameter("actualHeight")!=null){
	actualHeight=request.getParameter("actualHeight");
}
System.out.println("  acutalWidth is " + acutalWidth + " : " + actualHeight);
EmpInfo photoOwnerInfo=Util.getEmployeeById(ownerId);

System.out.println(" h " + winHie);
List<PhotoInfo> list= Util.getAllPhotoByAlbumId(albumId);
System.out.println(" listi " + list.size());
String basePath=PropertyReader.getValue("photoAlbumPath",request);
%>

 <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.js"></script>
  <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all"></link>
  <link href="css/myOwnPopup.css" rel="stylesheet" type="text/css" media="all"></link>
  <script type="text/javascript" src="jjs/tagjs.js"></script>
  <script type="text/javascript" src="js/SrapesUtil.js"></script>
      <script type="text/javascript" src="js/jquery.slimscroll.js"></script>
  <script type="text/javascript">
  var count = '<%=list.size()%>';
  if(count==0){
	  alert(" This photo has been deleted ");
	  disablePopup();
  }
	  
  $(function(){
		
	});
	
  </script>
 <script type="text/javascript">
 function  sharePost(){
	 
	var photoId= document.getElementById("photoIdHidden").value;
 }
 
function commentFocus(){
	document.getElementById("commentText").focus();
}
 
 </script>
  <script type="text/javascript">
  function updateDownloadBox(){ 
	   
	}
  </script>
  
  <script type="text/javascript">

//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus1 = 0;

//loading popup with jQuery magic!
function loadPopup(path,albumId){
	//loads popup only if it is disabled
	if(popupStatus1==0){
		$("#backgroundPopup2").css({
			"opacity": "0.8"
			
			
		});
		//alert(" here data " + albumId + " : " + ownerId);
		
		document.getElementById("myIFrame2").src="";
		var windowWidth2 = document.documentElement.clientWidth;
		var windowHeight2 = document.documentElement.clientHeight;
		var urrl='share.jsp?photoId='+albumId+'&path='+path;
		
		//alert(" url i s " + urrl);
		document.getElementById("myIFrame2").src=urrl; 
		//document.getElementById("myIFrame").contentDocument.location.reload(true);
		$("#backgroundPopup2").fadeIn("slow");
		$("#popupContact2").fadeIn("slow");
		popupStatus1 = 1;
	}
}

//disabling popup with jQuery magic!
function disablePopup(){
	//disables popup only if it is enabled
	if(popupStatus1==1){
		$("#backgroundPopup2").fadeOut("slow");
		$("#popupContact2").fadeOut("slow");
		popupStatus1 = 0;
	}
}

//centering popup
function centerPopup(){
	//request data for centering

	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	
	var popupHeight = $("#popupContact2").height();
	var popupWidth = $("#popupContact2").width();
	//centering
	$("#popupContact2").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopup2").css({
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
	$("#popupContactClose2").click(function(){
		disablePopup();
		//location.reload();
	});
	//Click out event!
	$("#backgroundPopup2").click(function(){
		disablePopup();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus1==1){
			disablePopup();
		}
	});

});

  function updateHiddenBox (path,albumId) {
  	
	//  alert(" path " + albumId + " : " + path);
  		
  		centerPopup();
  		
  		//load popup
  		loadPopup(path,albumId);
  		
  		
  	}
  
  
  </script>
  
  
  
  <style type="text/css">
  div.clear { 
	clear: both; 
}
div.caption { 
	 display: block !important;  
	 position: relative;
}
div.caption img { 
	/* position: absolute !important */
}
div.caption div { 
	background: #000; 
	background: rgba(0,0,0,0.8); 
	/* color: white !important; */ 
	display: none; 
	padding: 0px 0px  0px 0px !important; 
	text-align: center; 
	position: absolute !important;
	bottom: 0 !important; 
	left: 0 !important; 
}
div.caption div big {
	font-weight: bold; 
}
  </style>
  <style>
  .headerLink {
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			font-size: 16px;
			font-style: normal;
			display: block;
			color:#696969;
			font-weight: bold;
	}
	.backgroundPopup{
display:none;
position:fixed;
_position:absolute; /* hack for internet explorer 6*/
height:100%;
width:100%;
top:0;
left:0;
background:#000000;
border:1px solid #cecece;
z-index:1;
}
.headerLinkMO{
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			font-size: 16px;
			font-style: normal;
			font-weight: bold;
			display: block;
			

	}
.headerLinkMOBlock{
			background-color: #AEC9E2;
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
}
.headerLinkBlock{
			 padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:balck;
}
.link {
	text-decoration: none;
	font-family: Tahoma;
	font-weight: bold;
	font-size: 16px;

}
.headerGap {
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			
	}
.frameBorder {
			border-style: solid;
			border-width: 6px;
			border-color: black;

}
  </style>

 <script type="text/javascript">
 
  $(document).ready(function(){
	    var submit = $("#button");
	    $("textarea").blur(function() {
	        if ($(this).val() == "") {
	            $(this).val("Write a comment")
	                   .removeClass("comment_filled")
	                   .addClass("comment_empty");
	            submit.hide();
	        }
	    }).focus(function() {
	        if ($(this).val() == "Write a comment") {
	            $(this).val("")
	                   .removeClass("comment_empty")
	                   .addClass("comment_filled");
	            submit.show();
	        }
	    });
	});
  
  </script>
<script type="text/javascript" > 
$("#backgroundPopup2").css({
	"opacity": "0.9"
});
$("#backgroundPopup2").fadeIn("slow");
</script>
<script type="text/javascript">

function showdeleteButton(key){
	var k = 'delete_'+key;
	document.getElementById(k).style.display='block';
}
function hidedeleteButton(key){
	var k = 'delete_'+key;
	document.getElementById(k).style.display='none';
}
</script>
  <script type="text/javascript">
  function showLikedUsers(){
	  
	  $('#simplediv').css('display','block');
	  return false;
  }
  function hideLikedUsers(){
	  $('#simplediv').css('display','none');
  }

  function hideUserInfo(){
  
	  $('#userInfo').css('display','none');
  }
  </script>
 <script type="text/javascript">
 function downloadPopup(id){
	  var userDiv= document.getElementById('downloadInfo');
	  var id =document.getElementById('photoIdHidden').value;
	  var createdOn=document.getElementById('createdOnHidden').value;
	 // alert(" user id "+id);
	 var div='';
				  div='<div class="popupMainDiv3">';
				  div=div+'<div class="popupSecondDiv2">';
				  div=div+'<table><tr><td  valign="top"><a href="DownloadImage?imageId="'+id+'&createdOn="'+createdOn+'"  style="text-decoration: none;color:white;font-weight: bold; vertical-align: middle"  >Download <img src="img/download.jpg" width="20px" height="20px" /></a> </td>';
				  div=div+'<td valign="top"><table><tr><td class="nameInPopupStyle">  </td></tr></table></td></tr><table></div></div>';
				
				 var keyUser = 'optionBlockId';
				 //var offset = $(keyUser).offset();
				 // alert(" hi "+keyUser);
				  var obj = document.getElementById(keyUser);
				 // alert(" hi 3 "+obj);
				  var posX = obj.offsetLeft;
				//  alert(" hi 2"+keyUser);
				  var posY = obj.offsetTop;
				  //alert(" hi d" + keyUser);
		  //$("#popupMainDiv").css('left',offset.left);    
		  //$("#popupMainDiv").css('top',offset.top);
		  //alert(" hi off set " + obj.offsetLeft);
		  //
		  while(obj.offsetParent){
			  posX=posX+obj.offsetParent.offsetLeft;
			  posY=posY+obj.offsetParent.offsetTop;
			  if(obj==document.getElementsByTagName('body')[0]){break;}
			  else{obj=obj.offsetParent;}
			  }
			  
			  
		  
		  //
		  posY=posY+20;
		  //alert(" posX " + posX  + " posY " + posY);
		  //$('#downloadInfo').css('box-shadow','0px 2px 2px 0px black');
		  $('#downloadInfo').css('left',posX-180);
		  $('#downloadInfo').css('width',200);
		  $('#downloadInfo').css('top',posY);
		  $('#downloadInfo').css('display','block');
		  
		  
		  userDiv.innerHTML=div;
		  //alert(" div is " + div);
		  //sdf
		  
			 
			 
 }
 </script>
<script type="text/javascript">
  function getUserInfo(id,commentId){
	  var userDiv= document.getElementById('userInfo');
	 // alert(" user id "+id);
	 var div='';
	  $.getJSON("GetUserInfo", {'userId' :id}, function(datas) { // Do an AJAX call
			// alert(" data s" + datas);
			 // $.each(datas, function(l,items){
				  var name=datas.empName;
				  var imagePath=datas.imagePath;
				  
				  if(imagePath=='' || imagePath==null)
					  imagePath='images/person_icon.jpg';
				  //alert(" name " + name);
				  //alert(" image path " + imagePath);
				  div='<div class="popupMainDiv2">';
				  div=div+'<div class="popupSecondDiv">';
				  div=div+'<table><tr><td  valign="top"><img src="'+imagePath+'" width="50px" height="50px"/> </td>';
				  div=div+'<td valign="top"><table><tr><td class="nameInPopupStyle"> '+name+'</td></tr><tr><td class="otherInPopupStyle">'+datas.strDepartment+' | '+datas.strDesignation+' </td></tr></table></td></tr><table></div></div>';
				
				 var keyUser = 'user_'+id +''+commentId;
				 //var offset = $(keyUser).offset();
				 // alert(" hi "+keyUser);
				  var obj = document.getElementById(keyUser);
				 // alert(" hi 3 "+obj);
				  var posX = obj.offsetLeft;
				//  alert(" hi 2"+keyUser);
				  var posY = obj.offsetTop;
				  //alert(" hi d" + keyUser);
		  //$("#popupMainDiv").css('left',offset.left);    
		  //$("#popupMainDiv").css('top',offset.top);
		  //alert(" hi off set " + obj.offsetLeft);
		  //
		  while(obj.offsetParent){
			  posX=posX+obj.offsetParent.offsetLeft;
			  posY=posY+obj.offsetParent.offsetTop;
			  if(obj==document.getElementsByTagName('body')[0]){break;}
			  else{obj=obj.offsetParent;}
			  }
			  
			  
		  
		  //
		  posY=posY+20;
		 
		  $('#userInfo').css('box-shadow','0px 2px 2px 0px black');
		  $('#userInfo').css('left',posX-20);
		  $('#userInfo').css('top',posY);
		  $('#userInfo').css('display','block');
		  
		  
		  userDiv.innerHTML=div;
		  //alert(" div is " + div);
		  //sdf
		  
			 });
			 
  }
  </script>
 
  <script type="text/javascript">
  function deleteComments(key){
	 // alert(" delete " + key);
	  $.getJSON("DeleteComment", {'commentId' :key}, function(datas) { // Do an AJAX call
		//alert(" result " + datas + ' : ' + datas.result);
		  if(datas.result=="SUCCESS"){
			  document.getElementById(key).style.display='none';
		  }
	  });
  }
  </script>
<script type="text/javascript">
function doTest(){
}
 function createCommentsTest(){
	 var bPath='<%=basePath%>';
	 var pId= document.getElementById("photoIdComment").value;
	  var commentedUserId= document.getElementById("commentedUserId").value;
	  var text= document.getElementById("commentText").value;
	  if(text=="" ||text=="Write a comment")
		  return;
	  // alert(" in craete comment " + pId + ' : ' + commentedUserId + ' : ' + text);
	  //var url='CreateComment?photoId='+photoId+'&commentUserId='+commentedUserId+'&text='+text;
	  $.getJSON("CreateComment", {'photoId' :pId,'commentUserId':commentedUserId,'text':text}, function(datas) { // Do an AJAX call
		 // $.each(datas, function(l,items){
			  var ph=datas.commentUserPhoto;
			  if(ph=='')
				  ph='images/person_icon.jpg';
			  else
				  ph=bPath+ph;
			  var likedId=datas.id;
			  var key=datas.id;
		  
			  //new coide
			  
			   div='<div id="'+key+'" class="commentPanel" style="width:95%;"  align="left" onmouseout="hidedeleteButton(\''+key+'\')" onmouseover="showdeleteButton(\''+key+'\')">';
			  div=div+'<div  style="display:table-cell; width:5%;"  cellpadding="0" cellspacing="0">';
			  
			  var iiidd= '<%=emp.getId()%>';
			  var useruser='user_';
			  var keykey=useruser+iiidd+key;
			  // div=div+'<table  border="0" style="width:100%" cellpadding="0" cellspacing="0">'+
			  // '<tr><td valign="top" style="width:10%;background-color:gray;">';
			  div=div+'<img src="'+ph+'" width="35px" class="CommentImg" style="float:left;" alt="" /> </div>';
			  //'<td style="width:90%;background-color:lime;">';
			  div=div+'<div style="display:table-cell;width:95%;vertical-align: top; " > ';
			  div=div+'<div style="display:table;width:100%;vertical-align:top;"> ';
			  div=div+'<div style="display:table-cell;width:95%;vertical-align: top;" align="left"> ';
			  div=div+'<label style="backgrond-color:blue;"   class="postedComments" >';
			  div=div+'<span   class="photoNameHeader" id="'+keykey+'" onmouseout="hideUserInfo()" onmouseover="getUserInfo(\'<%=emp.getId()%>\',\''+key+'\')">'+datas.commentUserName+'</span> &nbsp;';
			  div=div+ text;
					
			  div=div+'</label>';
			  div=div+'<br clear="all" />';
			  div=div+'<span style="margin-left:3px; color:#666666; font-size:11px">';
			  div=div+datas.timeInStr;
			  div=div+'</span>';
			  div=div+'</div>';
			  div=div+'<div style="display:table-cell;vertical-align:top;float:right;width:10%;" align="right">';
			  div=div+'<div style="width:100%;vertical-align: top;float:right;" id="delete_'+key+'"  class="deleteButtonHidden" align="right">';
			  div=div+'<a href="#" style="text-decoration:none;" onclick="deleteComments(\''+key+'\')") ><span class="deleteButtonCmm">X</span></a></div>';
			  div=div+'</div>';
			  div=div+'</div>';
			  div=div+'</div>';
			  //div=div+'</td><td width="4%" valign="top" style="background-color:yellow;">';
			 
			//  div=div+'</td></tr></table>';
			  div=div+'</div>';
			  
			  //end of new code --------------------------------------------------------------------------------
			 
			  // end of data
			  $('#commentDivHeader').append(div);
			  
			 // break;
			  
		 // });
		  
	  });
	  document.getElementById("commentText").value="";
	 // alert("e xt ");
 }

 </script>
 <script type="text/javascript">
  function commentsForPhoto(ppId){
	  var empId='<%=emp.getId()%>';
	  var bPath='<%=basePath%>';
	  var mainDiv=document.getElementById("commentDivHeader");
	 var mainDivStr='';
	 var tagDiv='';
	 // alert(" in comment id " + ppId);
	 $("#taggedUserList").empty();
	 $("#userTagArrow").css('display','none');
	 $.getJSON("TagGetByPhoto", {'photoId' :ppId}, function(datas) { // Do an AJAX call
			
		 var stt='';
		 $('#showTaggerUsers').css('display','none');
	        
     	$('#taggedUserList').css('display','none');
     	$('#arrowtagUserPermanent').css('display','none');
			var count=parseInt(datas.length);
			var i=0;
			$.each(datas, function(l,item){
				i=i+1;
				console.log(" i =" + i + " count =" + count);
				stt+='<a href="#" id="'+item.taggerUserIdInfo.id+'_'+item.taggerUserIdInfo.id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+item.taggerUserIdInfo.id+'\',\''+item.taggerUserIdInfo.id+'\')">'+item.taggerUserIdInfo.empName+'</a>&nbsp;';
				if(count==i){
					$("#taggedUserList").append(stt);
					
					var obj = document.getElementById("myTagPoint");
		        	var posX = obj.offsetLeft;
		        	var posY = obj.offsetTop;
					console.log(" .height() " + $("#showTaggerUsers").outerHeight());
					console.log(" .height() " + $("#showTaggerUsers").innerHeight());
					console.log("javascript " + document.getElementById("showTaggerUsers").clientHeight);
					console.log("javascript " + document.getElementById("taggedUserList").clientHeight);
					
		        	while(obj.offsetParent){
		        	posX=posX+obj.offsetParent.offsetLeft;
		        	posY=posY+obj.offsetParent.offsetTop;
		        	if(obj==document.getElementsByTagName('body')[0]){break;}
		        	else{obj=obj.offsetParent;}
		        	}
		        	var p = $("#myTagPoint");
		        	var position = p.position();
		        	var offset = p.offset();
		        	console.log( "left: off " + offset.left + ", top: off " + offset.top );
		        	console.log( "left: " + position.left + ", top: " + position.top );
		        	posX=  position.left;
		        	posY=position.top;
		        	/* $('#arrowtagUserPermanent').css('position','absolute');
		        	$('#arrowtagUserPermanent').css('left',posX+490);
		        	$('#arrowtagUserPermanent').css('top',posY-25);
		        	$('#arrowtagUserPermanent').css('display','block'); */
		        	
		        	
		        	
		        	$('#showTaggerUsers').css('z-index','100');
		        	$('#taggedUserList').css('z-index','101');
		        	$('#arrowtagUserPermanent').css('position','absolute');
		        	$('#arrowtagUserPermanent').css('left',posX+10);
		        	$('#arrowtagUserPermanent').css('top',posY+25);
		        	
		        	$('#arrowtagUserPermanent').css('z-index','1');
					$('#showTaggerUsers').css('display','block');
			        
		        	$('#taggedUserList').css('display','block');
		        	$('#arrowtagUserPermanent').css('display','block');
				}
				
			});
		 
	 });
	 
	  $.getJSON("GetPhotoComments", {inputQueryString : ppId}, function(datas) { // Do an AJAX call
		// alert(" ----- data comment" + datas);
		// alert(" data comments " + datas);
		 
	  	  mainDivStr='';
	  
	  	 $.each(datas, function(k,item){
	  		//alert(" comment item " + item);
	  		
	  		var key=item.id;
			  var photo_id=item.photoId;
			  var commentUserId= item.commmentUserId;
			  var commentUserName =item.commentUserName;
			  var commentUserPhoto = item.commentUserPhoto;
			  var commentDate =item.commentDate;
			  var commentDesc = item.commentDesc;
			  var timeline = item.timeInStr;
			  var div='';
			  var pph='';
			  if(commentUserPhoto==null || commentUserPhoto==""){
				  pph='images/person_icon.jpg';
			  }else{
				  pph=bPath+commentUserPhoto;
			  }
			 // alert(" comment user id " + commentUserId); onmouseout="hidedeleteButton(\''+key+'\')" onmouseover="showdeleteButton(\''+key+'\')"
			  div='<div id="'+key+'" class="commentPanel" style="width:95%;"  align="left" onmouseout="hidedeleteButton(\''+key+'\')" onmouseover="showdeleteButton(\''+key+'\')">';

			  div=div+'<div  style="display:table-cell; width:5%;"  cellpadding="0" cellspacing="0">';
			  div=div+'<img src="'+pph+'" width="35px" class="CommentImg" style="float:left;" alt="" />'+
			  			' </div>';
			  
			  div=div+'<div style="display:table-cell;width:95%;vertical-align: top; " > ';
			  div=div+'<div style="display:table;width:100%;vertical-align:top;"> ';
			
			  div=div+'<div style="display:table-cell;width:95%;vertical-align: top;" align="left"> ';
			  div=div+'<label style="backgrond-color:blue;"   class="postedComments" >';
			  div=div+'<span   class="photoNameHeader" id="user_'+commentUserId+''+key+'" onmouseout="hideUserInfo()" onmouseover="getUserInfo(\''+commentUserId+'\',\''+key+'\')">'+commentUserName+'</span> &nbsp;';
			  div=div+ commentDesc;
					
			  div=div+'</label>';
			  div=div+'<br clear="all" />';
			  div=div+'<span style="margin-left:3px; color:#666666; font-size:11px">';
			  div=div+timeline;
			  div=div+'</span>';
			  div=div+'</div>';	  
			  
			  div=div+'<div style="display:table-cell;height:100%;vertical-align:top;float:right;width:10%;" align="right">';
			  div=div+'<div style="width:100%;height:100%;vertical-align: top;float:right;" id="delete_'+key+'"  class="deleteButtonHidden" align="right">';
			  div=div+'<a href="#" style="text-decoration:none;height:100%;" onclick="deleteComments(\''+key+'\')") ><span class="deleteButtonCmm" style="height:100%;">X</span></a>'+
			  			'</div>';
			  div=div+'</div>';
			  div=div+'</div>';
			  div=div+'</div>';
			  //div=div+'</td><td width="4%" valign="top" style="background-color:yellow;">';
			 
			//  div=div+'</td></tr></table>';
			  div=div+'</div>';
			 // alert(" div " + div);
				mainDivStr=mainDivStr+div;
	  	 });
		// alert(" ------------- exit " + mainDivStr);
		 mainDivStr=mainDivStr;
		  mainDivStr=mainDivStr+'';
		  
		 
		  var pidC=document.getElementById("photoIdComment");
		  pidC.value=ppId;
		  var cUid=document.getElementById("commentedUserId");
		  cUid.value=empId;
		  if(mainDivStr==''){
			 // alert(" empty "+$("#myImg").height());
			 // $("#middleSection").css("height",10);
			// $("#mainDivId").css({'height':$("#myImg").height()});if
			if($("#myImg").height()>600)
				 $("#commentinfoData").css("height",400);
			else
				 $("#commentinfoData").css("height",$("#myImg").height()-150);	
		  }else{
			 // alert(" data  " + mainDivStr);
		  }
	  	mainDiv.innerHTML=mainDivStr;
	  	//alert(" ++++++++++ complete exit " + mainDivStr);
	  });
  }
  
  </script>
<script type="text/javascript">
var likeBoxText="";
function liked(){
	var divIID=document.getElementById("toggleLike");
	  var photoId= document.getElementById("photoIdHidden").value;
	  var empId='<%=emp.getId()%>';
	 
	 // alert(" queryString " );
	  var divIIDs=document.getElementById("toggleLike");
		var dd=divIIDs.innerHTML;
		//alert(" click on " + dd);
		if(dd=='Unlike'){
			var ddd=document.getElementById("likeKey");
			dd=dd+'~@~'+ddd.value;
		}
		 var queryString=photoId+'~@~'+empId+'~@~'+dd;
		//alert(" dd " + dd);
	  $.getJSON("createLike", {inputQueryString : queryString}, function(datass) { // Do an AJAX call
			
		  $.each(datass, function(i,item){
			  
				 // dateCreated=item.date;
				//  desc=item.description;
				  
				  divIID.innerHTML=item.likeStatus;
				  var ddd=document.getElementById("likeKey");
				  ddd.value= item.likeKey;
				  getDescription(photoId,document.getElementById("createdOnHidden").value,document.getElementById("ownerIdHidden").value);

				  
		  });
				  
			
			divIID.innerHTML=datass;
		  
		});
	  
	  
}
function getDescription(id,createdOn,ownerId){
	var queryString=id;
	var downloadLInk = document.getElementById("downloadLink");
	var dataaaaaa='<a href="DownloadImage?imageId='+id+'"  style="text-decoration: none;color:white;font-size: bold;" >Download</a>';
	downloadLInk.innerHTML=dataaaaaa;
	commentsForPhoto(id);
	
	var isFound=0;
	//alert("hi");
  	$.getJSON("GetPhotoDescription", {inputQueryString : queryString,createdOn:createdOn,ownerId:ownerId}, function(datas) { // Do an AJAX call
 	   	   
  		var dateCreated="";
  	   	var fullDate="";
  	   	var desc="";
  	   	var likeCount=0;
  	   	var ownerId="";
  	   	var likedUserNames="";
  	   
  	 	var dddd=document.getElementById("likeKey");
  	 	var actualId='<%=emp.getId()%>';
  	 	dddd.value="";
	  	$.each(datas, function(i,item){
		  
		  dateCreated=item.date;
		  desc=item.description;
		  fullDate = item.fullTime;
		  ownerId= item.owner;
		  $.each(item.likedList,function(j,likes){
				if(likes.likedUserId==actualId){
					isFound=1;
				  	var divIIDs=document.getElementById("toggleLike");
					divIIDs.innerHTML='Unlike';
					var ddd=document.getElementById("likeKey");
					ddd.value=likes.id;
				}else{
					likeCount=likeCount+1;
					
				}
				var photoPath="";
			 	if(likes.userPhotoPath==null || likes.userPhotoPath=="")
				 	photoPath='images/person_icon.jpg';
			 	else
				 	photoPath=likes.userPhotoPath;
			 	//alert(" photopath " + photoPath);
			  	var str='<div class="likeDivStyle"><img src="'+photoPath+'" width="50px" height="50px"/> &nbsp;'+likes.userName+'</div>';
			  	likedUserNames=likedUserNames+str;
		  });
	  });
	  //	alert("hiss")
	  likedUserNames='<div class="likeHeader">People who likes this</div>'+likedUserNames;
	  var popUpDiv= document.getElementById("simplediv");
	  popUpDiv.innerHTML=likedUserNames;
	  var divId= document.getElementById("DescriptionDiv");
  		var timeDiv= document.getElementById("timeDiv");
  		
  		timeDiv.innerHTML='<span class="datestyleforPhoto" title="'+fullDate+'">'+dateCreated+'</span>';
  		var str='<table width=\"100%\"><tr><td ><span>'+desc+'</span></td></tr></table>';
		divId.innerHTML=desc;
		
		var divIIDss=document.getElementById("toggleLike");
		if(isFound==0)
			divIIDss.innerHTML='Like';
		
		var dinfo=document.getElementById("likedInfo");
		var sttt="";
		if(isFound==0){
			sttt= likeCount + ' Likes';
		}else{
			sttt= ' You ';
			if(likeCount>0)
				sttt+='and Other '+likeCount;
		}
		
		dinfo.innerHTML=sttt;
	});
  
  
  }
  
  // for comment sections
  
  
</script>
<script type="text/javascript">
      var img = 0;    // Current image
      var plength= '<%=list.size() %>';
      var plist= new Array();
      var i=-1;
      var currentId=0;
    
      
    <% 
    
    for(PhotoInfo p:list){
    	    
    	%>
    	var found=0;
    	//alert(" hi ");
    	var tt = '<%=p.getPhotoPath()%>';
    	//alert(" tt is  " + tt + " value is  " + '<%=path%>');
    	var hes = '<%=p.getHeight()%>';
    	var wes = '<%=p.getWidth()%>';
    	if(tt=='<%=path%>'){
    		i=i+1;
    		found=1;
    		currentId=i;
    	}else{
    		i=i+1;
    	}
    	
    	if(hes>wes)
    			plist[i]= '<%= PropertyReader.getValue("photoAlbumPath",request)%>'+'<%=p.getPhotoPath().replaceFirst("a_","b_")%>'+'~~~'+'<%=p.getIdPhotoInfo()%>'+'~~~'+'<%=p.getCreaedOn()%>'+'~~~'+'<%=p.getOwnerId()%>';
    		else
    			plist[i]= '<%= PropertyReader.getValue("photoAlbumPath",request)%>'+'<%=p.getPhotoPath().replaceFirst("a_","b_")%>'+'~~~'+'<%=p.getIdPhotoInfo()%>'+'~~~'+'<%=p.getCreaedOn()%>'+'~~~'+'<%=p.getOwnerId()%>';
    	console.log(plist[i]);
    	<%
    }
    %>
      
			
     
    </script>
    
    <script type="text/javascript">
    function switchImage(i,option)
    {
    	backButtonHide();
    	closeMeTag();
    	appendNewPhoto();
    
    /* 	$('#myImg').removeAttr('height');
    	$('#myImg').removeAttr('width');
    	$('#imageFrame').removeAttr('height');
    	$('#imageFrame').removeAttr('width');
    	$("#mainDivId").removeAttr('height');
    	$("#mainDivId").removeAttr('width'); */
    	//alert("test withd "+ $("#myImg").width());
    	$("#myImg").hide();
		// previous clicked
		if(option==-1){
			if(currentId==0){
				currentId=plength-1;
				
			}else{
				currentId=currentId-1;
			}
			$("#myImg").attr("src",plist[currentId].split("~~~")[0]).load(function() {
				  //alert("cool "+$("#myImg").width());
				//$(this).hide();
				updateIMageData();
				});
			//i.src=plist[currentId].split("~~~")[0];
			//alert(" value " + plist[currentId].split("~~~")[0]);
			getDescription(plist[currentId].split("~~~")[1],plist[currentId].split("~~~")[2],plist[currentId].split("~~~")[3]);
			document.getElementById("photoIdHidden").value=plist[currentId].split("~~~")[1];
			document.getElementById("createdOnHidden").value=plist[currentId].split("~~~")[2];
			document.getElementById("ownerIdHidden").value=plist[currentId].split("~~~")[3];
		}
		// next clicked
		else if(option==1){
			if(currentId==plength-1){
				currentId=0;
			}else{
				currentId=currentId+1;
			}
			//i.src=plist[currentId].split("~~~")[0];
			
			$("#myImg").attr("src",plist[currentId].split("~~~")[0]).load(function() {
				  //alert("cool "+$("#myImg").width());
				  //this.hide();
					updateIMageData();
				});
			getDescription(plist[currentId].split("~~~")[1],plist[currentId].split("~~~")[2],plist[currentId].split("~~~")[3]);
			document.getElementById("photoIdHidden").value=plist[currentId].split("~~~")[1];
			document.getElementById("createdOnHidden").value=plist[currentId].split("~~~")[2];
			document.getElementById("ownerIdHidden").value=plist[currentId].split("~~~")[3];
		}
		//updateIMageData();
		var widdd = $("#myImg").width();
		var windowWidthIs = $(window).width();
		//alert(" image " + widdd  + " window size " + windowWidthIs); 
		$("#nextButton").css("left",widdd-30);
		  document.getElementById('backButton').style.visibility='hidden';
		  $("#myImg").show();
		  updateDownloadBox();
	  }
    </script>
    <script type="text/javascript">
    windowWidth1 = '<%=winWid%>';
	windowHeight1= '<%=winHie %>';
    </script>
    <script type="text/javascript">
    function updateIMageData(){
    	var frame12 = $('#popupContact', window.parent.document);
    	 
       		var flag=0;
        	//var popupHeight = 300;
        	//var popupWidth = 500;
        	var popupHeight = $("#myImg").height();
        	var popupWidth = $("#myImg").width();
        	//imageDisplay
        	if(popupHeight<300){
        		flag=1;
        		popupHeight=popupHeight+300;
        	}
        	if(popupWidth<600){
        		flag=1;
        		popupWidth=popupWidth+250;
        		$("#myImg").css({'left':125});
        	}
        	
        	//centering
        	//var windhe = 400/2;//-popupHeight/2;
        	var  windhe=windowHeight1/2;
        	var d1 = parseInt(windowHeight1);
        	var d2 = parseInt(windowWidth1);
        	var d3= parseInt(popupHeight);
        	var d4= parseInt(popupWidth+300);
        	if(popupHeight<600){
        		popupHeight=600;
        	}

     	$("#imageFrame").css({'width':popupWidth});
     	$("#imageFrame").css({'height':popupHeight});
    
     	if(flag==1){
     	//	alert("2");
     		
     	
     	//	$("#imageDisplay").css({'vertical-align':middle});
     	}
     	
     	var frame2 = $('body', window.parent.document);
    	var frame = $('#popupContact', window.parent.document);
        //     alert(" height " + frame2.scrollTop());
            frame.height(popupHeight);
            frame.width(popupWidth+300);
            
         //  alert(" wodm " + windowHeight1);
        	var windhe = windowHeight1/2;//-popupHeight/2;
        	frame.css({
        		"position": "absolute",
        		"top": frame2.scrollTop()+20,
        		"left": windowWidth1/2-(popupWidth+300)/2
        	});
        	
        	$("#mainDivId").css({'width':popupWidth+100});
        	$("#mainDivId").css({'height':popupHeight});
         	$("#mainDivId").css({'vertical-align:':'middle'});
        	//alert(" image withd toset next button " + rightPos);
        	$("#nextButton").css("left",popupWidth-30);
        	$("#myImg").css({'vertical-align:':'middle'});
        	$("#myImg").show();
        	$("#rightSideSlider").css({'width':300});
        	$("#rightInnerwidth").css({'width':300});
        	$("#optionBlockId").css({'left':popupWidth+250});
        	
			//updateDownloadBox();
			

        	var obj = document.getElementById("myTagPoint");
        	var posX = obj.offsetLeft;
        	var posY = obj.offsetTop;

        	while(obj.offsetParent){
        	posX=posX+obj.offsetParent.offsetLeft;
        	posY=posY+obj.offsetParent.offsetTop;
        	if(obj==document.getElementsByTagName('body')[0]){break;}
        	else{obj=obj.offsetParent;}
        	}
        	var p = $( "#myTagPoint" );
        	var position = p.position();
        	var offset = p.offset();
        	console.log( "left: off " + offset.left + ", top: off " + offset.top );
        	console.log( "left: " + position.left + ", top: " + position.top );
        	posX=  position.left;
        	posY=position.top;
        	/* $('#arrowtagUserPermanent').css('position','absolute');
        	$('#arrowtagUserPermanent').css('left',posX+490);
        	$('#arrowtagUserPermanent').css('top',posY-25);
        	$('#arrowtagUserPermanent').css('display','block'); */
        	
        	
        	
        	$('#showTaggerUsers').css('z-index','100');
        	$('#taggedUserList').css('z-index','101');
        	$('#arrowtagUserPermanent').css('position','absolute');
        	$('#arrowtagUserPermanent').css('left',posX);
        	$('#arrowtagUserPermanent').css('top',posY-12);
        	
        	$('#arrowtagUserPermanent').css('z-index','1');
        	
    }
    </script>
    
      <script>
  
  function backButtonShow(){
	  
	//alert(" show me");
	  document.getElementById('backButton').style.visibility='visible';
  }
  function backButtonHide(){
	  
  		//alert("hide me");
	  document.getElementById('backButton').style.visibility='hidden';
  }
  function hideDownload(){
	  document.getElementById('downloadInfo').style.display='none';
  }
  function nextButtonShow(){
	  
  		//alert(" vidisi");
  		  
	  document.getElementById('nextButton1').style.display='block';
  }
  function nextButtonHide(){
	  document.getElementById('nextButton1').style.display='none';
  }

  </script>
  <script type="text/javascript">
  function appendNewPhoto(){
	  var blocks = document.getElementById("imageDisplay");
	  var str='';
	  str=str+'<div id="imageFrame" style="width: 100%;height:100%;opacity:1.0;z-index:0;vertical-align:middle;">';
		
	  str=str+'  <div style="position:absolute;opacity:1.0;z-index:1; height: 100%; vertical-align: middle;" onmouseover="backButtonShow()" onmouseout="backButtonHide()" >';
	  str=str+'		<input type="button" id="backButton" name="txtPre" style=" background:url(images/p_icon.PNG) center no-repeat; width:30px; height:100%;  border: none;" onmouseover="this.style.cursor=\'pointer\';" onclick="switchImage(document.getElementById(\'myImg\'),-1)"/>';
	  str=str+'</div>';
	  str=str+'<div id="nextButton" align="right" style="position:absolute;opacity:1.0;z-index:5; float:right;width:30px; vertical-align: middle;height: 100%;" align="right" onmouseover="nextButtonShow()" onmouseout="nextButtonHide()" >';
	  str=str+'	<input type="button" id="nextButton1" name="txtNext"  style="background:url(images/n_icon.png) center no-repeat; border: none;width:30px; height:100%;display: none; "  onmouseover="this.style.cursor=\'pointer\';" onclick="switchImage(document.getElementById(\'myImg\'),1)"/>';
	  str=str+'</div>'; 
	  str=str+'<div class="caption" id="imageDivEnlarge" style="height: 100%;width: 100%;vertical-align:middle;position:relative;"  align="center">';
	  str=str+'	<img id="myImg" alt="test"  border="0"  align="center"   src="" style="vertical-align:middle;position:absolute;top:0;bottom:0;left:0;margin:auto;"/>';
	  str=str+'<div style="text-align: left;">';
				
				
	  str=str+'			<table height="60px" align="left">';
	  str=str+'				<tr>	';
	  str=str+'					<td style="padding-left: 50px;">';
	  str=str+'						<span id="downloadLink" name="downloadLink">';
									

	  str=str+'						</span>';
	  str=str+'					</td>';
	  str=str+'				</tr>';
	  str=str+'			</table>';
				
				
				
	  str=str+'		</div>';
	  str=str+'</div> ';
		
	
	  str=str+'</div> '; 
	  blocks.innerHTML=str;
  }
  </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body id="myDisplayBody" marginheight="0" marginwidth="0" style="width: 100%;height:100%;opacity:1.0;text-align: center;vertical-align: middle;"  >
	<input type="hidden" name="photoIdHidden" id="photoIdHidden"/>
	<input type="hidden" name="createdOnHidden" id="createdOnHidden"/>
	<div id="optionBlockId" class="optionBlock">
		<a href="#" onclick="downloadPopup()"><img src="img/info.jpg" width="20px" height="20px"/></a>
	</div>	
	<div id="downloadInfo" class="displayStyleDownoad" Style="width:250px;position: absolute;" ></div>
	<div id="mainDivId" onclick="hideDownload()" style="width: 100%;float:left; display: table;opacity:1.0;height: 100%;padding-bottom: 0;padding-left: 0;padding-right: 0;padding-top: 0;" align="center">
		
		<div id="imageDisplay"  style="display: table-cell; width: 75%;opacity:1.0;z-index:1; height: 100%;vertical-align:middle; background-color: black;">
			
			<!--  here append will happen -->
		</div>
		
		
		
		<div id="rightSideSlider" style="display: table-cell; height: 100%;vertical-align: top;">
		
		
		<div id="rightInnerwidth" class="headerStyleDivTop" style="height: 100%;width: 100%;" >
			
			<div style="display: table;width: 100%;height: 10%;" >
		
			 <div class="headerStyleDivTop" style="width: 100%;" >
				<div style="display: table-cell;vertical-align: top;text-align: left;width: 10%;padding-left: 5px;" align="left">
					<%if(photoOwnerInfo.getImage()==null ){ %>
						<img src="iamges/person_icon.jpg" style="width: 50px;" height="50px"/>
					<%}else{ %>
					
						<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%=photoOwnerInfo.getImage().getPhotoPath().replaceFirst("a_","h_")%>" style="width: 50px;" height="50px"/>
					<%} %>
				</div>
				 <div style="display: table-cell;padding-left: 5px;width: 80%;" align="left">
					
					<div style="display: table;width: 100%;">
					<div style="display: table-cell;width: 80%;">
					
					<span class="photoNameOwnerHeader"><%=photoOwnerInfo.getEmpName() %></span><br/>
					 <div id="timeDiv" align="left" style="display: table-row;"></div>
					 <div style="display: table-row;">
						
					</div>
				
					</div>
				
					
					
					</div>
				</div>  
			</div> 
				
				<div id="showTaggerUsers" style="background-color: white;padding: 4px 4px 4px 4px;z-index: 100;float:left;">
					<div id="taggedUserList" class="showTaggerUsers" style="padding-bottom:10px;word-wrap: break-word;display:block;float:left;width: 290px"></div>
				</div>
				<div style="vertical-align: top;">
					<table align="left" >
						<tr>
							<td>
								<a  class="likeTextStyle" onclick="liked()">
							
									<span id="toggleLike" class="likeTextStyle"  name="toggleLike">
										
									</span> 
									
									<input type="hidden" id="likeKey"  name="likeKey"/>
									 
								</a>
								.
							</td>	
								
							<td>
								<a  class="likeTextStyle"><span onclick="updateHiddenBox(plist[currentId].split('~~~')[0],plist[currentId].split('~~~')[1])">share</span></a>
								.
							</td>
							<td>
								<a  class="likeTextStyle"><span id="myTagPoint" onclick="tagPhoto(plist[currentId].split('~~~')[0],plist[currentId].split('~~~')[1],'<%=basePath%>')">Tag</span></a>
								.						</td>
							<td>	
								<a  class="likeTextStyle"><span onclick="commentFocus()">Comment</span></a>
							</td>
						</tr>
						
					</table>
					
					</div>
					<div  id="arrowtagUserPermanent" style="width:10px;height:10px;background-color: #E1F0F5;-webkit-transform: rotate(45deg);  /* Chrome, Safari 3.1+ */
    -moz-transform: rotate(45deg);  /* Firefox 3.5-15 */
      -ms-transform: rotate(45deg);  /* IE 9 */
        -o-transform: rotate(45deg);  /* Opera 10.50-12.00 */
         transform: rotate(45deg);"></div>
						
					</div>
		<div id="commentinfoData" style="height: 78%;;width: 100%;" align="left">
			<!--  end of first section -->
			<div id="middleSection" style=" width:100%;height: 100%;overflow: auto;display: table-row;" align="left">
			
		 	<div class="headerStyleDivTop" style="vertical-align: top;overflow-x:auto;width: 100%; " align="left"> 
				 <div style="display: table-row; vertical-align: top;height: auto;text-align: left;width: 100%;" align="left">
					<div align="left" style="vertical-align: top; height: auto;padding-left: 2px;display: table-cell;text-align: left;width: 100%;height: auto;">
						<div id="DescriptionDiv" style="text-align: left;width: 100%;height: auto;" align="left" >
						
						</div>
					</div>
				</div> 
				
			 	<div style="display: table-row;vertical-align: top;width: 100%;display: block;" align="left"> 
					<div style="display: table-cell;border-top-color: #EEEEEE;border-top-style:ridge ;border-top-width: 1px;
					border-bottom-color: #EEEEEE;border-bottom-style:ridge ;border-bottom-width: 1px;width: 100%;display: block;" >
						<img src="img\icon_like.png" style="padding-top: 2px;"/>
					
					<span id="likedInfo" style="width: 100%;" class="likeInfo" name="likedInfo"  onmouseover="showLikedUsers()" onmouseout="hideLikedUsers()">
					</span>
					  <div id="simplediv" style="background-color:#BDC5D7;border:1px solid #8C8C8C;display:none; height:auto ;position:absolute; width: 100%;">
					</div>													
					</div>
					<div id="userInfo" Style="width:250px; ;position: absolute;" ></div>
				</div> 
				<div style="display: table-row;vertical-align: top;width: 100%;background-color: orange;height: 100%;" align="left">
					<div id="commentDivHeader" class="commentMainDivStyle" style="width: 100%;" onmouseout="hideUserInfo()" >
					  										
					</div>
				</div>
			 </div>
			 </div>
			 </div>
			 <input type="hidden" id="photoIdComment" /> 
					  <input type="hidden" id="commentedUserId" />
			 <div style="display: table-row;width: 100%;height: 10%; vertical-align: top;overflow: auto;background-color: blue;"  align="left" >
			 
			  <div  class="headerStyleDivTop" style="vertical-align: top; height: 100%;width: 100%;" align="left" >
			 	
			 	  <div class="newComment" style="vertical-align: top; height: 100%;width: 100%;vertical-align: text-top;" align="left" >
					 								
					 
					   <table align="left" border="0" cellpadding="0" class="borderForCOmmment" cellspacing="0" style="vertical-align: top;height: 100%;" >
					  	<tr valign="top" align="left">
					  		<td valign="top" style="width:50px;">
					  		<%System.out.println(" photoOwnerInfo "+ photoOwnerInfo.getImagePath()); %>
					  			<%if((emp!=null && emp.getImage()==null)){ %>
									<img src="iamges/person_icon.jpg" style="width: 50px;" height="40px"/>
								<%}else{ %>
								
									<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%=emp.getImage().getPhotoPath().replaceFirst("a_","h_")%>" style="width: 50px;" height="40px"/>
								<%} %>
					  		</td>
					  		<td style="padding-left: 2px;" valign="top" >
					  			<!-- <input type="text" id="commentText" /> -->
					  			<textarea id="commentText" name="commentText" class="comment_empty">Write a comment</textarea>
					  			
								
					  		</td>
					  		<td style="padding-left: 5px;">
					  			<input class="Button" width="20px" type="button" id="button1" onclick="createCommentsTest()"  style="display: block; width: 40px;height: 20px;" value="Post"/>
					  		</td>
					  		
					  	</tr>
					  </table> 
				</div> 
			 </div> 
			</div>
		
		
		</div>
	</div>
	</div>
	<!--  here is new popup -->
	<div id="popupContact2" class="borderStyleShare" style="width:700px;height:300px;z-index: 25;overflow: auto;" align="right">
		
		<!-- photoList.jsp?albumId=63&ownerId=62 -->
		<iframe src="#" class="borderStyleShare" style="z-index: 10;" width="100%" scrolling="no" height="100%" id="myIFrame2" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup2" style=" height: 100%;"></div> 
	<div   style="border-style: outset ;border-color: black;width:50px;height:50px;background-color: black;-webkit-transform: rotate(45deg);  /* Chrome, Safari 3.1+ */
    -moz-transform: rotate(45deg);  /* Firefox 3.5-15 */
      -ms-transform: rotate(45deg);  /* IE 9 */
        -o-transform: rotate(45deg);  /* Opera 10.50-12.00 */
         transform: rotate(45deg);" id="userTagArrow"></div>
	<div id="tagSection" class="tagClass">
		<table class="tagHeading">
			<tr>
				<td align="left">
					Tag this photo to users
				</td>
			</tr>
		</table>
			
		<div id="userInfo" class="rightarrowdiv"></div>
		<div id="userInfoPopup" class="leftarrowdiv" style="width:300px;"></div>
		<div style="display: table; height: 427px;overflow: scroll;">
				<div style="display: table-cell ;width:70%;background-color: #EAEAEA;vertical-align: top;">
					<div id="userList" style="height: 420px;" ></div>
				</div>
				<div style="display: table-cell ;width:30%;background-color: white;">
				<div id="selectedUsersScroll" style="height: 420px;border-left-color: gray;border-left-style: ridge;border-left-width: thin;">
					<div id="selectedUsers" style="height: 400px;"></div>
					</div>
				</div>
		</div>
		<table class="tagHeading">
			<tr>
				<td align="left">
					<table cellpadding="0" cellspacing="0" width="100%"><tr><td align="right" style="padding-right: 10px;"> <table style="background-color: #CDD8E5;" align="right" cellpadding="0" cellspacing="0"><tr><td class="buttonStyleInTagl" id="tagMe" onclick="addMyTag(plist[currentId].split('~~~')[1])">Tag</td><td class="buttonStyleInTagr" onclick="closeMeTag()" id="cancelMe">Cancel</td></tr></table> </td></tr></table>
				</td>
			</tr>
		</table>
	</div>
	<!--  end of new popup -->
</body>
</html>

<script type="text/javascript">


addScroll();
appendNewPhoto();
var divHeight=$(window).height();
var divWidth=$(window).width();
document.getElementById('backButton').style.visibility='hidden';
document.getElementById('nextButton1').style.display='hidden';

//alert("****   " + plist[currentId].split("~~~")[0]);
//iii.src=plist[currentId].split("~~~")[0];
//$("#myImg").attr("src", plist[currentId].split("~~~")[0]);
//load(function() {
				  //alert("cool "+$("#myImg").width());
				  //this.hide();
					//updateIMageData();
				//});
				if(found=0){
					alert(" this image has been removed ");
					disablePopup();
				}
$("#myImg").attr("src",plist[currentId].split("~~~")[0]).load(function() {
	  //alert("cool "+$("#myImg").width());
	  //this.hide();
		updateIMageData();
	});
//$("#myImg").attr("src",plist[currentId].split("~~~")[0]);
//updateIMageData1();

//var imageWidth = $("#myImg").width();

getDescription(plist[currentId].split("~~~")[1],plist[currentId].split("~~~")[2],plist[currentId].split("~~~")[3]);
document.getElementById("photoIdHidden").value=plist[currentId].split("~~~")[1];
document.getElementById("createdOnHidden").value=plist[currentId].split("~~~")[2];
document.getElementById("ownerIdHidden").value=plist[currentId].split("~~~")[3];

//updateDownloadBox();

</script>