<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="com.pro.emp.dao.LikeTableDAO"%>
<%@page import="com.pro.emp.domain.LikeTable"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.domain.PhotoInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>

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
if(request.getParameter("albumId")!=null){
	albumId=request.getParameter("albumId");
}
if(request.getParameter("ownerId")!=null){
	ownerId=request.getParameter("ownerId");
}
if(request.getParameter("path")!=null){
	path=request.getParameter("path");
}
//System.out.println("  path is " + path);
EmpInfo photoOwnerInfo=Util.getEmployeeById(ownerId);


List<PhotoInfo> list= Util.getAllPhotoByAlbumId(albumId);
//System.out.println(" listi " + list.size());
String basePath=PropertyReader.getValue("photoAlbumPath",request);

//System.out.println(" hiiiii ");
%>




<html>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all"></link>
  <script type="text/javascript" src="js/jquery4.1.min.js"></script>
  <script type="text/javascript" src="js/popup.js"></script>
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
		  $('#userInfo').css('left',posX);
		  $('#userInfo').css('top',posY);
		  $('#userInfo').css('box-shadow','0px 2px 2px 0px black');
		 
		  $('#userInfo').css('display','block');
		
		  
		  userDiv.innerHTML=div;
		  //alert(" div is " + div);
		  //sdf
		  
			 });
			 
  }
  </script>
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
 function createCommentsTest(){
	//alert(" comment craer");
	var bPath='<%=basePath%>';
	  var pId= document.getElementById("photoIdComment").value;
	  var commentedUserId= document.getElementById("commentedUserId").value;
	  var text= document.getElementById("commentText").value;
	  if(text=="" ||text=="Write a comment")
		  return;
	// alert(" in craete comment " + pId + ' : ' + commentedUserId + ' : ' + text);
	  //var url='CreateComment?photoId='+photoId+'&commentUserId='+commentedUserId+'&text='+text;
	  $.getJSON("CreateComment", {'photoId' :pId,'commentUserId':commentedUserId,'text':text}, function(datas) { // Do an AJAX call
		// alert(" data s" + datas);
		 // $.each(datas, function(l,items){
			  var ph=datas.commentUserPhoto;
			  if(ph=='')
				  ph='images/person_icon.jpg';
			  else
				  ph=bPath+ph;
			  var likedId=datas.id;
			  var key=datas.id;
		  
			  
			  // here is data
			   div='<div id="'+key+'" class="commentPanel" align="left" onmouseout="hidedeleteButton(\''+key+'\')" onmouseover="showdeleteButton(\''+key+'\')">';
			  div=div+'<table cellpadding="0" cellspacing="0"><tr><td>';
			   div=div+'<table cellpadding="0" cellspacing="0"><tr><td valign="top">';
			  div=div+'<img src="'+ph+'" width="35" class="CommentImg" style="float:left;" alt="" /> </td><td>';
			  div=div+'<label class="postedComments">';
			  div=div+'<span class="photoNameHeader">'+datas.commentUserName+'</span> &nbsp;';
			  div=div+ text;
					
			  div=div+'</label>';
			  div=div+'<br clear="all" />';
			  div=div+'<span style="margin-left:3px; color:#666666; font-size:11px">';
			  div=div+datas.timeInStr;
			  div=div+'</span></td></tr></table>';
			  div=div+'</td><td width="10px" valign="top">';
			  div=div+'<div align="right" id="delete_'+key+'" width="100%" class="deleteButtonHidden">';
			  div=div+'<a href="#" onclick="deleteComments(\''+key+'\')") ><span class="deleteButtonCmm">X</span></a></div>';
			  div=div+'</td></tr></table>';
			  div=div+'</div>';
			  // end of data
			  $('#commentDivHeader').append(div);
			  
			 // break;
			  
		 // });
		  
	  });
	  document.getElementById("commentText").value="";
	 // alert("e xt ");
 }

 </script>
<script type="text/javascript" > 
$("#backgroundPopup").css({
	"opacity": "0.9"
});
$("#backgroundPopup").fadeIn("slow");
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
  
  <script>
  var likeBoxText="";
  function liked(){
	  
	  var photoId= document.getElementById("photoIdHidden").value;
	  var empId='<%=emp.getId()%>';
	 
	  //alert(" queryString " + queryString);
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
			  
				 // alert(" item " + item.id);
				  //dateCreated=item.date;
				  //desc=item.description;
				  var divIID=document.getElementById("toggleLike");
				  divIID.innerHTML=item.likeStatus;
				  var ddd=document.getElementById("likeKey");
				  ddd.value= item.likeKey;
				  getDescription(photoId);
		  });
				  
			
			//divIID.innerHTML=datass;
		  
		});
	  
	  
  }
  function getDescription(id,createOn){
	//  alert(" hhhh");
	 // alert(" id " + id);
	  var queryString=id;
	  commentsForPhoto(id);
	 var isFound=0;
  $.getJSON("GetPhotoDescription", {inputQueryString : queryString,createdOn:createOn}, function(datas) { // Do an AJAX call
	  //alert(" data " + datas);
 	   	   
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
		  
		 // alert(" item " + item.id);
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
					//likeCount=likeCount+1;
				}else{
					likeCount=likeCount+1;
					
				}
				var photoPath="";
			 if(likes.userPhotoPath==null || likes.userPhotoPath=="")
				 photoPath='images/person_icon.jpg';
			 else
				 photoPath=likes.userPhotoPath;
			  var str='<div class="likeDivStyle"><img src="'+photoPath+'" width="50px" height="50px"/> &nbsp;'+likes.userName+'</div>';
			  likedUserNames=likedUserNames+str;
		  });
		  
	  });
	  likedUserNames='<div class="likeHeader">People who likes this</div>'+likedUserNames;
	 // alert(" likedUserName " + likedUserNames);
	  //likeBoxText=likeUserName;
	  //alert(" likedUserName 2" + likedUserNames);
	  var popUpDiv= document.getElementById("simplediv");
	  popUpDiv.innerHTML=likedUserNames;
	  //alert(" likedUserName 3" + likedUserNames);
	  var divId= document.getElementById("DescriptionDiv");
  		var timeDiv= document.getElementById("timeDiv");
  		
  		timeDiv.innerHTML='<span class="datestyleforPhoto" title="'+fullDate+'">'+dateCreated+'</span>';
  		var str='<table width=\"100%\"><tr><td width=\"100%\"><span>'+desc+'</span></td></tr></table>';
		divId.innerHTML=desc;
		
		//var likeStr=datas.split("+-+")[1];
		//alert(" likstr " + likeStr);
		var divIIDss=document.getElementById("toggleLike");
		//alert(' is found ' + isFound);
		if(isFound==0)
			divIIDss.innerHTML='Like';
		
		var dinfo=document.getElementById("likedInfo");
		var sttt="";
		if(isFound==0){
			sttt= likeCount + ' Likes';
		}else{
			sttt= ' Me and Other '+likeCount;
		}
		
		dinfo.innerHTML=sttt;
		//alert("hi how2 " + id);
		
		//alert(" ^^^^^^^^^^^^ exit in main");
	});
  
  
  }
  
  // for comment sections
  
  

  
 
  
  </script>
  <script type="text/javascript">
  function commentsForPhoto(ppId){
	  var empId='<%=emp.getId()%>';
	  var bPath='<%=basePath%>';
	  var mainDiv=document.getElementById("commentDivHeader");
	 var mainDivStr='';
	 // alert(" in comment id " + ppId);
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
			 // alert(" comment user id " + commentUserId);
			  div='<div id="'+key+'" class="commentPanel" align="left" onmouseout="hidedeleteButton(\''+key+'\')" onmouseover="showdeleteButton(\''+key+'\')">';
			  div=div+'<table border="1" width="10px" cellpadding="0" cellspacing="0"><tr><td>';
			   div=div+'<table border="1" cellpadding="0" cellspacing="0"><tr><td valign="top">';
			  div=div+'<img src="'+pph+'" width="35" class="CommentImg" style="float:left;" alt="" /> </td><td width="10px">';
			  div=div+'<label class="postedComments">';
			  div=div+'<span class="photoNameHeader" id="user_'+commentUserId+''+key+'" onmouseout="hideUserInfo()" onmouseover="getUserInfo(\''+commentUserId+'\',\''+key+'\')">'+commentUserName+'</span> &nbsp;';
			  div=div+ commentDesc;
					
			  div=div+'</label>';
			  div=div+'<br clear="all" />';
			  div=div+'<span style="margin-left:3px; color:#666666; font-size:11px">';
			  div=div+timeline;
			  div=div+'</span></td></tr></table>';
			  div=div+'</td><td width="10px" valign="top">';
			  div=div+'<div align="right" id="delete_'+key+'" width="100%" class="deleteButtonHidden">';
			  div=div+'<a href="#" onclick="deleteComments(\''+key+'\')") ><span class="deleteButtonCmm">X</span></a></div>';
			  div=div+'</td></tr></table>';
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
	  	mainDiv.innerHTML=mainDivStr;
	  	//alert(" ++++++++++ complete exit " + mainDivStr);
	  });
  }
  
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
    	//alert(" hi ");
    	var tt = '<%=p.getPhotoPath()%>';
    	//alert(" tt is  " + tt + " value is  " + '<%=path%>');
    	if(tt=='<%=path%>')
    		currentId=i+1;
    	i=i+1;
    	plist[i]= '<%= PropertyReader.getValue("photoAlbumPath",request)%>'+'<%=p.getPhotoPath()%>'+'~~~'+'<%=p.getIdPhotoInfo()%>'+'~~~'+'<%=p.getCreaedOn()%>';
    	
    	<%
    }
    %>
      function switchImage(i,option)
      {
		// previous clicked
		if(option==-1){
			if(currentId==0){
				currentId=plength-1;
				
			}else{
				currentId=currentId-1;
			}
			i.src=plist[currentId].split("~~~")[0];
			//alert(" value " + plist[currentId].split("~~~")[0]);
			getDescription(plist[currentId].split("~~~")[1],plist[currentId].split("~~~")[2]);
			document.getElementById("photoIdHidden").value=plist[currentId].split("~~~")[1];
		}
		// next clicked
		else if(option==1){
			if(currentId==plength-1){
				currentId=0;
			}else{
				currentId=currentId+1;
			}
			i.src=plist[currentId].split("~~~")[0];
			getDescription(plist[currentId].split("~~~")[1],plist[currentId].split("~~~")[2]);
			document.getElementById("photoIdHidden").value=plist[currentId].split("~~~")[1];
			}
	  }
			
     
    </script>

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
			color:white;
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
			color:white;
}
.headerLinkBlock{
			 background-color: white; 
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
  
  <title>Photo album</title>
  <script>
  
  function backButtonShow(){
	  document.getElementById('backButton').style.visibility='visible';
  }
  function backButtonHide(){
	  document.getElementById('backButton').style.visibility='hidden';
  }
  function nextButtonShow(){
	  document.getElementById('nextButton').style.visibility='visible';
  }
  function nextButtonHide(){
	  document.getElementById('nextButton').style.visibility='hidden';
  }

  </script>
</head>
<body marginheight="0" marginwidth="0" style="width: 100%;height: 100%;">



		

		
	<input type="hidden" name="photoIdHidden" id="photoIdHidden"/>	
   
    <table cellpadding="0" border="0" cellspacing="0" height="100%" width="100%" style=" width: 100%; ">
    	<tr>
    		
    		<td width="870px"  height="610px" valign="middle">
    			<div style="position:absolute; height: 100%; vertical-align: middle;" onmouseover="backButtonShow()" onmouseout="backButtonHide()" >
    				<input type="button" id="backButton" name="txtPre" style=" background:url(images/btn_prev.jpg) center no-repeat; width:30px; height:600px;  border: none;" onmouseover="this.style.cursor='pointer';" onclick="switchImage(document.getElementById('myImg'),-1)"/>
    			</div>
    			<div align="right" style="position:absolute;left:806px; float:right;width:30px; vertical-align: middle;height: 100%;" align="right" onmouseover="nextButtonShow()" onmouseout="nextButtonHide()" >
    				<input type="button" id="nextButton" name="txtNext"  style=" position:absolute;background:url(images/btn_next.jpg) center no-repeat; border: none;width:30px; height:600px; "  onmouseover="this.style.cursor='pointer';" onclick="switchImage(document.getElementById('myImg'),1)"/>
    			</div>
    			
    			<img id="myImg"  src="" width="870px" align="middle" height="612px"/>
    			
    		</td>
    		<td valign="top"  align="left" height="100%" style="background-color: white;width: 500px;" >
    			     <table align="left" style="padding-left: 2px; " width="100%"  >
					  	<tr>
					  		<td width="100%" >
					  			<Table>
					  				<tr>
								  		<td align="left" valign="top" style="background-color: white;width:20px;">
								  			<% if(photoOwnerInfo.getImage()!=null){ %>
								  			<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%=photoOwnerInfo.getImage().getPhotoPath().replaceFirst("a_","g_")%>" style="width: 70px;" height="50px"/>
								  			<%}else{ %>
								  			<img src="images/person_icon.jpg" style="width: 70px;" height="50px"/>
								  			<%} %>
								  		</td>
								  		<td valign="top" align="left" width="100px" style="background-color: white;" >
								  			<span class="photoNameOwnerHeader"><%=photoOwnerInfo.getEmpName() %></span>
								  			
								  			<div id="timeDiv" align="left"></div>
								  			<div align="left">
								  				<a href="#" class="likeTextStyle" onclick="liked()">
													<!-- <img src="images/like.jpg" /> -->
													<span id="toggleLike" class="likeTextStyle"  name="toggleLike">
														
													</span>
													<input type="hidden" id="likeKey"  name="likeKey"/>
												</a>
								  			</div>
								  		</td>
						  			</tr>
						  		</Table>
						  	</td>
					  	</tr>
					  	<tr>
					  		<td width="100%" height="100%">
					  			<table width="100%" height="100%">
					  				<tr>
					  					<td class="imageDescBackGround" width="100%">
					  						<table width="100%">
					  							<tr>
					  								<td>
					  									<div id="DescriptionDiv" >
					 
					  									</div>
					  								</td>
					  							</tr>
					  							<tr>
					  							<td>
					  						<!-- <div> -->
					  							<table  cellpadding="0" cellspacing="0" width="100%">
					  								
					  								<tr>
					  									<td width="100%">
					  										<table width="100%" cellpadding="0" cellspacing="0" >
					  											<tr>
					  												<td width="100%" class="likeBarStyle">
					  													<table cellpadding="0" cellspacing="0" width="100%">
					  														<tr>
					  															<td width="2%">
					  																<img src="images\like.jpg" style="padding-top: 2px;"/>
					  															</td>
					  															<td width="98%">
					  																<span id="likedInfo" class="likeInfo" name="likedInfo"  onmouseover="showLikedUsers()" onmouseout="hideLikedUsers()">
					  													
								  													</span>
								  													<div id="simplediv" style="background-color:#BDC5D7;border:1px solid #8C8C8C;display:none; width:200px;height:auto ;position:absolute; ">
																					</div>
																					<div id="userInfo" Style="width:250px;height:auto ;position: absolute;" ></div>
					  															</td>
					  														</tr>
					  													</table>
					  													
					  														
					  												</td>
					  											</tr>
					  										</table>
					  									</td>
					  								</tr>
					  								<tr>
					  									<td width="100%;" >
					  									<!-- <form  name="commentedForm" method="get"> -->
					  										<table width="100%" cellpadding="0" cellspacing="0">
					  											<tr>
					  												<td width="100%" style="height: 360px;">
					  													<div id="commentDivHeader" style="height: 360px" class="commentMainDivStyle" onmouseout="hideUserInfo()" >
					  										
					  													</div>
					  													
					  												</td>
					  											</tr>
					  											<tr>
					  												<td style="background-color: green; position: absolute;top:510px;"  > 
					  													<div class="newComment" style="vertical-align: top;">
					  											
																					  <input type="hidden" id="photoIdComment" /> 
																					  <input type="hidden" id="commentedUserId" />
																					  <table border="0" cellpadding="0" cellspacing="0" style="vertical-align: top;">
																					  	<tr valign="top">
																					  		<td valign="top">
																					  			<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%=(emp.getImagePath()==null?"images\\person_icon.jpg":emp.getImage().getPhotoPath().replaceFirst("a_","g_")) %>" width="35px"/>
																					  		</td>
																					  		<td style="padding-left: 2px;" valign="top">
																					  			<!-- <input type="text" id="commentText" /> -->
																					  			<textarea id="commentText" name="commentText" class="comment_empty">Write a comment</textarea>
																					  			
																
																					  		</td>
																					  		<td valign="top" style="padding-left: 3px;">
																					  			<input class="Button" width="20px" type="button" id="button" onclick="createCommentsTest()" style="display: none; width: 40px;height: 20px;" value="Post"/>
																					  		</td>
																					  	</tr>
																					  </table>
																					  		
																				</div>
																		
					  												</td>
					  											</tr>
					  										</table>
					  										
					  										
																	  
					  									<!-- </form> -->
					  									</td>
					  								</tr>
					  							</table>
					  							</td>
					  							</tr>
					  							</table>
					  						<!-- </div> -->
					  					</td>
					  				</tr>
					  			</table>
					  		</td>
					  	
					  	</tr>
					  </table> 
					  <!-- <table>
					  	<tr>
					  		<td style="position:absolute; ;top:510px ;">
					  			
					  		</td>
					  	</tr>
					  </table> -->
    		</td>
    	</tr>
    </table>

<div id="backgroundPopup" class="backgroundPopup" style="height: 100%;"></div>
<div id="userInfo" Style="width:250px;height:auto ;" ></div>
</body>
</html>

