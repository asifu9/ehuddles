<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.dao.PhotoAlbumDAO"%>
<%@page import="com.pro.emp.domain.PhotoAlbum"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<%
	EmpInfo emp = null;
	if (Session_control.getSession(request) != null) {
		emp = Session_control.getSession(request);
		System.out.println(" emp " + emp.getId());
	} else {
		response.sendRedirect(request.getContextPath()
				+ "/sessionover.jsp");
	}

	//System.out.println(" here ia m 2");
%>
<script type="text/javascript" src="js/Jquery8ui.min.js"></script>
<script type="text/javascript" src="js/Jquery8.js"></script>
<script type="text/javascript" src="js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/messages.css" rel="stylesheet" type="text/css"
	media="all">

<!-- <script type="text/javascript" src="js/jquery_.min.js"></script> -->



<script type="text/javascript">
		var usId= '<%=emp.getId()%>';
		var str='';
		var i=0;
		alert(" usId " + usId);
		$.getJSON("GetMessages", {'userId' :usId}, function(datas) { // Do an AJAX call
			//var trDiv = document.getElementById(albumId);
			$.each(datas, function(i,item){
				var m=null;
				if(item.mailIdInfo!=null)
				m= item.mailIdInfo.message;
				
			if(m!=null && m.length>20)
					m=m.substring(0,20);
					var st='';
					var co=item.newMessageCount;
					var nameAndDes=item.fromIdInfo.empName;
					if(co!='0'){
						st='style="background-color:#61B7DC;" ';
						nameAndDes=	item.fromIdInfo.empName+'('+item.newMessageCount+')';
					}
					
							
					//if(item.status!='0')
						
					str='<div class="mailSection" width="100%" '+st+' onclick="displayMessage(\''+item.fromIdInfo.id+'\')" id="'+item.fromIdInfo.id+'_1">';
					str+='<table style="width: 100%;" border="0"><tr><td width="40px"><img src="'+item.fromIdInfo.imagePath+'" width="30px" height="30px">';
					str+='</td><td><table><tr><td>'+nameAndDes+'</td></tr><tr><td>' +m+ '</td></tr></table><div><span class="userDateTimeMailStyle">'+item.mailTimeStr2+'</span></div></td></tr></table>';
					str+='</div>';
					if(document.getElementById(item.fromIdInfo.id+'_1')==null)
						$("#addMails").append(str);
			
			});
		
			
		
			$(".mailSection").click(function() {
				 
				 $("#addMails > div[id]").each(function(){
					    var context = $(this).attr('id');
					    var x=$(this).css("backgroundColor");
					    var c='#'+context;
					    if(x=='rgb(97, 183, 220)'){}else{
					    $(c).css({'background-color':'#EEF7FB'});
					    }
					    var $bac=$(this).css("background-color");
					    
					});
				
				var keyFull = $(this).attr('id');
				  var k='#'+keyFull;
				  udpateStatus(keyFull,usId);
				  $(k).css({'background-color':'#A2D5EA'});
			});

			// for send item start
			$.getJSON("GetSentMessages", {'userId' :usId}, function(datas) { // Do an AJAX call
			//var trDiv = document.getElementById(albumId);
			$.each(datas, function(i,item){
				var m=null;
				if(item.mailIdInfo!=null)
				m= item.mailIdInfo.message;
			
			if(m!=null && m.length>20)
					m=m.substring(0,20);
					var st='';
					var co=item.newMessageCount;
					var nameAndDes=item.toIdInfo.empName;
					if(co!='0'){
						st='style="background-color:#61B7DC;" ';
						nameAndDes=	item.toIdInfo.empName+'('+item.newMessageCount+')';
					}
					
							
					//if(item.status!='0')
						
					str='<div class="mailSection" width="250px" '+st+' onclick="displayMessageSent(\''+item.toIdInfo.id+'\')" id="'+item.toIdInfo.id+'_1">';
					str+='<table style="width: 100%;" border="0" ><tr><td width="40px" align="left"><img src="'+item.toIdInfo.imagePath+'" width="30px" height="30px">';
					str+='<td class="adjustTable" style="vertical-align: top;"><table width="100%"><tr><td>'+nameAndDes+'</td></tr><tr><td >' +m+ '</td></tr><table style="width: 100%;" border="0" ><tr><td width="40px" align="left"><span class="userDateTimeMailStyle">'+item.mailTimeStr2+'</span></td></tr></table></td></tr> </td></tr></table></table>';
					str+='</div>';
					//str+='<span class="userDateTimeMailStyle">'+item.mailTimeStr2+'</span>';
					str+='</div>';
					if(document.getElementById(item.fromIdInfo.id+'_2')==null)
						$("#noReplay").append(str);
			
			});
			/* $('#addMails').slimScroll({
			     height: '350px',
			     alwaysVisible: false,
			     size: '8px',
			     color: '#555555'
			     }); */
			
		/* 
			$(".mailSection").click(function() {
				 
				 $("#addMails > div[id]").each(function(){
					    var context = $(this).attr('id');
					    var x=$(this).css("backgroundColor");
					    var c='#'+context;
					    if(x=='rgb(97, 183, 220)'){}else{
					    $(c).css({'background-color':'#EEF7FB'});
					    }
					    var $bac=$(this).css("background-color");
					    
					});
				
				var keyFull = $(this).attr('id');
				  var k='#'+keyFull;
				  udpateStatus(keyFull,usId);
				  $(k).css({'background-color':'#A2D5EA'}); */
			});
			
			// for sent item end
			
			$(".mailSectionNew").click(function() {
				 
				 $("#addMails > div[id]").each(function(){
					    var context = $(this).attr('id');
					    var c='#'+context;
					    $(c).css({'background-color':'#EEF7FB'});
					  	
					});
				 
				var keyFull = $(this).attr('id');
				
				  var k='#'+keyFull;
				  $(k).css({'background-color':'#A2D5EA'});
			});
			$('#noReplay').slimScroll({
			     height: '350px',
			     alwaysVisible: false,
			     size: '8px',
			     color: '#555555'
			     });
		});
		
		function hexc(colorval) {
		    var parts = colorval.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
		    delete(parts[0]);
		    for (var i = 1; i <= 3; ++i) {
		        parts[i] = parseInt(parts[i]).toString(16);
		        if (parts[i].length == 1) parts[i] = '0' + parts[i];
		    }
		    color = '#' + parts.join('');
		}
function udpateStatus(context,usId){
	context = context.replace('_1','');
	$.getJSON("UpdateMailStatus", {'fromId' :context,'toId':usId}, function(ddatas) {
		
	});
}		
		
function replyMessageMe(toUserId){
	var usId= '<%=emp.getId()%>';

//	var mess=document.getElementById("mes").value;
	var m=document.getElementById("replyMessage").value;
	
	$.getJSON("CreateMessage", {'idlist' :toUserId,'fromId':usId,'message':m}, function(datass) {
		
		$.each(datass, function(i,datas){
		var p='images/person.jpg';
		if(datas.fromIdInfo.imagePath!=null || datas.fromIdInfo.imagePath!='')
			p=datas.fromIdInfo.imagePath;
		str='<div class="eachMailStyle" >';
		str+='<table width="100%"><tr><td style="vertical-align: top;" width="30px"><img src="'+p+'" width="30px" height="30px" ></td>';
		str+='<td class="adjustTable" style="vertical-align: top;"><table width="100%"><tr><td align="left" ><span class="userNameStleMail" >'+datas.fromIdInfo.empName+'</span><span class="justifyMessage">'+m+'</span></td></tr></table></td></tr></table>';
		str+='<span class="userDateTimeMailStyle">1 secon ago</span>';
		str+='</div>';
		$("#replayToTHisThrewad").prepend(str);
		});
	});
	document.getElementById("replyMessage").value="";
}
function displayMessage(userId){
	var usId= '<%=emp.getId()%>';
	$("#addUserMails").html("");
	$("#newMailSection").css('display','none');
	var str1='';
	var str='';
	str1+='<div class="replySectionOk" > <textarea rows="2" cols="48" id="replyMessage" name="replyMessage"></textarea><br><div align="right"><input class="buttonStyleInSend" type="button" onclick="replyMessageMe(\''+userId+'\')" value="Reply"></div></div><div id="replayToTHisThrewad"></div>';
	$("#addUserMails").append(str1);
	$.getJSON("GetMessagesByMail", {'userId' :usId,'mailedUserId':userId,'flow':'inbox'}, function(datas) { // Do an AJAX call
		$.each(datas, function(i,item){
			var st='';
			if(item.status=='0' && userId== item.fromIdInfo.id)
				st='style="background-color:#61B7DC;" ';
			
		str='<div class="eachMailStyle" '+st+' id="'+item.key+'">';
		str+='<table width="100%"><tr><td style="vertical-align: top;" width="30px"><img src="'+item.fromIdInfo.imagePath+'" width="30px" height="30px" ></td>';
		str+='<td class="adjustTable" style="vertical-align: top;"><table width="100%"><tr><td align="left" ><span class="userNameStleMail" >'+item.fromIdInfo.empName+'</span><span class="justifyMessage">'+item.mailIdInfo.message+'</span></td></tr></table></td></tr></table>';
		str+='<span class="userDateTimeMailStyle">'+item.mailTimeStr+'</span>';
		str+='</div>';
		$("#addUserMails").append(str);
	});
	});
	 $('#addUserMails').slimScroll({
	     height: '349px',
	     alwaysVisible: false,
	     size: '8px',
	     color: '#555555'
	     });
}

function displayMessageSent(userId){
	var usId= '<%=emp.getId()%>';
		$("#addUserMails").html("");
		$("#newMailSection").css('display', 'none');
		var str1 = '';
		var str = '';
		str1 += '<div class="replySectionOk" > <textarea rows="2" cols="48" id="replyMessage" name="replyMessage"></textarea><br><div align="right"><input class="buttonStyleInSend" type="button" onclick="replyMessageMe(\''
				+ userId
				+ '\')" value="Reply"></div></div><div id="replayToTHisThrewad"></div>';
		$("#addUserMails").append(str1);
		$
				.getJSON(
						"GetMessagesByMail",
						{
							'userId' : usId,
							'mailedUserId' : userId,
							'flow' : 'sent'
						},
						function(datas) { // Do an AJAX call
							$
									.each(
											datas,
											function(i, item) {
												var st = '';
												if (item.status == '0'
														&& userId == item.fromIdInfo.id)
													st = 'style="background-color:#61B7DC;" ';

												str = '<div class="eachMailStyle" '+st+' id="'+item.key+'">';
												str += '<table width="100%"><tr><td style="vertical-align: top;" width="30px"><img src="'+item.fromIdInfo.imagePath+'" width="30px" height="30px" ></td>';
												str += '<td class="adjustTable" style="vertical-align: top;"><table width="100%"><tr><td align="left" ><span class="userNameStleMail" >'
														+ item.fromIdInfo.empName
														+ '</span><span class="justifyMessage">'
														+ item.mailIdInfo.message
														+ '</span></td></tr></table></td></tr></table>';
												str += '<span class="userDateTimeMailStyle">'
														+ item.mailTimeStr
														+ '</span>';
												str += '</div>';
												$("#addUserMails").append(str);
											});
						});
		$('#addUserMails').slimScroll({
			height : '349px',
			alwaysVisible : false,
			size : '8px',
			color : '#555555'
		});
	}
</script>

<script type="text/javascript">
	function sendMessage() {
		var list = '';
		$(".selectedContactDiv > div[id]").each(function() {
			var context = $(this);
			list += this.id + '~~~';

		});
		var mess = document.getElementById("mes").value;
		var fromId = document.getElementById("currentId").value;
		if (list == '' || mess == '') {
		} else {
			$
					.getJSON(
							"CreateMessage",
							{
								'idlist' : list,
								'fromId' : fromId,
								'message' : mess
							},
							function(datas) {
								document.getElementById('myText').innerHTML = 'Message Sent Successfully';

							});
			document.getElementById("mes").value = "";
		}
	}
</script>
<style>

/* ---------------------- */
.headerLink1 {
	text-decoration: none;
	font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
	font-stretch: expanded;
	font-size: 16px;
	font-style: normal;
	display: block;
	color: #696969;
	font-weight: bold;
}

.headerLinkMO1 {
	text-decoration: none;
	font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
	font-stretch: expanded;
	color: white;
	font-size: 16px;
	font-style: normal;
	font-weight: bold;
	display: block;
}

.headerLinkMOBlock1 {
	background-color: #AEC9E2;
	padding-left: 5px;
	padding-bottom: 5px;
	padding-right: 5px;
	padding-top: 5px;
	width: 70px;
	color: white;
}

.headerLinkBlock1 {
	background-color: white;
	padding-left: 5px;
	padding-bottom: 5px;
	padding-right: 5px;
	padding-top: 5px;
	width: 70px;
	color: balck;
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
}

.infoHeader {
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

.infoHeaderh {
	padding-left: 2px;
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

.infoMiddleh {
	border-bottom-color: #EEEEEE;
	border-bottom-style: inset;
	border-bottom-width: 1px;
	background-color: #EEEEEE;
	padding-top: 5px;
	padding-bottom: 5px;
}

.infoMiddle {
	border-bottom-color: #EEEEEE;
	border-bottom-style: inset;
	border-bottom-width: 1px;
	padding-top: 5px;
	padding-bottom: 5px;
}

.infoFooter {
	border-bottom-color: #EEEEEE;
	border-bottom-style: inset;
	border-bottom-width: 1px;
	padding-top: 5px;
	padding-bottom: 5px;
}
</style>

<!-- <script type="text/javascript" src="js/search.js"></script> -->
<script type="text/javascript">
	// Safely inject CSS3 and give the search results a shadow

	var cssObj = {
		'box-shadow' : '#888 5px 10px 10px', // Added when CSS3 is standard

		'-webkit-box-shadow' : '#888 5px 10px 10px', // Safari

		'-moz-box-shadow' : '#888 5px 10px 10px'
	}; // Firefox 3.5+

	$("#suggestions").css(cssObj);

	// Fade out the suggestions box when not active
	$("input").blur(function() {
		$('#suggestions').fadeOut();

	});
	// Fade out the suggestions box when not active
	$("select").blur(function() {
		$('#suggestions').fadeOut();
	});
	//$("p").height()

	function lookup(inputString, searchType) {
		if (inputString.length == 0) {
			$('#suggestions').fadeOut(); // Hide the suggestions box
		} else {
			$("#suggestions").height("auto");
			$.post("SearchUsers", {
				queryString : "" + inputString + "~" + searchType + ""
			}, function(data) { // Do an AJAX call
				$('#suggestions').fadeIn(); // Show the suggestions box

				$('#suggestions').html(data); // Fill the suggestions box

				$('#searchresultsMessage').slimScroll({
					height : '225px',
					alwaysVisible : false,
					size : '8px',
					color : '#555555'
				});
			});
		}
	}

	function removeMe(id) {
		$(id).remove();
	}
	function addMetoList(id, photo, name) {
		var str = '';
		str = '<div class="displayContacts" id="'+id+'"><table width="100%"><tr><td width="30px"><img src="'+photo+'" width="30px" height="30px"></td><td>'
				+ name
				+ '</td><td aligh="right" width="10px"><span class="closeOption" onclick="removeMe(\'#'
				+ id + '\')">x</span></td></tr></table> </div>';
		if (document.getElementById(id) == null)
			$("#addNamesDynamically").append(str);
		$("#suggestions").html("");
	}

	function showNewMessageSection() {
		$("#addUserMails").html("");
		$("#newMailSection").css('display', 'block');

	}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title><%=emp.getEmpName()%>'s - Message Box</title>

</head>
<body marginheight="0" marginwidth="0">
	<div align="center" id="header">
		<div style="background-color: #00688B; height: 60px; width: 100%;">
			<div style="height: 20px;"></div>
			<div style="padding-left: 100px; vertical-align: baseline;"
				align="left">

				<span class="NewHeaderStyle"><%=AppConstants.APP_NAME%></span>
			</div>
		</div>
		<div style="width: 100%;">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td
						style="padding-bottom: 0px; border-bottom-style: groove; border-bottom-width: 1px; border-bottom-color: #A2A2A2; background-color: #33A1C9;">
						<span class="headerLink" style="padding-left: 5px;"><%=emp.getEmpName()%></span>
					</td>
					<td valign="bottom" width="70%" align="right"
						style="padding-bottom: 0px; border-bottom-style: groove; border-bottom-width: 1px; border-bottom-color: #A2A2A2; background-color: #33A1C9">
						<table cellpadding="0" cellspacing="0" align="right">
							<tr>
								<td class="headerGap"
									onmouseover="this.className='headerLinkMOBlock'"
									onmouseout="this.className='headerLinkBlock'" align="center">
									<a class="headerLink" onmouseout="this.className='headerLink'"
									onmouseover="this.className='headerLinkMO'" href="feeds.jsp">Home</a>
								</td>
								<td class="headerGap"
									onmouseover="this.className='headerLinkMOBlock'"
									onmouseout="this.className='headerLinkBlock'" align="center">
									<a class="headerLink" onmouseout="this.className='headerLink'"
									onmouseover="this.className='headerLinkMO'"
									href="profilefeed.jsp">Profile</a>
								</td>
								<td class="headerGap"
									onmouseover="this.className='headerLinkMOBlock'"
									onmouseout="this.className='headerLinkBlock'" align="center">
									<a class="headerLink" onmouseout="this.className='headerLink'"
									onmouseover="this.className='headerLinkMO'" href="photoall.jsp">Photo</a>
								</td>

								<td class="headerGap"
									onmouseover="this.className='headerLinkMOBlock'"
									onmouseout="this.className='headerLinkBlock'" align="center">
									<a class="headerLink" onmouseout="this.className='headerLink'"
									onmouseover="this.className='headerLinkMO'" href="logout.jsp">Log
										out</a>
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

			<div align="left" style="padding-left: 5px;">
				<div style="display: table-cell; vertical-align: top; width: 15%; border-right-color: balck; border-right-style: inset; border-right-width: 1px; padding-left: 0px;">
					<!--  left menu -->
					<div>
						<table  cellpadding="0" cellspacing="0">
							<tr>
								<td width="200px">
									<table width="100%"  cellpadding="0" cellspacing="0">
										<tr>
											<td
												style="padding-top: 20px; border-bottom-color: gray;padding-left: 5px; border-bottom-style: ridge; border-bottom-width: thin;">
												 <%
													 	if (emp.getImagePath() == null
													 			|| emp.getImagePath().trim().equalsIgnoreCase("")) {
													 %>
																									<img src="images/person.jpg" width="150" height="100" /> <%
													 	} else {
													 %>
												<img
												src="<%=PropertyReader.getValue("photoAlbumPath", request)%><%=emp.getImage().getPhotoPath()
																		.replaceFirst("a_", "g_")%>"
																								width="160" /> <%
												 	}
													 %> 
												<div align="center">
													<span class="empNameDisplayStyle"><%=emp.getEmpName()%></span><br>
													<span class="empFollowersDisplayStyle">Followers <span
														id="followCount"></span></span>
												</div>
											</td>
										</tr>

									</table>
									<div style="padding-top: 5px"></div>
									<table width="100%">


										<tr>
											<td class="headerGap1"
												onmouseover="this.className='headerLinkMOBlock1'"
												onmouseout="this.className='headerLinkBlock1'"><a
												href="editProfile.jsp" class="leftLinkHeader">Profile</a></td>
										</tr>
										<tr>
											<td class="headerGap1" 
												onmouseover="this.className='headerLinkMOBlock1' "
												onmouseout="this.className='headerLinkBlock1'"><a
												href="feeds.jsp" class="leftLinkHeader"> Feeds</a></td>
										</tr>

										<tr>
											<td class="headerGap1"
												onmouseover="this.className='headerLinkMOBlock1'"
												onmouseout="this.className='headerLinkBlock1'"><a
												href="photo.jsp" class="leftLinkHeader">Photo</a></td>
										</tr>
										<tr>
											<td class="headerGap1" style="background-color: #AEC9E2;"
												onmouseover="this.className='headerLinkMOBlock1'"
												onmouseout="this.className='headerLinkBlock1'"><a
												href="messages.jsp" class="leftLinkHeader"><span><img class="iconStyleLeftHeadings" src="icon/message.jpg"></span>Message &nbsp<span
													id="messageCount"></span></a></td>
										</tr>


										<tr>
											<td class="headerGap1"
												onmouseover="this.className='headerLinkMOBlock1'"
												onmouseout="this.className='headerLinkBlock1'"><a
												href="colleagueList.jsp" class="leftLinkHeader">Colleagues</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>


				</div>
				<div
					style="display: table-cell; vertical-align: top; width: 20%; border-right-color: balck; border-right-style: inset; border-right-width: 1px; padding-right: 4px;">
					<div class="Inboxsection" style="width: 100%;">
						<span class="inboxStyle" style="width: 100%;">Messages</span>
						<div id="addMails" style="height: 400px; width: 250px;"></div>
					</div>

				</div>
				<input type="hidden" id="currentId" value="<%=emp.getId()%>">
				<div style="display: table-cell; width: 40%;" align="left">
					<div class="messagesection" align="right">
						<input class="newMailStyle" type="button"
							onclick="showNewMessageSection()" value="New Message">
					</div>
					<div id="newMailSection" class="newMessageSectionStyle"
						style="display: none;">
						<span class="mesageDetailsHeade">Type a contact name and
							click to select</span>
						<form name="myForm" action="" method="post">
							<div>
								<input type="text" class="searchtextBox" size="48" value=""
									id="inputString"
									onclick="lookup(this.value,myForm.searchType.value);"
									onkeyup="lookup(this.value,myForm.searchType.value);" /> <select
									style="display: none;" class="searchComboBox" name="searchType"
									id="searchType" onchange="lookup(inputString.value,this.value)">
									<option class="searchcomboBoxItems" id="view"
										selected="selected">View</option>

								</select>
								<div id="suggestions" class="displayOptions"
									style="width: 300px;"></div>
							</div>
							<div id="addNamesDynamically" class="selectedContactDiv"></div>
							<div style="padding-top: 15px;">
								<span class="mesageDetailsHeade">Write your message here</span><br>
								<textarea rows="2" cols="48" id="mes" name="mes"></textarea>
								<br> <span id="myText"></span>

								<div align="right">
									<input type="button" class="buttonStyleInSend" value="Send"
										onclick="sendMessage()">
								</div>
							</div>





						</form>
					</div>

					<div class="" id="addUserMails"></div>
					<div class="borderForMails" id="test" style="height: 2px;"></div>

				</div>

				<div class="Inboxsection" style="display: table-cell; width: 20%;"
					align="left">
					<span class="inboxStyle" style="width: 100%;">Sent items</span>
					<div id="noReplay" style="height: 500px; width: 250px;"></div>
				</div>


			</div>
		</div>
	</div>

</body>
</html>