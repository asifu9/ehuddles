<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pro.emp.domain.PostLikeTable"%>
<%@page import="com.pro.emp.domain.PostComments"%>
<%@page import="com.pro.emp.domain.PostInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.util.PostUtil"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <link rel="icon" type="image/png" href="icon/browserIcon.png">
     <link href="css/myOwnPopup.css" rel="stylesheet" type="text/css" media="all"></link>
     <script type="text/javascript" src="js/jquery4.1.min.js"></script>
     <script type="text/javascript" src="js/jquery.textbox-hinter.js"></script>
     
     <script type="text/javascript">
     
     $(document).ready(function(){
   			alert(" hi ");
    		$('.feedInsertbox').tbHinter({
    			text: 'A hint fo this text ...',
    			styleClass: 'grey'
    		});
    	});
     </script>
    <script type="text/javascript">
    

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
  		alert(" in popup");
  		document.getElementById("myIFrame").src="";
  		var windowWidth2 = document.documentElement.clientWidth;
  		var windowHeight2 = document.documentElement.clientHeight;
  		var urrl='photoList1.jsp?albumId='+albumId+'&ownerId='+ownerId+'&path='+path+'&windowWidth2='+windowWidth2+'&windowHeight2='+windowHeight2+'&acutalWidth='+width+'&actualHeight='+height;
  		
  		alert(" url i s " + urrl);
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
  		alert("1");
  		centerPopup();
  		//load popup
  		loadPopup(albumId,ownerId,path,width,height);
  		
  	}
  	
    </script>
    
    
    
    
      <link href="css/PostCss.css" rel="stylesheet" type="text/css" media="all"></link>
        <title>Sanah Employee Management System </title>
		<%
			EmpInfo emp=null;
		
		HashMap<String,Boolean> roleMap=new HashMap<String,Boolean>();
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
				roleMap= EmployeeInfo.getRolesForEmpId(emp.getId());
			}
			else {
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
			String likeStr="";
			List<PostInfo> posts=null; //PostUtil.getAllPosts();
		
			
			String str="<input type=\"hidden\" value=\""+emp.getId()+"\" id=\"hiddenCurrentUser\" >" ;
			for(PostInfo p:posts){
				String likeStatus="Like";
				String likeId="";
				int totalLikes=0;
				String likemessage="";
				List<String> list=new ArrayList<String>();
				// get all likes
				int count=0;
				
				//likesinfo
				if(p.getLikes()!=null){
					totalLikes= p.getLikes().size();
					likeStr="<div>";
					for(PostLikeTable l:p.getLikes()){
						
						likeStr+="<div>"+l.getUserName()+"</div>";
						if(l.getLikedUserId().equalsIgnoreCase(emp.getId())){
							likeStatus="Unlike";
							likeId=l.getKey();
							
							
						}else{
							list.add(l.getUserName());
						}
					}
						if(likeStatus.equalsIgnoreCase("unlike")){
							likemessage="<span class=\"myNameStyle\" > You";
							int i=1;
							for(String ss:list){
								if(i<=2){
									likemessage+=", <a href=\"#\"  class=\"likeNameStyleLink\">"+ss+"</a>";
								}
								i+=1;
							}
							if(list.size()>2){
								likemessage+= " and "+(list.size()-2)+ " <a href=\"#\" class=\"likeNameStyleLink\">other</a> like this.</span>";
							}
						}else{
							int i=0;
							for(String ss:list){
								if(i<=2){
									if(likemessage.trim().equalsIgnoreCase("")){
										likemessage+="<span class=\"myNameStyle\" > <a href=\"#\" class=\"likeNameStyleLink\">"+ss+"</a>";
									}else{
										likemessage+=",  <a href=\"#\" class=\"likeNameStyleLink\">"+ss+"</a>";
									}
									i+=1;
								}
							}
							if(list.size()>2){
								if(!(list.size()-3==0))
								likemessage+= " and "+(list.size()-2)+ " <a href=\"#\" class=\"likeNameStyleLink\">other</a> like this.</span>";
							}
						}
				}
				//like info
				
				System.out.println(": likeStr " + likeStr);
				str=str+"<div id=\""+p.getKey()+"\"  onmouseOut=\"hideDeleteButton('"+p.getKey()+"')\" onmouseOver=\"showDeleteButton('"+p.getKey()+"')\" class=\"postSection\" width=\"100%\">";
				str+="<div class=\"postSectionInside\">";
				str+="<div id=\"delete_"+p.getKey()+"\" class=\"deletePostStyle\" onclick=\"deletePost('"+p.getKey()+"')\" ><img  src=\"img/deletePost.png\"/></div>";
				str+="<div class=\"imageSection\"> <img src=\""+p.getPostedByUserInfo().getImagePath()+"\" width='50px' height='50px'></div> ";
				str+="<div class=\"descSection\"> <span class=\"postedUserNameStyle\"> "+ p.getPostedByUserInfo().getEmpName() +"</span>:<br><span \"postDescriptionStyle\">" + p.getPostedDesc()+ "</span>";
				if(p.getPostedByPhotoInfo()!=null){
					System.out.println( " : " + PropertyReader.getValue("photoAlbumPath",request)+p.getPostedByPhotoInfo().getPhotoPath());
					str+="<br><table><tr><td><a href=\"#\" onclick=\"updateHiddenBox('"+p.getPostedByPhotoInfo().getIdPhotoAlbum()+"','"+p.getPostedByPhotoInfo().getOwnerId()+"','"+p.getPostedByPhotoInfo().getPhotoPath()+"','1','"+p.getPostedByPhotoInfo().getWidth()+"','"+p.getPostedByPhotoInfo().getHeight()+"')\" > <img src=\""+PropertyReader.getValue("photoAlbumPath",request)+p.getPostedByPhotoInfo().getPhotoPath().replace("/thumbbig_","/thumbsmall2_")+"\" /></a></td></tr></table>";
					str+="</div>";
				}else{
					str+="</div>";
				}
				
				
				str=str+"<div style=\"padding-left:60px;\">";
				str+="<div class=\"LikeSection\"><div class=\"commentPhotoStyleComment\" width=\"100%\"><a  class=\"linkStyleHref\" alt=\"aabcd\"  id=\"Like_"+p.getKey()+"\"><span class=\"linkStyleHref\" id=\"span_Like_"+p.getKey()+"\">" +likeStatus+"</span> </a>.<a href=\"#\"  class=\"linkStyleHref\" onclick=\"shareme()\" >Share</a> .<a class=\"linkStyleHref\" onclick=\"focusCommentBox('"+p.getKey()+"')\"> Comment</a> </div></div>";
				str+="<div class=\"borderForLike\"><div><table><tr><td class=\"\"><img src=\"img/icon_like.png\" height=\"20px\" /></td><td><span id=\"likeMessage_"+p.getKey()+"\" class=\"likeTextStyle\">"+likemessage+"</span></td></tr></table></div></div>";
				str+="<div id=\"comment_"+p.getKey()+"\" class=\"CurrentUserCommentSection\">";
				if(p.getComments()!=null){
					for(PostComments pc:p.getComments()){
						str+="<div id=\"deleteCom_"+pc.getKey()+"\" class=\"commentStyle\" onmouseover=\"showCommentDeleteButton('"+pc.getKey()+"')\" onmouseout=\"hideCommentDeleteButton('"+pc.getKey()+"')\" >";
						
						
						str+="<div class=\"commentPhotoStyle\" >";
						
						str+="<div class=\"photoSpaceStyle\"><img src=\""+pc.getCommentUserPhoto()+"\" width=\"50px\" "+
						"height=\"50px\">";
						str+="</div></div>";
						
						str+="<div class=\"commentPhotoStyle1\" width=\"800px\"><span width=\"100%\" class=\"commentedUserNameStyle\" > "+pc.getCommentUserName()+ "</span>: <span width=\"100%\" class=\"commentedDescriptionStyle\" > "+pc.getCommentDesc()+"</span> </div>";
						str+="<div><div id=\"deleteComment_"+pc.getKey()+"\" class=\"deleteCommentStyle\"><span class=\"commentDeleteTextStyle\" onclick=\"deleteComment('"+pc.getKey()+"')\" >x</span> </div></div>";
						str+="</div>";
					}
				}
				str=str+"</div>";
				str+="<div id=\"appendKey_"+p.getKey()+"\" class=\"commentStyle1111\">";
				str+="<div class=\"commentPhotoStyle\" width=\"10px\"><div class=\"photoSpaceStyle\"><img src=\""+emp.getImagePath()+"\" width=\"50px\" height=\"50px\"></div></div>";
				str+="<div class=\"commentPhotoStyle2\" width=\"100%\"> <textarea rows=\"1\" id=\"txtCommentForPost_"+p.getKey()+"\" cols=\"40\"></textarea><br><input type=\"button\" onclick=\"addPostComment('"+p.getKey()+"')\" value=\"Comment\"></div>";
				str+="</div>";
				
				str+="</div>";
				str+="</div>";
				str+="</div>";
				/* str+="<div class=\"commentNewSection\">";
				str+="<div class=\"commentNewSectionStyle\">abcd</div></div>";
				str+="<img str=\""+emp.getImagePath()+"\" width=\"20px\" height=\"20px\">";
				str+="<textarea rows=\"2\" id=\"txtCommentForPost\" cols=\"50\"></textarea>";
				str+="<input type=\"button\" onclick=\"addPostComment('"+p.getKey()+"')\" value=\"Comment\">";
			 	str+="</div>";		 */
				
				
			}
			str+="</div>";
		%>

 <style type="text/css">
.frameprofile
{
background-color:white;
position:absolute;
left:200px;
top:100px;
border: 1px ;
}
.grey{
	color: #999999;
}
 </style>
 <link href="../css/mystyle.css" rel="stylesheet" type="text/css" media="all">
 <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all"></link>
 
 <script type="text/javascript" src="js/jquery4.1.min.js"></script>
 <script type="text/javascript" src="js/SrapesUtil.js"></script>
 
   </head>

<body marginheight="0" marginwidth="0">

	<div align="center" id="header" >
	  <div style="background-color: black;height: 60px;width: 100%;">
	  		<div style="height: 10px;"></div>
	  		<div style="padding-left: 100px;vertical-align: baseline;" align="left">
	  		
	  			<span  class="NewHeaderStyle"><%=AppConstants.APP_NAME %></span>
	  		</div>
	  </div>
	  <div style="width: 80%" >
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  >
			<tr>
				<!-- <td width="30%" style="padding-bottom: 0px; border-bottom-style: groove;border-bottom-width: 1px;border-bottom-color: #A2A2A2;">
					<img src="images/logo.jpg"/>
				</td> -->
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #747474;">
					<table  cellpadding="0" cellspacing="0" align="right">
						<tr>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="home.jsp" ><span class="homeImage1">Home</span></a>
							</td>
							<% if(emp !=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) {%>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="listUsers.jsp">Profile</a>
							</td>
							<% }else if(emp !=null && (roleMap.get(Constant.PROFILE_VIEW)==null?false:true)){ %>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="listUsers.jsp" >View</a>
							</td>
							<%} %>
							<% if(emp!=null && (roleMap.get(Constant.PHOTO_VIEW)==null?false:true)) {%>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="photoall.jsp?userId=<%=emp.getId() %>" >Photo</a>
							</td>
							<%} %>
							
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'"  href="logout.jsp" >Log out</a>
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
		<!-- <div style="width: 100%">
			<hr class="line">
		</div> -->
		<div style="display: table; width: 100%;vertical-align: top;" align="center">
		
			<div style="display: table-cell; width: 15%%;vertical-align: top;" align="left">
			
				<div>
					<table>
						<tr>
							<td width="200px">
								<table>
									<tr>
										<td style="padding-top: 20px">
										<% //System.out.println(" dddd "+emp.getImagePath()); %>
											<% if(emp.getImagePath()==null || emp.getImagePath().trim().equalsIgnoreCase("")) {%>
												<img src="images/person.jpg" width="150" height="100" />
												<%}else{ %>
												<img src="<%= emp.getImagePath()%>"  width="160"/>
												<%} %>
											<%-- <img src="<%=(emp.getImagePath().trim()%>" width="200px" height="200px"> --%>
										</td>
									</tr>
									
								</table>
								<div style="padding-top: 5px"></div>
						<table width="100%">
							
							<% if(emp!=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) { %>
							<tr> 
								<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
									<a href="editProfile.jsp?editEmpId=<%=emp.getId()%>" class="leftLinkHeader">Profile</a>
								</td>
							</tr>
							
						<%} %>
							<%if(emp!=null && (roleMap.get(Constant.PHOTO_EDIT)==null?false:true)){ %>
							<tr> 
								<td class="headerGap1" style="background-color: #AEC9E2;" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
									<a href="photo.jsp?editEmpId=<%=emp.getId()%>" class="leftLinkHeader">Photo</a>
								</td>
							</tr>
							<%} %>
							
							<tr>
								<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
									<a href="colleagueList.jsp"  class="leftLinkHeader">Colleagues</a>
								</td>
							</tr>
						</table>
							</td>
						</tr>
					</table>
				</div>
				
			</div>
			<!--  end of left section -->
			<!--  start of middle section -->
			<div style="display: table-cell; width: 65%;" align="left">
				<!-- scrapping layer begins here... enjoy -->
			 <br>
				<div style="vertical-align: top;">
					<input type="hidden" id="txtPhotoPath" value="<%=emp.getImagePath()%>">	
					<input type="hidden" id="txtEmpId" value="<%=emp.getId()%>">	
					<input type="hidden" id="txtEmpName" value="<%=emp.getEmpName()%>">				
					<input  class="feedInsertbox" id="txtNewPost" size="70px" />
					<input type="button" onclick="addPost()" value="Post">
				</div>
				
				<div id="insertPost" style="width: 80%;" >
					<%=str %>
				</div>

			</div>
			<!--  end of middle section -->
			<!--  start of last section -->
			<div style="display: table-cell; width: 20%;" align="left">
				
			</div>
			<!--  end of last section -->
		</div>
	
	<!-- popup for images -->
	    <div id="popupContact" style="width: 50%;height: 50%;" align="center">
		<a id="popupContactClose">x</a>
		<!-- photoList.jsp?albumId=63&ownerId=62 -->
		<iframe src="#" style="margin: 0px;" width="100%" scrolling="no" height="100%" id="myIFrame" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 100%;"></div> 
          
 </body>
           
</html> 