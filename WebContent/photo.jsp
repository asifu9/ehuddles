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
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/PostCss.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/styles.css" rel="stylesheet" media="screen" />
<link href="css/style.css" rel="stylesheet" media="screen" />
<script type="text/javascript" src="js/jquery_.min.js"></script>
<script type="text/javascript">
	function photoUpload(aId, eId) {
		$.getJSON("Uploader", {
			'albumId' : aId,
			'eed' : eId
		}, function(datas) { // Do an AJAX call
			window.location.href = "fileUploader.jsp";
		});
	}
	function deleteAlbum(albumId, name, trId) {

		var r = confirm("Are you sure want to delete " + name + " album");
		if (r == true) {
			$
					.getJSON(
							"DeleteAlbum",
							{
								'AlbumId' : albumId
							},
							function(datas) { // Do an AJAX call
								$('#simplediv').css('display', 'block');
								//var trDiv = document.getElementById(albumId);
								$
										.each(
												datas,
												function(i, item) {
													if (item.result == "SUCCESS") {
														document
																.getElementById(trId).style.visibility = 'hidden';
														var trDiddv = document
																.getElementById("td_"
																		+ trId);
														trDiddv.style.height = '0';
														document
																.getElementById("tr_td_"
																		+ trId).trDiddv.style.visibility = 'hidden';
														document
																.getElementById("tr_td_"
																		+ trId).trDiddv.style.height = '0';

													}
												});

							});
		}

	}
</script>
<script type="text/javascript" language="javascript">
	function AjaxToSubmit() {
		var is_chrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
		var txtName = document.popForm.txtName.value;
		var userId = document.popForm.txtUserId.value;
		var descc = document.popForm.txtDesc.value;
		var privacy = "";

		if (is_chrome) {
			privacy = document.popForm.txtPrivacy.value;

		} else {
			var getSelectedIndex1 = document.popForm.txtPrivacy.selectedIndex;
			privacy = document.popForm.txtPrivacy[getSelectedIndex1].text;
			//alert(" privacy " + privacy);
		}

		/* var queryString=txtName+"~~~#"+privacy+"~~~#"+userId;
		
		$.post("createAlbum", {inputQueryString : queryString}, function(datas) { // Do an AJAX call
			var divId= document.getElementById("updateDiv");
			divId.innerHTML=datas;
		}); */
		$.getJSON("createAlbum", {
			'userId' : userId,
			'name' : txtName,
			'desc' : descc,
			'pv' : privacy
		}, function(datas) { // Do an AJAX call

			$(".QTPopup").css('display', 'none');
			window.location.reload();
			// var kk = 'but_'+textBx;
			// document.getElementById(kk).style.display='none';
		});
	}

	$(document).ready(function() {
		$(".QTPopup").css('display', 'none')
		$(".lnchPopop").click(function() {
			$(".QTPopup").animate({
				width : 'show'
			}, 'slow');
		})
		$(".closeBtn").click(function() {
			$(".QTPopup").css('display', 'none');
			window.location.reload();
		})
	})
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
	color: pink;
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	EmpInfo emp = null;
	if (Session_control.getSession(request) != null) {
		emp = Session_control.getSession(request);
	} else {
		response.sendRedirect(request.getContextPath()
				+ "/sessionover.jsp");
	}

	//System.out.println(" here ia m 4");
	List<PhotoAlbum> list = Util.getAllPhotoAlbulmByUser(emp.getId());
	//System.out.println(" here ia m 3");
	//System.out.println(" here ia m 2");
%>

<title><%=emp.getEmpName()%> - Photo Albums</title>
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="jjs/bootstrap.js"></script>
</head>
<body marginheight="0" marginwidth="0">
	<div class="navbar navbar-inverse navbar-static-top"
		style="margin: 0; background-color: #000000;">
		<div class="container" style="margin-top: -35px; margin-bottom: 0px;">
			<h3>
				<a href="#" class="navbar-brand"
					style="font-size: 30px; margin-top: 5px;"><span
					style="color: white;"><span class="rednum"
						style="font-family: calibri; font-weight: bold; font-size: 30px;">e&nbsp;
					</span>Huddle</span></a>
			</h3>
		</div>
		<div class="container" style="margin: 0;">

			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>

			</button>
			<div class="collapse navbar-collapse navHeaderCollapse">

				<ul class="nav navbar-nav navbar-right">
					<li><a href="feeds.jsp"><i class="fa fa-home fa-2x"></i>Home</a></li>
					<li><a href="profilefeed.jsp"><i class="fa fa-user fa-2x"></i>
							Profile</a></li>

					<li class="active"><a href="photoall.jsp"><i
							class="fa fa-picture-o fa-2x"></i>Photo</a></li>
					<li><a href="logout.jsp"><i class="fa fa-sign-out fa-2x"></i>Logout</a></li>
				</ul>



			</div>
		</div>
	</div>

	<div class="container" style="margin-top: 0px;">
		<div class="row" style="margin-top: 0px;">
			<div class="col-lg-2">
				<div onclick="coloseAllAlret()"
					style="display: table-cell; width: 15%; vertical-align: top;"
					align="left" onmouseover="hideUserInfo('sdf')">

					<div>
						<table>
							<tr>
								<td width="200px">
									<table width="100%">
										<tr>
											<td
												style="padding-top: 20px; border-bottom-color: gray; border-bottom-style: ridge; border-bottom-width: thin;">
												<%
													System.out.println(" dddd " + emp.getImagePath());
												%> <%
 	if (emp.getImagePath() == null
  											    			|| emp.getImagePath().trim().equalsIgnoreCase("")) {
 %> <img src="images/person.jpg" width="150" height="100" /> <%
 	} else {
 %> <img
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

									<ul class="nav nav-pills nav-stacked" style="margin-top: 5px;">
										<li><a href="editProfile.jsp"><i
												class="fa fa-user fa-lg"></i>Profile</a></li>
										<li><a href="feeds.jsp"><i
												class="fa fa-envelope-square fa-lg"></i> Feeds</a></li>
										<li class="active"><a href="photo.jsp"><i
												class="fa fa-picture-o fa-lg"></i> Photo</a></li>
										<li><a href="messages.jsp"><i
												class="fa fa-envelope-square fa-lg"></i> Message</a></li>
										<li><a href="colleagueList.jsp"><i
												class="fa fa-users fa-lg"></i> Colleagues</a></li>
									</ul>


								</td>
							</tr>
						</table>
					</div>

				</div>


			</div>


			<div class="col-lg-10" style="margin-top: 0px; margin-left: 0px;">
				<div class="panel panel-default" style="margin-top: 0px;">
					<div class="panel-body" style="margin-top: 0px; margin-left: -0px;">
						<table width="100%" border="0" align="left">
							<tr>
								<td valign="top"><span class="photoHeadingstyle"><%=emp.getEmpName()%>'s
										Photo Albums </span></td>
								<td align="right" valign="top" style="padding-top: 5px;"><span>
										<a href="#" class="lnchPopop"
										style="font-size: 16px; font-weight: bold; border: 0px solid #dadada; background: #fff; line-height: 5px; padding: 0px; color: gray; text-decoration: none;">
											Create New Album </a>
								</span></td>
							</tr>
						</table>


						<table width="100%" align="left" border="0">
							<tr>
								<td
									style="padding-bottom: 50px; padding-top: 20px; padding-left: 10px; border-bottom-color: #EEEEEE; border-bottom-style: inset; border-bottom-width: thin;">


								</td>
							</tr>

							<%
							for (PhotoAlbum p : list) {
						%>
							<tr id="tr_td_tr_id_<%=p.getIdPhotoAlbum()%>" align="left">
								<td id="td_tr_id_<%=p.getIdPhotoAlbum()%>"
									class="photoAlbumSaperator" style="padding-left: 0px;"
									align="left">

									<table id="tr_id_<%=p.getIdPhotoAlbum()%>" cellpadding="0"
										cellspacing="0" width="50%" border="0" align="left">
										<tr>
											<td class="photoAlbumSaperatorBlck2" width="200px"
												style="padding-left: 0px;" align="center"><a
												onclick="photoUpload('<%=p.getIdPhotoAlbum()%>','<%=emp.getId()%>')"
												class="editAlbumStyle"> <%
 	if (p.getCoverScreenPath() == null
 				|| p.getCoverScreenPath().trim().equals("")) {
 %> <img src="images/camera.jpg" width="200px" height="120px"> <%
													} else {
												%> <%
 	if (p.getWidth() < 200 || p.getHeight() < 200) {
 %> <img width="200px" height="200px"
													src="<%=PropertyReader.getValue("photoAlbumPath",
								request)
								+ ""
								+ p.getCoverScreenPath().replaceFirst("a_",
										"f_")%>">
													<%
													} else {
												%> <img
													src="<%=PropertyReader.getValue("photoAlbumPath",
								request)
								+ ""
								+ p.getCoverScreenPath().replaceFirst("a_",
										"f_")%>">
													<%
													}
												%> <%
 	}
 %>
											</a> <!-- 	</a> --></td>
											<td valign="top" class="" style="padding-left: 5px;"
												align="left">
												<table>
													<tr>
														<td>
															<%-- <a class="photoAlbumnNameLink" href="photoList.jsp?albumId=<%=p.getIdPhotoAlbum()%>&ownerId=<%=emp.getId()%>"> --%>
															<%=p.getName()%> <!-- </a> -->
														</td>
													</tr>
													<tr>
														<td><span class="albumnDescStyle"><%=p.getDesc()%>
														</span></td>
													</tr>
													<tr>
														<td><a href="#"
															onclick="photoUpload('<%=p.getIdPhotoAlbum()%>','<%=emp.getId()%>')"
															class="editAlbumStyle">Edit Album</a></td>
													</tr>
													<tr>
														<td><a href="#"
															onclick="deleteAlbum('<%=p.getIdPhotoAlbum()%>','<%=p.getName()%>','tr_id_<%=p.getIdPhotoAlbum()%>')"
															class="editAlbumStyle">Delete Album</a></td>
													</tr>
													<tr>
														<td><span class="photoAlbumDateStyle">Created
																on <%=p.getUpdatedOn()%></span></td>
													</tr>
												</table>

											</td>
										</tr>

									</table>


								</td>
							</tr>
							<%
							}
						%>

						</table>
						</span>
						</td>
						</tr>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>


	<!--  popup code -->
	<form name="popForm" action="">
		<input type="hidden" value="<%=emp.getId()%>" name="txtUserId">
		<div class="QTPopup">
			<div class="popupGrayBg"></div>
			<div class="QTPopupCntnr">
				<div class="gpBdrLeftTop"></div>
				<div class="gpBdrRightTop"></div>
				<div class="gpBdrTop"></div>
				<div class="gpBdrLeft">
					<div class="gpBdrRight">
						<div class="caption">Create new Album</div>
						<a href="#" class="closeBtn" title="Close"></a>

						<div class="content">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>Album name</td>
									<td width="260">
										<div class="titlebarLeftc">
											<div class="titlebarRightc">
												<div class="titlebar" style="width: 250px;">
													<input type="text" value="" name="txtName"
														style="border: 0px; background: none; margin-top: 5px; width: 245px;" />
												</div>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td>Description</td>
									<td width="260">
										<div class="titlebarLeftc">
											<div class="titlebarRightc">
												<div class="titlebar" style="width: 250px;">
													<input type="text" value="" name="txtDesc"
														style="border: 0px; background: none; margin-top: 5px; width: 245px;" />
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
												<div class="titlebar" style="width: 250px;">

													<select name="txtPrivacy"
														style="border: 0px; background: none; margin-top: 5px; width: 245px;">
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
									<td width="200px"><input type="button" value="Create"
										onclick="AjaxToSubmit()"
										style="margin-top: 5px; width: 100px;">
										<div id="updateDiv"></div></td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td></td>
									<td width="200px"></td>
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
	</form>
	<!--  end of popup code -->
</body>
</html>